package eu.krdb.ssqlt.db

class Attribute(
    val name: String,
    val type: String,
    val nullable: Boolean,
) {
    fun toSql(): String {
        return "$name $type${if (nullable) "" else " NOT NULL"}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        other as Attribute

        if (name != other.name) return false
        if (type != other.type) return false
        if (nullable != other.nullable) return false

        return true
    }
}
