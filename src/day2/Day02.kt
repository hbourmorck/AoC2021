package day2

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val instructions = input.map { Pair(it.split(" ")[0], it.split(" ")[1].toInt()) }.toList()
        val forwardInstructions = instructions.filter { it.first == "forward" }.sumOf { it.second }
        val upInstructions = instructions.filter { it.first == "up" }.sumOf { it.second }
        val downInstructions = instructions.filter { it.first == "down" }.sumOf { it.second }
        return forwardInstructions * (downInstructions - upInstructions)
    }

    fun part2(input: List<String>): Int {
        val instructions = input.map { Pair(it.split(" ")[0], it.split(" ")[1].toInt()) }.toList()
        var aim = 0
        var forwardInstructions = 0
        var upInstructions = 0
        var downInstructions = 0
        var depth = 0
        instructions.forEach {
            when (it.first) {
                "forward" -> {
                    depth += it.second * aim
                    forwardInstructions += it.second
                }
                "down" -> {
                    aim += it.second
                    downInstructions += it.second
                }
                else -> {
                    aim -= it.second
                    upInstructions += it.second
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
