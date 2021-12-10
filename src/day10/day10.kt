package day10

import readInput
import kotlin.system.exitProcess

fun main() {
    val chunks:Map<Char, Char> = mapOf('(' to ')', '<' to '>', '[' to  ']', '{' to '}')

    fun part1(input: List<String>): Int {
        val missingChar = input.map {
            var visited = mutableListOf<Char>()
            it.forEach { c ->
                when (c) {
                    in chunks.keys -> {
                        visited.add(c)
                    }
                    chunks[visited.last()] -> {
                        visited.removeLast()
                    }
                    else -> {
                        return@map c
                    }
                }
            }
            return@map ' '
        }
        return missingChar.map {
            when (it) {
                ')' -> {
                     3
                }
                ']' -> {
                    57
                }
                '}' -> {
                    1197
                }
                '>' -> {
                    25137
                }
                else -> {
                    0
                }
            }
    }.sum()
    }


    fun part2(input: List<String>): Long {
        val missingChar = input.map {
            var visited = mutableListOf<Char>()
            it.forEachIndexed { index, c ->
                if (c in chunks.keys)
                {
                    visited.add(c)
                }
                else if(c == chunks[visited.last()])
                {
                    visited.removeLast()
                }
                else{
                    return@map listOf<Char>()
                }
            }
            visited.map { chunks[it] }.asReversed()
        }

        val sorted = missingChar.filter { it.isNotEmpty() }.map {
            it.fold(0L){acc, c ->
                (acc * 5) + when(c)
                {
                    ')' -> { 1 }
                    ']' -> { 2 }
                    '}' -> { 3 }
                    '>' -> { 4 }
                    else -> exitProcess(1)
                }
            }
        }.sorted()
        return sorted[sorted.size / 2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day10", "Day10_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput("src/day10", "Day10")
    println(part1(input))
    println(part2(input))
}