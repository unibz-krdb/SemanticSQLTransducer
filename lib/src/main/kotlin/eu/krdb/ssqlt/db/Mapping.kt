package eu.krdb.ssqlt.db

import java.util.Dictionary

class Mapping(
    val schema: String,
    val sourceTable: String,
    val targetTable: String,
    val attributes: List<Attribute>
) {}
