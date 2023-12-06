package main.aoc2023.day5

import java.io.File

fun part1(input: File) : Long = findLowestSeedLocation(input.readLines())

fun part2(input: File) : Long = findLowestSeedLocation2(input.readLines())

//brute force, takes forever
fun findLowestSeedLocation2(readLines: List<String>): Long {
    val regex = "\\d+".toRegex()
    val seedMap = regex.findAll(readLines[0]).map { m -> m.value.toLong() }.toList()

    val seeds = seedMap.windowed(2,2)
    val groups = groupAsListsByWhiteSpace(readLines,regex)

  var minValue = Long.MAX_VALUE
     seeds.forEach { seedRange ->
        (seedRange[0] until seedRange[0]+seedRange[1]).forEach { seed ->
                var seedValue = seed
                for (g in groups) {
                    seedValue = findSeedValue(g, seedValue)

                    }
            if (seedValue < minValue)
                  minValue = seedValue
        }
    }
    return minValue
}

fun findLowestSeedLocation(readLines: List<String>): Long {
    val regex = "\\d+".toRegex()
    val seeds = regex.findAll(readLines[0]).map { m -> m.value.toLong() }.toList()

    val groups = groupAsListsByWhiteSpace(readLines, regex)

    return seeds.map { seed ->
        var seedValue = seed
        groups.forEach { g ->
          seedValue = findSeedValue(g,seedValue)
        }
        seedValue
    }.minOf { it }
}

private fun groupAsListsByWhiteSpace(
    readLines: List<String>,
    regex: Regex
): List<List<List<Long>>> {
    val groups = readLines.asSequence().drop(1).fold(mutableListOf<MutableList<String>>()) { acc, s ->
        if (s.isEmpty()) {
            acc.add(mutableListOf())
        } else {
            acc.last().add(s)
        }
        acc

    }.asSequence().map { it.drop(1) }.map {
        it.map { a ->
            regex.findAll(a).map { nums -> nums.value.toLong() }.toList()
        }
    }.toList()
    return groups
}

fun findSeedValue(g: List<List<Long>>, seedValue: Long): Long {
    val mappingToUse = g.firstOrNull { v -> seedValue in v[1]until(v[1]+v[2])  } ?: return seedValue
    return seedValue + (mappingToUse[0]-mappingToUse[1])

}

