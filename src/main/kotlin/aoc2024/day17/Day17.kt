package main.aoc2024.day17

import java.io.File
import kotlin.math.pow


fun part1(input: File) : String = "skip"//opcode(input.readLines())
fun part2(input: File) : String = opcode2(input.readLines())

fun opcode2(readLines: List<String>): String {
    val regex = "\\d+".toRegex()
    val instructions = regex.findAll(readLines[4]).map { it.value.toInt() }.toList()

    val test =findMinX(listOf(8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8),instructions,instructions.size)

    var pointer = 0
    var output = listOf<Int>()
//999999999999999L
    var regA = 0L
    while(output != instructions){
        regA+=1
        output = runProgram(pointer, instructions, regA, 0, 0, true)

        if(output.size > instructions.size){
            error("oh no")
        }
    }

    return regA.toString()
}

fun opcode(readLines: List<String>): String {

    val regA = readLines[0].toCharArray().filter { it.isDigit() }.joinToString("").toLong()
    val regB = readLines[1].toCharArray().filter { it.isDigit() }.joinToString ("").toLong()
    val regC = readLines[2].toCharArray().filter { it.isDigit() }.joinToString("").toLong()

    val regex = "\\d+".toRegex()
    val instructions = regex.findAll(readLines[4]).map { it.value.toInt() }.toList()

    val pointer = 0

    val output = runProgram(pointer, instructions, regA, regB, regC)


    return output.toString().trim('[',']').filterNot { it.isWhitespace() }
}

private fun runProgram(
    pointer: Int,
    instructions: List<Int>,
    regA: Long,
    regB: Long,
    regC: Long,
    part2: Boolean = false
): List<Int> {
    var pointer1 = pointer
    var regA1 = regA
    var regB1 = regB
    var regC1 = regC
    var output1 = listOf<Int>()
    //&& (instructions.take(output1.size) == output1 || !part2)
    while (pointer1 < instructions.size - 1 && (instructions.take(output1.size) == output1 || !part2)) {

        val opcode = instructions[pointer1]
        val literal = instructions[pointer1 + 1]

        val combo = when (literal) {
            0, 1, 2, 3, 7 -> literal.toLong()
            4 -> regA1
            5 -> regB1
            6 -> regC1
            else ->
                throw Exception("fml")
        }

        when (opcode) {
            0 -> {
                regA1 /= (2.0.pow(combo.toDouble())).toLong()
                pointer1 += 2
            }

            1 -> {
                regB1 = regB1.xor(literal.toLong())
                pointer1 += 2
            }

            2 -> {
                regB1 = combo % 8
                pointer1 += 2
            }

            3 -> {
                if (regA1 == 0L) {
                    pointer1 += 2
                } else {
                    pointer1 = literal
                }
            }

            4 -> {
                regB1 = regB1.xor(regC1)
                pointer1 += 2
            }

            5 -> {
                output1 = output1.plus((combo % 8).toInt())
                if(output1.size > 14){
                    println(output1)
                }
                pointer1 += 2
            }

            6 -> {
                regB1 = regA1 / (2.0.pow(combo.toDouble())).toLong()
                pointer1 += 2
            }

            7 -> {
                regC1 = regA1 / (2.0.pow(combo.toDouble())).toLong()
                pointer1 += 2
            }


            else ->
                throw Exception("fml")
        }
    }
    return output1
}

fun findMinX(num: List<Int>, rem: List<Int>, k: Int): Long {
    var x = 1L // Initialize result

    // As per the Chinese remainder theorem,
    // this loop will always break.
    while (true) {
        // Check if remainder of x % num[j] is
        // rem[j] or not (for all j from 0 to k-1)
        var j = 0
        while (j < k) {
            val l = x / (8.0.pow(j+1)).toLong()
            if (l % num[j] != rem[j].toLong()) break
            j++
        }

        // If all remainders matched, we found x
        if (j == k)
            return x

        // Else try next number
        x++
    }
}
