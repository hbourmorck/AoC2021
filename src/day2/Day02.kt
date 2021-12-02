package day2

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val instructions = input.map { Pair(it.split(" ")[0], it.split(" ")[1].toInt()) }.
                fold(mutableMapOf<String, Int>()) { acc, pair ->
                    acc[pair.first] = (acc[pair.first] ?: 0).plus(pair.second)
                    acc
                }
        return instructions["forward"]!! * (instructions["down"]!! - instructions["up"]!!)
    }

    fun part2(input: List<String>): Int {
        val instructions = input.map { Pair(it.split(" ")[0], it.split(" ")[1].toInt()) }.toList()
        var aim = 0
        var forwardInstructions = 0
        var depth = 0
        instructions.forEach {
            when (it.first) {
                "forward" -> {
                    depth += it.second * aim
                    forwardInstructions += it.second
                }
                "down" -> {
                    aim += it.second
                }
                else -> {
                    aim -= it.second
                }
            }
        }
        return depth * forwardInstructions
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day2", "Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("src/day2", "Day02")
    println(part1(input))
    println(part2(input))
}
