package eu.krdb.ssqlt

class TestingUtils {
    companion object {
        fun getResourceFileString(filename: String): String {
            return object {}.javaClass.classLoader.getResource(filename).readText()
        }
    }
}