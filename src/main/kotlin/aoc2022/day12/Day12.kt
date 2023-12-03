package main.aoc2022.day12
import java.io.File

var minVal = Int.MAX_VALUE
var endPos = Pair(0,0)

fun part1(input : File) :Int {
    val readLines  = input.readLines()
    val matrix: Array<Array<Pair<Int,Int>>> = Array(readLines.size) { Array(readLines.first().length, init = {Pair(0,Int.MAX_VALUE)}) }
    var startPos = Pair(0,0)
    readLines.forEachIndexed { oi, s ->
        s.forEachIndexed {ii,c ->
            matrix[oi][ii] = Pair(c.code, Int.MAX_VALUE)
            if(c == 'E') {
                endPos = Pair(oi,ii)
                matrix[oi][ii] = Pair('z'.code, Int.MAX_VALUE)
            }
            if(c.code == 83) {
                startPos = Pair(oi,ii)
                matrix[oi][ii] = Pair('a'.code, Int.MAX_VALUE)
            }
        }
    }

    return traverse(matrix, startPos, listOf(startPos)).first -1
}

fun traverse(matrix: Array<Array<Pair<Int, Int>>>, pos: Pair<Int, Int>, path:List<Pair<Int,Int>>):Pair<Int,Boolean> {
    if(pos == endPos) {
        if(path.size < minVal)
            minVal = path.size
        return Pair(path.size, true)
    }

    if(path.size >= minVal){
        return Pair(path.size,false)
    }

    var sizePath = Pair(Int.MAX_VALUE,false)

    if(canMoveDown(matrix,pos, path)){
        val newPos = Pair(pos.first + 1, pos.second)
        val somePath = traverse(matrix, newPos, path.plus(newPos))
        if(somePath.second)
            sizePath = somePath
    }
    if(path.size >= minVal){
        return sizePath
    }
    if(canMoveUp(matrix,pos,path)){
        val newPos = Pair(pos.first -1, pos.second)
        val newSize = traverse(matrix, newPos,path.plus(newPos))
        if(newSize.first < sizePath.first && newSize.second)
            sizePath = newSize
    }
    if(path.size >= minVal){
        return sizePath
    }
    if(canMoveRight(matrix,pos,path)){
        val newPos = Pair(pos.first, pos.second +1)
        val newSize = traverse(matrix, newPos,path.plus(newPos))
        if(newSize.first < sizePath.first && newSize.second)
            sizePath = newSize
    }
    if(path.size >= minVal){
        return sizePath
    }
    if(canMoveLeft(matrix,pos,path)){
        val newPos = Pair(pos.first, pos.second -1)
        val newSize = traverse(matrix, newPos,path.plus(newPos))
        if(newSize.first < sizePath.first && newSize.second)
            sizePath = newSize
    }

    return sizePath
}

fun canMoveLeft(matrix: Array<Array<Pair<Int, Int>>>, pos: Pair<Int, Int>, path: List<Pair<Int, Int>>): Boolean {
    val newPos = Pair(pos.first, pos.second -1)
    if (newPos.second >= 0 && !path.contains(newPos) && matrix[newPos.first][newPos.second].first-1 <= matrix[pos.first][pos.second].first ){
        return true
    }
    return false
}

fun canMoveRight(matrix: Array<Array<Pair<Int, Int>>>, pos: Pair<Int, Int>, path: List<Pair<Int, Int>>): Boolean {
    val newPos = Pair(pos.first, pos.second +1)
    if (newPos.second < matrix[1].size &&!path.contains(newPos) && matrix[newPos.first][newPos.second].first-1 <= matrix[pos.first][pos.second].first  ){
        return true
    }
    return false
}

fun canMoveUp(matrix: Array<Array<Pair<Int, Int>>>, pos: Pair<Int, Int>, path: List<Pair<Int, Int>>): Boolean {
    val newPos = Pair(pos.first -1, pos.second)
    if (newPos.first >= 0 && !path.contains(newPos) && matrix[newPos.first][newPos.second].first-1 <= matrix[pos.first][pos.second].first  ){
        return true
    }
    return false
}

fun canMoveDown(matrix: Array<Array<Pair<Int, Int>>>, pos: Pair<Int, Int>, path: List<Pair<Int, Int>>): Boolean {
    val newPos = Pair(pos.first +1, pos.second)
    if (newPos.first < matrix.size && !path.contains(newPos) && matrix[newPos.first][newPos.second].first-1 <= matrix[pos.first][pos.second].first  ){
        return true
    }
 return false
}

fun part2(input : File) :Int {


    return 0
}