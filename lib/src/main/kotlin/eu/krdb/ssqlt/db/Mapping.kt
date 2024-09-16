package eu.krdb.ssqlt.db

class Mapping(
    val schema: String,
    val sourceTable: String,
    val targetTable: String,
    val attributes: List<Attribute>,
    val nullAttributes: Map<Attribute, Boolean>
) {
    fun toSql(includeSchema: Boolean = true, customSourceTable: String? = null): String {

        var strSourceTable = customSourceTable ?: sourceTable
        if (includeSchema) {
            strSourceTable = "$schema.$strSourceTable"
        }

        // SELECT
        var sqlString =
            "SELECT ${attributes.map { a -> strSourceTable + "." + a.name }.joinToString( ", ")}"

        // FROM
        sqlString += " FROM ${strSourceTable}"

        // WHERE
        if (!nullAttributes.isEmpty()) {
            sqlString += " WHERE"
            var andString = " "
            nullAttributes.forEach { (key, value) ->
                sqlString += andString
                if (!value) {
                    sqlString += "${strSourceTable}.${key.name} IS NOT NULL"
                } else {
                    sqlString += "${strSourceTable}.${key.name} IS NULL"
                }
                andString = " AND "
            }
        }

        return sqlString
    }
}
