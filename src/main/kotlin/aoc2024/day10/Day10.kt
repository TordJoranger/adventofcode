package main.aoc2024.day10
import java.io.File

fun part1(input: File) : Long = findTrailheads(input.readLines())

fun part2(input: File) : Long = findTrailheads2(input.readLines())

fun findTrailheads(readLines: List<String>): Long {
    val matrix = readLines
        .map { it.toCharArray().map{c -> c.digitToInt() }.toList() }
        .toList()
   val sum = matrix.mapIndexed { i, ints ->
        ints.mapIndexed { ii, y ->
            if(y == 0) {
                val visited = mutableSetOf<Pair<Int,Int>>()
                tryFindNine(Pair(i,ii), 0, matrix, visited)
            } else
                0

        }.sum()
    }.sum()

    return sum.toLong()

}

fun tryFindNine(
    position: Pair<Int, Int>,
    level: Int,
    matrix: List<List<Int>>,
    visited: MutableSet<Pair<Int, Int>>
) : Int{
    visited.add(position)
    if(level == 9)
        return 1
    else{
        var sum = 0
        if(!visited.contains(Pair(position.first+1,position.second)) && position.first+1 < matrix.size && matrix[position.first+1][position.second] == level+1){ //go down
            sum+= tryFindNine(
                Pair(position.first+1,position.second),
                level+1,
                matrix,
                visited
            )
        }
        if(!visited.contains(Pair(position.first-1,position.second)) && position.first > 0 && matrix[position.first-1][position.second] == level+1){ //go down
            sum+= tryFindNine(
                Pair(position.first-1,position.second),
                level+1,
                matrix,
                visited
            )
        }
        if(!visited.contains(Pair(position.first,position.second-1)) && position.second > 0 && matrix[position.first][position.second-1] == level+1){ //go left
            sum+= tryFindNine(
                Pair(position.first,position.second-1),
                level+1,
                matrix,
                visited
            )
        }
        if(!visited.contains(Pair(position.first,position.second+1)) && position.second+1 < matrix[0].size && matrix[position.first][position.second+1] == level+1){ //go right
            sum+= tryFindNine(
                Pair(position.first,position.second+1),
                level+1,

                matrix,
                visited
            )
        }
        return sum
    }
}

fun findTrailheads2(readLines: List<String>): Long {
    val matrix = readLines
        .map { it.toCharArray().map{c -> c.digitToInt() }.toList() }
        .toList()

    val sum = matrix.mapIndexed { i, ints ->
        ints.mapIndexed { ii, y ->
            if(y == 0) {
                val visited = mutableSetOf<Pair<Int,Int>>()
                tryFindNine2(Pair(i,ii), 0, matrix, visited)
            } else
                0

        }.sum()
    }.sum()

    return sum.toLong()
}
    fun tryFindNine2(
            position: Pair<Int, Int>,
    level: Int,

    matrix: List<List<Int>>,
    visited: MutableSet<Pair<Int, Int>>
    ) : Int{
        visited.add(position)
        if(level == 9)
            return 1
        else{

            var sum = 0
            if( position.first+1 < matrix.size && matrix[position.first+1][position.second] == level+1){ //go down
                sum+= tryFindNine2(
                    Pair(position.first+1,position.second),
                    level+1,
                    matrix,
                    visited
                )
            }
            if( position.first > 0 && matrix[position.first-1][position.second] == level+1){ //go down
                sum+= tryFindNine2(
                    Pair(position.first-1,position.second),
                    level+1,
                    matrix,
                    visited
                )
            }
            if( position.second > 0 && matrix[position.first][position.second-1] == level+1){ //go left
                sum+= tryFindNine2(
                    Pair(position.first,position.second-1),
                    level+1,
                    matrix,
                    visited
                )
            }
            if( position.second+1 < matrix[0].size && matrix[position.first][position.second+1] == level+1){ //go right
                sum+= tryFindNine2(
                    Pair(position.first,position.second+1),
                    level+1,
                    matrix,
                    visited
                )
            }
            return sum
        }
}
