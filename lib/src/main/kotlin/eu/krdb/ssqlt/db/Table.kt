package eu.krdb.ssqlt.db

import net.sf.jsqlparser.parser.CCJSqlParserUtil
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
            val res = CCJSqlParserUtil.parse(sql)
            throw NotImplementedError()
        }
    }

    fun toCreateTableSql(): String {
        val builder = StringBuilder()
        builder.append("CREATE TABLE $schema.$name (\n")
        builder.append(attributes.joinToString(",\n") { it.toSql() })
        if (primaryKey.isNotEmpty()) {
            builder.append(",\nPRIMARY KEY (${primaryKey.joinToString(", ")})")
        }
        builder.append("\n)")
        return builder.toString().trimIndent()
    }

}
