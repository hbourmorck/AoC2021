package day15
import java.util.PriorityQueue
import readInput

fun main() {


    fun part1(input: List<String>): Int {
        val gridSize = input.size
        val grid = (input.indices).map { i ->
            (input.indices).map { j ->
                input[i][j].toString().toInt()
            }
        }
        val pq = PriorityQueue<Triple<Int, Int, Int>> { a, b -> a.third - b.third }
        val risk = Array(grid.size) { Array(grid.size) { Int.MAX_VALUE } }

        risk[0][0] = 0
        pq.add(Triple(0, 0, 0))
        while (pq.isNotEmpty()) {
            val (x, y, dist) = pq.poll()
            listOf(x to y + 1, x to y - 1, x + 1 to y, x - 1 to y).forEach { (X, Y) ->
                if (X in grid.indices && Y in grid[0].indices && risk[X][Y] > dist + grid[X][Y]) {
                    risk[X][Y] = dist + grid[X][Y]
                    pq.add(Triple(X, Y, risk[X][Y]))
                }
            }
        }
        return risk.last().last()
    }


    fun part2(input: List<String>): Int
    {
        val gridSize = input.size
        val grid = (0 until gridSize * 5).map { i ->
            (0 until gridSize * 5).map { j ->
                (input[i % gridSize][j % gridSize] - '0' + (i / gridSize + j / gridSize)).let { if (it < 10) it else it - 9 }
            }
        }
        val pq = PriorityQueue<Triple<Int, Int, Int>> { a, b -> a.third - b.third }
        val risk = Array(grid.size) { Array(grid.size) { Int.MAX_VALUE } }

        risk[0][0] = 0
        pq.add(Triple(0, 0, 0))
        while (pq.isNotEmpty()) {
            val (x, y, dist) = pq.poll()
            listOf(x to y + 1, x to y - 1, x + 1 to y, x - 1 to y).forEach { (X, Y) ->
                if (X in grid.indices && Y in grid[0].indices && risk[X][Y] > dist + grid[X][Y]) {
                    risk[X][Y] = dist + grid[X][Y]
                    pq.add(Triple(X, Y, risk[X][Y]))
                }
            }
        }
        return risk.last().last()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("src/day15", "Day15_test")
    check(part1(testInput) == 40)
    check(part2(testInput) == 315)

    val input = readInput("src/day15", "Day15")
    println(part1(input))
    println(part2(input))
}