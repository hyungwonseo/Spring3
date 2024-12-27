INSERT INTO instructor (id, name, career) VALUES
            (1, 'John Doe', 'Experienced Instructor');
INSERT INTO instructor (id, name, career) VALUES
            (2, '홍길동', '초보강사');

INSERT INTO course (id, title, description, instructor_id)
VALUES (1, 'Java Programming', 'Learn the basics of Java', 1);
INSERT INTO course (id, title, description, instructor_id)
VALUES (2, 'Python Programming', 'Learn the basics of Python', 2);


INSERT INTO student (id, name, email)
VALUES (1, 'Steve', 'steve@email.com'),
       (2, 'Alice', 'alice@email.com'),
       (3, 'Andy', 'andy@email.com');

INSERT INTO course_student (course_id, student_id)
VALUES (1, 1), (1, 2), (2, 3);