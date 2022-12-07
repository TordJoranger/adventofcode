package main.aoc2022.day7

import java.io.File
private class Node(val Name: String, var size: Int, val Children: MutableList<Node>, var parent: Node?) {
    fun setSizes(size: Int) {
        this.size+=size
        if (parent!= null)
            parent!!.setSizes(size)
    }
}

fun part1(input: File) : Int = findLargestSizes(input.readLines())
fun part2(input: File) : Int = findLargestSizes2(input.readLines())

fun findLargestSizes2(lines: List<String>): Int {
    val node = Node("/",0, mutableListOf(),null)
    val inst = lines.toMutableList()
    val sets = mutableListOf<Node>()
    getNodes(inst,node,node,sets)
    val unused = 70000000 - node.size
    val toFreeUp = 30000000 - unused

    return sets.filter { it.size > toFreeUp }.reduce { a, b ->
        if(b.size > toFreeUp && a.size > b.size)
            b
        else
            a
    }.size
}

fun findLargestSizes(lines: List<String>): Int {
  val node = Node("/",0, mutableListOf(),null)

val inst = lines.toMutableList()

val sets = mutableListOf<Node>()

    getNodes(inst,node,node,sets)

return sets.sumOf {
    if (it.size <= 100000)
        it.size
    else 0
}
}

private fun getNodes(lines: MutableList<String>, node: Node, root: Node, sets: MutableList<Node>) {
    if (lines.isEmpty()) {
        return
    }
    val instruction = lines.first()
    lines.removeFirst()

    if (instruction.contains("cd")) {
        if (instruction.startsWith("$ cd /")) {
            getNodes(lines, root, root, sets)
        } else if (instruction.startsWith("$ cd ..")) {
            getNodes(lines, node.parent!!, root, sets)
        } else if (instruction.startsWith("$ cd")) {
            val child = node.Children.find { it.Name == instruction.substringAfter("cd").trim() }
            getNodes(lines, child!!, root, sets)
        }
    }

    if (instruction.contains("ls")) {
        getNodes(lines, node, root, sets)
    }
    if (instruction.startsWith("dir")) {
        val newNode = Node(instruction.substringAfter("dir").trim(), 0, mutableListOf(), node)
        sets.add(newNode)
        node.Children.add(newNode)
        getNodes(lines, node, root, sets)
    } else if (instruction.any { it.isDigit() }) { //files
        val name = instruction.filterNot { it.isDigit() }.trim()
        val size = instruction.filter { it.isDigit() }.toInt()
        val fileNode = Node(name, size, mutableListOf(), node)
        fileNode.parent!!.setSizes(size)
        node.Children.add(fileNode)
        getNodes(lines, node, root, sets)
    }

}
