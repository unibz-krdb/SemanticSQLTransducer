package eu.krdb.ssqlt.transducer

import eu.krdb.ssqlt.db.Schema
import eu.krdb.ssqlt.db.Table

class Generator(val context: Context) {

    companion object {

        private fun generateInsertDeleteTables(schema: Schema): List<Table> {
            val tables = mutableListOf<Table>()
            for (table in schema.tables) {
                tables.add(
                    Table(
                        table.schemaName,
                        table.tableName + "_INSERT",
                        table.attributes,
                        table.primaryKey))
                tables.add(
                    Table(
                        table.schemaName,
                        table.tableName + "_DELETE",
                        table.attributes,
                        table.primaryKey))
            }
            return tables
        }
    }

    fun generateInsertDeleteTables(): List<Table> {
        val tables = listOf<Table>()
        val schemas = listOf(context.source, context.target)
        return tables
    }
}
