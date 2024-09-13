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
        val tableIdentifier = TableIdentifier(schema, name)
        val attributes =
            listOf(
                Attribute("ssn", "VARCHAR(100)", false, tableIdentifier),
                Attribute("phone", "VARCHAR(100)", false, tableIdentifier),
                Attribute("manager", "VARCHAR(100)", true, tableIdentifier),
                Attribute("title", "VARCHAR(100)", true, tableIdentifier),
                Attribute("city", "VARCHAR(100)", false, tableIdentifier),
                Attribute("country", "VARCHAR(100)", false, tableIdentifier),
                Attribute("mayor", "VARCHAR(100)", false, tableIdentifier),
            )
        val primaryKey = listOf("ssn", "phone")
        val db = Table(tableIdentifier, attributes, primaryKey)
        assertEquals(tableIdentifier, db.identifier)
        assertEquals(attributes, db.attributes)
        assertEquals(primaryKey, db.primaryKey)
    }

    @Test
    fun testToSql() {
        val schema = "ssqlt_test"
        val name = "_person"
        val tableIdentifier = TableIdentifier(schema, name)
        val attributes =
            listOf(
                Attribute("ssn", "VARCHAR (100)", false, tableIdentifier),
                Attribute("phone", "VARCHAR (100)", false, tableIdentifier),
                Attribute("manager", "VARCHAR (100)", true, tableIdentifier),
                Attribute("title", "VARCHAR (100)", true, tableIdentifier),
                Attribute("city", "VARCHAR (100)", false, tableIdentifier),
                Attribute("country", "VARCHAR (100)", false, tableIdentifier),
                Attribute("mayor", "VARCHAR (100)", false, tableIdentifier),
            )
        val primaryKey = listOf("ssn", "phone")
        val db = Table(tableIdentifier, attributes, primaryKey)
        assertEquals(sqlPersonCreate, db.toCreateTableSql())
    }

    @Test
    fun testFromString() {
        val table = Table.fromString(sqlPersonCreate)
        val tableIdentifier = TableIdentifier("ssqlt_test", "_person")
        assertEquals(tableIdentifier, table.identifier)
        assertEquals(
            listOf(
                Attribute("ssn", "VARCHAR (100)", false, tableIdentifier),
                Attribute("phone", "VARCHAR (100)", false, tableIdentifier),
                Attribute("manager", "VARCHAR (100)", true, tableIdentifier),
                Attribute("title", "VARCHAR (100)", true, tableIdentifier),
                Attribute("city", "VARCHAR (100)", false, tableIdentifier),
                Attribute("country", "VARCHAR (100)", false, tableIdentifier),
                Attribute("mayor", "VARCHAR (100)", false, tableIdentifier),
            ),
            table.attributes)
        assertEquals(listOf("ssn", "phone"), table.primaryKey)
        assertEquals(sqlPersonCreate, table.toCreateTableSql())
    }
}
