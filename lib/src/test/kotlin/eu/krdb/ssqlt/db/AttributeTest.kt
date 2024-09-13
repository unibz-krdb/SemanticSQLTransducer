package eu.krdb.ssqlt.db

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AttributeTest {

    @Test
    fun testConstructor() {
        val tableIdentifier = TableIdentifier("transducer", "person")
        val attribute = Attribute("ssn", "VARCHAR(100)", false, tableIdentifier)
        assertEquals("ssn", attribute.name)
        assertEquals("VARCHAR(100)", attribute.type)
        assertEquals(false, attribute.nullable)
    }

    @Test
    fun testToSql() {
        val tableIdentifier = TableIdentifier("transducer", "person")
        var attribute = Attribute("ssn", "VARCHAR(100)", false, tableIdentifier)
        assertEquals("ssn VARCHAR(100) NOT NULL", attribute.toSql())
        attribute = Attribute("ssn", "VARCHAR(100)", true, tableIdentifier)
        assertEquals("ssn VARCHAR(100)", attribute.toSql())
    }
}
