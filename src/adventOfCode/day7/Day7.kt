package adventOfCode.day7

import java.io.File

class Bag(val Color: String, val Bags : MutableList<Pair<Bag,Int>>)

fun day7() = countBags(File("src/adventOfCode/day7/day7").readLines())

fun countBags(lines: List<String>): Int {
    val newlines = lines.map { l-> l.split(" ","contain", "bags","bag", ",", ".", "no", "other")
        .filter { l-> l.isNotBlank() }}.associateBy(keySelector ={l -> l[0]+" "+l[1]},valueTransform = {v -> createBagMap(v)})

//    val set = HashMap<String,Int>()
//    newlines.map { nl -> findGold(nl.value, Pair(nl.key,1), newlines,set)}.sum()

  val count = countChildren("shiny gold", newlines, 1)
return count;

   // return set.values.sum();
}


fun findGold(nl: List<Pair<String, Int>>, pair : Pair<String,Int>, newlines: Map<String, List<Pair<String, Int>>>, set : HashMap<String,Int>): Int {
    if(nl.any{s-> s.first == "shiny gold" }){
        set.merge(pair.first,pair.second) { a, b -> a + b }
    }
    if(nl.isEmpty())
        return 0
    return if(pair.first == "shiny gold")
        1
    else {
        val sum = nl.map { k -> findGold(newlines[k.first]!!, k, newlines, set) }.sum()
        if(sum > 0)
            set.merge(pair.first,pair.second) { a, b -> a + b }
        sum
    }
}


fun countChildren(color: String, newlines: Map<String, List<Pair<String, Int>>>, second: Int): Int {

    return if(newlines[color].isNullOrEmpty())
        0
    else
       return newlines[color]!!.map{l->
            countChildren(
                l.first,
                newlines, l.second) + l.second
        }.sum() * second
}

fun createBagMap(l: List<String>): List<Pair<String,Int>> {
    return l.takeLast(l.size-2).chunked(3).map { l -> Pair(l[1]+ " " +l[2],l[0].toInt()) }
}


