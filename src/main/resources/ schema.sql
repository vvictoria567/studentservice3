CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE skills (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    skill_name VARCHAR(255),
    FOREIGN KEY (student_id) REFERENCES students(id)
);