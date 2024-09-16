package eu.krdb.ssqlt.db

import java.util.Dictionary

class Mapping(
    val schema: String,
    val sourceTable: String,
    val targetTable: String,
    val attributes: List<Attribute>,
    val nullAttributes: Dictionary<Attribute, Boolean>
) {
    fun toSql(
        includeSchema: Boolean = true,
        customSourceTable: String? = null
    ): String {

        var strSourceTable = customSourceTable ?: sourceTable
        if (includeSchema) {
            strSourceTable = "$schema.$strSourceTable"
        }

        // SELECT
        var sqlString = "SELECT ${attributes.map { a -> strSourceTable + "." + a.name }.joinToString( ", ")}"

        // FROM
        sqlString += " FROM ${strSourceTable}"

        // WHERE
        if (nullAttributes.isEmpty()) {
            sqlString += " WHERE"
            var andString = " "
            for (key in nullAttributes.keys()) {
                sqlString += andString
                if (!nullAttributes[key]) {
                    sqlString += "${strSourceTable}.${key.name} IS NOT NULL"
                } else {
                    sqlString += "${strSourceTable}.${key.name} IS NULL"
                }
                andString = " AND "
            }
        };

        return sqlString
    }
}
