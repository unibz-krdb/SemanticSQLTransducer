package eu.krdb.ssqlt.db

class Attribute(
    val name: String,
    val type: String,
    val nullable: Boolean,
) {
    fun toSql(): String {
        return "$name $type${if (nullable) "" else " NOT NULL"}"
    }
}
