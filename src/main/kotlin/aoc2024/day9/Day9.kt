package main.aoc2024.day9

import java.io.File

fun part1(input: File) : Long = findCheckSum(input.readText())

fun findCheckSum(readLines: String): Long {

    val smt = readLines.trim().windowed(2,2,true)
        .withIndex()
        .flatMap { (index, value) ->
            List(value.first().digitToInt()) { _ -> index.toLong() } +
                    List(value.getOrElse(1){ _ -> '0' }.digitToInt()) { null }
        }

    val emptyBlocks = smt.indices.filter { smt[it] == null }.toMutableList()

    return smt.withIndex().reversed().sumOf { (index, value) ->
        if (value != null) {
            value * (emptyBlocks.removeFirstOrNull() ?: index)
        } else {
            emptyBlocks.removeLastOrNull()
            0
        }
    }
}

fun part2(input: File) : Long = findCheckSum2(input.readLines())

fun findCheckSum2(readLines: List<String>): Long {
  val ints =  readLines.first().toCharArray().map { it.digitToInt() }

  val stacks =  ints.mapIndexed { index, i ->
        if (index % 2 == 0) {
            val range = (1..i).toList().map { index/2 }
           range.toList()
        } else {
            val range = (1..i).toList().map { "." }
           range.toList()
        }
    }.filter { it.isNotEmpty() }
    val smt = stacks.foldRightIndexed(stacks){index, p, acc ->
        if(p.contains(".") || p.isEmpty())
            acc
        else{
        val replaceIndex = acc.withIndex().find { it.value.filter { it == "." }.size >= p.size }
        if(replaceIndex != null && replaceIndex.index <= index){
            val startPoint = replaceIndex.value.indexOf(".")
            val ind = replaceIndex.index
            val replace = acc.map { it.toMutableList() }.toMutableList()

            for (i in startPoint until startPoint+p.size) {
                replace[ind].removeAt(startPoint)
            }

           val addDotIndex = acc.indexOf(p)
            replace[addDotIndex].removeAll(p)
            replace[addDotIndex].addAll((1..p.size).toList().map { "." }.toList())
            replace[ind].addAll(startPoint,p)
            replace.toList()
        }else
            acc
        }
    }.filter { it.isNotEmpty() }


    return smt.flatten().foldIndexed(0L) { index, acc, s ->
         if(s.toString() == ".")
             acc
        else
            acc + (s.toString().toLong() *index) }
}

