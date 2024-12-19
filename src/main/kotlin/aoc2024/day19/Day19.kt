package main.aoc2024.day19

import java.io.File

fun part1(input: File) : Long = designs(input.readLines())
fun part2(input: File) : Long = designs(input.readLines(),true)

fun designs(readLines: List<String>, part2: Boolean= false): Long {
   val towels = readLines[0].split(", ")
    return readLines.drop(2).sumOf { isPossible(it, towels,part2) }.toLong()
}

val knownSubstrings = HashMap<String,Long>()

fun isPossible(it: String, towels: List<String>, part2: Boolean = false) : Long{
    if(it == ""){
        return 1
    }

    var toReturn = 0L
    for(i in 1 .. it.length){
        val element = it.take(i)

        if(towels.contains(element)) {
            val rest = it.drop(i)

            val known = knownSubstrings[rest]
            if (known != null) {
                toReturn += known
            } else {
                val p = isPossible(it.drop(i), towels, part2)
                if (p > 0) {
                    if(!part2) {
                        return p //early return as we count only first success
                    }
                    knownSubstrings[rest] = p
                    toReturn += p
                }
            }
        }
    }
    return toReturn
}
