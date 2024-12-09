package main.aoc2024.day6

import java.io.File

data class NodeWithDirection(val direction:String,val node: Pair<Int,Int>)

fun part1(input: File) : Int = visitedPoints(input.readLines())

fun visitedPoints(readLines: List<String>): Int {
    val matrix = readLines
            .map { it.toCharArray().toList() }
            .toList()



    val pos = matrix.withIndex()
            .flatMap { (y, row) ->
                row.withIndex()
                        .filter { (_, c) -> c == '^' }
                        .map { (x, _) -> Pair(y, x) }
            }
            .first()

    val visitedNodes = mutableSetOf(NodeWithDirection("N",pos))

    traverse(pos,"N", matrix,visitedNodes)

    return visitedNodes.map { it.node }.distinct().size

}

fun traverse(pos: Pair<Int,Int>, direction: String, matrix: List<List<Char>>, visitedNodes: MutableSet<NodeWithDirection>) : Int {
    when (direction){
        "N" -> return goNorth(matrix, pos, visitedNodes)
        "E" -> return goEast(matrix,pos,visitedNodes)
        "S" -> return goSouth(matrix,pos,visitedNodes)
        "W" -> return goWest(matrix,pos,visitedNodes)
    }
    return 0
}

fun goWest(matrix:List<List<Char>>, pos:Pair<Int,Int>, visitedNodes: MutableSet<NodeWithDirection>): Int {
    val horizontal = matrix[pos.first].take( pos.second)
    val goUntil = horizontal.lastIndexOf('#')

    if (goUntil == -1){
        for (i in 0.. pos.first) {
            visitedNodes.add(NodeWithDirection("W",Pair(pos.first, i)))
        }
       return 0
    }
    else {
        for (i in pos.second downTo goUntil +1) {
            if(!visitedNodes.add(NodeWithDirection("W",Pair(pos.first, i))))
                return 1
        }
        val newPos = Pair(pos.first,goUntil+1)
        return  traverse(newPos, "N", matrix, visitedNodes)
    }
}

fun goSouth(matrix: List<List<Char>>, pos: Pair<Int, Int>, visitedNodes: MutableSet<NodeWithDirection>): Int {
    val vertical = matrix.indices.map { y -> matrix[y][pos.second] }.joinToString("").takeLast(matrix.size - pos.first-1)
    val goUntil = vertical.indexOf('#')
    if (goUntil == -1){
        for (i in 1 .. vertical.length) {
            visitedNodes.add(NodeWithDirection("S",Pair(pos.first+i, pos.second)))
        }
        return 0
    }
    else {
        for (i in 1 ..goUntil) {
            if(!visitedNodes.add(NodeWithDirection("S",Pair(pos.first+i, pos.second))))
                return 1
        }
        val newPos = Pair(pos.first+goUntil, pos.second)
        return traverse(newPos, "W", matrix, visitedNodes)
    }

}

fun goEast(matrix: List<List<Char>>, pos: Pair<Int, Int>, visitedNodes: MutableSet<NodeWithDirection>): Int {
    val horizontal = matrix[pos.first].takeLast(matrix[0].size - pos.second-1)
    val goUntil = horizontal.indexOf('#')

    if (goUntil == -1){
        for (i in 1 .. horizontal.size) {
            visitedNodes.add(NodeWithDirection("E",Pair(pos.first, pos.second+i)))
        }
        return 0
    }
    else {
        for (i in 1 ..goUntil) {
            if(!visitedNodes.add(NodeWithDirection("E",Pair(pos.first, pos.second+i))))
                return 1
        }
        val newPos = Pair(pos.first,pos.second+goUntil)
        return traverse(newPos, "S", matrix, visitedNodes)
    }
}

private fun goNorth(matrix: List<List<Char>>, pos: Pair<Int,Int>, visitedNodes: MutableSet<NodeWithDirection>): Int {
    val vertical = matrix.indices.map { y -> matrix[y][pos.second] }.joinToString("").take(pos.first)
    val goUntil = vertical.lastIndexOf('#')
    val newPos = Pair(goUntil + 1, pos.second)
    if (goUntil == -1){
        for (i in 0 .. vertical.length) {
            visitedNodes.add(NodeWithDirection("N",Pair(i, pos.second)))
        }
        return 0
    }

    else {
        for (i in pos.first-1 downTo  goUntil+1) {
            if(!visitedNodes.add(NodeWithDirection("N",Pair(i, pos.second))))
                return 1
        }
        return traverse(newPos, "E", matrix, visitedNodes)
    }

}

fun part2(input: File) : Int = findLoops(input.readLines())


fun findLoops(readLines: List<String>): Int {
    val matrix = readLines
            .map { it.toCharArray().toList() }
            .toList()

    val pos = matrix.withIndex()
            .flatMap { (y, row) ->
                row.withIndex()
                        .filter { (_, c) -> c == '^' }
                        .map { (x, _) -> Pair(y, x) }
            }
            .first()
    val visitedNodes = mutableSetOf(NodeWithDirection("N",pos))
    traverse(pos,"N",matrix,visitedNodes)

    val sum = visitedNodes.map { it.node }.distinct().sumOf { n->
        val newMatrix = matrix.mapIndexed { i, chars -> chars.mapIndexed { ii, c ->
            if(i == n.first && ii == n.second && c != '^')
                 '#'
            else c
        } }

       val innsersum = traverse(pos,"N",newMatrix, mutableSetOf(NodeWithDirection("N",pos)))
        innsersum
    }

    return  sum
}
