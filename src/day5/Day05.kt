package day5

import readInput
import kotlin.math.abs
import kotlin.math.max

fun main() {
    fun getLines(input:List<String>): List<List<Pair<Int, Int>>>
    {
        return input.map { it.split(" -> ").map { coordinate -> Pair(coordinate.split(",")[0].toInt(), coordinate.split(",")[1].toInt()) } }
    }

    fun move(motions: List<List<Pair<Int, Int>>>) : MutableMap<Pair<Int,Int>, Int>
    {
        val points: MutableMap<Pair<Int,Int>, Int> = mutableMapOf()
        motions.forEach {
            val startXPoint = it[0].first
            val endXPoint = it[1].first
            val startYPoint = it[0].second
            val endYPoint = it[1].second
            val distance = max(abs(startXPoint - endXPoint), abs(startYPoint - endYPoint))
            for(i in 0..distance)
            {
                val x = startXPoint + if (startXPoint == endXPoint) 0 else if(startXPoint < endXPoint) i else -i
                val y = startYPoint + if (startYPoint == endYPoint) 0 else if(startYPoint < endYPoint) i else -i
                points[Pair(x,y)] = (points[Pair(x, y)]?:0) + 1
            }
        }
        return points
    }

    fun part1(input: List<String>): Int {
        val lines = getLines(input)
        val motions = lines.filter {
            vertical@(it[0].first == it[1].first) ||
            horizontal@(it[0].second == it[1].second)
        }
        return move(motions).count{it.value >=2}
    }

    fun part2(input: List<String>): Int {
        val lines = getLines(input)
        val motions = lines.filter {
            vertical@(it[0].first == it[1].first) ||
            horizontal@(it[0].second == it[1].second) ||
            diagonal@(abs(it[0].first - it[1].first) == abs(it[0].second - it[1].second))
        }
        return move(motions).count{it.value >=2}
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day5","Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("src/day5","Day05")
    println(part1(input))
    println(part2(input))
}
