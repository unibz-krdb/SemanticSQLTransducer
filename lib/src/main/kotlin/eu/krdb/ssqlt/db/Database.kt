package eu.krdb.ssqlt.db

import java.sql.*
import java.util.Properties
import mu.KotlinLogging

class Database(url: String, properties: Properties) {

    private var url: String
    private var props: Properties
    private var conn: Connection
    private var logger = KotlinLogging.logger {}

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
        this.logger.info { "Connecting to $url..." }
        try {
            this.conn = DriverManager.getConnection(url, properties)
        } catch (e: Exception) {
            this.logger.error { "failed" }
            this.logger.debug { e.stackTraceToString() }
            throw e
        }
        this.logger.debug { "success" }
    }

    /**
     * Execute a query and return the result set.
     *
     * @param qry The query to execute.
     * @param commit Whether to commit the transaction after executing the query.
     * @return The result set.
     */
    fun query(qry: String, commit: Boolean = false): ResultSet {
        this.logger.info { "Executing query..." }
        this.logger.debug { "Executing query: $qry" }
        var stmt = this.conn.createStatement()
        var rs =
            try {
                stmt.executeQuery(qry)
            } catch (e: Exception) {
                this.logger.error { "failed" }
                this.logger.debug { e.stackTraceToString() }
                throw e
            }
        this.logger.info { "success" }
        if (commit) {
            this.commit()
        }
        return rs
    }

    /** Commit the database. */
    fun commit() {
        this.logger.info { "Committing..." }
        try {
            this.conn.commit()
        } catch (e: Exception) {
            this.logger.error { "failed" }
            this.logger.debug { e.stackTraceToString() }
            throw e
        }
        this.logger.info { "success" }
    }

    fun close() {
        this.logger.info { "Closing connection..." }
        try {
            this.conn.close()
        } catch (e: Exception) {
            this.logger.error { "failed" }
            this.logger.debug { e.stackTraceToString() }
            throw e
        }
        this.logger.info { "success" }
    }

    val isClosed: Boolean
        get() = this.conn.isClosed
}
