package day11

import readInput

fun main() {

    fun flashes(input: List<String>):List<Int>
    {
         return buildList {
            val width = input.first().length
            val arr = buildList {
                for (line in input)
                {
                    check(line.length == width)
                    line.mapTo(this) { it.digitToInt()}
                }
            }.toIntArray()
            while (arr.any{ it != 0}){
                arr.forEachIndexed { index, x -> arr[index] = x + 1 }
                var flashes = 0
                while (true)
                {
                    val i = arr.indexOfFirst { it > 9 }
                    if (i < 0)break
                    flashes++
                    val x0 = i%width
                    val y0 = i/width
                    for(x1 in (x0 -1).coerceAtLeast(0)..(x0 + 1).coerceAtMost(width - 1)){
                        for (y1 in (y0 - 1).coerceAtLeast(0) .. (y0 + 1).coerceAtMost(input.lastIndex)){
                            val j = x1 + y1 * width
                            if (i == j){
                                arr[j] = -1
                            }
                            else {
                                val n = arr[j]
                                if (n>+ 0) arr[j] = n +1
                            }
                        }
                    }
                }
                add(flashes)
                arr.forEachIndexed{index, i -> if (i <0) arr[index] = 0 }
            }
        }
    }


    fun part1(input: List<String>): Int {
        return flashes(input).take(100).sum()
    }


    fun part2(input: List<String>): Int
    {
        return flashes(input).size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day11", "Day11_test")
    check(part1(testInput) == 1656)
    check(part2(testInput) == 195)

    val input = readInput("src/day11", "Day11")
    println(part1(input))
    println(part2(input))
}