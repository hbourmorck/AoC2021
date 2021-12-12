package day9
//Solution copied from other... Not my code!

import readInput
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

enum class CardinalDirection {
    NORTH, WEST, EAST, SOUTH;

    companion object
}

@OptIn(ExperimentalContracts::class)
inline fun CardinalDirection.Companion.forEach(x: Int, y: Int, block: (x: Int, y: Int) -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.AT_LEAST_ONCE)
    }

    block(x, y - 1)
    block(x - 1, y)
    block(x + 1, y)
    block(x, y + 1)
}

fun main() {
    fun part1(input: List<String>): Int {
        return input.withIndex().sumOf { (i, line) ->
            line.withIndex().sumOf { (j, c) ->
                if (
                    c.isDigit() &&
                    line.getOrNull(j - 1)?.let { c < it } != false &&
                    line.getOrNull(j + 1)?.let { c < it } != false &&
                    input.getOrNull(i - 1)?.getOrNull(j)?.let { c < it } != false &&
                    input.getOrNull(i + 1)?.getOrNull(j)?.let { c < it } != false
                ) c.digitToInt() + 1 else 0
            }
        }
    }

    fun part2(input: List<String>): Int {

        val basins = mutableListOf<Int>()
        val visited = Array(input.size) { i ->
            BooleanArray(input[i].length) { j ->
                val c = input[i][j]
                !c.isDigit() || c.digitToInt() >= 9
            }
        }
        input.forEachIndexed { i, line ->
            line.forEachIndexed inner@{ j, c ->
                if (visited[i][j] || !c.isDigit() || c.digitToInt() >= 9) return@inner
                visited[i][j] = true
                var basin = 0
                val stack = mutableListOf(i to j)
                while (true) {
                    val (i2, j2) = stack.removeLastOrNull() ?: break
                    basin++
                    CardinalDirection.forEach(i2, j2) { i3, j3 ->
                        if (i3 in visited.indices && j3 in visited[i3].indices && !visited[i3][j3]) {
                            visited[i3][j3] = true
                            stack.add(i3 to j3)
                        }
                    }
                }
                basins.add(basin)
            }
        }
        basins.sort()
        return basins.asReversed().take(3).fold(1) { x, y -> x * y }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day9", "Day09_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("src/day9", "Day09")
    println(part1(input))
    println(part2(input))
}