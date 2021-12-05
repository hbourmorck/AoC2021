package day5

import readInput
import kotlin.math.abs

fun main() {
    fun getLines(input:List<String>): List<List<Pair<Int, Int>>>
    {
        return input.map { it.split(" -> ").map { coordinate -> Pair(coordinate.split(",")[0].toInt(), coordinate.split(",")[1].toInt()) } }
    }

    fun verticalMotion(verticalMotions: List<List<Pair<Int, Int>>>, points: MutableMap<Pair<Int,Int>, Int>)
    {
        verticalMotions.forEach {
        val startPoint = it[0].second
        val endPoint = it[1].second
        val xPoint = it[0].first
        val direction = if (startPoint < endPoint) startPoint..endPoint else endPoint..startPoint

        direction
            .forEach { i ->
                points[Pair(xPoint,i)] = (points[Pair(xPoint, i)]?:0) + 1
            }
        }
    }

    fun horizontalMotion(horizontalMotions: List<List<Pair<Int, Int>>>, points: MutableMap<Pair<Int,Int>, Int>)
    {
        horizontalMotions.forEach {
            val startPoint = it[0].first
            val endPoint = it[1].first
            val yPoint = it[0].second
            val direction = if (startPoint < endPoint) startPoint..endPoint else endPoint..startPoint

            direction
                .forEach { i ->
                    points[Pair(i,yPoint)] = (points[Pair(i, yPoint)]?:0) + 1
                }

        }
    }

    fun part1(input: List<String>): Int {
        val lines = getLines(input)
        val verticalMotions = lines.filter { it[0].first == it[1].first }
        val horizontalMotions = lines.filter { it[0].second == it[1].second }
        val points: MutableMap<Pair<Int,Int>, Int> = mutableMapOf()
        verticalMotion(verticalMotions, points)
        horizontalMotion(horizontalMotions, points)
        return points.count{
            it.value >= 2
        }
    }

    fun part2(input: List<String>): Int {
        val lines = getLines(input)
        val verticalMotions = lines.filter { it[0].first == it[1].first }
        val horizontalMotions = lines.filter { it[0].second == it[1].second }
        val diagonalMotion = lines.filter { abs(it[0].first - it[1].first) == abs(it[0].second - it[1].second) }
        val points: MutableMap<Pair<Int,Int>, Int> = mutableMapOf()
        verticalMotion(verticalMotions, points)
        horizontalMotion(horizontalMotions, points)
        diagonalMotion.forEach {
            val startXPoint = it[0].first
            val endXPoint = it[1].first
            val startYPoint = it[0].second
            val endYPoint = it[1].second
            val distance = abs(startXPoint - endXPoint)
            for(i in 0..distance)
            {
                val x = startXPoint + if(startXPoint < endXPoint) i else -i
                val y = startYPoint + if(startYPoint < endYPoint) i else -i
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
