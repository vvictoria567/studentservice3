package com.example.studentsservice.repository

import com.example.studentsservice.model.Skill
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SkillRepository : CrudRepository<Skill, Long>