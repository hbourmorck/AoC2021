package day3

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var mostCommonBit = ""
        var lastCommonBit = ""
        for (i:Int in 0 until input[0].length)
        {
            val numberOf0 = input.map { it[i] }.count { it == '0' }
            val numberOf1 = input.map { it[i] }.count { it == '1' }
            if(numberOf0 < numberOf1) {
                mostCommonBit += "1"
                lastCommonBit += "0"
            }
            else
            {
                mostCommonBit += "0"
                lastCommonBit += "1"
            }
        }

        return lastCommonBit.toInt(2) * mostCommonBit.toInt(2)
    }

    fun part2(input: List<String>): Int {
        var index = 0
        var dataOxygen = input.toMutableList()
        while (dataOxygen.size > 1) {
            val numberOf0 = dataOxygen.map { it[index] }.count { it == '0' }
            val numberOf1 = dataOxygen.map { it[index] }.count { it == '1' }
            val mostFound = if (numberOf1 >= numberOf0) '1' else '0'

            val newData = dataOxygen.filter { it[index] == mostFound }.toMutableList()
            dataOxygen = newData
            index++
        }
        val oxygen = dataOxygen.first()

        index = 0
        var dataCo2 = input.toMutableList()
        while (dataCo2.size > 1) {
            val numberOf0 = dataCo2.map { it[index] }.count { it == '0' }
            val numberOf1 = dataCo2.map { it[index] }.count { it == '1' }
            val mostFound = if (numberOf0 <= numberOf1) '0' else '1'
            val newData = dataCo2.filter { it[index] == mostFound }.toMutableList()
            dataCo2 = newData
            index++
        }
        val co2 = dataCo2.first()
        return oxygen.toInt(2) * co2.toInt(2)
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day3", "Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("src/day3", "Day03")
    println(part1(input))
    println(part2(input))
}
