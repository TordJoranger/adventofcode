import common.getTestInput
import com.squareup.kotlinpoet.*


suspend fun main() {
    val day = 6

    val testClass = ClassName("src/test/kotlin/aoc2024", "Day$day")
    val testAnnotation = AnnotationSpec.builder(ClassName("org.junit", "Test")).build()


    val file = FileSpec.builder("", "Day$day")
        .addType(
            TypeSpec.classBuilder("Day$day+Test")
                .addFunction(
                    FunSpec.builder("greet")
                        .addStatement("println(%P)", "Hello, \$name")
                        .build()
                )
                .build()
        )
        .addFunction(
            FunSpec.builder("main")
                .addParameter("args", String::class, KModifier.VARARG)
                .addStatement("%T(args[0]).greet()", testClass)
                .build()
        )
        .build()

    file.writeTo(System.out)
    val packageName = "com.example.generatedtests"

    // The name of the test class
    val className = "MyGeneratedTest"


    val testInput = getTestInput(day)




}