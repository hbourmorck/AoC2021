package day11

import Point2D
import readInput

fun main() {
    class Instruction(val direction:String, val position:Int)

    fun prin(points:Set<Pair<Int, Int>>)
    {
        for (y in 0 ..  points.maxOf { it.second })
        {
            for (x in 0 .. points.maxOf { it.first })
            {
                if(points.any { it.first == x && it.second == y })
                {
                    print(" # ")
                }
                else
                {
                    print(" . ")
                }
            }
            println()
        }
    }

    fun fold(points: Set<Pair<Int,Int>>, instruction:Instruction): Set<Pair<Int,Int>>
    {
        val pointTmp = mutableSetOf<Pair<Int, Int>>();
        if(instruction.direction == "y")
        {
            points.forEach{
                val  x = it.first
                val y = if(it.second < instruction.position) it.second else (2*instruction.position) - it.second
                pointTmp.add(Pair(x, y))
            }
        }
        else
        {
            points.forEach{
                val  x = if(it.first < instruction.position) it.first else (2 * instruction.position) - it.first
                val y = it.second
                pointTmp.add(Pair(x, y))
            }
        }
        return pointTmp
    }

    fun part1(input: List<String>): Int {
        val pointLocation = mutableSetOf<Pair<Int,Int>>()
        val instructions = mutableListOf<Instruction>()
        input.forEach {
            if(it.isNotEmpty()) {
                if (it.startsWith("fold along ")) {
                    val instructionTmp = it.replace("fold along ", "").split("=")
                    instructions.add(Instruction(instructionTmp[0], instructionTmp[1].toInt()))
                } else {
                    pointLocation.addAll(it.split(",").zipWithNext { a, b -> Pair(a.toInt(), b.toInt()) })
                }
            }
        }

        println("Initial")
        //prin(pointLocation)
        val point = fold(pointLocation, instructions[0])
        println("After 1 fold")
        prin(point)

        return point.size
    }


    fun part2(input: List<String>): String
    {
        var pointLocation = mutableSetOf<Pair<Int,Int>>()
        val instructions = mutableListOf<Instruction>()
        input.forEach {
            if(it.isNotEmpty()) {
                if (it.startsWith("fold along ")) {
                    val instructionTmp = it.replace("fold along ", "").split("=")
                    instructions.add(Instruction(instructionTmp[0], instructionTmp[1].toInt()))
                } else {
                    pointLocation.addAll(it.split(",").zipWithNext { a, b -> Pair(a.toInt(), b.toInt()) })
                }
            }
        }

        instructions.forEachIndexed{index, it ->
            pointLocation = fold(pointLocation, it).toMutableSet()
            println("After $index fold")
            prin(pointLocation)
        }
        return "EPZGKCHU"
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day13", "Day13_test")
    check(part1(testInput) == 17)
    //check(part2(testInput) == 195)

    val input = readInput("src/day13", "Day13")
    println(part1(input))
    println(part2(input))
}