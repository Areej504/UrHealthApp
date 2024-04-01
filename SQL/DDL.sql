-- Members table
CREATE TABLE Members (
    email VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

-- Health Metrics table
CREATE TABLE Health_metrics (
    mem_email VARCHAR(255) PRIMARY KEY,
    blood_pressure NUMERIC,
    heart_rate NUMERIC,
    blood_sugar NUMERIC,
    weight NUMERIC
);

-- Fitness Goals table
CREATE TABLE Fitness_goals (
    mem_email VARCHAR(255) PRIMARY KEY,
    weight NUMERIC,
    running_time NUMERIC
);

-- Routine table
CREATE TABLE Routine (
    mem_email VARCHAR(255) PRIMARY KEY,
    routine TEXT
);

-- Achievement table
CREATE TABLE Achievement (
    mem_email VARCHAR(255) PRIMARY KEY,
    achievement TEXT
);

-- Billings table
CREATE TABLE Billings (
    billing_id SERIAL PRIMARY KEY,
    mem_id VARCHAR(255) REFERENCES Members(email),
    amount NUMERIC,
    date DATE
);

-- Trainer table
CREATE TABLE Trainer (
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
    mem_email VARCHAR(255) REFERENCES Members(email),
    class_id INT,
    PRIMARY KEY (mem_email, class_id)
);

-- Group Classes table
CREATE TABLE Group_classes (
    class_id SERIAL PRIMARY KEY,
    trainer_email VARCHAR(255) REFERENCES Trainer(email),
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
    mem_email VARCHAR(255) REFERENCES Members(email)
);

-- Personal Sessions table
CREATE TABLE Personal_sessions (
    session_id SERIAL PRIMARY KEY,
    trainer_email VARCHAR(255) REFERENCES Trainer(email),
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