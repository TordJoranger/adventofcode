package adventOfCode.day2

import java.io.File


fun day2() = password(File("src/adventOfCode/day2/day2.txt").readLines())

private fun password(list: List<String>) : Int {
    var valid = 0

    list.forEach { pa ->
       if(isValidPart2(pa))
           valid++
    }
    return valid
}

private fun isValidPart2(string : String) : Boolean {
    val spaceSplit = string.split(" ")
    val rangeSplit = spaceSplit[0].split("-")
    val lowerBound = rangeSplit[0].toInt()
    val upperBound = rangeSplit[1].toInt()

    val letter = spaceSplit[1][0]

    val password = spaceSplit[2]

    if((password[lowerBound-1] == letter) xor (password[upperBound-1] == letter))
        return true

    return false
}


private fun isValidPart1(string : String) : Boolean {
    val spaceSplit = string.split(" ")
    val rangeSplit = spaceSplit[0].split("-")
    val lowerBound = rangeSplit[0].toInt()
    val upperBound = rangeSplit[1].toInt()

    val letter = spaceSplit[1][0]

    val password = spaceSplit[2]

    val  count = password.count{a-> a == letter}

    if(count in lowerBound..upperBound)
        return true

    return false
}