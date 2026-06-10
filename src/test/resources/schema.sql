CREATE TABLE IF NOT EXISTS students
(
     id int AUTO_INCREMENT PRIMARY KEY,
     full_name varchar(100) NOT NULL,
     furigana varchar(100) NOT NULL,
     nickname varchar(50),
     email varchar(255) NOT NULL,
     area varchar(100),
     age int,
     gender varchar(10),
     remark varchar(500),
     is_deleted boolean
);

CREATE TABLE IF NOT EXISTS students_courses
(
     id varchar(50) PRIMARY KEY,
     course_name varchar(100) NOT NULL,
     start_date datetime,
     expected_end_date datetime,
     created_at datetime,
     updated_at datetime,
     student_id int NOT NULL
):

CREATE TABLE IF NOT EXISTS enrollment_status
(
     id varchar(50) PRIMARY KEY,
     student_course_id varchar(50) NOT NULL,
     status varchar(20) NOT NULL
):
