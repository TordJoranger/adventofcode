package pre2021.adventOfCode2019.day9

import java.io.File

fun day9() = part2(File("src/pre2021.adventOfCode/day9/day9").readLines().map { it.toLong() })

fun part2(list: List<Long>): Long {
return  findTheBadSeedP2(list,list)
}

fun findTheBadSeedP2(list: List<Long>, fullList: List<Long>): Long {
    list.forEachIndexed { index, i ->
        if(index > 24) {
            return if(isNumberValid(i,list.subList(0,25))){
                findTheBadSeedP2(list.takeLast(list.size-1),fullList)
            } else
               return findContiguousSetSmallestAndLargest(i,fullList)
        }
    }

    return 0L
}

fun findContiguousSetSmallestAndLargest(i: Long, subList: List<Long>): Long {
    var index =0
    var acc = subList[0]
    var lowest = index
    while(lowest > i) {
        index++
        lowest = index
    }
    index++
    while(index < subList.size){
        when {
            subList[lowest] > i -> {
                index = ++lowest
                acc = subList[index]

            }
            acc + subList[index] == i -> {
                return subList[lowest] + subList[index]
            }
            acc + subList[index] < i -> {
                acc += subList[index]
                index++
            }

            else -> {
                acc -= subList[lowest]
                lowest++
            }
        }
     }
 return 0
}


fun findTheBadSeed(list: List<Long>): Long {
    list.forEachIndexed { index, i ->
        if(index > 24) {
            return if(isNumberValid(i,list.subList(0,25))){
                findTheBadSeed(list.takeLast(list.size-1))
            } else
               i
        }
    }

  return 0L
}

fun isNumberValid(i: Long, subList: List<Long>): Boolean {
      return  subList.any{a-> subList.minus(a).any{b -> a+b == i}}
}


