package com.example.studentservice

import com.example.studentservice.model.Student
import com.example.studentservice.repository.StudentRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(private val repository: StudentRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        // Пример XML строки — замените на чтение из файла по необходимости.
        val xmlContent = """
            <students>
                <student>
                    <name>Иван Иванов</name>
                    <skills>
                        <skill>Java</skill>
                        <skill>Kotlin</skill>
                    </skills>
                </student>
                <student>
                    <name>Петр Петров</name>
                    <skills>
                        <skill>Python</skill>
                        <skill>Django</skill>
                    </skills>
                </student>
            </students>
        """.trimIndent()

        // Парсим студентов из XML строки.
        val students = parseStudentsFromXml(xmlContent)

        // Сохраняем каждого студента в базу.
        for (student in students) {
            repository.save(student)
        }

        println("Данные успешно загружены")
    }
}