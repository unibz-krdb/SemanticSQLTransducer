package eu.krdb.ssqlt.db

enum class ConstraintType {
    FUNCTIONAL_DEPENDENCY,
    MULTIVALUED_DEPENDENCY,
    INCLUSION_DEPENDENCY
}

class Constraint(
    val schemaName: String,
    val tableName: String,
    val constraintType: ConstraintType,
    val lhs: List<Attribute>,
    val rhs: List<Attribute>
) {}
