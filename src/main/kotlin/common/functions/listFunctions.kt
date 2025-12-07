package main.common.functions

fun diagonal(lines: List<String>): List<String> {
    val width = lines[0].length
    val height = lines.size
    return (height * -1 until height).map { y ->
        (0 until width).map<Int, Any> { step ->
            if (y + step !in 0 until height || step !in 0 until width) " " else lines[y + step][step]
        }.joinToString("").trim()
    }
}

fun vertical(lines: List<String>, lineSeparator: String=""): List<String> {
   return lines[0].indices.map { x -> lines.indices.map { y -> lines[y][x] }.joinToString(lineSeparator) }
}

fun <T> List<T>.split(predicate: (T) -> Boolean): List<List<T>> {
    val idx = this.indexOfFirst(predicate)
    return if (idx == -1) {
        listOf(this)
    } else {
        return listOf(this.take(idx)) + this.drop(idx + 1).split(predicate)
    }
}