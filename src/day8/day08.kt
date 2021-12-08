package day6

import readInput
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        return input.map {
            it.split(("|"))[1].split(" ")
                .filter { (it.length == 4 || it.length == 7 || it.length == 2 || it.length == 3) }.count()
        }.sum()
    }

    fun part2(input: List<String>): Int {
        val number = input.map { Pair(it.split("|")[0].strip().split(" "), it.split("|")[1].strip().split(" ")) }
        val numbersList = mutableListOf<Int>()
        number.forEach {
            val number8 = it.first.first { it.length == 7 }
            val number4 = it.first.first { it.length == 4 }
            val number1 = it.first.first { it.length == 2 }
            val number7 = it.first.first { it.length == 3 }

            var segment1 = number7.first{ !number4.contains(it) }
            var segment4List = number8.toMutableList()
            var segment6List = number4.filter { !number1.contains(it) }.toMutableList()

            val numbersWith5Segment = it.first.filter { it.length == 5 }
            val numberWith6Segment = it.first.filter { it.length == 6 }

            val number6 = numberWith6Segment.find { !it.contains(number1[0]) || !it.contains(number1[1]) }
            val number09 = numberWith6Segment.filter { it != number6 }
            val segment2 = number1.first { !number6!!.contains(it) }
            val segment3 = number1.filter { it != segment2 }

            val number9 = number09.find {
                number4.map { char -> it.contains(char) }.count{it} == 4
            }
            val number0 = number09.find { it != number9 }


            val number2 = numbersWith5Segment.find { !it.contains(segment3) }
            val number5 = numbersWith5Segment.find { !it.contains(segment2) }
            val number3 = numbersWith5Segment.find { it != number2 && it!=number5 }

            fun mapNumber(nombre:String):String
            {
                if(number1.toList().containsAll(nombre.toList()))
                {
                    return "1"
                }
                else if(number7.toList().containsAll(nombre.toList()))
                {
                    return "7"
                }
                else if(number4.toList().containsAll(nombre.toList()))
                {
                    return "4"
                }
                else if(number2!!.toList().containsAll(nombre.toList()))
                {
                    return "2"
                }
                else if(number3!!.toList().containsAll(nombre.toList()))
                {
                    return "3"
                }
                else if(number5!!.toList().containsAll(nombre.toList()))
                {
                    return "5"
                }
                else if(number9!!.toList().containsAll(nombre.toList()))
                {
                    return "9"
                }
                else if(number6!!.toList().containsAll(nombre.toList()))
                {
                    return "6"
                }
                else if(number0!!.toList().containsAll(nombre.toList()))
                {
                    return "0"
                }
                else
                {
                    return "8"
                }
            }
            val numbers = it.second.map { nombre -> mapNumber(nombre) }.joinToString("")
            numbersList.add(numbers.toInt())
        }
        println(numbersList.sum())

        return numbersList.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day8", "Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("src/day8", "Day08")
    println(part1(input))
    println(part2(input))
}