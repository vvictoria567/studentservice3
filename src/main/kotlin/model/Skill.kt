package com.example.studentsservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("skills")
data class Skill(
    @Id val id: Long? = null,
    val studentId: Int,
    val skillName: String
)