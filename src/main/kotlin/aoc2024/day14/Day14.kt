package main.aoc2024.day14

import java.io.File

const val height = 103
const val length = 101
const val middleH = (height-1)/2
const val middleL = (length-1)/2

fun part1(input: File) : Long = findSafetyFactor(input.readLines())
fun part2(input: File) : Long = christmasTree(input.readLines())


fun findSafetyFactor(readLines: List<String>): Long {
    val positions =  readLines.map {
       findPosition(it, 100)
   }.filter { it.first != middleL && it.second != middleH }

   val grouped = positions.groupBy {
        if(it.first <  middleL && it.second < middleH)
            "1"
        else if(it.first > middleL && it.second < middleH){
            "2"
        } else if(it.first < middleL && it.second > middleH){
            "3"
        }else if(it.first > middleL && it.second > middleH){
            "4"
        }
        else
            ""
    }

    val sum = grouped.values.fold(1L) { acc, c ->
        acc * c.size
    }
    return sum
}

fun findPosition(string: String, seconds: Int): Pair<Int,Int>{
    val regex = "-?\\d+".toRegex()
    val decimals =regex.findAll(string).iterator()
    val x = decimals.next().value.toInt()
    val y = decimals.next().value.toInt()
    val v1 = decimals.next().value.toInt()
    val v2 = decimals.next().value.toInt()
    var nxx =(x+(v1*seconds)) % length
    var nyy =(y+(v2*seconds)) % height

    if(nxx < 0){
        nxx += length
    }
    if(nyy < 0)
        nyy += height

    return Pair(nxx,nyy)
}


fun christmasTree(readLines: List<String>): Long {

    var highestY = 0

    for (i in 0..10000 step 1) {
        val positions = readLines.map {
            findPosition(it, i)
        }


        val grouped = positions.filter { it.first != middleL && it.second != middleH }
            .groupBy {
                it.second
        }

        val maxY = grouped.values.maxOf { it.size }
        if(maxY > 25) {
            printCoordinatesAsMatrix(positions,i)
        }

    }
    return 0
}
fun printCoordinatesAsMatrix(coords: List<Pair<Int, Int>>, seconds: Int) {
    if (coords.isEmpty()) {
        println("No coordinates to display.")
        return
    }

    // Determine the bounding box of the coordinates
    val maxX = coords.maxOf { it.first }
    val maxY = coords.maxOf { it.second }

    // Create a grid large enough to hold all coordinates
    // Rows (height) = maxY + 1, columns (width) = maxX + 1
    val grid = Array(height) { Array(length) { ' ' } }

    // Place a dot at each coordinate
    for ((x, y) in coords) {
        grid[y][x] = '.'
    }

    // Print the grid
    for (row in grid) {
        println(row.joinToString(""))
    }
    println(seconds)
}


