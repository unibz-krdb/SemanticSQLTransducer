package eu.krdb.ssqlt.db

import eu.krdb.ssqlt.TestingUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DatabaseTests {

    private val sqlPersonCreate = TestingUtils.getResourceFileString("person.sql")

    @Test
    fun testConstructor() {
        val db = Database("postgresql", "localhost", "5432", "ssqlt_test")
        assert(!db.isClosed)
        db.close()
        assert(db.isClosed)
    }
}
