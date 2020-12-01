package adventOfCode.day1
import java.io.File


fun day1() = sumOf2020op2(File("src/adventOfCode/day1/day1.txt").readLines().map { it.toInt() })

private fun sumOf2020op(list: List<Int>) : Int {
    val sortedList = list.sorted()
        var left = 0
        var right = sortedList.size - 1
        while (left < right) {
            when {
                sortedList[left] + sortedList[right] == 2020 -> return sortedList[left] * sortedList[right]
                sortedList[left] + sortedList[right] < 2020 -> left++
                else -> right--
            }
        }
        return 0
}

private fun sumOf2020op2(list: List<Int>) : Int {
    val sortedList = list.sorted()

    sortedList.forEach{ z ->
        var left = 0
        var right = sortedList.size - 1
        while (left < right) {
            val x = sortedList[left]
            val y = sortedList[right]
            when {
                x + y + z == 2020 -> return x*y*z
                x + y + z < 2020 -> left++
                else -> right--
            }
        }
    }

    return 0

}


private fun sumOf2020(list: List<Int>) : Int {
    list.forEach{ y ->
            list.forEach { x ->
                if (x + y == 2020) {
                    return x * y
                }
        }
    }
    return 0
}

private fun sumOf2020part2(list: List<Int>) : Int {
    list.forEach{ y ->
        list.forEach { x ->
            list.forEach{ z ->
                if(x+y+z == 2020)
                    return x*y*z
            }
        }
    }
    return 0
}