package day7

import readInput
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val values = input.flatMap { it.split(",").map { it.toInt() } }
        val distances = mutableListOf<Int>()
        for (i in (values.minOrNull()?:0) .. (values.maxOrNull()?:1))
            distances.add(values.map { abs(it - i) }.sum())
        return distances.minOrNull()?:0
    }

    val registerSum = mutableMapOf<Int, Long>()

    fun sum(num: Int):Long
    {
        return registerSum.getOrPut(num) { (1L..num).sum() } //To accelerate the computation
    }

    fun part2(input: List<String>): Long {
        val values = input.flatMap { it.split(",").map { it.toInt() } }
        val distances = mutableListOf<Long>()
        for (i in (values.minOrNull()?:0) .. (values.maxOrNull()?:1)) {
            distances.add(
                values.map {
                sum(abs(it - i)) }.sum())
        }
        return distances.minOrNull()?:0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day7","Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168L)

    val input = readInput("src/day7","Day07")
    println(part1(input))
    println(part2(input))
}
