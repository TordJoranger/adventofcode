package aoc2021.day3

import java.io.File

fun day3() = getPowerConsumptionP2(File("src/aoc2021/day3/day3.txt").readLines())


fun getPowerConsumptionP2(list: List<String>): Long {

    val transposed = ArrayList<String>(12)
    list.forEachIndexed {outer,  i ->
        if (outer == 0){
            i.forEach { c -> transposed.add(c.toString()) }
        }
        i.forEachIndexed { index, c -> transposed[index] += c.toString() }
    }

    val ogenerator = getOgen(list, 0)
    val scrubber = getScrubber(list,0)

    val ognen = getDecimalNumber(ogenerator.toLong())
    val scrub = getDecimalNumber(scrubber.toLong())
    return ognen * scrub
}

fun getOgen(list: List<String>, index: Int): String {
    if(list.size == 1){
        return list[0]
    }
    val (ones, zeroes) = getOnesAndZeroes(list, index)
    return if(ones >= zeroes){
        getOgen(list.filter { strings -> strings[index] == '1' }, index +1)
    }else{
        getOgen(list.filter { strings -> strings[index] == '0' }, index +1)
    }
}

fun getScrubber(list: List<String>, index: Int): String {
    if(list.size == 1){
        return list[0]
    }
    val (ones, zeroes) = getOnesAndZeroes(list, index)
    return if(zeroes <= ones){
        getScrubber(list.filter { strings -> strings[index] == '0' }, index +1)
    }else{
        getScrubber(list.filter { strings -> strings[index] == '1' }, index +1)
    }
}

private fun getOnesAndZeroes(
    list: List<String>,
    index: Int
): Pair<Int, Int> {
    val transposed = ArrayList<String>(12)
    list.forEachIndexed { outer, i ->
        if (outer == 0) {
            i.forEach { c -> transposed.add(c.toString()) }
        }else
        i.forEachIndexed { inner, c -> transposed[inner] += c.toString() }
    }
    val ones = transposed[index].count { c -> c == '1' }
    val zeroes = transposed[index].count { c -> c == '0' }
    return Pair(ones, zeroes)
}

fun getPowerConsumption(list: List<String>): Long {

    val transposed = ArrayList<String>(12)
    list.forEachIndexed {outer,  i ->
        if (outer == 0){
            i.forEach { c -> transposed.add(c.toString()) }
        }
        i.forEachIndexed { index, c -> transposed[index] += c.toString() }
    }
    var gamma = "";
    var epsilon = ""
    transposed.forEach { s ->
      val ones = s.count { c -> c == '1' }
        val zeroes = s.count{c-> c== '0' }
        if(ones > zeroes){
            gamma += "1"
            epsilon += "0"
    }else{
            gamma += "0"
            epsilon += "1"
        }

    }
    // Transpose the matrix

 return getDecimalNumber(gamma.toLong()) * getDecimalNumber(epsilon.toLong())

}

fun getDecimalNumber(binaryNumber: Long): Long {
    var binaryNumber = binaryNumber
    var decimalNo = 0
    var power = 0

    while (binaryNumber > 0) {
        val r = binaryNumber % 10
        decimalNo = (decimalNo + r * Math.pow(2.0, power.toDouble())).toInt()
        binaryNumber /= 10
        power++
    }
    return decimalNo.toLong()
}
