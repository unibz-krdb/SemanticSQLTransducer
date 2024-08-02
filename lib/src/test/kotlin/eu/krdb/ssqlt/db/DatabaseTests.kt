package eu.krdb.ssqlt.db

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DatabaseTests {
    @Test fun testConstructor() {
        var db = Database("postgresql", "localhost", "5432", "ssqlt_test")
        db.close()
    }
}
