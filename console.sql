CREATE DATABASE management;
USE management;

CREATE TABLE student(
                        student_id CHAR(5) PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        dob DATE NOT NULL,
                        email VARCHAR(100) NOT NULL UNIQUE,
                        gender BOOLEAN NOT NULL,
                        phone VARCHAR(20),
                        created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE account(
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(50) unique ,
                        password VARCHAR(255) NOT NULL default ('123456'),
                        role ENUM('admin', 'student') NOT NULL,
                        email VARCHAR(100) UNIQUE,
                        student_id CHAR(5) UNIQUE,
                        FOREIGN KEY (student_id) REFERENCES student(student_id),
                        FOREIGN KEY (email) REFERENCES student(email)
);

CREATE TABLE course(
                       course_id CHAR(5) PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       duration INT NOT NULL,
                       instructor VARCHAR(100) NOT NULL,
                       create_at DATE DEFAULT(CURRENT_DATE)
);

CREATE TABLE enrollment (
                            enrollment_id INT PRIMARY KEY AUTO_INCREMENT,
                            student_id CHAR(5) NOT NULL,
                            course_id CHAR(5) NOT NULL,
                            registered_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                            status ENUM('WAITING', 'DENIED', 'CANCEL', 'CONFIRM') DEFAULT 'WAITING',
                            FOREIGN KEY (student_id) REFERENCES student(student_id),
                            FOREIGN KEY (course_id) REFERENCES course(course_id)
);

-- Thêm dữ liệu vào bảng student
INSERT INTO student (student_id, name, dob, email, gender, phone) VALUES
                                                                      ('S0001', 'Nguyen Van A', '2000-01-15', 'a@gmail.com', 1, '0909123456'),
                                                                      ('S0002', 'Tran Thi B', '2001-05-22', 'b.tran@example.com', 0, '0911223344'),
                                                                      ('S0003', 'Le Van C', '1999-08-09', 'c.le@example.com', 1, '0988991122'),
                                                                      ('S0004', 'Pham Thi D', '2002-03-10', 'd.pham@example.com', 0, '0912345678'),
                                                                      ('S0005', 'Hoang Van E', '2001-11-23', 'e.hoang@example.com', 1, '0909988776'),
                                                                      ('S0006', 'Vo Thi F', '2000-07-30', 'f.vo@example.com', 0, '0977123456'),
                                                                      ('S0007', 'Dang Van G', '1999-12-12', 'g.dang@example.com', 1, '0966887744'),
                                                                      ('S0008', 'Bui Thi H', '2003-09-17', 'h.bui@example.com', 0, '0955112233'),
                                                                      ('S0009', 'Nguyen Van I', '2002-04-25', 'i.nguyen@example.com', 1, '0933445566'),
                                                                      ('S0010', 'Tran Thi J', '2001-10-05', 'j.tran@example.com', 0, '0922113344');

-- Thêm dữ liệu admin vào bảng account
INSERT INTO account (username, password, role) VALUES
                                                   ('admin1', 'password123', 'admin'),
                                                   ('admin2', 'admin@2024', 'admin');

-- Thêm dữ liệu sinh viên vào bảng account
INSERT INTO account ( password, role, email, student_id) VALUES
                                                             ('student123', 'student', 'a@gmail.com', 'S0001'),
                                                             ('student456', 'student', 'b.tran@example.com', 'S0002'),
                                                             ( 'student789', 'student', 'c.le@example.com', 'S0003'),
                                                             ( 'student101', 'student', 'd.pham@example.com', 'S0004'),
                                                             ( 'student202', 'student', 'e.hoang@example.com', 'S0005'),
                                                             ('123456','student','f.vo@example.com','S0006'),
                                                             ('123456','student','g.dang@example.com','S0007'),
                                                             ('123456','student','h.bui@example.com','S0008'),
                                                             ('123456','student','i.nguyen@example.com','S0009'),
                                                             ('123456','student','j.tran@example.com','S0010');



-- Thêm dữ liệu vào bảng course
INSERT INTO course (course_id, name, duration, instructor) VALUES
                                                               ('C0001', 'Web Development', 90, 'Nguyen Van Khoa'),
                                                               ('C0002', 'Data Science', 120, 'Tran Thi Minh'),
                                                               ('C0003', 'Cyber Security', 100, 'Pham Quang Huy'),
                                                               ('C0004', 'Machine Learning', 130, 'Nguyen Thi An'),
                                                               ('C0005', 'Mobile App Development', 90, 'Le Van Long'),
                                                               ('C0006', 'Cloud Computing', 110, 'Pham Tien Dat'),
                                                               ('C0007', 'UI/UX Design', 80, 'Tran Ngoc Bao'),
                                                               ('C0008', 'DevOps Fundamentals', 100, 'Doan Minh Anh'),
                                                               ('C0009', 'Game Development', 120, 'Nguyen Hoang Nam'),
                                                               ('C0010', 'AI & Robotics', 140, 'Vo Thi Kim');

-- Thêm dữ liệu vào bảng enrollment (sửa CANCER thành CANCEL)
INSERT INTO enrollment (student_id, course_id, status) VALUES
                                                           ('S0001', 'C0001', 'CONFIRM'),
                                                           ('S0002', 'C0002', 'WAITING'),
                                                           ('S0003', 'C0003', 'DENIED'),
                                                           ('S0001', 'C0002', 'CANCEL'),
                                                           ('S0004', 'C0001', 'CONFIRM'),
                                                           ('S0005', 'C0003', 'WAITING'),
                                                           ('S0006', 'C0004', 'CONFIRM'),
                                                           ('S0007', 'C0002', 'DENIED'),
                                                           ('S0008', 'C0006', 'WAITING'),
                                                           ('S0009', 'C0005', 'CONFIRM'),
                                                           ('S0010', 'C0001', 'CANCEL'),
                                                           ('S0004', 'C0002', 'CONFIRM'),
                                                           ('S0005', 'C0007', 'WAITING'),
                                                           ('S0006', 'C0008', 'CONFIRM'),
                                                           ('S0007', 'C0010', 'WAITING'),
                                                           ('S0008', 'C0009', 'CONFIRM'),
                                                           ('S0009', 'C0003', 'CONFIRM'),
                                                           ('S0010', 'C0004', 'WAITING'),
                                                           ('S0004', 'C0006', 'CANCEL'),
                                                           ('S0005', 'C0008', 'DENIED'),
                                                           ('S0006', 'C0009', 'WAITING');

-- Stored Procedures
DELIMITER //
CREATE PROCEDURE check_login(
    IN username_in VARCHAR(50),
    IN password_in VARCHAR(255),
    OUT return_code INT
)
BEGIN
    DECLARE user_role VARCHAR(10);

    -- Check for admin login using username
SELECT role INTO user_role
FROM account
WHERE role = 'admin'
  AND username = username_in
  AND password = password_in
    LIMIT 1;

IF user_role = 'admin' THEN
        SET return_code = 1; -- Admin login
ELSE
        -- Check for student login using email
SELECT role INTO user_role
FROM account
WHERE role = 'student'
  AND email = username_in
  AND password = password_in
    LIMIT 1;

IF user_role = 'student' THEN
            SET return_code = 2; -- Student login
ELSE
            SET return_code = 0; -- Invalid login
END IF;
END IF;
END //
DELIMITER ;

# drop procedure check_login;

DELIMITER //
CREATE PROCEDURE get_total_pages()
BEGIN
    DECLARE total_courses INT;
    DECLARE total_pages INT;

    -- Lấy tổng số khóa học
SELECT COUNT(*) INTO total_courses FROM course;

-- Tính tổng số trang
SET total_pages = CEIL(total_courses / 5);

    -- Trả về tổng số trang
SELECT total_pages AS total_pages;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE get_courses_by_page(
    page_number INT
)
BEGIN
    DECLARE offset_value INT;

    SET offset_value = (page_number - 1) * 5;

SELECT *
FROM course
         LIMIT 5 OFFSET offset_value;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE CheckCourseIdExists(
    IN course_id_in VARCHAR(50),
    OUT exists_flag BOOLEAN
)
BEGIN
    DECLARE count_val INT;

SELECT COUNT(*) INTO count_val
FROM course
WHERE course.course_id = course_id_in;

IF count_val > 0 THEN
        SET exists_flag = TRUE;
ELSE
        SET exists_flag = FALSE;
END IF;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE add_course(
    course_id_in CHAR(5),
    name_in VARCHAR(100),
    duration_in INT,
    instructor_in VARCHAR(100)
)
BEGIN
INSERT INTO course(course_id, name, duration, instructor)
VALUES (course_id_in, name_in, duration_in, instructor_in);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetCourseById(IN p_course_id CHAR(5))
BEGIN
SELECT course_id, name, duration, instructor, create_at
FROM course
WHERE course_id = p_course_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE UpdateCourse(
    p_course_id CHAR(5),
    p_name VARCHAR(100),
    p_duration INT,
    p_instructor VARCHAR(100)
)
BEGIN
UPDATE course
SET name = p_name,
    duration = p_duration,
    instructor = p_instructor
WHERE course_id = p_course_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE DeleteCourse(
    IN p_course_id CHAR(5)
)
BEGIN
    DECLARE enrollment_count INT;

    -- Đếm số sinh viên đang đăng ký khóa học
SELECT COUNT(*) INTO enrollment_count
FROM enrollment
WHERE course_id = p_course_id;

IF enrollment_count > 0 THEN
        -- Nếu có sinh viên đang đăng ký thì báo lỗi (sử dụng SIGNAL)
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Không thể xóa khóa học vì đang có sinh viên đăng ký.';
ELSE
        -- Nếu không có sinh viên nào, tiến hành xóa
DELETE FROM course WHERE course_id = p_course_id;
END IF;
END //
DELIMITER ;

# drop procedure DeleteCourse;

DELIMITER //
CREATE PROCEDURE GetTotalSearchCoursePages(
    search_name VARCHAR(100)
)
BEGIN
    DECLARE total_paginated_courses INT;
    DECLARE total_pages INT;

    -- Lấy tổng số khóa học
SELECT COUNT(*) INTO total_paginated_courses FROM course
WHERE name LIKE CONCAT('%', LOWER(search_name), '%');

-- Tính tổng số trang
SET total_pages = CEIL(total_paginated_courses / 5);

    -- Trả về tổng số trang
SELECT total_pages AS total_pages;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetCoursesBySearchNamePages(
    search_name VARCHAR(100),
    page_number INT
)
BEGIN
    DECLARE offset_value INT;

    SET offset_value = (page_number - 1) * 5;

SELECT *
FROM course
WHERE name LIKE CONCAT('%', LOWER(search_name), '%')
    LIMIT 5 OFFSET offset_value;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SortCoursesByNameAsc(
    page_number INT
)
BEGIN
    DECLARE offset_value INT;

    SET offset_value = (page_number - 1) * 5;
SELECT * FROM course
ORDER BY name ASC
    LIMIT 5 OFFSET offset_value;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SortCoursesByNameDesc(
    page_number INT
)
BEGIN
    DECLARE offset_value INT;

    SET offset_value = (page_number - 1) * 5;
SELECT * FROM course
ORDER BY name DESC
    LIMIT 5 OFFSET offset_value;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SortCoursesByIdAsc(
    page_number INT
)
BEGIN
    DECLARE offset_value INT;

    SET offset_value = (page_number - 1) * 5;
SELECT * FROM course
ORDER BY course_id ASC
    LIMIT 5 OFFSET offset_value;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SortCoursesByIdDesc(
    page_number INT
)
BEGIN
    DECLARE offset_value INT;

    SET offset_value = (page_number - 1) * 5;
SELECT * FROM course
ORDER BY course_id DESC
    LIMIT 5 OFFSET offset_value;
END //
DELIMITER ;



DELIMITER //
CREATE PROCEDURE getTotalStudentPages()
BEGIN
    DECLARE total_student INT;
    DECLARE total_pages INT;

    -- Lấy tổng số khóa học
SELECT COUNT(*) INTO total_student FROM student;

-- Tính tổng số trang
SET total_pages = CEIL(total_student / 5);

    -- Trả về tổng số trang
SELECT total_pages AS total_pages;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE getStudentsByPage(
    page_number INT
)
BEGIN
    DECLARE offset_value INT;

    SET offset_value = (page_number - 1) * 5;

SELECT *
FROM student
         LIMIT 5 OFFSET offset_value;
END //
DELIMITER ;



DELIMITER //
CREATE PROCEDURE AddNewStudent(
    IN p_student_id CHAR(5),
    IN p_name VARCHAR(100),
    IN p_dob DATE,
    IN p_email VARCHAR(100),
    IN p_gender BOOLEAN,
    IN p_phone VARCHAR(20)
)
BEGIN
INSERT INTO student(student_id, name, dob, email, gender, phone)
VALUES(p_student_id, p_name, p_dob, p_email, p_gender, p_phone);

INSERT INTO account( role, email, student_id)
VALUES( 'student', p_email, p_student_id);
END //
DELIMITER ;

# drop procedure AddNewStudent;


DELIMITER //
CREATE PROCEDURE isStudentExists(IN p_student_id CHAR(5))
BEGIN
SELECT COUNT(*) > 0 AS is_exists
FROM student
WHERE student_id = p_student_id;
END //
DELIMITER ;


DELIMITER //

CREATE PROCEDURE updateStudent(
    IN p_student_id CHAR(5),
    IN p_name VARCHAR(100),
    IN p_dob DATE,
    IN p_email VARCHAR(100),
    IN p_gender BOOLEAN,
    IN p_phone VARCHAR(20)
)
BEGIN
UPDATE student
SET
    name = p_name,
    dob = p_dob,
    email = p_email,
    gender = p_gender,
    phone = p_phone
WHERE student_id = p_student_id;
END;
DELIMITER ;




DELIMITER //
CREATE PROCEDURE deleteStudent(
    IN p_student_id CHAR(5)
)
BEGIN
    -- Xoá tài khoản trước (nếu có liên kết foreign key)
DELETE FROM account WHERE student_id = p_student_id;

-- Sau đó xoá sinh viên
DELETE FROM student WHERE student_id = p_student_id;
END //
DELIMITER ;



DELIMITER //
CREATE PROCEDURE getSearchStudentsByPage(
    searchName VARCHAR(100)
)
BEGIN
    DECLARE total_students INT;
    DECLARE total_pages INT;

SELECT COUNT(*) INTO total_students FROM student
WHERE student_id LIKE CONCAT('%', searchName, '%')
   OR name LIKE CONCAT('%', searchName, '%')
   OR email LIKE CONCAT('%', searchName, '%');

-- Tính tổng số trang
SET total_pages = CEIL(total_students / 5);

    -- Trả về tổng số trang
SELECT total_pages AS total_pages;
END //
DELIMITER ;

# drop procedure getSearchStudentsByPage;



DELIMITER //
CREATE PROCEDURE searchStudents(
    IN p_search VARCHAR(100),
    IN p_page_number INT
)
BEGIN
    DECLARE p_offset INT;

    SET p_offset = (p_page_number - 1) * 5;

SELECT student_id, name, dob, email, gender, phone, created_at
FROM student
WHERE student_id LIKE CONCAT('%', p_search, '%')
   OR name LIKE CONCAT('%', p_search, '%')
   OR email LIKE CONCAT('%', p_search, '%')
    LIMIT 5 OFFSET p_offset;
END //
DELIMITER ;



DELIMITER //
CREATE PROCEDURE SortStudentByNameAsc(
    page_number INT
)
BEGIN
    DECLARE offset_value INT;

    SET offset_value = (page_number - 1) * 5;
SELECT * FROM student
ORDER BY name ASC
    LIMIT 5 OFFSET offset_value;
END //
DELIMITER ;



DELIMITER //
CREATE PROCEDURE SortStudentByNameDesc(
    page_number INT
)
BEGIN
    DECLARE offset_value INT;

    SET offset_value = (page_number - 1) * 5;
SELECT * FROM student
ORDER BY name DESC
    LIMIT 5 OFFSET offset_value;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE SortStudentByIdAsc(
    page_number INT
)
BEGIN
    DECLARE offset_value INT;

    SET offset_value = (page_number - 1) * 5;
SELECT * FROM student
ORDER BY student_id ASC
    LIMIT 5 OFFSET offset_value;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SortStudentByIdDesc(
    page_number INT
)
BEGIN
    DECLARE offset_value INT;

    SET offset_value = (page_number - 1) * 5;
SELECT * FROM student
ORDER BY student_id DESC
    LIMIT 5 OFFSET offset_value;
END //
DELIMITER ;

# drop procedure SortStudentByNameDesc;


DELIMITER //
create procedure getStudentByEmail(
    email_in VARCHAR(100)
)
begin
select * from student where LOWER(email) = LOWER(email_in);
end //
DELIMITER ;

# drop procedure getStudentByEmail;

DELIMITER //
CREATE PROCEDURE registerEnrollment(
    student_id_in CHAR(5),
    course_id_in CHAR(5)
)
BEGIN
    DECLARE enrollment_count INT;
    DECLARE student_exists INT;
    DECLARE course_exists INT;

SELECT COUNT(*) INTO student_exists
FROM student
WHERE student_id = student_id_in;

-- Kiểm tra xem khóa học có tồn tại không
SELECT COUNT(*) INTO course_exists
FROM course
WHERE course_id = course_id_in;

IF student_exists = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Sinh viên không tồn tại.';
    ELSEIF course_exists = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Khóa học không tồn tại.';
ELSE
        -- Kiểm tra nếu sinh viên đã đăng ký khóa học này chưa
SELECT COUNT(*) INTO enrollment_count
FROM enrollment
WHERE student_id = student_id_in AND course_id = course_id_in;

IF enrollment_count > 0 THEN
            SIGNAL SQLSTATE '45000'
                SET MESSAGE_TEXT = 'Sinh viên đã đăng ký khóa học này.';
ELSE
            -- Thực hiện đăng ký
            INSERT INTO enrollment (student_id, course_id)
            VALUES (student_id_in, course_id_in);
END IF;
END IF;
END //
DELIMITER ;


# drop procedure registerEnrollment;


DELIMITER //
create procedure showAllRegistedEnrollment(
    student_id_in CHAR(5)
)
begin
select course.course_id, course.name, enrollment.status
from enrollment
         join course on enrollment.course_id = course.course_id
where enrollment.student_id = student_id_in;
end //
DELIMITER ;



# drop procedure showAllRegistedEnrollment;


DELIMITER //
create procedure cancelEnrollment(
    student_id_in CHAR(5),
    course_id_in CHAR(5)
)
begin
    declare enrollment_count int;
    declare status_check varchar(10);

select count(*) into enrollment_count
from enrollment
where student_id = student_id_in and course_id = course_id_in;

select status into status_check
from enrollment
where student_id = student_id_in and course_id = course_id_in;

if enrollment_count = 0 then
        signal sqlstate '45000'
            set message_text = 'Sinh viên chưa đăng ký khóa học này.';
    elseif status_check = 'CONFIRM' then
        signal sqlstate '45000'
            set message_text = 'Không thể hủy đăng ký khóa học đã xác nhận.';
else
delete from enrollment
where student_id = student_id_in and course_id = course_id_in;
end if;
end //;
DELIMITER ;

# drop procedure cancelEnrollment;

DELIMITER //
create procedure checkOldPassword(
    student_id_in CHAR(5),
    password_in VARCHAR(255)
)
begin
    declare student_exists int;
    declare password_check varchar(255);
select count(*) into student_exists
from student
where student_id = student_id_in;
if student_exists = 0 then
        signal sqlstate '45000'
            set message_text = 'Sinh viên không tồn tại.';
end if;

select password into password_check
from account
where student_id = student_id_in;

if password_check = password_in then
select 'true' as result;
else
        signal sqlstate '45000'
            set message_text = 'Mật khẩu không đúng.';
select 'false' as result;

end if;
end //

DELIMITER ;
# drop procedure checkOldPassword;


DELIMITER //
create procedure updatePassword(
    student_id_in CHAR(5),
    new_password_in VARCHAR(255)
)
begin
    declare student_exists int;
select count(*) into student_exists
from student
where student_id = student_id_in;
if student_exists = 0 then
        signal sqlstate '45000'
            set message_text = 'Sinh viên không tồn tại.';
end if;

update account
set password = new_password_in
where student_id = student_id_in;
end //
DELIMITER ;


DELIMITER //
create procedure getTotalRegistedEnrollmentPages(
    student_id_in CHAR(5)
)
begin
    declare total_enrollment int;
    declare total_pages int;

select count(*) into total_enrollment
from enrollment
where student_id = student_id_in;

-- Tính tổng số trang
set total_pages = ceil(total_enrollment / 5);

    -- Trả về tổng số trang
select total_pages as total_pages;
end //;

DELIMITER ;


DELIMITER //
create procedure getRegistedEnrollmentByPage(
    student_id_in CHAR(5),
    page_number int
)
begin
    declare offset_value int;

    set offset_value = (page_number - 1) * 5;

select course.course_id, course.name, enrollment.status
from enrollment
         join course on enrollment.course_id = course.course_id
where enrollment.student_id = student_id_in
    limit 5 offset offset_value;
end //


DELIMITER //
CREATE PROCEDURE checkEmailExist(
    email VARCHAR(50),
    OUT exists_flag BOOLEAN
)
BEGIN
    DECLARE count_val INT;

SELECT COUNT(*) INTO count_val
FROM account
WHERE account.email = email;

IF count_val > 0 THEN
        SET exists_flag = TRUE;
ELSE
        SET exists_flag = FALSE;
END IF;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE displayStudentByEnrollmentList(
    course_id_in CHAR(5)
)
BEGIN
SELECT s.student_id, s.dob, s.name, s.phone, s.email
FROM student s
         JOIN enrollment e ON s.student_id = e.student_id
WHERE e.course_id = course_id_in;
END //
DELIMITER ;

# drop procedure displayStudentByEnrollmentList;

DELIMITER //
create procedure denyEnrollmentStatus(
    student_id_in CHAR(5),
    course_id_in CHAR(5)
)
begin
    declare enrollment_count int;
    declare status_check varchar(10);

select count(*) into enrollment_count
from enrollment
where student_id = student_id_in and course_id = course_id_in;

select status into status_check
from enrollment
where student_id = student_id_in and course_id = course_id_in;

if enrollment_count = 0 then
        signal sqlstate '45000'
            set message_text = 'Sinh viên chưa đăng ký khóa học này.';
else
update enrollment
set status = 'CONFIRM'
where student_id = student_id_in and course_id = course_id_in;
end if;
end //

DELIMITER ;



DELIMITER //
create procedure getAllWaitingStatusEnrollment()
begin
select course.course_id, course.name, enrollment.student_id, enrollment.status
from enrollment
         join course on enrollment.course_id = course.course_id
where enrollment.status = 'WAITING';
end //
DELIMITER ;



DELIMITER //
CREATE PROCEDURE approveEnrollment(
    student_id_in CHAR(5),
    course_id_in CHAR(5)
)
BEGIN
    DECLARE current_status VARCHAR(10);

SELECT status INTO current_status
FROM enrollment
WHERE student_id = student_id_in AND course_id = course_id_in;

IF current_status IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Sinh viên chưa đăng ký khóa học này.';
    ELSEIF current_status <> 'WAITING' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Chỉ có thể duyệt những yêu cầu đang chờ.';
ELSE
UPDATE enrollment
SET status = 'CONFIRM'
WHERE student_id = student_id_in AND course_id = course_id_in;
END IF;
END //
DELIMITER ;

# drop procedure approveEnrollment;


DELIMITER //
create procedure deniedEnrollment(
    student_id_in CHAR(5),
    course_id_in CHAR(5)
)
begin
    declare enrollment_count int;
    declare status_check varchar(10);

select count(*) into enrollment_count
from enrollment
where student_id = student_id_in and course_id = course_id_in;

select status into status_check
from enrollment
where student_id = student_id_in and course_id = course_id_in;

if status_check = 'CONFIRM' then
        signal sqlstate '45000'
            set message_text = 'Không thể từ chối yêu cầu đã xác nhận.';
end if;

    if enrollment_count = 0 then
        signal sqlstate '45000'
            set message_text = 'Sinh viên chưa đăng ký khóa học này.';
else
update enrollment
set status = 'DENIED'
where student_id = student_id_in and course_id = course_id_in;
end if;
end //
DELIMITER ;

# drop procedure deniedEnrollment;


DELIMITER //
create procedure statisticCourseByStudent()
begin
select course.course_id, course.name, count(enrollment.student_id) as total_students
from enrollment
         join course on enrollment.course_id = course.course_id where enrollment.status = 'CONFIRM'
group by course.course_id;
end //
DELIMITER ;


DELIMITER //
create procedure statisticCourseTop5HighestRegisted()
begin
select course.name, count(enrollment.student_id) as total_students
from enrollment
         join course on enrollment.course_id = course.course_id where enrollment.status = 'CONFIRM'
group by course.course_id
order by total_students desc
    limit 5;
end //
DELIMITER ;

# drop procedure statisticCourseTop5HighestRegisted;




DELIMITER //
create procedure statisticCourseWith10StudentsOrHigher()
begin
select course.name, count(enrollment.student_id) as total_students
from enrollment
         join course on enrollment.course_id = course.course_id where enrollment.status = 'CONFIRM'
group by course.course_id
having total_students >= 10;
end //
DELIMITER ;

# drop procedure statisticCourseWith10StudentsOrHigher;
