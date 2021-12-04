package day4

import readInput
import java.util.function.BiFunction

fun main() {

    fun checkVertical(grid: List<List<Pair<Int, Boolean>>>):Boolean
    {
        for (i in 0 until grid.size)
        {
            if (grid.all { it[i].second })
            {
                return true
            }
        }
        return false
    }

    fun parseGrids(input:List<String>): MutableList<MutableList<MutableList<Pair<Int, Boolean>>>>
    {
        var index = 0
        var grid = mutableListOf<MutableList<Pair<Int, Boolean>>>()
        var grids = mutableListOf<MutableList<MutableList<Pair<Int, Boolean>>>>()
        for (i in 0 .. input.size)
        {
            if (index == 5)
            {
                index = 0
                grids.add(grid.toMutableList())
                grid.clear()
            }
            else
            {
                val line = input[i].split(" ").filter { it != "" }.map { Pair(it.toInt(), false) }.toMutableList()
                grid.add(line)
                index++
            }
        }
        return grids
    }

    fun part1(input: List<String>): Int {
        val draw = input[0].split(",").map { it.toInt() }.toList()
        val grids = parseGrids(input.subList(2, input.size))
        draw.forEach {
            for (i in 0 until grids.size)
            {
                for (j in 0 until grids[i].size)
                {
                   var line = grids[i][j]
                   for (x in 0 until line.size)
                   {
                       if(line[x].first == it)
                       {
                           line[x] = Pair(it, true)
                       }
                   }
                   if(line.all { it.second } or checkVertical(grids[i])){
                        val grid = grids[i]
                        val sum = grid.map { it.filter { !it.second }.map { it.first } }.
                                sumOf { it.sum() }
                       return sum * it
                   }
                }
            }
        }
        return 0
    }


    fun part2(input: List<String>): Int {
        val draw = input[0].split(",").map { it.toInt() }.toList()
        val grids = parseGrids(input.subList(2, input.size))
        draw.forEach {
            val gridToRemove:MutableList<MutableList<MutableList<Pair<Int, Boolean>>>> = mutableListOf()
            for (i in 0 until grids.size)
            {
                for (j in 0 until grids[i].size)
                {
                    var line = grids[i][j]
                    for (x in 0 until line.size)
                    {
                        if(line[x].first == it)
                        {
                            line[x] = Pair(it, true)
                        }
                    }
                    if(line.all { it.second } or checkVertical(grids[i])){
                        if(grids.size > 1)
                        {
                            gridToRemove.add(grids[i])
                        }
                        else
                        {
                            val grid = grids[0]
                            val sum = grid.map { it.filter { map -> !map.second }.map { it.first } }.sumOf { it.sum() }
                            return sum * it
                        }
                    }
                }
            }
            grids.removeAll(gridToRemove)
        }
        return 0
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day4", "Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("src/day4", "Day04")
    println(part1(input))
    println(part2(input))
}
