package com.example.studentservice

import com.example.studentservice.model.Skill
import com.example.studentservice.model.Student
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory

fun parseStudentsFromXml(xmlContent: String): List<Student> {
    val factory = DocumentBuilderFactory.newInstance()
    val builder = factory.newDocumentBuilder()
    val inputStream = xmlContent.byteInputStream()
    val doc = builder.parse(inputStream)

    val studentsNodes = doc.getElementsByTagName("student")

    val students = mutableListOf<Student>()

    for (i in 0 until studentsNodes.length) {
        val node = studentsNodes.item(i) as? Element ?: continue

        val nameNodeList = node.getElementsByTagName("name")
        if (nameNodeList.length == 0) continue

        val name = nameNodeList.item(0).textContent

        // Получение скиллов внутри каждого студента
        val skillsNodeList = node.getElementsByTagName("skill")
        val skills = mutableListOf<Skill>()

        for (j in 0 until skillsNodeList.length) {
            val skillElement = skillsNodeList.item(j) as? Element ?: continue
            val skillName = skillElement.textContent.trim()
            skills.add(Skill(name = skillName))
        }

        students.add(Student(name = name, skills = skills))
    }

    return students
}