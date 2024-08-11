package eu.krdb.ssqlt.db

import net.sf.jsqlparser.parser.CCJSqlParserUtil
import net.sf.jsqlparser.statement.create.table.CreateTable
import java.nio.file.Path
import java.nio.file.Files
import java.nio.charset.StandardCharsets
import java.io.Reader

class Table(
    val schema: String,
    val name: String,
    val attributes: List<Attribute>,
    val primaryKey: List<String>
) {

    companion object {

        fun fromFile(path: Path): Table {
            return this.fromReader(Files.newBufferedReader(path, StandardCharsets.UTF_8))
        }

        fun fromReader(reader: Reader): Table {
            return this.fromString(reader.toString())
        }

        fun fromString(sql: String): Table {
            val parsed = CCJSqlParserUtil.parse(sql)
            parsed?.let {
                if (it is CreateTable) {
                    val schema = it.table.schemaName
                    val name = it.table.name
                    val attributes = it.columnDefinitions.map {
                        Attribute(it.columnName, it.colDataType.dataType, it.columnSpecs.isNullOrEmpty())
                    }
                    val primaryKey = it.indexes.find { it.type == "PRIMARY KEY" }?.columns?.map { it.columnName } ?: emptyList()
                    return Table(schema, name, attributes, primaryKey)
                }
                else {
                    throw Exception("Not a Create Table statement")
                }
            }
            throw Exception("Invalid SQL")
        }
    }

    fun toCreateTableSql(): String {
        val builder = StringBuilder()
        builder.append("CREATE TABLE $schema.$name(")
        builder.append(attributes.joinToString(",") { it.toSql() })
        if (primaryKey.isNotEmpty()) {
            builder.append(",PRIMARY KEY (${primaryKey.joinToString(",")})")
        }
        builder.append(")")
        return builder.toString()
    }

}
