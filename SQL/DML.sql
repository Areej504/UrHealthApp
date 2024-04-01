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
INSERT INTO Fitness_goals (mem_email, weight, running_time)
VALUES
    ('bugs.bunny@example.com', 160, '15'),
    ('daffy.duck@example.com', 155, '45'),
    ('porky.pig@example.com', 150, '30');

-- Routine table
INSERT INTO Routine (mem_email, routine)
VALUES
    ('bugs.bunny@example.com', 'Cardio'),
    ('daffy.duck@example.com', 'Weight training'),
    ('porky.pig@example.com', 'Yoga');

-- Achievement table
INSERT INTO Achievement (mem_email, achievement)
VALUES
    ('bugs.bunny@example.com', 'Ran 15 minutes'),
    ('daffy.duck@example.com', 'Reached 155 kg'),
    ('porky.pig@example.com', 'Ran 30 minutes');

-- Billings table
INSERT INTO Billings (mem_id, amount, date)
VALUES
    ('bugs.bunny@example.com', 15.00, '2024-03-01'),
    ('daffy.duck@example.com', 15.00, '2024-03-05'),
    ('porky.pig@example.com', 15.00, '2024-03-10');

-- Trainer table
INSERT INTO Trainer (email, first_name, last_name)
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
    ('daffy.duck@example.com', 1),
    ('porky.pig@example.com', 3);

-- Group Classes table
INSERT INTO Group_classes (trainer_email, class_date, class_time, room_id)
VALUES
    ('elmer.fudd@example.com', '2024-03-01', '10:00', 1),
    ('tweety.bird@example.com', '2024-03-05', '11:00', 2),
    ('sylvester.cat@example.com', '2024-03-10', '12:00', 3);

-- Personal Bookings table
INSERT INTO Personal_bookings (session_id, mem_email)
VALUES
    (1, 'bugs.bunny@example.com'),
    (2, 'daffy.duck@example.com'),
    (3, 'porky.pig@example.com');

-- Personal Sessions table
INSERT INTO Personal_sessions (trainer_email, session_date, session_time, room_id)
VALUES
    ('elmer.fudd@example.com', '2024-03-01', '13:00', 1),
    ('tweety.bird@example.com', '2024-03-05', '12:00', 2),
    ('sylvester.cat@example.com', '2024-03-10', '20:00', 3);

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
