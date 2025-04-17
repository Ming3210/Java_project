create database management;
use management;

create table admin(
    id int auto_increment primary key,
    username varchar(50) not null unique ,
    password varchar(255) not null
);

create table student(
    student_id char(5) primary key ,
    name varchar(100) not null,
    dob date not null ,
    email varchar(100) not null unique,
    gender bit not null ,
    phone varchar(20) ,
    password varchar(255) not null ,
    created_at datetime default current_timestamp
);

create table course(
    course_id char(5) primary key ,
    name varchar(100) not null,
    duration INT NOT NULL,
    instructor VARCHAR(100) NOT NULL,
    create_at DATE DEFAULT(now())
);

CREATE TABLE enrollment (
    enrollment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id char(5) NOT NULL,
    course_id char(5) NOT NULL,
    registered_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('WAITING', 'DENIED', 'CANCER', 'CONFIRM') DEFAULT 'WAITING',
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (course_id) REFERENCES course(course_id)
);


-- Thêm dữ liệu vào bảng admin
INSERT INTO admin (username, password) VALUES
   ('admin1', 'password123'),
   ('admin2', 'admin@2024');

-- Thêm dữ liệu vào bảng student
INSERT INTO student (student_id, name, dob, email, gender, phone, password) VALUES
    ('S0001', 'Nguyen Van A', '2000-01-15', 'a.nguyen@example.com', 1, '0909123456', 'passA123'),
    ('S0002', 'Tran Thi B', '2001-05-22', 'b.tran@example.com', 0, '0911223344', 'passB123'),
    ('S0003', 'Le Van C', '1999-08-09', 'c.le@example.com', 1, '0988991122', 'passC123'),
    ('S0004', 'Pham Thi D', '2002-03-10', 'd.pham@example.com', 0, '0912345678', 'passD123'),
    ('S0005', 'Hoang Van E', '2001-11-23', 'e.hoang@example.com', 1, '0909988776', 'passE123'),
    ('S0006', 'Vo Thi F', '2000-07-30', 'f.vo@example.com', 0, '0977123456', 'passF123'),
    ('S0007', 'Dang Van G', '1999-12-12', 'g.dang@example.com', 1, '0966887744', 'passG123'),
    ('S0008', 'Bui Thi H', '2003-09-17', 'h.bui@example.com', 0, '0955112233', 'passH123'),
    ('S0009', 'Nguyen Van I', '2002-04-25', 'i.nguyen@example.com', 1, '0933445566', 'passI123'),
    ('S0010', 'Tran Thi J', '2001-10-05', 'j.tran@example.com', 0, '0922113344', 'passJ123');

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

-- Thêm dữ liệu vào bảng enrollment
INSERT INTO enrollment (student_id, course_id, status) VALUES
   ('S0001', 'C0001', 'CONFIRM'),
   ('S0002', 'C0002', 'WAITING'),
   ('S0003', 'C0003', 'DENIED'),
   ('S0001', 'C0002', 'CANCER'),
   ('S0004', 'C0001', 'CONFIRM'),
   ('S0005', 'C0003', 'WAITING'),
   ('S0006', 'C0004', 'CONFIRM'),
   ('S0007', 'C0002', 'DENIED'),
   ('S0008', 'C0006', 'WAITING'),
   ('S0009', 'C0005', 'CONFIRM'),
   ('S0010', 'C0001', 'CANCER'),
   ('S0004', 'C0002', 'CONFIRM'),
   ('S0005', 'C0007', 'WAITING'),
   ('S0006', 'C0008', 'CONFIRM'),
   ('S0007', 'C0010', 'WAITING'),
   ('S0008', 'C0009', 'CONFIRM'),
   ('S0009', 'C0003', 'CONFIRM'),
   ('S0010', 'C0004', 'WAITING'),
   ('S0004', 'C0006', 'CANCER'),
   ('S0005', 'C0008', 'DENIED'),
   ('S0006', 'C0009', 'WAITING');



DELIMITER //
CREATE PROCEDURE check_login(
    IN username_in VARCHAR(50),
    IN password_in VARCHAR(255),
    OUT return_code INT
)
BEGIN
    DECLARE admin_exists INT;
    DECLARE student_exists INT;

    -- Check if admin exists
    SELECT COUNT(*) INTO admin_exists
    FROM admin
    WHERE username = username_in AND password = password_in;

    IF admin_exists > 0 THEN
        SET return_code = 1; -- Admin login
    ELSE
        -- Check if student exists
        SELECT COUNT(*) INTO student_exists
        FROM student
        WHERE email = username_in AND password = password_in;

        IF student_exists > 0 THEN
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

create procedure get_courses_by_page(
    page_number int
)
begin
    declare offset_value int;

    set offset_value = (page_number - 1) * 5;

    select *
    from course
    limit 5 offset offset_value;
end //

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

# drop procedure CheckCourseIdExists;


DELIMITER //
create procedure add_course(
    course_id_in char(5),
    name_in varchar(100),
    duration_in int,
    instructor_in varchar(100)
)
begin
    insert into course(course_id, name, duration, instructor)
    values (course_id_in, name_in, duration_in, instructor_in);
end;
//
DELIMITER ;
