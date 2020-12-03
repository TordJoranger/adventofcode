package adventOfCode.day3

import java.io.File

fun day3() = countTreesPart2(File("src/adventOfCode/day3/day3.txt").readLines())

private fun countTreesPart1(list: List<String>) : Int {
    return countTrees(list,1,3);
}

private fun countTreesPart2 (list: List<String>) : Long {
    return countTrees(list,1,1).toLong() * countTrees(list,1,3) * countTrees(list,1,5)* countTrees(list,1,7) * countTrees(list ,2,1)
}

private fun countTrees(list: List<String>, down : Int=1,right : Int) : Int  {
    var trees =0
    var counter = 0

    for ((i,s) in list.withIndex()) {
        if(i == 0 || i%down != 0) continue

        counter += right
        if(counter > 30)
            counter -= 31

        if(s[counter] == '#')
            trees++
    }
    return trees;
}
