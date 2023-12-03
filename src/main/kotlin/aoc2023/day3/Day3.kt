package main.aoc2023.day3

import java.io.File

fun part1(input: File) : Int = sumNonAdjacent(input.readLines())

fun part2(input: File) : Int = sumGears(input.readLines())

fun sumGears(readLines: List<String>): Int {
    val starEx = "\\*+".toRegex()
    val regex = "\\d+".toRegex()
    var sum = 0
    readLines.forEachIndexed { index, s ->

       sum += starEx.findAll(s).sumOf { match ->
           val starDex = match.range.first
           val list = mutableListOf<Int>()
           list.addAll(regex.findAll(readLines[index]).filter { digit -> digit.range.contains(starDex-1) || digit.range.contains(starDex+1)}.map { m -> m.value.toInt() })
           if(index > 0)//Ignores first row
              list.addAll(regex.findAll(readLines[index -1]).filter { digit -> digit.range.contains(starDex) || digit.range.contains(starDex-1) ||digit.range.contains(starDex+1)}.map { m -> m.value.toInt() })
           if(index < readLines.size-1)//ignores last row
               list.addAll(regex.findAll(readLines[index +1]).filter { digit -> digit.range.contains(starDex) || digit.range.contains(starDex-1) ||digit.range.contains(starDex+1)}.map { m -> m.value.toInt() })

           if(list.size ==2)
               list.reduce { acc, i -> acc*i }
           else
            0

        }
    }
    return  sum
}

fun sumNonAdjacent(readLines: List<String>): Int {

    val regex = "\\d+".toRegex()
    var sum = 0
    readLines.forEachIndexed { index, s ->
        sum += regex.findAll(s).sumOf { match ->
            val extendedRange = match.range.first-1..match.range.last+1

            if(extendedRange.any{rIndex ->
                    if(rIndex == -1 || rIndex == s.length) false
                    else isNotDigitOrFullStop(s[rIndex])
                            || (index > 0 && isNotDigitOrFullStop(readLines[index-1][rIndex]))
                            || (index < readLines.size-1 && isNotDigitOrFullStop(readLines[index+1][rIndex]))
                })
                match.value.toInt()
            else
                0
        }
    }
    return sum
}

private fun isNotDigitOrFullStop(s: Char) : Boolean =
   s != '.' && !s.isDigit()
