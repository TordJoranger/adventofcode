package main.aoc2024.day15

import main.common.functions.printMatrix
import java.io.File

fun part1(input: File) : Long = lanternFish(input.readLines(),false)
var pos = Pair(2,2)

fun lanternFish(readLines: List<String>, part2:Boolean): Long {
    val matrix = readLines.takeWhile { it != "" }
        .map { it.toMutableList() }
        .toMutableList()

    if(part2){
        matrix.forEachIndexed {index, line ->
           val nl = line.flatMap { c ->
               if (c == '@'){
                   mutableListOf(c,'.')
               }
                else if (c != 'O')
                    mutableListOf(c, c)
                else
                    mutableListOf('[', ']')
            }
            matrix[index] = nl.toMutableList()
        }
    }

    val instructions = readLines.takeLastWhile { it != "" }.fold("") { acc, s -> acc+s }

     matrix.forEachIndexed{ i, l ->
        val found = l.indexOf('@')
        if(found!= -1) {
            pos = Pair(i,found)
            return@forEachIndexed
        }
    }

    instructions.forEach { ins->
        if(part2){
            traverse2(ins,matrix)
        } else {
            traverse(ins,matrix)
        }
    }
    printMatrix<Char>(matrix)
  val sum =  matrix.withIndex().sumOf { list ->
        val i = list.index
        list.value.withIndex().sumOf {
            if(it.value == 'O' || it.value == '[')
                100L* i +it.index
            else
                0L
        }
    }

return sum
}


fun traverse(ins: Char, matrix: List<MutableList<Char>>) {
    when(ins){
        '>'-> {
            val horizontal = matrix[pos.first].takeLast(matrix[0].size - pos.second-1)
            if(!horizontal.any { it == '.' })
                return

            val firstWall = horizontal.indexOf('#')
            val firstEmpty = horizontal.indexOf('.')

            if(firstWall < firstEmpty)
                return

            val first = horizontal.first()
            if(first == 'O' ){
                matrix[pos.first][pos.second+firstEmpty+1] = 'O'
            }
            matrix[pos.first][pos.second] = '.'
            matrix[pos.first][pos.second+1] = '@'
            pos = Pair(pos.first,pos.second+1)
        }
        '<' ->{
            val horizontal = matrix[pos.first].take( pos.second)
            if(!horizontal.any { it == '.' })
                return

            val firstWall = horizontal.lastIndexOf('#')
            val firstEmpty = horizontal.lastIndexOf('.')

            if(firstWall > firstEmpty)
                return

            val first = horizontal.last()
            if(first == 'O' ){
                for (i in firstEmpty until pos.second){
                    matrix[pos.first][i] = matrix[pos.first][i+1]
                }
            }
           // matrix[pos.first][pos.second-1] = '@'
            matrix[pos.first][pos.second] = '.'
            pos = Pair(pos.first,pos.second-1)
        }
        '^' ->{
            val vertical = matrix.indices.map { y -> matrix[y][pos.second] }.joinToString("").take(pos.first)
            if(!vertical.any{it == '.'})
                return

            val firstWall = vertical.lastIndexOf('#')
            val firstEmpty = vertical.lastIndexOf('.')

            if(firstWall > firstEmpty)
                return

            val first = vertical.last()
            if(first == 'O' ){
                matrix[firstEmpty][pos.second] = 'O'
            }
            matrix[pos.first-1][pos.second] = '@'
            matrix[pos.first][pos.second] = '.'
            pos = Pair(pos.first-1, pos.second)
        }
        'v'->{
            val vertical = matrix.indices.map { y -> matrix[y][pos.second] }.joinToString("").takeLast(matrix.size - pos.first-1)
            if(!vertical.any{it == '.'})
                return

            val firstWall = vertical.indexOf('#')
            val firstEmpty = vertical.indexOf('.')

            if(firstWall < firstEmpty)
                return

            val first = vertical.first()
            if(first == 'O' ){
                matrix[pos.first+firstEmpty+1][pos.second] = 'O'
            }
            matrix[pos.first+1][pos.second] = '@'
            matrix[pos.first][pos.second] = '.'
            pos = Pair(pos.first+1, pos.second)
        }

    }
}

fun part2(input: File) : Long = lanternFish(input.readLines(),true)

fun traverse2(ins: Char, matrix: List<MutableList<Char>>) {
    when(ins){
        '>'-> {
            val horizontal = matrix[pos.first].takeLast(matrix[0].size - pos.second-1)
            if(!horizontal.any { it == '.' })
                return

            val firstWall = horizontal.indexOf('#')
            val firstEmpty = horizontal.indexOf('.')

            if(firstWall < firstEmpty)
                return

            val first = horizontal.first()
            if(first == '[' ){
                for (i in pos.second+firstEmpty+1 downTo  pos.second+1){
                    matrix[pos.first][i] = matrix[pos.first][i-1]
                }
            }
            matrix[pos.first][pos.second] = '.'
            matrix[pos.first][pos.second+1] = '@'
            pos = Pair(pos.first,pos.second+1)

        }
        '<' ->{
            val horizontal = matrix[pos.first].take(pos.second)
            if(!horizontal.any { it == '.' })
                return

            val firstWall = horizontal.lastIndexOf('#')
            val firstEmpty = horizontal.lastIndexOf('.')

            if(firstWall > firstEmpty)
                return

            val first = horizontal.last()
            if(first == ']' ){
               for (i in firstEmpty until pos.second){
                   matrix[pos.first][i] = matrix[pos.first][i+1]
               }
            }
            matrix[pos.first][pos.second] = '.'
            matrix[pos.first][pos.second-1] = '@'
            pos = Pair(pos.first,pos.second-1)
        }
        '^' ->{
            val nextPos = Pair(pos.first-1,pos.second)
            val nextChar = matrix[pos.first-1][pos.second]
            if(nextChar == '.'){
                matrix[pos.first][pos.second] = '.'
                matrix[pos.first-1][pos.second] = '@'
                pos = Pair(pos.first-1,pos.second)
            }else if(nextChar == '#') {
                return
            }
            else {
                val tryPushUp = tryPushUp(nextPos, matrix)
                if(tryPushUp.first){
                    tryPushUp.second.distinct().forEach { p->
                        val tmp = matrix[p.first-1][p.second]
                        matrix[p.first-1][p.second] = matrix[p.first][p.second]
                        matrix[p.first][p.second] = tmp
                    }
                    matrix[pos.first][pos.second] = '.'
                    matrix[pos.first-1][pos.second] = '@'
                    pos = nextPos
                }
            }
        }
        'v'->{
            val nextPos = Pair(pos.first+1,pos.second)
            val nextChar = matrix[pos.first+1][pos.second]
            if(nextChar == '.'){
                matrix[pos.first][pos.second] = '.'
                matrix[pos.first+1][pos.second] = '@'
                pos = nextPos
            }else if(nextChar == '#') {
                return
            }
            else {
                val tryPushDown = tryPushDown(nextPos, matrix)
                if(tryPushDown.first){
                    tryPushDown.second.distinct().forEach { p->
                        val tmp = matrix[p.first+1][p.second]
                        matrix[p.first+1][p.second] = matrix[p.first][p.second]
                        matrix[p.first][p.second] = tmp
                    }
                    matrix[pos.first][pos.second] = '.'
                    matrix[pos.first+1][pos.second] = '@'
                    pos = nextPos
                }
            }
        }
    }
}

fun tryPushUp(thisPos: Pair<Int, Int>, matrix: List<MutableList<Char>>) : Pair<Boolean,List<Pair<Int,Int>>> {
val c = matrix[thisPos.first][thisPos.second]
    if (c == '.') {
        return Pair(true, emptyList())
    }
    if (c == '#') {
        return Pair(false, emptyList())
    }
    val up = tryPushUp(Pair(thisPos.first-1,thisPos.second),matrix)
    if(!up.first)
        return Pair(false, emptyList())

    when (c) {
        '[' -> {
            val  upRight =tryPushUp(Pair(thisPos.first-1,thisPos.second+1),matrix)
            return if(upRight.first) {
                Pair(true,up.second.plus(upRight.second).plus(thisPos).plus(Pair(thisPos.first,thisPos.second+1)))
            }else {
                Pair(false, emptyList())
            }

        }
        ']' -> {
            val  upLeft = tryPushUp(Pair(thisPos.first-1,thisPos.second-1),matrix)
            return if(upLeft.first) {
                Pair(true,up.second.plus(upLeft.second).plus(thisPos).plus(Pair(thisPos.first,thisPos.second-1)))
            }else {
                Pair(false, emptyList())
            }
        }
        else -> throw Exception("wtf")
    }

}

fun tryPushDown(thisPos: Pair<Int, Int>, matrix: List<MutableList<Char>>) : Pair<Boolean,List<Pair<Int,Int>>> {
        val c = matrix[thisPos.first][thisPos.second]
        if (c == '.') {
            return Pair(true, emptyList())
        }
        if (c == '#') {
            return Pair(false, emptyList())
        }

    val down = tryPushDown(Pair(thisPos.first+1,thisPos.second),matrix)

    if(!down.first){
        return Pair(false, emptyList())
    }
    when (c) {
        '[' -> {
            val  downright =tryPushDown(Pair(thisPos.first+1,thisPos.second+1),matrix)
            return if(downright.first) {
                Pair(true,down.second.plus(downright.second).plus(thisPos).plus(Pair(thisPos.first,thisPos.second+1)))
            }else {
                Pair(false, emptyList())
            }
        }
        ']' -> {
            val  downLeft =tryPushDown(Pair(thisPos.first+1,thisPos.second-1),matrix)
            return if(downLeft.first) {
                Pair(true,down.second.plus(downLeft.second).plus(thisPos).plus(Pair(thisPos.first,thisPos.second-1)))
            }else {
                Pair(false, emptyList())
            }
        }
        else -> throw Exception("wtf")
    }
}
