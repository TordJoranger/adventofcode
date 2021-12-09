package aoc2021.day2

import java.io.File

fun day2() = getPosition(File("src/aoc2021/day2/day2.txt").readLines())

fun getPositionP2(readLines: List<String>): Long {
    var x = 0L;
    var y = 0L;
    var aim = 0L
    readLines.forEach { line ->
        val split = line.split(" ")
        val dir = split[0]
        val value = split[1].toInt()
        when(dir){
            "forward" ->  {
                x += value
                y +=  value * aim
            }
            "up" -> aim -=value
            "down" -> aim += value
        }
    }
    return x*y
}

fun getPosition(readLines: List<String>): Long {
    var x = 0L;
    var y = 0L;
    readLines.forEach { line ->
       val split = line.split(" ")
         val dir = split[0]
         val value = split[1].toInt()

        when(dir){
            "forward" ->  x += value
            "up" -> y -=value
            "down" -> y += value
        }


    }

return x*y

}


