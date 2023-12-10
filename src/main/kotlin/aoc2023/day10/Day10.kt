package main.aoc2023.day10

import java.io.File
import kotlin.math.abs

fun part1(input: File) : Int = findMaxLoopDistance(input.readLines())

fun findMaxLoopDistance(readLines: List<String>): Int {

    val matrix: Array<Array<Char>> = Array(readLines.size) { outer -> Array(readLines.first().length, init = { i -> readLines[outer].toCharArray()[i]}) }

    val x = matrix.indexOfFirst{ i -> i.any { it == 'S' }}
    val y = matrix[x].indexOf('S')

    var currentNode = findCurrentNode(matrix,(x to y))
    var previousNode = (x to y)
    var count = 1
    do {
        val tempNode = currentNode
        currentNode = getNextNode(currentNode,previousNode,matrix)
        previousNode = tempNode

        count++

    }while (symbolOf(matrix, currentNode) != 'S')

    return count/2
}

fun findCurrentNode(matrix: Array<Array<Char>>, pair: Pair<Int, Int>): Pair<Int, Int> {
    return if(pair.first < matrix.size && (matrix[pair.first+1][pair.second] == '|' || matrix[pair.first+1][pair.second] == 'J' || matrix[pair.first+1][pair.second] == 'L'))
        pair.first+1 to pair.second
    else if(pair.first != 0 && (matrix[pair.first-1][pair.second] == '|' || matrix[pair.first-1][pair.second] == 'F' || matrix[pair.first-1][pair.second] == '7'))
        pair.first-1 to pair.second
    else if(pair.second != 0 && ( matrix[pair.first][pair.second-1] == '-' || matrix[pair.first][pair.second-1] == 'F' || matrix[pair.first][pair.second-1] == 'L'))
        pair.first to pair.second -1
    else
        pair.first to pair.second +1
}

fun getNextNode(currentNode: Pair<Int, Int>, previousNode: Pair<Int, Int>, matrix: Array<Array<Char>>): Pair<Int, Int> {
    if(symbolOf(matrix, currentNode) == '-'){
        return if(currentNode.second > previousNode.second) //from west to east
            currentNode.first to currentNode.second+1
        else currentNode.first to currentNode.second-1
    }
    if(symbolOf(matrix, currentNode) == '|'){
        return if(currentNode.first < previousNode.first) //from north to south
            currentNode.first-1 to currentNode.second
        else currentNode.first+1 to currentNode.second
    }
    if(symbolOf(matrix, currentNode) == 'L'){
       return if(currentNode.first > previousNode.first) //from north to east
            currentNode.first to currentNode.second +1
        else currentNode.first -1 to currentNode.second //from east to north
    }
    if(symbolOf(matrix, currentNode) == 'J'){
        return if(currentNode.first > previousNode.first) //from north to west
            currentNode.first to currentNode.second -1
        else currentNode.first -1 to currentNode.second //from west to north
    }
    if(symbolOf(matrix, currentNode) == '7'){
        return if(currentNode.first < previousNode.first) //from south to west
            currentNode.first to currentNode.second -1
        else currentNode.first +1 to currentNode.second //from west to south
    }
    if(symbolOf(matrix, currentNode) == 'F'){
        return if(currentNode.first < previousNode.first) //from south to east
            currentNode.first to currentNode.second +1
        else currentNode.first +1 to currentNode.second //from east to south
    }


    return currentNode




}

private fun symbolOf(
    matrix: Array<Array<Char>>,
    currentNode: Pair<Int, Int>
) = matrix[currentNode.first][currentNode.second]

fun part2(input: File) : Int = findEnclosed(input.readLines())

fun findEnclosed(readLines: List<String>): Int {
    val matrix: Array<Array<Char>> = Array(readLines.size) { outer -> Array(readLines.first().length, init = { i -> readLines[outer].toCharArray()[i]}) }

    val x = matrix.indexOfFirst{ i -> i.any { it == 'S' }}
    val y = matrix[x].indexOf('S')

    val visitedNodes = mutableListOf<Pair<Int,Int>>()
    //val yVertices = mutableListOf<Double>()
    visitedNodes.add(x to y)
    //yVertices.add((matrix.size-1) -x.toDouble())
    var currentNode = findCurrentNode(matrix,(x to y))
    var previousNode = (x to y)

    do {
        visitedNodes.add(currentNode)
        val tempNode = currentNode
        currentNode = getNextNode(currentNode,previousNode,matrix)
        previousNode = tempNode

    }while (symbolOf(matrix, currentNode) != 'S')

    var count = 0
    matrix.forEachIndexed { yAxis ,row ->
       row.forEachIndexed { xAxis, c ->
           if(!visitedNodes.contains(yAxis to xAxis)){
               val verticalLines = countVerticalLinesToTheRight(yAxis to xAxis,matrix,visitedNodes)
               if(verticalLines % 2 != 0)
                   count++
           }
       }
   }

    return count
}

fun countVerticalLinesToTheRight(
    pair: Pair<Int, Int>,
    matrix: Array<Array<Char>>,
    visitedNodes: MutableList<Pair<Int, Int>>
): Int {

  return  (pair.second until matrix.first().size).sumOf { n ->
      if (visitedNodes.contains(pair.first to n)) {
          val symbolOf = matrix[pair.first][n]
          if (symbolOf != '.' && symbolOf != '-' && symbolOf != 'F' && symbolOf != '7') { //manually add symbolOf != '7' if S = 'J' 'L' or '|'
              1
          } else
              "0".toInt()
      } else
          0
  }

}



fun polygonArea(
    x: DoubleArray, y: DoubleArray,
    n: Int
): Double {
    // Initialize area
    var area = 0.0


    // Calculate value of shoelace formula
    var j = n - 1
    for (i in 0 until n) {
        area += (x[j] + x[i]) * (y[j] - y[i])


        // j is previous vertex to i
        j = i
    }


    // Return absolute value
    return abs(area / 2.0)
}

// Driver program


