package day1

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }.zipWithNext().count { it.second > it.first }
    }

    fun part2(input: List<String>): Int {
        return input.map { it.toInt() }.mapIndexed { index, i ->
            if (index + 3 <= input.size)
                i + input[index + 1].toInt() + input[index + 2].toInt()
            else
                Int.MIN_VALUE
        }.zipWithNext().count { it.second > it.first }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day1","Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("src/day1","Day01")
    println(part1(input))
    println(part2(input))
}
