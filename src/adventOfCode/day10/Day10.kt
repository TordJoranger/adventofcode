package adventOfCode.day10

import java.io.File

fun day10() = arrangementCounter(File("src/adventOfCode/day10/day10").readLines().map { it.toLong() }.plus(0).sorted())


fun arrangementCounter(list: List<Long>) : Long {
    val map = list.map { i -> Pair(i, 0L) }.toMap().toMutableMap()
    map[0] = 1

    list.forEachIndexed { index, i ->

        if (list.size > index + 1 && list[index + 1] < i + 4)
            map[list[index + 1]] = map[i]!!.plus(map[list[index + 1]]!!)
        if (list.size > index + 2 && list[index + 2] < i + 4)
            map[list[index + 2]] = map[i]!!.plus(map[list[index + 2]]!!)
        if (list.size > index + 3 && list[index + 3] < i + 4)
            map[list[index + 3]] = map[i]!!.plus(map[list[index + 3]]!!)

    }
    return map[list[list.size - 1]]!!

}
fun joltCounter(list: List<Int>): Int {

    var three=1;
    var one =1;

    list.forEachIndexed {index, l->
        if(index != list.size-1){
            if (list[index+1] == l+3){
                three++
            }else if(list[index+1] == l+1)
                one++
        }
    }
    return three * one

}

