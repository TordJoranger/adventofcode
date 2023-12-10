package main.aoc2023.day7

import java.io.File

fun part1(input: File) : Long = day71(input.readLines())

fun part2(input: File) : Long = day72(input.readLines())

fun day72(readLines: List<String>): Long {
    return readLines.sortedBy { s ->
        findStrengthWithJoker(s.split(" ")[0])
    }
        .map { it.split(" ")[1].toLong() }
        .reduceIndexed{index, acc, s ->
            acc + s*(index+1)
        }
}

fun day71(readLines: List<String>): Long {

   return readLines.sortedBy { s ->
       findStrengthStrength(s.split(" ")[0])
   }
       .map { it.split(" ")[1].toLong() }
       .reduceIndexed{index, acc, s ->
        acc + s*(index+1)
       }
}

fun findStrengthStrength(it: String): Int {
    var sum = it
    val groups = it.toCharArray().groupBy { it }
    sum = if(groups.size == 1) //5
        "9$sum"
    else if (groups.size == 2 && groups.any{ g -> g.value.size == 4}) //4
        "8$sum"
    else if(groups.size == 2 && groups.any{ g -> g.value.size == 3}) //3+2
        "7$sum"
    else if(groups.size == 3 && groups.any{ g -> g.value.size == 3}) //3
        "6$sum"
    else if(groups.size == 3 && groups.any{ g -> g.value.size == 2}) //2+2
        "5$sum"
    else if(groups.size == 4) //2
        "4$sum"
    else  //1
        "3$sum"

    sum = findCardStrength(it, sum)
    return sum.toInt(16)
}

fun findStrengthWithJoker(it: String): Int {
    var sum = it
    val chars = it.toCharArray()
    val gr = chars.groupBy { it }


    val toJoin = chars.groupBy { it }['J']?.map { _ ->
        gr.maxBy {
            if(it.key == 'J')
                0
            else {
                val sizeAndType = it.value.size.toString() + it.value.first().toString()
                findCardStrength(it.value.first().toString(), sizeAndType, "1")
                    .toInt(16)
            }
        }.value.first()
    }?.first()

    val replacedChars = chars.map {
            if(it == 'J')
                toJoin
            else
                it
        }

    val groups = replacedChars.groupBy { it }

    sum = if(groups.size == 1) //5
        "9$sum"
    else if (groups.size == 2 && groups.any{ g -> g.value.size == 4}) //4
        "8$sum"
    else if(groups.size == 2 && groups.any{ g -> g.value.size == 3}) //3+2
        "7$sum"
    else if(groups.size == 3 && groups.any{ g -> g.value.size == 3}) //3
        "6$sum"
    else if(groups.size == 3 && groups.any{ g -> g.value.size == 2}) //2+2
        "5$sum"
    else if(groups.size == 4) //2
        "4$sum"
    else  //1
        "3$sum"

    sum = findCardStrength(it, sum, "1")
    return sum.toInt(16)
}

private fun findCardStrength(it: String, sum: String, jokerStrength : String = "B"): String {
    var sum1 = sum
    it.toCharArray().forEachIndexed { index, c ->
        val toReplace = index + 1
        if (!c.isDigit()) {
            when (c) {
                'T' -> sum1 = sum1.replaceRange(toReplace, toReplace + 1, "A")
                'J' -> sum1 = sum1.replaceRange(toReplace, toReplace + 1, jokerStrength)
                'Q' -> sum1 = sum1.replaceRange(toReplace, toReplace + 1, "C")
                'K' -> sum1 = sum1.replaceRange(toReplace, toReplace + 1, "D")
                'A' -> sum1 = sum1.replaceRange(toReplace, toReplace + 1, "E")
            }
        }
    }
    return sum1
}


