package adventOfCode.day4

import java.io.File

fun day4Stolen() = File("src/adventOfCode/day4/day4")
    .readText().split("\r\n\r\n")
    .map { it
        .split(Regex("\\s"))
        .filter { a -> a.isNotBlank() }
        .map(String::first)
        .sorted()
        .joinToString("")
    }
    .count { it == "bceehhip" || it == "beehhip" }


    fun day4() = countValidPassports(File("src/adventOfCode/day4/day4").readText())

    private fun countValidPassports(s: String): Long {
        val list = s.split("\r\n\r\n").map{x -> x.replace("\r\n", " ")}

      return  list.sumBy { passport -> if(
          hasValidDigits(passport,"byr:",1920,2002)
          && hasValidDigits(passport,"iyr:",2010,2020)
          && hasValidDigits(passport,"eyr:",2020,2030)
          && hasValidHeight(passport)
          && hasValidHairColor(passport)
          && hasValidEyeColor(passport)
          && hasValidPID(passport)
      ) 1 else 0 }.toLong()
    }

fun hasValidPID(passport: String): Boolean {
    val startIndex = passport.indexOf("pid:")+4
    val pid = passport.substring(startIndex).substringBefore(" ")

   return Regex("^[0-9]{9}$").matches(pid)
}

fun hasValidEyeColor(passport: String): Boolean {
    val startIndex = passport.indexOf("ecl:")+4
    val ec = passport.substring(startIndex,startIndex+3)

    return Regex("^(amb|blu|brn|gry|grn|hzl|oth){1}$").matches(ec);
}

fun hasValidHairColor(passport: String): Boolean {
    val startIndex = passport.indexOf("hcl:")+4
    val hc = passport.substring(startIndex).substringBefore(" ")

    return Regex("^#([0-9a-f]{6})$").matches(hc);
}

fun hasValidHeight(passport: String): Boolean {
    val startIndex = passport.indexOf("hgt:")+4

    val hgt = passport.substring(startIndex)
    return when{
        hgt.contains("cm") && hgt.indexOf("cm") == 3 ->  passport.substring(startIndex,startIndex+3).toIntOrNull() in 150..193
        hgt.contains("in")  && hgt.indexOf("in") == 2 ->  passport.substring(startIndex,startIndex+2).toIntOrNull() in 59..76
        else -> false
    }
}

fun hasValidDigits(passport: String, code :String, lowerBound : Int,upperBound: Int, digits :Int = 4 ) : Boolean {
    val startIndex = passport.indexOf(code) + code.length
    val numbers = passport.substring(startIndex).substringBefore(" ")
    return  numbers.toIntOrNull() in lowerBound..upperBound
}

