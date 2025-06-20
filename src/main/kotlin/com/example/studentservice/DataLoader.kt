package com.example.studentsservice

import com.example.studentsservice.model.Student
import com.example.studentsservice.xml.XmlParser
import com.example.studentsservice.repository.StudentRepository
import com.example.studentsservice.repository.SkillRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val studentRepository: StudentRepository,
    private val skillRepository: SkillRepository,
    private val xmlParser: XmlParser
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val students = xmlParser.parse("src/main/resources/students.xml")
        saveStudents(students)
    }

    fun saveStudents(students: List<Student>) {
        students.forEach { student ->
            val savedStudent = studentRepository.save(student)
            student.skills?.forEach { skill ->
                val skillEntity = skill.copy(studentId = savedStudent.id!!)
                skillRepository.save(skillEntity)
            }
        }
    }
}