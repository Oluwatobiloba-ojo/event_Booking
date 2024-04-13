

INSERT INTO USER(id, name, mail, password) VALUES
 (200, "test", "test@email.com", "password");


INSERT INTO USER(id, name, mail, password) VALUES
    (201, "test", "test@email.com", "password");

INSERT INTO EVENT(id, name, available_attendees_count, event_Description, date, category, user_id) VALUES
 (201, "Event", 20, "Description", "2004-04-21", "Concert", 200);

INSERT INTO EVENT(id, name, available_attendees_count, event_Description, date, category, user_id) VALUES
 (202, "Event2", 10, "Description", "1998-03-21", "Game", 200);

INSERT INTO EVENT(id, name, available_attendees_count, event_Description, date, category, user_id) VALUES
 (203, "Event2", 0, "Description", "1998-03-21", "Game", 201);

