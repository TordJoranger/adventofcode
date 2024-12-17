package main.common.functions


fun <T> printMatrix(matrix: List<MutableList<Char>>) {
    for (row in matrix) {
        println(row.joinToString(" "))
    }
}