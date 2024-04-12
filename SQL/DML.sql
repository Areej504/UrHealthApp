-- Members table
INSERT INTO Members (email, first_name, last_name)
VALUES
    ('bugs.bunny@example.com', 'Bugs', 'Bunny'),
    ('daffy.duck@example.com', 'Daffy', 'Duck'),
    ('porky.pig@example.com', 'Porky', 'Pig');
	
-- Health Metrics table
INSERT INTO Health_metrics (mem_email, blood_pressure, heart_rate, blood_sugar, weight)
VALUES
    ('bugs.bunny@example.com', 120, 70, 90, 70.5),
    ('daffy.duck@example.com', 130, 75, 95, 72.3),
    ('porky.pig@example.com', 125, 72, 92, 68.9);

-- Fitness Goals table
INSERT INTO Fitness_goals (mem_email, goal)
VALUES
    ('bugs.bunny@example.com', 'Deadlift 45 kg'),
    ('daffy.duck@example.com', 'Stretch for 15 mins daily'),
    ('porky.pig@example.com', 'Run for 30 minutes');

-- Routine table
INSERT INTO Routine (mem_email, routine)
VALUES
    ('bugs.bunny@example.com', 'Cardio'),
	('bugs.bunny@example.com', 'Yoga'),
    ('daffy.duck@example.com', 'Weight training'),
	('daffy.duck@example.com', 'Zumba Dance'),
    ('porky.pig@example.com', 'Yoga');
	('porky.pig@example.com', 'Deadlifting');

-- Achievement table
INSERT INTO Achievement (mem_email, achievement)
VALUES
    ('bugs.bunny@example.com', 'Run 15 minutes'),
    ('daffy.duck@example.com', 'Drop to 155 kg'),
    ('porky.pig@example.com', 'Run 30 minutes');

-- Billings table
INSERT INTO Billings (mem_email, amount, date)
VALUES
    ('bugs.bunny@example.com', 15.00, '2024-03-01'),
    ('daffy.duck@example.com', 15.00, '2024-03-05'),
    ('porky.pig@example.com', 15.00, '2024-03-10');

-- Trainers table
INSERT INTO Trainers (email, first_name, last_name)
VALUES
    ('elmer.fudd@example.com', 'Elmer', 'Fudd'),
    ('tweety.bird@example.com', 'Tweety', 'Bird'),
    ('sylvester.cat@example.com', 'Sylvester', 'Cat');

-- Rooms table
INSERT INTO Rooms (room_id, building)
VALUES
    (1, 'Arise Building'),
    (2, 'Arise Building'),
    (3, 'Arise Building');

-- Group Bookings table
INSERT INTO Group_bookings (mem_email, class_id)
VALUES
    ('bugs.bunny@example.com', 1),
    ('daffy.duck@example.com', 2),
    ('porky.pig@example.com', 1);

-- Group Classes table
INSERT INTO Group_classes (trainer_email, routine, class_date, class_time, room_id)
VALUES
    ('elmer.fudd@example.com', 'Yoga', '2024-03-01', '10:00', 1),
    ('tweety.bird@example.com', 'Zumba Dance', '2024-03-05', '11:00', 2),
    ('sylvester.cat@example.com', 'Swimming', '2024-03-10', '12:00', 3);

-- Personal Bookings table
INSERT INTO Personal_bookings (session_id, mem_email)
VALUES
    ('bugs.bunny@example.com', 1),
    ('daffy.duck@example.com', 2),
    ('porky.pig@example.com', 3);

-- Personal Sessions table
INSERT INTO Personal_sessions (trainer_email, routine, session_date, session_time, room_id)
VALUES
    ('elmer.fudd@example.com', 'Cardio', '2024-03-01', '13:00', 1),
    ('tweety.bird@example.com', 'Weight training', '2024-03-05', '12:00', 2),
    ('sylvester.cat@example.com', 'Deadlifting', '2024-03-10', '20:00', 2);

-- Admin Staff table
INSERT INTO Admin_staff (email, first_name, last_name)
VALUES
    ('road.runner@example.com', 'Road', 'Runner'),
    ('yosemite.sam@example.com', 'Yosemite', 'Sam'),
    ('lola.bunny@example.com', 'Lola', 'Bunny');

-- Equipment table
INSERT INTO Equipment (equip_name, maintenance_date)
VALUES
    ('Treadmill', '2024-03-01'),
    ('Cable machine', '2024-03-05'),
    ('Leg press', '2024-03-10');
