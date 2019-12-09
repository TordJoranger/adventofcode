package day6

import java.io.File

class Node(val Name: String, val Children : List<Node>)

fun day6part2() :Int {
    val nodes = getMap(emptyMap(),File("day6.txt").readLines())
    val parentOfYou = findParentsOf(nodes, arrayOf(),nodes["YOU"]!!)
    val parentOfSan = findParentsOf(nodes, arrayOf(),nodes["SAN"]!!)
    parentOfYou.withIndex().forEach { (index, node) ->
        parentOfSan.withIndex().forEach { (index2, it) ->
            if(node.Name == it.Name)
                return index+index2
        }
    }
    return 0
}

fun day6() :Int {
    val nodes = getMap(emptyMap(),File("day6.txt").readLines())
    val start = nodes["COM"]
    return countNodes(start!!, nodes.minus(start.Name),0)
}

fun countNodes(node: Node, nodes: Map<String,Node>, count: Int) :Int{
    if (node.Children.any()) {
        var temp = count
          node.Children.forEach {
           val child = nodes[it.Name]
              temp += countNodes(child!!, nodes, count+1)
        }
        return temp
    }
    return count
}
fun findParentsOf(nodes: Map<String,Node>, parents: Array<Node>, currentNode: Node): Array<Node>{
    val list = nodes.toList()
    list.forEach(){o->
            if( o.second.Children.any {c->  c.Name == currentNode.Name } )
                return findParentsOf(nodes,parents.plus(o.second),o.second)
        }
    return  parents
}

fun getMap(nodes: Map<String, Node>, list: List<String>): Map<String, Node> {
        if (list.count() == 0) {
            return nodes
        }
        val line = list.first().split(")")
        val key = line[0]
        val value = line[1]
        val nextNode = Node(value, emptyList())
           val newNodes = if(!nodes.containsKey(nextNode.Name))
               nodes.plus(Pair(nextNode.Name, nextNode))
           else
               nodes

        return if (!nodes.containsKey(key)) {
            val currentNode = Node(key, listOf(nextNode))
            val newMap = newNodes.plus(Pair(key, currentNode))

            getMap(newMap, list.drop(1))
        } else {
            val newMap = newNodes.mapValues {
                if (it.key == key)
                    Node(key, it.value.Children.plus(nextNode))
                else
                    it.value
            }
            getMap(newMap, list.drop(1))
        }
    }
