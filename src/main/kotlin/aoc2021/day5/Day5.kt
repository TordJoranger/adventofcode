package main.aoc2021.day5
import java.io.File

fun day5() = countLines(File("src/aoc2021/day5/day5.txt").readLines())

class Vector(val Start : Pair<Int,Int>, val End : Pair<Int, Int>)

fun countLines(readLines: List<String>): Long {
    val vectors = readLines.map { l -> val split = l.split(" -> ")
        val pairs = split.map { n ->  val split1 = n.split(",").map { a ->a.toInt()}
            Pair(split1[0], split1[1])
        }
        Vector(pairs[0],pairs[1])
    }
    var count = 0
    val max = readLines.map { l -> l.split(" -> ", ",") }.flatMap { n-> n.map(String::toInt) }.max()!!
    val matrix: Array<IntArray> = Array(max+1) { IntArray(max+1) }
    vectors.forEach { v ->
        if(v.Start.first == v.End.first){
            val range = if(v.Start.second <= v.End.second)  v.Start.second..v.End.second else v.End.second..v.Start.second
            range.forEach { r ->
                if(matrix[v.Start.first][r] == 1){
                    count++
                }
                matrix[v.Start.first][r] += 1
            }
        }else if(v.Start.second == v.End.second){

            val range = if(v.Start.first <= v.End.first)  v.Start.first..v.End.first else v.End.first..v.Start.first
            range.forEach { r ->
                if(matrix[r][v.Start.second] == 1){
                    count++
                }
                matrix[r][v.Start.second] += 1
            }
            //part 2, diagonals
        }else {
            val xGrow = v.Start.first <= v.End.first
            val range = if (xGrow) {
                v.Start.first..v.End.first
            } else {
                v.End.first..v.Start.first
            }
            val yGrow = v.Start.second <= v.End.second
            range.forEachIndexed{ i, r ->
                if(yGrow && xGrow) {
                    if(matrix[r][v.Start.second+i] ==1) {
                        count++
                    }
                    matrix[r][v.Start.second+i]+=1
                }
                else if(xGrow){
                    if(matrix[r][v.Start.second-i]==1) {
                        count++
                    }
                    matrix[r][v.Start.second-i]+=1
                }else if(yGrow){
                    if(matrix[r][v.End.second-i] ==1) {
                        count++
                    }
                    matrix[r][v.End.second-i]+=1

                }else {
                    if(matrix[r][v.End.second+i] ==1) {
                        count++
                    }
                    matrix[r][v.End.second+i]+=1
                }
            }
        }
    }
    return count.toLong();
}
