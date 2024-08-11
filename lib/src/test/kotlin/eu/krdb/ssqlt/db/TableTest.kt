package eu.krdb.ssqlt.db

import eu.krdb.ssqlt.TestingUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TableTest {

    private val sqlPersonCreate = TestingUtils.getResourceFileString("person.sql")

    @Test
    fun testConstructor() {
        val schema = "transducer"
        val name = "person"
        val attributes = listOf(
            Attribute("ssn", "VARCHAR(100)", false),
            Attribute("phone", "VARCHAR(100)", false),
            Attribute("manager", "VARCHAR(100)", true),
            Attribute("title", "VARCHAR(100)", true),
            Attribute("city", "VARCHAR(100)", false),
            Attribute("country", "VARCHAR(100)", false),
            Attribute("mayor", "VARCHAR(100)", false),
        )
        val primaryKey = listOf("ssn", "phone")
        val db = Table(schema, name, attributes, primaryKey)
        assertEquals(schema, db.schema)
        assertEquals(name, db.name)
        assertEquals(attributes, db.attributes)
        assertEquals(primaryKey, db.primaryKey)
    }

    @Test
    fun testToSql() {
        val schema = "transducer"
        val name = "person"
        val attributes = listOf(
            Attribute("ssn", "VARCHAR(100)", false),
            Attribute("phone", "VARCHAR(100)", false),
            Attribute("manager", "VARCHAR(100)", true),
            Attribute("title", "VARCHAR(100)", true),
            Attribute("city", "VARCHAR(100)", false),
            Attribute("country", "VARCHAR(100)", false),
            Attribute("mayor", "VARCHAR(100)", false),
        )
        val primaryKey = listOf("ssn", "phone")
        val db = Table(schema, name, attributes, primaryKey)
        val expected = """
            CREATE TABLE transducer.person (
            ssn VARCHAR(100) NOT NULL,
            phone VARCHAR(100) NOT NULL,
            manager VARCHAR(100),
            title VARCHAR(100),
            city VARCHAR(100) NOT NULL,
            country VARCHAR(100) NOT NULL,
            mayor VARCHAR(100) NOT NULL,
            PRIMARY KEY (ssn, phone)
            )
        """.trimIndent()
        assertEquals(expected, db.toCreateTableSql())
    }
}
