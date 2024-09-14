package eu.krdb.ssqlt.transducer

import eu.krdb.ssqlt.db.Schema
import eu.krdb.ssqlt.db.Table

class Generator(val context: Context) {

    companion object {

        private fun generateInsertDeleteTables(table: Table): List<Table> {
            return listOf(
                Table(
                    table.schemaName,
                    table.tableName + "_INSERT",
                    table.attributes,
                    table.primaryKey
                ),
                Table(
                    table.schemaName,
                    table.tableName + "_DELETE",
                    table.attributes,
                    table.primaryKey
                )
            )
        }

        private fun generateInsertDeleteTables(schema: Schema): List<Table> {
            val tables = mutableListOf<Table>()
            schema.tables.forEach { table -> tables.addAll(generateInsertDeleteTables(table)) }
            return tables
        }

    }

}
