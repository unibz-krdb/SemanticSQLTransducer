package eu.krdb.ssqlt.db

class TableIdentifier(val schemaName: String, val tableName: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        other as TableIdentifier

        if (schemaName != other.schemaName) return false
        if (tableName != other.tableName) return false

        return true
    }
}
