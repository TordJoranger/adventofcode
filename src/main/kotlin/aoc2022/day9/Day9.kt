package main.aoc2022.day9

import java.io.File

 fun part1(input: File) : Int {
    val hashSet = HashSet<(Pair<Int,Int>)>()
    hashSet.add(Pair(0,0))
    var head = Pair(0,0)
    var tail = Pair(0,0)

    input.readLines().forEach {
        val amount = it.substring(2).toInt()
        val direction = it.substring(0,1)

        repeat(amount){
            when(direction){
                "R" -> {
                    head = Pair(head.first+1,head.second)
                    tail = move(head,tail)
                }
                "L" -> {
                    head = Pair(head.first-1,head.second)
                    tail = move(head,tail)
                }
                "U" ->{
                    head = Pair(head.first,head.second+1)
                    tail = move(head, tail)
                }
                "D" -> {
                    head = Pair(head.first,head.second-1)
                    tail = move(head,tail)
                    }
            }
            hashSet.add(tail)
        }
    }
    return hashSet.size
}

 fun part2(input: File) : Int {
    val hashSet = HashSet<(Pair<Int,Int>)>()
    hashSet.add(Pair(0,0))
    var head = Pair(0,0)
    var k1 = Pair(0,0)
    var k2 = Pair(0,0)
    var k3 = Pair(0,0)
    var k4 = Pair(0,0)
    var k5 = Pair(0,0)
    var k6 = Pair(0,0)
    var k7 = Pair(0,0)
    var k8 = Pair(0,0)
    var tail = Pair(0,0)

    input.readLines().forEach {
        val amount = it.substring(2).toInt()
        val direction = it.substring(0,1)

        repeat(amount){
            when(direction){
                "R" -> {
                    head = Pair(head.first+1,head.second)
                    k1 = move(head,k1)
                    k2 = move(k1,k2)
                    k3 = move(k2,k3)
                    k4 = move(k3,k4)
                    k5 = move(k4,k5)
                    k6 = move(k5,k6)
                    k7 = move(k6,k7)
                    k8 = move(k7,k8)
                    tail = move(k8,tail)
                }
                "L" -> {
                    head = Pair(head.first-1,head.second)
                    k1 = move(head,k1)
                    k2 = move(k1,k2)
                    k3 = move(k2,k3)
                    k4 = move(k3,k4)
                    k5 = move(k4,k5)
                    k6 = move(k5,k6)
                    k7 = move(k6,k7)
                    k8 = move(k7,k8)
                    tail = move(k8,tail)
                }
                "U" ->{
                    head = Pair(head.first,head.second+1)
                    k1 = move(head,k1)
                    k2 = move(k1,k2)
                    k3 = move(k2,k3)
                    k4 = move(k3,k4)
                    k5 = move(k4,k5)
                    k6 = move(k5,k6)
                    k7 = move(k6,k7)
                    k8 = move(k7,k8)
                    tail = move(k8,tail)
                }
                "D" -> {
                    head = Pair(head.first,head.second-1)
                    k1 = move(head,k1)
                    k2 = move(k1,k2)
                    k3 = move(k2,k3)
                    k4 = move(k3,k4)
                    k5 = move(k4,k5)
                    k6 = move(k5,k6)
                    k7 = move(k6,k7)
                    k8 = move(k7,k8)
                    tail = move(k8,tail)
                }
            }
            hashSet.add(tail)
        }
    }

    return hashSet.size
}

private fun move(
    head: Pair<Int, Int>,
    tail: Pair<Int, Int>
): Pair<Int, Int> {
    if(aheadUp(head, tail) && aheadRight(head, tail)){
        return Pair(tail.first+1,tail.second+1)
    }
    if(aheadRight(head,tail) && aheadDown(head, tail)){
        return Pair(tail.first+1,tail.second-1)
    }
    if(aheadUp(head, tail) && aheadLeft(head, tail)) {
        return Pair(tail.first-1,tail.second+1)
    }
    if(aheadLeft(head, tail) && aheadDown(head, tail)) {
        return Pair(tail.first-1,tail.second-1)
    }
    if (aheadUp(head, tail)) {
        return  if (head.first != tail.first) {
            Pair(head.first, tail.second + 1)
        } else {
            Pair(tail.first, tail.second + 1)
        }
    }else if(aheadDown(head, tail)){
        return if (head.first != tail.first){
            Pair(head.first,tail.second-1)
        }else{
            Pair(tail.first,tail.second-1)
        }
    }else if (aheadRight(head, tail)){
        return if(head.second != tail.second){
            Pair(tail.first+1,head.second)
        }else{
            Pair(tail.first+1,tail.second)
        }
    }else if(aheadLeft(head, tail)){
        return if(head.second != tail.second){
            Pair(tail.first-1,head.second)
        }else{
            Pair(tail.first-1,tail.second)
        }
    }
    return tail
}

private fun aheadLeft(
    head: Pair<Int, Int>,
    tail: Pair<Int, Int>
) = head.first - tail.first < -1

private fun aheadDown(
    head: Pair<Int, Int>,
    tail: Pair<Int, Int>
) = head.second - tail.second < -1

private fun aheadRight(
    head: Pair<Int, Int>,
    tail: Pair<Int, Int>
) = head.first - tail.first > 1

private fun aheadUp(
    head: Pair<Int, Int>,
    tail: Pair<Int, Int>
) = head.second - tail.second > 1
