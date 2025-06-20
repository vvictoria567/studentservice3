package com.example.studentsservice.xml

import com.example.studentsservice.model.Skill
import com.example.studentsservice.model.Student
import org.w3c.dom.Element
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

class XmlParser {
    fun parse(filePath: String): List<Student> {
        val students = mutableListOf<Student>()
        val file = File(filePath)
        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()
        val doc = dBuilder.parse(file)
        doc.documentElement.normalize()

        val studentNodes = doc.getElementsByTagName("student")
        for (i in 0 until studentNodes.length) {
            val node = studentNodes.item(i)
            if (node is Element) {
                val id = node.getElementsByTagName("id").item(0).textContent.toInt()
                val name = node.getElementsByTagName("name").item(0).textContent
                val skills = mutableListOf<Skill>()

                val skillsNode = node.getElementsByTagName("skills").item(0) as? Element
                skillsNode?.let {
                    val skillNodes = it.getElementsByTagName("skill")
                    for (j in 0 until skillNodes.length) {
                        val skillName = skillNodes.item(j).textContent
                        skills.add(Skill(studentId = id, skillName = skillName))
                    }
                }

                students.add(Student(id = id, name = name))
            }
        }
        return students
    }
}