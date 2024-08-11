package eu.krdb.ssqlt.db

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AttributeTest {

    @Test
    fun testConstructor() {
        val attribute = Attribute("ssn", "VARCHAR(100)", false)
        assertEquals("ssn", attribute.name)
        assertEquals("VARCHAR(100)", attribute.type)
        assertEquals(false, attribute.nullable)
    }
}
