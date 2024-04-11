-- Members table
CREATE TABLE Members (
    email VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

-- Health Metrics table
CREATE TABLE Health_metrics (
	goal_id SERIAL,
    mem_email VARCHAR(255) REFERENCES Members(email) ON UPDATE CASCADE,
    blood_pressure NUMERIC,
    heart_rate NUMERIC,
    blood_sugar NUMERIC,
    weight NUMERIC,
	PRIMARY KEY (mem_email, goal_id) -- composite key for the weak entity
);

-- Fitness Goals table
CREATE TABLE Fitness_goals (
    goal_id SERIAL PRIMARY KEY,
    mem_email VARCHAR(255) REFERENCES Members(email) ON UPDATE CASCADE,
    goal TEXT,
    CONSTRAINT unique_goals UNIQUE (mem_email, goal) -- Unique constraint to ensure no duplicate goals for the same member
);

-- Routine table
CREATE TABLE Routine (
    routine_id SERIAL PRIMARY KEY,
    mem_email VARCHAR(255) REFERENCES Members(email) ON UPDATE CASCADE,
    routine TEXT,
    CONSTRAINT unique_routines UNIQUE (mem_email, routine) -- Unique constraint to ensure no duplicate goals for the same member
);

-- Achievement table
CREATE TABLE Achievement (
    achievement_id SERIAL PRIMARY KEY,
    mem_email VARCHAR(255) REFERENCES Members(email) ON UPDATE CASCADE,
    achievement TEXT,
    CONSTRAINT unique_achievements UNIQUE (mem_email, achievement) -- Unique constraint to ensure no duplicate goals for the same member
);

-- Billings table
CREATE TABLE Billings (
    billing_id SERIAL PRIMARY KEY,
    mem_email VARCHAR(255) REFERENCES Members(email) ON UPDATE CASCADE,
    amount NUMERIC,
    date DATE
);

-- Trainer table
CREATE TABLE Trainers (
    email VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

-- Rooms table
CREATE TABLE Rooms (
    room_id NUMERIC PRIMARY KEY,
    building VARCHAR(100) NOT NULL
);

-- Group Bookings table
CREATE TABLE Group_bookings (
    mem_email VARCHAR(255) REFERENCES Members(email) ON UPDATE CASCADE,
    class_id INT,
    PRIMARY KEY (mem_email, class_id)
);

-- Group Classes table
CREATE TABLE Group_classes (
    class_id SERIAL PRIMARY KEY,
    trainer_email VARCHAR(255) REFERENCES Trainers(email),
    class_date DATE,
    class_time TIME,
    room_id INT REFERENCES Rooms(room_id),
	--constraint: cannot book a class in same room at same date&time
	CONSTRAINT unique_room_group UNIQUE (class_date, class_time, room_id),
	--constraint: cannot book a class w/ same trainer at same date&time
	CONSTRAINT unique_trainer_group UNIQUE (trainer_email, class_date, class_time)
);

-- Personal Bookings table
CREATE TABLE Personal_bookings (
    session_id INT PRIMARY KEY,
    mem_email VARCHAR(255) REFERENCES Members(email) ON UPDATE CASCADE
);

-- Personal Sessions table
CREATE TABLE Personal_sessions (
    session_id SERIAL PRIMARY KEY,
    trainer_email VARCHAR(255) REFERENCES Trainers(email),
    session_date DATE,
    session_time TIME,
    room_id INT REFERENCES Rooms(room_id),
	--constraint: cannot book a session in same room at same date&time
	CONSTRAINT unique_room_personal UNIQUE (session_date, session_time, room_id),
	--constraint: cannot book a session w/ same trainer at same date&time
	CONSTRAINT unique_trainer_personal UNIQUE (trainer_email, session_date, session_time)

);

-- Admin Staff table
CREATE TABLE Admin_staff (
    email VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

-- Equipment table
CREATE TABLE Equipment (
    equip_id SERIAL PRIMARY KEY,
    equip_name VARCHAR(100) NOT NULL,
    maintenance_date DATE
);