package main.common.functions


fun printMatrix(matrix: List<MutableList<Char>>) {
    for (row in matrix) {
        println(row.joinToString(" "))
    }
}