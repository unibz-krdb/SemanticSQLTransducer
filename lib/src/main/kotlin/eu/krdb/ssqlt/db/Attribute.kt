package eu.krdb.ssqlt.db

class Attribute(
    name: String,
    type: String,
    nullable: Boolean,
) {

    val name: String
    val type: String
    val nullable: Boolean

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("Attribute name cannot be blank.")
        }
        this.name = name.replace(" ", "")
        if (type.isBlank()) {
            throw IllegalArgumentException("Attribute type cannot be blank.")
        }
        this.type = type.replace(" ", "")
        this.nullable = nullable
    }

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
