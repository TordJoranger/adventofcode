import common.getTestInput
import com.squareup.kotlinpoet.*
import java.nio.file.Paths


suspend fun main() {
    val day = 10
    val year = 2024

    val testClass = ClassName("aoc2024.day$day", "Day$day")
    val testAnnotation = AnnotationSpec.builder(ClassName("org.junit", "Test")).build()

    val testInput = getTestInput(day,"src/test/kotlin/aoc$year/day$day/test$day.txt")

    val file = FileSpec.builder("", "Day$day")
        .addType(
            TypeSpec.classBuilder("Day$day"+"Test")
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
                //.addStatement("%T(args[0]).greet()", testClass)
                .build()
        )
        .build()

    file.writeTo(System.out)
    val outputDir = Paths.get("src", "test", "kotlin","aoc$year","day$day")
    file.writeTo(outputDir.toFile())






}