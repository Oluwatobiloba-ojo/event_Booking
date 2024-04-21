SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE USER;
TRUNCATE TABLE EVENT;

INSERT INTO USER(id, name, email, password) VALUES
 (200, "test", "opeoluwaagnes@gmail.com", "password");


INSERT INTO USER(id, name, email, password) VALUES
    (201, "test", "ojot630@gmail.com", "password");

INSERT INTO EVENT(id, name, available_attendees_count, event_Description, date, category, user_id) VALUES
 (201, "Event", 20, "Description", "2024-04-21", "Concert", 200);

INSERT INTO EVENT(id, name, available_attendees_count, event_Description, date, category, user_id) VALUES
 (202, "Event2", 10, "Description", "2024-04-21", "Game", 200);

INSERT INTO EVENT(id, name, available_attendees_count, event_Description, date, category, user_id) VALUES
 (203, "Event2", 0, "Description", "2024-04-21", "Game", 201);


