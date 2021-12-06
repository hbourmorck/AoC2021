package day6

import readInput

fun main() {
    class lanternfish(var timer:Int)
    {
        fun decrement():Boolean
            {
                if (timer == 0)
                {
                    timer = 6
                    return true
                }
                timer--
                return false
            }
    }

    tailrec fun solve(numberOfDays: Int, day: Int, input: Map<Int,Long>): Long = if (day == numberOfDays) input.values.sum()
    else {
        val dayTmp = day + 1
        val new = input[0] ?: 0
        val newMap = input.map { (fishTimer, counter) -> (if (fishTimer > 0) fishTimer - 1 else fishTimer) to counter }.toMap().toMutableMap()
        newMap[6] = (newMap[6] ?: 0) + new
        newMap[8] = new
        solve(numberOfDays, dayTmp, newMap)
    }

    fun part1(input: List<String>): Int {
        var lanternfishs = input.flatMap { it.split(",").map { lanternfish(it.toInt()) } }
        for (i in 0 until 80)
        {
            val lanternfishsTemp = lanternfishs.toMutableList()
            lanternfishs.forEach {
                if (it.decrement())
                {
                    lanternfishsTemp.add(lanternfish(8))
                }
            }
            lanternfishs = lanternfishsTemp.toList()
        }

        return lanternfishs.count()
    }

    fun part2(input: List<String>): Long {
        val lanternfishs = input.flatMap { it.split(",").map(String::toInt)}
        return solve(256, 0, (0..8).associateWith { nb -> lanternfishs.count { it == nb }.toLong() }) //Reduce the number of lantern fish...
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day6","Day06_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539)

    val input = readInput("src/day6","Day06")
    println(part1(input))
    println(part2(input))
}
