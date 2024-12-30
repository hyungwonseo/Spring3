INSERT INTO instructor (id, name, career) VALUES
    (1, 'John Doe', 'Experienced Instructor');
INSERT INTO instructor (id, name, career) VALUES
    (2, '홍길동', '초보강사');

INSERT INTO instructor_profile (id, bio, github_url, instructor_id) VALUES
    (1, 'John Doe는 풍부한 현장 경험과 깊은 전문성을 갖춘 Java 전문 강사입니다. 실무 중심의 교육을 통해 학생들이 실질적인 개발 역량을 키울 수 있도록 돕고 있습니다.'
    , 'www.github.com/johndoe', 1);
INSERT INTO instructor_profile (id, bio, github_url, instructor_id) VALUES
    (2, '홍길동은 실무 경험과 교육 열정을 겸비한 Python 전문가입니다. 데이터 분석부터 웹 개발까지 폭넓은 파이썬 활용법을 쉽고 체계적으로 지도합니다.'
    , 'www.github.com/honggildong', 2);

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