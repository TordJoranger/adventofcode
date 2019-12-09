import kotlin.math.abs

fun day3part1() {

    var closest : Int = Int.MAX_VALUE

    val lists1 =  getVectors("day3.1.txt")
    val lists2 =   getVectors("day3.2.txt")

    lists1.second.forEach { y ->
        lists2.first.forEach { x->
            val cross = intersect(
                x.start.first,
                x.start.second,
                x.end.first,
                x.end.second,
                y.start.first,
                y.start.second,
                y.end.first,
                y.end.second)

            if(cross != null && closest > abs(cross.first) + abs(cross.second))
                closest =   abs(cross.first) + abs(cross.second)
        }
    }
    lists2.second.forEach { y ->

        lists1.first.forEach { x->
            val cross = intersect(
                x.start.first,
                x.start.second,
                x.end.first,
                x.end.second,
                y.start.first,
                y.start.second,
                y.end.first,
                y.end.second)

            if(cross != null && closest > abs(cross.first) + abs(cross.second))
                closest =   abs(cross.first) + abs(cross.second)
        }
    }
    println(closest)
}


fun day3part2() {

    var closest : Int = Int.MAX_VALUE
    val lists1 =  getVectors("day3.1.txt")
    val lists2 =   getVectors("day3.2.txt")

    closest = findClosest(lists1.second, lists2.first, closest)
    closest = findClosest( lists2.second, lists1.first,closest)

    println(closest)
}

private fun findClosest(
   list1 : List<Vector>,
   list2 : List<Vector>,
    closest: Int
): Int {
    var closest1 = closest
    list1.forEach { y ->
        list2.forEach { x ->
            val cross = intersect(
                x.start.first,
                x.start.second,
                x.end.first,
                x.end.second,
                y.start.first,
                y.start.second,
                y.end.first,
                y.end.second
            )

          closest1 =  closestPart2(cross, x, y, closest1)

        }
    }
    return closest1
}

private fun closestPart2(cross: Pair<Int, Int>?, x: Vector, y: Vector, closest1: Int) : Int {
    var closest11 = closest1
    if (cross != null) {
        val rollback = abs(cross.first - x.end.first) + abs(cross.second - y.end.second)
        val dist = (x.steps + y.steps) - rollback.toInt()
        if (closest1 > dist)
            closest11 = dist
    }
    return closest11
}
