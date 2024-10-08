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
        val attributes =
            listOf(
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
        assertEquals(schema, db.schemaName)
        assertEquals(name, db.tableName)
        assertEquals(attributes, db.attributes)
        assertEquals(primaryKey, db.primaryKey)
    }

    @Test
    fun testToSql() {
        val schema = "ssqlt_test"
        val name = "_person"
        val attributes =
            listOf(
                Attribute("ssn", "VARCHAR (100)", false),
                Attribute("phone", "VARCHAR (100)", false),
                Attribute("manager", "VARCHAR (100)", true),
                Attribute("title", "VARCHAR (100)", true),
                Attribute("city", "VARCHAR (100)", false),
                Attribute("country", "VARCHAR (100)", false),
                Attribute("mayor", "VARCHAR (100)", false),
            )
        val primaryKey = listOf("ssn", "phone")
        val db = Table(schema, name, attributes, primaryKey)
        assertEquals(sqlPersonCreate, db.toCreateTableSql())
    }

    @Test
    fun testFromString() {
        val schemaName = "ssqlt_test"
        val tableName = "_person"
        val table = Table.fromString(sqlPersonCreate)
        assertEquals(schemaName, table.schemaName)
        assertEquals(tableName, table.tableName)
        assertEquals(
            listOf(
                Attribute("ssn", "VARCHAR (100)", false),
                Attribute("phone", "VARCHAR (100)", false),
                Attribute("manager", "VARCHAR (100)", true),
                Attribute("title", "VARCHAR (100)", true),
                Attribute("city", "VARCHAR (100)", false),
                Attribute("country", "VARCHAR (100)", false),
                Attribute("mayor", "VARCHAR (100)", false),
            ),
            table.attributes)
        assertEquals(listOf("ssn", "phone"), table.primaryKey)
        assertEquals(sqlPersonCreate, table.toCreateTableSql())
    }
}
