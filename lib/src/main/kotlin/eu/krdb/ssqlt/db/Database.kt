package eu.krdb.ssqlt.db

import java.sql.*
import java.util.Properties
import java.util.logging.Logger

class Database(url: String, properties: Properties) {

    private var url: String
    private var props: Properties
    private var conn: Connection

    constructor(
            dbms: String,
            server: String,
            port: String,
            db: String,
            properties: Properties = Properties()
    ) : this("jdbc:$dbms://$server:$port/$db", properties)

    init {
        this.url = url
        this.props = properties
        try {
            this.conn = DriverManager.getConnection(url, properties)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            throw e
        }
    }

    fun query(qry: String): ResultSet {
        var stmt = this.conn.createStatement()
        var rs = try {
            stmt.executeQuery(qry)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        };
        return rs

    }

    fun close() {
        this.conn.close()
    }
}
