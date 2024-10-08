package eu.krdb.ssqlt.db

import java.io.Reader
import java.nio.file.Files
import java.nio.file.Path
import net.sf.jsqlparser.parser.CCJSqlParserUtil
import net.sf.jsqlparser.statement.create.table.CreateTable

class Table(
    val schemaName: String,
    val tableName: String,
    val attributes: List<Attribute>,
    val primaryKey: List<String>,
) {

    companion object {

        fun fromFile(path: Path): Table {
            return this.fromReader(Files.newBufferedReader(path))
        }

        fun fromReader(reader: Reader): Table {
            return this.fromString(reader.toString())
        }

        fun fromString(sql: String): Table {
            val parsed = CCJSqlParserUtil.parse(sql)
            parsed?.let {
                if (it is CreateTable) {
                    val schemaName = it.table.schemaName
                    val tableName = it.table.name
                    val attributes =
                        it.columnDefinitions.map {
                            Attribute(
                                it.columnName,
                                it.colDataType.dataType,
                                it.columnSpecs.isNullOrEmpty(),
                            )
                        }
                    val primaryKey =
                        it.indexes.find { it.type == "PRIMARY KEY" }?.columns?.map { it.columnName }
                            ?: emptyList()
                    return Table(schemaName, tableName, attributes, primaryKey)
                } else {
                    throw Exception("Not a Create Table statement")
                }
            }
            throw Exception("Invalid SQL")
        }
    }

    fun toCreateTableSql(): String {
        val builder = StringBuilder()
        val schema = this.schemaName
        val name = this.tableName
        builder.append("CREATE TABLE $schema.$name(")
        builder.append(attributes.joinToString(",") { it.toSql() })
        if (primaryKey.isNotEmpty()) {
            builder.append(",PRIMARY KEY (${primaryKey.joinToString(",")})")
        }
        builder.append(")")
        return builder.toString()
    }
}
