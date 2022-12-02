package main.pre2021.adventOfCode2019.day2
import java.io.File

fun day2() = countValidPasswords(File("src/pre2021.adventOfCode/day2/day2.txt").readLines())

private fun countValidPasswords(list: List<String>) : Int  {
   return  list.sumBy { s ->
       val split = s.split(" ", "-", ": ")  //example "10-13 f: jfkwhzrtktphc"
       val lowerBound = split[0].toInt() -1 //subtract to match index
       val upperBound = split[1].toInt() -1 //subtract to match index
       val letter = split[2][0] //assumes always 1 letter
       val password = split[3]
       isValidPart2(lowerBound,upperBound,letter,password) }
}

private fun isValidPart1(lowerBound: Int,upperBound: Int,letter: Char,password : String) : Int {
    val count = password.count{c -> c == letter}
    return if (count in lowerBound..upperBound) 1 else 0
}
private fun isValidPart2(lowerBound: Int,upperBound: Int,letter: Char,password : String) : Int {
    return if ((password[lowerBound] == letter) xor (password[upperBound] == letter)) 1 else 0
}