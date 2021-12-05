package day5

import readInput
import kotlin.math.abs

fun main() {
    fun getLines(input:List<String>): List<List<Pair<Int, Int>>>
    {
        return input.map { it.split(" -> ").map { Pair(it.split(",")[0].toInt(), it.split(",")[1].toInt()) } }
    }

    fun part1(input: List<String>): Int {
        val lines = getLines(input)
        val verticalMotion = lines.filter { it[0].first == it[1].first }.toList()
        val horizontalMotion = lines.filter { it[0].second == it[1].second }.toList()

        var points: MutableMap<Pair<Int,Int>, Int> = mutableMapOf()

        verticalMotion.forEach {
            val startPoint = it[0].second
            val endPoint = it[1].second
            val xPoint = it[0].first
            val direction = if (startPoint < endPoint) startPoint..endPoint else endPoint..startPoint

            direction
                .forEach { i ->
                    points[Pair(xPoint,i)] = (points[Pair(xPoint, i)]?:0) + 1
                }

        }

        horizontalMotion.forEach {
            val startPoint = it[0].first
            val endPoint = it[1].first
            val yPoint = it[0].second
            val direction = if (startPoint < endPoint) startPoint..endPoint else endPoint..startPoint

            direction
                .forEach { i ->
                    points[Pair(i,yPoint)] = (points[Pair(i, yPoint)]?:0) + 1
                }

        }

        return points.count{
            it.value >= 2
        }
    }

    fun part2(input: List<String>): Int {
        val lines = getLines(input)
        val verticalMotion = lines.filter { it[0].first == it[1].first }.toList()
        val horizontalMotion = lines.filter { it[0].second == it[1].second }.toList()
        val diagonalMotion = lines.filter { abs(it[0].first - it[1].first) == abs(it[0].second - it[1].second) }

        var points: MutableMap<Pair<Int,Int>, Int> = mutableMapOf()

        verticalMotion.forEach {
            val startPoint = it[0].second
            val endPoint = it[1].second
            val xPoint = it[0].first
            val direction = if (startPoint < endPoint) startPoint..endPoint else endPoint..startPoint

            direction
                .forEach { i ->
                    points[Pair(xPoint,i)] = (points[Pair(xPoint, i)]?:0) + 1
                }

        }

        horizontalMotion.forEach {
            val startPoint = it[0].first
            val endPoint = it[1].first
            val yPoint = it[0].second
            val direction = if (startPoint < endPoint) startPoint..endPoint else endPoint..startPoint

            direction
                .forEach { i ->
                    points[Pair(i,yPoint)] = (points[Pair(i, yPoint)]?:0) + 1
                }

        }

        diagonalMotion.forEach {
            val startXPoint = it[0].first
            val endXPoint = it[1].first
            val startYPoint = it[0].second
            val endYPoint = it[1].second

            val distance = abs(startXPoint - endXPoint)
            for(i in 0..distance)
            {
                var x = startXPoint
                var y = startYPoint
                if(startXPoint < endXPoint)
                {
                    x += i
                }
                else
                {
                    x -= i
                }
                if(startYPoint < endYPoint)
                {
                    y += i
                }
                else
                {
                    y -= i
                }
                points[Pair(x,y)] = (points[Pair(x, y)]?:0) + 1
            }

        }

        return points.count{
            it.value >= 2
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day5","Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("src/day5","Day05")
    println(part1(input))
    println(part2(input))
}
