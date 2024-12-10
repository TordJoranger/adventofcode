package main.aoc2024.day8
import java.io.File
fun part1(input: File) : Long = findAntidotes(input.readLines())
fun part2(input: File) : Long = findAntidotes2(input.readLines())


class Point(val antenna: Char,val coordinate: Pair<Int,Int>)

var sizeY = 0
var sizeX = 0

fun findAntidotes(readLines: List<String>): Long {
    sizeY= readLines.size
    sizeX = readLines[0].length

   val signGroups =  readLines.flatMapIndexed {index, line -> line.toCharArray().mapIndexed { ii, s -> Point(s,Pair(index,ii))  } }.filter { point -> point.antenna!=  '.'}.groupBy { it.antenna }


  val visited =  signGroups.flatMap { p -> p.value.flatMapIndexed { index, co ->
        p.value.drop(index+1).flatMap { calculateNewPoints(co.coordinate,it.coordinate) }
    } }.distinct()
    return visited.size.toLong()
}

fun calculateNewPoints(pointA: Pair<Int,Int>, pointB: Pair<Int,Int>): List<Pair<Int,Int>> {

    val a = pointB.first + (pointB.first-pointA.first)
    val b = pointB.second +(pointB.second-pointA.second)
    val c = pointA.first - (pointB.first-pointA.first)
    val d = pointA.second -(pointB.second-pointA.second)

      return sequence {
          if(a < sizeY && b < sizeX && a>= 0 && b >= 0)
              yield(Pair(a, b))
          if(c < sizeY && d < sizeX && c>= 0 && d >= 0)
              yield(Pair(c,d))
      }.toList()
  }


fun findAntidotes2(readLines: List<String>): Long {
    sizeY= readLines.size
    sizeX = readLines[0].length

    val signGroups =  readLines.flatMapIndexed {index, line -> line.toCharArray().mapIndexed { ii, s -> Point(s,Pair(index,ii))  } }.filter { point -> point.antenna!=  '.'}.groupBy { it.antenna }


    val visited =  signGroups.flatMap { p -> p.value.flatMapIndexed { index, co ->
        p.value.drop(index+1).flatMap { calculateNewPoints2(co.coordinate,it.coordinate) }
    } }.distinct()
    return visited.size.toLong()
}


fun calculateNewPoints2(pointA: Pair<Int,Int>, pointB: Pair<Int,Int>): List<Pair<Int,Int>> {

    val a = (pointB.first-pointA.first)
    val b = pointB.second-pointA.second

    val c = (pointB.first-pointA.first)
    val d = pointA.second -(pointB.second-pointA.second)


    return sequence {
        yield(pointA)
        yield(pointB)

        (pointB.first+a until sizeY step a).forEachIndexed { index, np ->
            val nB = pointB.second + (b * (index + 1))
            if (nB < sizeX && np >= 0 && nB >= 0)
                yield(Pair(np, nB))
        }
        (pointA.first-a downTo  0 step a).forEachIndexed { index, np ->
            val nB = pointA.second - (b * (index + 1))
            if (nB < sizeX && np >= 0 && nB >= 0)
                yield(Pair(np, nB))
        }
    }.toList()

}


