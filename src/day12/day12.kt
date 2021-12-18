package day12

import readInput


fun main() {




    fun part1(input: List<String>): Int {
        val cavePairs = mutableMapOf<String, MutableSet<String>>()

        for (line in input)
        {
            val (linkA, linkB) = line.split("-")
            cavePairs.getOrPut(linkA){ mutableSetOf() }.add(linkB)
            cavePairs.getOrPut(linkB){ mutableSetOf() }.add(linkA)
        }
        var result = 0
        val alreadyVisit = mutableSetOf<String>()
        fun follow(currentCave: String)
        {
            if (currentCave == "end")
            {
                result++
                return
            }
            for (nextCave in cavePairs[currentCave]!!)
            {
                if(nextCave == "start") continue
                val bigCave = nextCave[0] in 'A'..'Z'
                if(!bigCave){
                    if(nextCave in alreadyVisit)continue
                    alreadyVisit += nextCave
                }
                follow(nextCave)
                if (!bigCave) alreadyVisit -= nextCave
            }
        }
        follow("start")
        return result
    }


    fun part2(input: List<String>): Int
    {
        val cavePairs = mutableMapOf<String, MutableSet<String>>()
        for (line in input)
        {
            val (linkA, linkB) = line.split("-")
            cavePairs.getOrPut(linkA){ mutableSetOf() }.add(linkB)
            cavePairs.getOrPut(linkB){ mutableSetOf() }.add(linkA)
        }
        var result = 0
        val alreadyVisit = mutableSetOf<String>()
        fun follow(currentCave: String, visited:Boolean)
        {
            if (currentCave == "end")
            {
                result++
                return
            }
            for (nextCave in cavePairs[currentCave]!!)
            {
                if(nextCave == "start") continue
                val bigCave = nextCave[0] in 'A'..'Z'
                var visitedTmp = visited
                if(!bigCave){
                    if (nextCave in alreadyVisit) {
                        if (visited) continue
                        visitedTmp = true
                    }
                    else
                    {
                        alreadyVisit += nextCave
                    }
                }
                follow(nextCave, visitedTmp)
                if (!bigCave && visited == visitedTmp) alreadyVisit -= nextCave
            }
        }
        follow("start", false)
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day12", "Day12_test")
    check(part1(testInput) == 19)
    check(part2(testInput) == 103)

    val input = readInput("src/day12", "Day12")
    println(part1(input))
    println(part2(input))
}