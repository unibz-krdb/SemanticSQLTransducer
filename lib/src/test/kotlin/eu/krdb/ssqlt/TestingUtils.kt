package eu.krdb.ssqlt

class TestingUtils {
    companion object {
        fun getResourceFileString(filename: String): String {
            return object {}
                .javaClass
                .getResourceAsStream(filename)
                ?.bufferedReader()
                ?.readLines()
                ?.joinToString("")
                ?: throw Exception("File not found: $filename")
        }
    }
}