package main.aoc2022.day5
import java.io.File

fun part1(input: File) : String = getTopBoxes(input,::part1Mover)
fun part2(input: File) : String = getTopBoxes(input,::part2Mover)

fun getTopBoxes(input: File, moverFunc: (List<Int>, List<ArrayDeque<Char>>) -> Unit): String {
  val (b, i) = input.readText().split("\n\n")
  val stacks = (1..33 step 4).map { startIndex ->
    b.toCharArray().foldRightIndexed(ArrayDeque()) { index: Int, c: Char, acc: ArrayDeque<Char> ->
      if (index == startIndex || (index - startIndex) % 36 == 0) {
        if(!c.isWhitespace())
            acc.add(c)
      }
      acc
    }
  }
  stacks.forEach {
    it.removeFirst()
  }

  i.split("\n").dropLast(1).forEach { s ->
      s.filterNot { it.isWhitespace() }.split("move", "from", "to").takeLast(3).map { it.toInt() }.apply {
        moverFunc(this, stacks)
      }
  }
  return stacks.fold("") { acc, chars -> acc + chars.last() }
}

private fun part1Mover(instruction: List<Int>, stacks: List<ArrayDeque<Char>>) {
  repeat(instruction[0]) {
    val toAdd = stacks[instruction[1] - 1].removeLast()
    stacks[instruction[2] - 1].add(toAdd)
  }
}
fun part2Mover(instruction: List<Int>, stacks: List<ArrayDeque<Char>>) {
  val toMove =stacks[instruction[1]-1].takeLast(instruction[0])
  repeat(instruction[0]) {
    stacks[instruction[1] - 1].removeLast()
  }
  stacks[instruction[2]-1].addAll(toMove)
}

