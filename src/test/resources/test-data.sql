--INSERT INTO admin (id, name) VALUES (1, 'Test Admin');
--
--INSERT INTO slot_details (id, admin_id, selected_date, slot_number, start_time, end_time, total_slots, slot_duration_in_seconds)
--VALUES (1, 1, '2024-05-15', 1, '10:00:00', '10:30:00', 3, 1800),
--       (2, 1, '2024-05-15', 2, '10:30:01', '11:00:00', 3, 1800),
--       (3, 1, '2024-05-15', 3, '11:00:01', '11:30:00', 3, 1800);
INSERT INTO admin (id, username, password) VALUES (1, 'existing_username', 'password');