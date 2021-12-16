package day11

import readInput

fun main() {

    fun <K> MutableMap<K, Long>.incr(key: K, by: Long = 1) {
        put(key, getOrDefault(key, 0) + by)
    }

    fun part1(input: List<String>): Int {
        var polymeres = input[0]
        var reaction = input.subList(2,input.size).flatMap { it.split(" -> ").zipWithNext{a,b ->
            Pair(a[0],a[1]) to b
        } }.toMap()

        for (i in 0 until 10) {
            polymeres = polymeres.zipWithNext { a, b ->
                "$a" + reaction[Pair(a, b)]
            }.joinToString("", postfix = ""+polymeres.last())
        }
        val char = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val timeAppears = polymeres.filter { it in char }
            .groupBy { it }
            .filter { it.value.isNotEmpty() }
        return timeAppears.maxOf { it.value.count() } - timeAppears.minOf { it.value.count() }
    }


    fun part2(input: List<String>): Long
    {
        val map = input.drop(2).map { it.split(" -> ") }.associate { it[0] to it[1] }
        var pairFreq = mutableMapOf<String, Long>()
        input[0].windowed(2).forEach { pairFreq.incr(it) }
        repeat(40) {
            val newMap = mutableMapOf<String, Long>()
            pairFreq.forEach { (k, v) ->
                newMap.incr(k[0] + map[k]!!, v)
                newMap.incr(map[k]!! + k[1], v)
            }
            pairFreq = newMap
        }
        val charFreq = mutableMapOf<Char, Long>()
        pairFreq.forEach { (k, v) -> charFreq.incr(k[0], v) }
        charFreq.incr(input[0].last())
        return charFreq.values.sorted().let { it.last() - it.first() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day14", "Day14_test")
    check(part1(testInput) == 1588)
    check(part2(testInput) == 2188189693529)

    val input = readInput("src/day14", "Day14")
    println(part1(input))
    println(part2(input))
}