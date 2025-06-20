package com.example.studentservice.repository

import com.example.studentservice.model.Student
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class StudentRepository(private val jdbcTemplate: JdbcTemplate) {

    fun save(student: Student): Long {
        // Сохраняем студента и получаем его ID
        val studentId = jdbcTemplate.queryForObject(
            "INSERT INTO students (name) VALUES (?) RETURNING id",
            Long::class.java,
            student.name
        ) ?: throw RuntimeException("Failed to insert student")

        // Сохраняем скиллы студента
        student.skills.forEach { skill ->
            jdbcTemplate.update(
                "INSERT INTO skills (name, student_id) VALUES (?, ?)",
                skill.name,
                studentId
            )
        }
        return studentId
    }
}