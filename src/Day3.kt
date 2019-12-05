import kotlin.math.abs

fun day3part1() {
    var yList1 : MutableList<Vector> = mutableListOf()
    var xList1 :  MutableList<Vector> = mutableListOf()
    var yList2 :  MutableList<Vector> = mutableListOf()
    var xList2 : MutableList<Vector> = mutableListOf()
    var closest : Int = Int.MAX_VALUE

    addToLists("day3.1.txt",yList1, xList1)
    addToLists("day3.2.txt",yList2, xList2)

    yList1.forEach { y ->
        xList2.forEach { x->
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
    yList2.forEach { y ->

        xList1.forEach { x->
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
    var yList1 : MutableList<Vector> = mutableListOf()
    var xList1 :  MutableList<Vector> = mutableListOf()
    var yList2 :  MutableList<Vector> = mutableListOf()
    var xList2 : MutableList<Vector> = mutableListOf()
    var closest : Int = Int.MAX_VALUE

    addToLists("day3.1.txt",yList1, xList1)
    addToLists("day3.2.txt",yList2, xList2)

    yList1.forEach { y ->

        xList2.forEach { x->
            val cross = intersect(
                x.start.first,
                x.start.second,
                x.end.first,
                x.end.second,
                y.start.first,
                y.start.second,
                y.end.first,
                y.end.second)

            if(cross !=null){
                val rollback =  abs(cross.first -   x.end.first ) + abs(cross.second-y.end.second )
                val dist = (x.steps + y.steps) - rollback.toInt()
                if (closest > dist)
                    closest = dist
            }

        }
    }
    yList2.forEach { y ->

        xList1.forEach { x->
            val cross = intersect(
                x.start.first,
                x.start.second,
                x.end.first,
                x.end.second,
                y.start.first,
                y.start.second,
                y.end.first,
                y.end.second)

            if(cross !=null){
                val rollback =  abs(cross.first - x.end.first) + abs(cross.second-y.end.second )
                val dist = (x.steps + y.steps) - rollback.toInt()
                if (closest > dist)
                    closest = dist
            }
        }
    }

    println(closest)
}
