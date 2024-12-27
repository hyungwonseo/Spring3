INSERT INTO instructor (id, name, career) VALUES (1, 'John Doe', 'Experienced Instructor');

INSERT INTO course (id, title, description, instructor_id)
VALUES (1, 'Java Programming', 'Learn the basics of Java', 1);

INSERT INTO student (id, name, email)
VALUES (1, 'Steve', 'steve@email.com'),
       (2, 'Alice', 'alice@email.com');

INSERT INTO course_student (course_id, student_id)
VALUES (1, 1), (1, 2);