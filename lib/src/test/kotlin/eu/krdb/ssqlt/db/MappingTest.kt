package eu.krdb.ssqlt.db

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MappingTest {

    @Test
    fun testConstructor() {
        val schema = "transducer"
        val sourceTable = "person"
        val targetTable = "person"
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
        val nullAttributes =
            mapOf(
                attributes[0] to false,
                attributes[1] to false,
                attributes[2] to true,
                attributes[3] to true,
                attributes[4] to false,
                attributes[5] to false,
                attributes[6] to false,
            )
        val mapping = Mapping(schema, sourceTable, targetTable, attributes, nullAttributes)
        assertEquals(schema, mapping.schema)
        assertEquals(sourceTable, mapping.sourceTable)
        assertEquals(targetTable, mapping.targetTable)
        assertEquals(attributes, mapping.attributes)
        assertEquals(nullAttributes, mapping.nullAttributes)
    }

    @Test
    fun testToSql() {
        val schema = "ssqlt_test"
        val sourceTable = "_person"
        val targetTable = "_person"
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
        val nullAttributes =
            mapOf(
                attributes[0] to false,
                attributes[1] to false,
                attributes[2] to true,
                attributes[3] to true,
                attributes[4] to false,
                attributes[5] to false,
                attributes[6] to false,
            )
        val mapping = Mapping(schema, sourceTable, targetTable, attributes, nullAttributes)
        val sqlString = mapping.toSql(includeSchema = false)
        val expectedSqlString =
            "SELECT _person.ssn, _person.phone, _person.manager, _person.title, _person.city, _person.country, _person.mayor FROM _person WHERE _person.ssn IS NOT NULL AND _person.phone IS NOT NULL AND _person.manager IS NULL AND _person.title IS NULL AND _person.city IS NOT NULL AND _person.country IS NOT NULL AND _person.mayor IS NOT NULL"
        assertEquals(expectedSqlString, sqlString)
    }
}
