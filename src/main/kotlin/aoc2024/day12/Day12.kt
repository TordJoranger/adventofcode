package main.aoc2024.day12

import java.io.File

val visited = mutableSetOf<Pair<Int,Int>>()
fun part1(input: File) : Long = findRegionPrice(input.readLines())

fun findRegionPrice(readLines: List<String>): Long {
    val matrix = readLines
        .map { it.toCharArray().toList() }
        .toList()

    return matrix.mapIndexed { i, chars ->
        chars.withIndex().sumOf { (ii, c) ->
            if(!visited.contains(Pair(i,ii))){
                val nodeSum = findRegionForNode(Pair(i,ii),c,matrix)
                nodeSum.first * nodeSum.second
            }
            else 0
        }
    }.sum()
}

fun findRegionForNode(position: Pair<Int, Int>, c: Char, matrix: List<List<Char>>) : Pair<Long,Long>{
    if(!visited.add(position)){
        return Pair(0L,0L)
    }
    var sum = Pair(1L,4L)
    if(position.first+1 < matrix.size){ //Down
        val down = matrix[position.first+1][position.second]
        if(down == c){
            val nodeSum =findRegionForNode(Pair(position.first+1,position.second),c,matrix)
            sum = Pair(sum.first+nodeSum.first,sum.second+nodeSum.second-1)
        }
    }
    if (position.first-1 >= 0){//UP
        val up = matrix[position.first-1][position.second]
        if(up == c){
            val nodeSum = findRegionForNode(Pair(position.first-1,position.second),c,matrix)
            sum = Pair(sum.first+nodeSum.first,sum.second+nodeSum.second-1)
        }
    }
    if(position.second-1 >= 0){ //LEFT
        val left = matrix[position.first][position.second-1]
        if(left == c){
            val nodeSum = findRegionForNode(Pair(position.first,position.second-1),c,matrix)
            sum = Pair(sum.first+nodeSum.first,sum.second+nodeSum.second-1)
        }
    }
    if (position.second+1 < matrix[0].size){//Right
        val right = matrix[position.first][position.second+1]
        if(right == c){
            val nodeSum = findRegionForNode(Pair(position.first,position.second+1),c,matrix)
            sum = Pair(sum.first+nodeSum.first,sum.second+nodeSum.second-1)
        }
    }

    return sum
}

fun part2(input: File) : Long = findRegionPrice2(input.readLines())

fun findRegionPrice2(readLines: List<String>): Long {
visited.clear()
    val matrix = readLines
        .map { it.toCharArray().toList() }
        .toList()

    return matrix.mapIndexed { i, chars ->
        chars.withIndex().sumOf { (ii, c) ->
            if(!visited.contains(Pair(i,ii))){

                val nodeSum = findRegionForNode2(Pair(i,ii),c,matrix)
                nodeSum.first * nodeSum.second
            }
            else 0
        }
    }.sum()
}

fun findRegionForNode2(position: Pair<Int, Int>, c: Char, matrix: List<List<Char>>): Pair<Long,Long> {
    if(!visited.add(position)){
        return Pair(0L,0L)
    }
    var sum = Pair(1L,0L)
    var down = '.'
    var up = '.'
    var right = '.'
    var left = '.'
    if(position.first+1 < matrix.size){ //Down
        down = matrix[position.first+1][position.second]
        if(down == c){
            val nodeSum =findRegionForNode2(Pair(position.first+1,position.second),c,matrix)
            sum = Pair(sum.first+nodeSum.first,sum.second+nodeSum.second)
        }
    }
    if (position.first-1 >= 0){//UP
         up = matrix[position.first-1][position.second]
        if(up == c){
            val nodeSum = findRegionForNode2(Pair(position.first-1,position.second),c,matrix)
            sum = Pair(sum.first+nodeSum.first,sum.second+nodeSum.second)
        }
    }
    if(position.second-1 >= 0){ //LEFT
         left = matrix[position.first][position.second-1]
        if(left == c){
            val nodeSum = findRegionForNode2(Pair(position.first,position.second-1),c,matrix)
            sum = Pair(sum.first+nodeSum.first,sum.second+nodeSum.second)
        }
    }
    if (position.second+1 < matrix[0].size){//Right
         right = matrix[position.first][position.second+1]
        if(right == c){
            val nodeSum = findRegionForNode2(Pair(position.first,position.second+1),c,matrix)
            sum = Pair(sum.first+nodeSum.first,sum.second+nodeSum.second)
        }
    }
    var corner = sum.second
    if(right != c && up != c){
        corner++
    }
    if(right == c && up == c && matrix[position.first-1][position.second+1] != c){
        corner++
    }
    if(right != c && down != c){
        corner++
    }
    if(right == c && down == c && matrix[position.first+1][position.second+1] != c){
        corner++
    }
    if(left != c && down != c){
        corner++
    }
    if(left == c && down == c && matrix[position.first+1][position.second-1] != c){
        corner++
    }
    if(left != c && up != c){
        corner++
    }
    if(left == c && up == c && matrix[position.first-1][position.second-1] != c){
        corner++
    }


    return Pair(sum.first, corner)
}
