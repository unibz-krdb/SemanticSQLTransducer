package eu.krdb.ssqlt.db

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DatabaseTests {

    private val sqlPersonCreate = object {}.javaClass.getResourceAsStream("person.sql")?.bufferedReader()?.readLines()

    @Test
    fun testConstructor() {
        var db = Database("postgresql", "localhost", "5432", "ssqlt_test")
        assert(!db.isClosed)
        db.close()
        assert(db.isClosed)
    }

}
