package day5

import Line
import Point
import readInput
import kotlin.math.abs
import kotlin.math.max

fun main() {
    fun getLines(input:List<String>): List<Line>
    {
        return input.flatMap { it.split(" -> ").map { coordinate -> Point(coordinate.split(",")[0].toInt(), coordinate.split(",")[1].toInt()) }.zipWithNext{start, end -> Line(start, end)} }
    }

    fun move(motions: List<Line>) : MutableMap<Pair<Int,Int>, Int>
    {
        val points: MutableMap<Pair<Int,Int>, Int> = mutableMapOf()
        motions.forEach {
            val startXPoint = it.startPoint.X
            val endXPoint = it.endPoint.X
            val startYPoint = it.startPoint.Y
            val endYPoint = it.endPoint.Y
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
        val motions = lines.filter { it.isHorizontal() || it.isVertical() }
        return move(motions).count{it.value >= 2}
    }

    fun part2(input: List<String>): Int {
        val lines = getLines(input)
        val motions = lines.filter {
            it.isVertical() || it.isHorizontal() || it.isDiagonal45Degree()
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
