package main.aoc2022.day11
import java.io.File

private class Monkey(var items :List<Long> ,val operation : (Long) -> Long, val test: Int, val throwTrue: Int, val throwFalse: Int, var inspected: Long = 0) {
    fun addItem(item: Long) {
        this.items = items.plus(item)
    }
    fun runOperation(old: Long):Long = operation.invoke(old)
    fun runTest(item:Long) : Boolean = item % test == 0L
}

fun part1(input: File): Long {
    val monkeys = getMonkeys(input)
    repeat(20) {
        runSim(monkeys, 3, 1)
    }
    return monkeys.map { it.inspected }.sorted().takeLast(2).reduce { acc, i -> acc * i }
}

fun part2(input : File): Long {
    val monkeys = getMonkeys(input)
    val product = monkeys.map { it.test.toLong() }.reduce { acc, monkey -> acc*monkey }
    repeat(10000) {
        runSim(monkeys,1,product)
    }
    return monkeys.map { it.inspected }.sorted().takeLast(2).reduce { acc, i -> acc * i }
}

private fun runSim(monkeys: List<Monkey>, divide: Long, product: Long) {
    monkeys.forEach { m ->
        m.items.forEach { i ->
            m.inspected++
            val x = m.runOperation(i)/divide
            val n = x%product

            if(m.runTest(n))
                monkeys[m.throwTrue].addItem(n)
            else
                monkeys[m.throwFalse].addItem(n)
        }
        m.items = mutableListOf()
    }
}

fun createOperation(s: String): (Long) -> Long {
    val ops =s.split(" ")
    return { a -> doOperation(a,ops[1],ops[2])  }
}

fun doOperation(a: Long, s: String, s1: String): Long {
    val b: Long = if(s1 == "old")
        a
    else s1.toLong()
    return when(s){
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> a / b
        else -> {0}
    }
}

private fun getMonkeys(input: File) =
    input.readLines().windowed(6, 7).map{strings: List<String> ->
        val items = strings[1].substringAfter(":").split(",").map { it.trim().toLong() }
        val operation = createOperation(strings[2].substringAfter("old"))
        val test = strings[3].filter { it.isDigit() }.toInt()
        val throwTrue = strings[4].filter { it.isDigit() }.toInt()
        val throwFalse = strings[5].filter { it.isDigit() }.toInt()
        val monkey = Monkey(items, operation, test, throwTrue, throwFalse)
        monkey
    }