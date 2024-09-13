package eu.krdb.ssqlt.transducer

import eu.krdb.ssqlt.db.Mapping
import eu.krdb.ssqlt.db.Schema

class Context(val source: Schema, val target: Schema, val mapping: Mapping) {}
