class Table(
    schema: String,
    name: String,
    attributes: List<String>,
    types: List<String>,
    primaryKey: List<String>
) {

    val schema: String
    val name: String
    val attributes: List<String>
    val types: List<String>
    val primaryKey: List<String>

    init {
        this.schema = schema
        this.name = name
        this.attributes = attributes
        this.types = types
        this.primaryKey = primaryKey
    }
}
