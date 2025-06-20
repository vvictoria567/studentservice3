package com.example.studentservice.model

data class Student(
    val id: Long? = null,
    val name: String,
    val skills: List<Skill> = listOf()
)

data class Skill(
    val id: Long? = null,
    val name: String,
    val studentId: Long? = null
)