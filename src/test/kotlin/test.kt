import common.getTestInput
import com.squareup.kotlinpoet.*


suspend fun main() {
    val day = 6

    val testClass = ClassName("src/test/kotlin/aoc2024", "Day$day")
    val testAnnotation = AnnotationSpec.builder(ClassName("org.junit", "Test")).build()

    val packageName = "com.example.generatedtests"

    // The name of the test class
    val className = "MyGeneratedTest"


    val testInput = getTestInput(day)




}