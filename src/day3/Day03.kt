package day3

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day3", "Day03_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("src/day3", "Day03")
    println(part1(input))
    println(part2(input))
}