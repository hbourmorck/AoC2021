package day6

import readInput
import java.util.*

fun main() {
    fun part1(input: List<String>): Int {
        return input.map {
            it.split(("|"))[1].split(" ")
                .count { (it.length == 4 || it.length == 7 || it.length == 2 || it.length == 3) }
        }.sum()
    }

    fun part2(input: List<String>): Int {
        val number = input.map {
            it.split("|")[0].split(" ").filter { it.isNotEmpty() }.map { it.toSortedSet() } to
            it.split("|")[1].split(" ").filter { it.isNotEmpty() }.map { it.toSortedSet() }
        }

        val numbersDisplayed = mutableListOf<Int>()
        number.forEach {
            val number8 = it.first.first { it.size == 7 }
            val number4 = it.first.first { it.size == 4 }
            val number1 = it.first.first { it.size == 2 }
            val number7 = it.first.first { it.size == 3 }
            val numbersWith5Segments = it.first.filter { it.size == 5 }
            val numbersWith6Segments = it.first.filter { it.size == 6 }
            val number6 = numbersWith6Segments.find { !it.contains(number1.elementAt(0)) || !it.contains(number1.elementAt(1)) }
            val number9 = numbersWith6Segments.find {number4.map { char -> it.contains(char) }.count{it} == 4}
            val number0 = numbersWith6Segments.find { it != number9 && it != number6}
            val segment2 = number1.first { !number6!!.contains(it) }
            val segment3 = number1.first { it != segment2 }
            val number2 = numbersWith5Segments.find { !it.contains(segment3) }
            val number5 = numbersWith5Segments.find { !it.contains(segment2) }
            val number3 = numbersWith5Segments.find { it != number2 && it!=number5 }

            fun mapNumber(numberDisplayed:SortedSet<Char>):String
            {
                return when(numberDisplayed) {
                    number0 -> "0"
                    number1 -> "1"
                    number2 -> "2"
                    number3 -> "3"
                    number4 -> "4"
                    number5 -> "5"
                    number6 -> "6"
                    number7 -> "7"
                    number8 -> "8"
                    else -> "9"
                }
            }
            numbersDisplayed.add(it.second.map { number -> mapNumber(number) }.joinToString("").toInt())
        }

        return numbersDisplayed.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day8", "Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("src/day8", "Day08")
    println(part1(input))
    println(part2(input))
}