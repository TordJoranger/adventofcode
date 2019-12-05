import java.io.File
import kotlin.math.abs

class Vector(val start : Pair<Double,Double>, val end: Pair<Double,Double>, val steps : Int )

fun intersect(p0x: Double, p0y: Double,p1x: Double, p1y: Double,p2x: Double, p2y: Double, p3x: Double, p3y: Double) : Int {
    val s10_x = p1x - p0x
    val s10_y = p1y - p0y
    val s32_x = p3x - p2x
    val s32_y = p3y - p2y

    val denom = s10_x * s32_y - s32_x * s10_y

    if(denom.equals(0.0)) {
        return Int.MAX_VALUE
    }

    val denom_is_positive = denom > 0.0
    val s02_x = p0x - p2x
    val s02_y = p0y - p2y
    val s_numer = s10_x * s02_y - s10_y * s02_x

    if ((s_numer < 0.0) == denom_is_positive) return Int.MAX_VALUE

    val t_numer = s32_x * s02_y - s32_y * s02_x

    if ((t_numer < 0.0) == denom_is_positive)  return Int.MAX_VALUE
    if ((s_numer > denom) == denom_is_positive || (t_numer > denom) == denom_is_positive) {
        return Int.MAX_VALUE
    }

    val t = t_numer.div(denom)
    val intersectX = p0x + (t*s10_x)
    val interSectY = p0y + (t*s10_y)

    if(intersectX.equals(0.0) && interSectY.equals(0.0)) return Int.MAX_VALUE

    val stepx = intersectX - p1x
    val stepy = interSectY -p3y
    val rollback = abs(stepx).toInt()  + abs(stepy).toInt()

    return abs(rollback)
}

fun day3() {
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

            if(cross < Int.MAX_VALUE && closest > (x.steps + y.steps-cross))
                closest = (x.steps + y.steps - cross)
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

            if(cross < Int.MAX_VALUE && closest > (x.steps + y.steps-cross))
                closest = (x.steps + y.steps - cross)
        }
    }

    println(closest)
}

private fun addToLists(
    inputPath: String,
    yList:   MutableList<Vector>,
    xList:   MutableList<Vector>
) {
    val input =  File(inputPath).readText()
    val list : List<String> = input.split(",")
    var coordinate = Pair<Double,Double>(0.0,0.0)

    var currentSteps = 0;

    list.forEach {

        val length = it.substring(1).toString().toInt()
        currentSteps  += length
        when (it.subSequence(0, 1)) {
            "U" -> {
                val end = Pair(coordinate.first, coordinate.second+ length)
                yList.add(Vector(coordinate, end,currentSteps))
                coordinate = end
            }
            "D" -> {
                val end = Pair(coordinate.first, coordinate.second - length)
                yList.add(Vector(coordinate, end, currentSteps))
                coordinate = end
            }
            "R" -> {
                val end = Pair(coordinate.first + length, coordinate.second)
                xList.add(Vector(coordinate, end,currentSteps))
                coordinate = end
            }
            "L" -> {
                val end = Pair(coordinate.first - length, coordinate.second )
                xList.add(Vector(coordinate, end,currentSteps))
                coordinate = end
            }
        }
    }
}
