package main.aoc2022.day10

import java.io.File

fun part1(input: File) : Int {
    var x = 1
    var cycle = 1
    val list = input.readLines()
    var result = 0

    list.forEach {  s ->
        if (cycle == 20 || (cycle - 20) % 40 == 0) {
            result += cycle * x
        }
        if (s.startsWith("n")){
            cycle++
        }else{
            cycle++
            if (cycle == 20 || (cycle - 20) % 40 == 0) {
                result += cycle * x
            }
            x += s.substring(5).toInt()
            cycle++
        }
    }
    return result
}

fun part2(input: File) : String {
    var x = 1
    var cycle = 1
    val list = input.readLines()
    var result = ""

    list.forEachIndexed { index, s ->
        result += hitsPixel(cycle, x)
        if (s.startsWith("n")){
            cycle++
        }else{
            cycle++
            result += hitsPixel(cycle, x)
            x += s.substring(5).toInt()
            cycle++
        }
    }
    return result
}

private fun hitsPixel(cycle: Int, x: Int) :String
{
   var res = if ((cycle-1) % 40 - x in  -1 .. 1) {
        "#"
    } else {
        "."
}
    if(cycle != 240 && cycle%40 ==0){
        res+=  "\n"
    }
return res
}