package aoc2021.day4

import java.io.File

fun day4() = winBingo(File("src/aoc2021/day4/day4.txt").readLines())

fun winBingo(readLines: List<String>): Long {

    val input = readLines[0].split(",").map { s -> s.toInt() }

    val boards = readLines.takeLast(readLines.size-1)
        .filter { s-> s != "" }.chunked(5)
        .map { b ->  b
            .map { i -> i.split(" ").filter { s -> s != "" }
            .map { s-> s.trim().toInt()} }}

      return playBingoP2(boards,input)

}

fun playBingoP2(boards: List<List<List<Int?>>>, input: List<Int?>): Long {

    val numb = input[0]
    val updatedBoard = playNumber(boards,numb)

    val bingo = checkBingo(updatedBoard)
    var result = 0L;
    if(bingo.second.isEmpty())
        result = bingo.first

    return if(result > 0) {
        result * numb!!
    } else
        playBingoP2(bingo.second, input.takeLast(input.size-1))

}

fun playBingo(boards: List<List<List<Int?>>>, input: List<Int?>): Long {

    val numb = input[0]
    val updatedBoard = playNumber(boards,numb)

    val result = checkBingo(updatedBoard).first
    return if(result > 0){
        result * numb!!
    }else{
        playBingo(updatedBoard, input.takeLast(input.size-1))
    }

}

fun checkBingo(updatedBoard: List<List<List<Int?>>>): Pair<Long, List<List<List<Int?>>>> {
    val returnList = updatedBoard.toMutableList()
    var result = 0L;
    updatedBoard.forEachIndexed {index, board ->
        var columnBingo = true

        for ((ii, x) in board.withIndex()) {
            if(x.all { y -> y == null }){
                if(returnList.size > index)
                returnList.removeAt(index)
                result = board.sumBy { b -> b.sumBy { v-> v ?: 0 } }.toLong()
            }
            if(board[ii][ii] != null)
               columnBingo = false
        }
        if(columnBingo){
            if(returnList.size > index)
            returnList.removeAt(index)
            result = board.sumBy { b -> b.sumBy { v-> v ?: 0 } }.toLong()
        }
    }

    return Pair(result, returnList)
}

fun playNumber(boards: List<List<List<Int?>>>, numb: Int?): List<List<List<Int?>>> {
    return boards.map { board -> board.map{row ->
        row.map{n ->
            if(n == numb){
                null
            }else{
                n
            }
        }
    } }
}





