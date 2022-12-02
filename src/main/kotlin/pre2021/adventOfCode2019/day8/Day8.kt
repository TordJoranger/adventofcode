package main.pre2021.adventOfCode2019.day8

import java.io.File

fun day8() = accumulatePart2(File("src/pre2021.adventOfCode/day8/day8").readLines())

fun accumulate(lines: List<String>): Long {
    var acc = 0L
    var index =0
    val visited = HashSet<Int>()
    while (index < lines.size){
        if(!visited.add(index))
            return 0 //return acc, for part 1
        val pair = whenLoop(lines, index, acc)
        acc = pair.first
        index = pair.second
    }
  return acc
}

fun accumulatePart2(lines: List<String>): Long {
    var acc = 0L
    var index =0
    val visited = HashSet<Int>()
    while (index < lines.size){
        if(!visited.add(index)) {
            visited.forEach{ v->
                val updated: List<String>
                if(lines[v].substring(0,3) == "nop") {
                    updated = lines.updated(v,"jmp"+ lines[v].substring(3))
                    acc = accumulate(updated)
                    if (acc != 0L) {
                        return acc
                    }
                }
                else if(lines[v].substring(0,3) == "jmp") {
                    updated = lines.updated(v,"nop"+ lines[v].substring(3))
                    acc = accumulate(updated)
                    if (acc != 0L) {
                        return acc
                    }
                }
            }
                return 0
        }
        val pair = whenLoop(lines, index, acc)
        acc = pair.first
        index = pair.second
    }
    return acc
}

private fun whenLoop(
    lines: List<String>,
    index: Int,
    acc: Long
): Pair<Long, Int> {
    var index1 = index
    var acc1 = acc
    when (lines[index1].substring(0, 3)) {
        "nop" -> {
            index1++
        }
        "jmp" -> {
            index1 += lines[index1].substring(4).toInt()
        }
        "acc" -> {
            acc1 += lines[index1].substring(4).toInt()
            index1++
        }
    }
    return Pair(acc1, index1)
}

fun <E> Iterable<E>.updated(index: Int, elem: E) = mapIndexed { i, existing ->  if (i == index) elem else existing }
