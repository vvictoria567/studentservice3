package com.example.studentsservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("students")
data class Student(
    @Id val id: Int? = null,
    val name: String,
    val skills: List<Skill> = listOf()
)