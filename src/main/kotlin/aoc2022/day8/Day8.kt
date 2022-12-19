package main.aoc2022.day8

import java.io.File

fun part1(input: File) : Int {
    val readLines = input.readLines()
    val matrix: Array<IntArray> = Array(readLines.size) { IntArray(readLines.first().length) }

    readLines.forEachIndexed { oi, s ->
        s.forEachIndexed {ii,c ->
            matrix[oi][ii] = c.digitToInt() }
    }
    var count = 0;
    readLines.forEachIndexed { oi, s ->
        s.forEachIndexed {ii,c ->
            if (oi == 0 || ii == 0 || oi == readLines.size-1 || ii == s.length-1)
                count++
            else if (canBeSeen(matrix,oi,ii))
                count++
             }
    }

    return count
}

fun part2(input: File) : Int {
    val readLines = input.readLines()
    val matrix: Array<IntArray> = Array(readLines.size) { IntArray(readLines.first().length) }

    readLines.forEachIndexed { oi, s ->
        s.forEachIndexed {ii,c ->
            matrix[oi][ii] = c.digitToInt() }
    }
    var max = 0
    readLines.forEachIndexed { oi, s ->
        s.forEachIndexed {ii,c ->

             val temp = canSeeFrom(matrix,oi,ii)
            if(temp > max)
                max = temp;
        }
    }

    return max
}

fun canSeeFrom(matrix: Array<IntArray>, row: Int, column: Int): Int {
    val rowLen = matrix.size -1
    val colLen = matrix[0].size -1
    val height = matrix[row][column]
var up = 0
     for (i in row-1 downTo  0) {
        if(matrix[i][column] < height) {
            up++
        } else {
            up++
            break
        }
    }

    var down = 0
    for (i in row+1 ..  rowLen) {
        if(matrix[i][column] < height) {
            down++
        } else {
            down++
            break
        }
    }

    var left = 0
    for (i in column-1 downTo 0){
        if (matrix[row][i] < height)
            left++
        else {
            left++
            break
        }
    }

    var right = 0
    for (i in column +1 .. colLen){
        if (matrix[row][i] < height) {
            right++
        } else {
            right++
            break
        }
    }

    return up*left*right*down
}

fun canBeSeen(matrix: Array<IntArray>, row: Int, column: Int): Boolean {
    val rowLen = matrix.size -1
    val colLen = matrix[0].size -1
    val height = matrix[row][column]
    //from top
    if((0 until row).all { i -> matrix[i][column] < height}) {
        return true
    }
    //from bottom
    if((rowLen downTo  row+1).all { i -> matrix[i][column] < height}) {
        return true
    }
    //from left
    if((0 until column).all { i -> matrix[row][i] < height}) {
        return true
    }
    //from right
    if((colLen downTo column+1).all { i -> matrix[row][i] < height}){
        return true
    }
    return false
    }
