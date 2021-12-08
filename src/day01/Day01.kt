package day01

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.mapIndexed { index, s ->
            when(index) {
                0 -> false
                else -> input[index-1].toInt() < s.toInt()
            }
        }.partition { it -> it}
        .first.size
    }

    fun part2(input: List<String>): Int {
        var previous = 0
        return input.windowed(3)
            .map {
                it.sumOf { it.toInt() }
            }
            .mapIndexed { index, sum ->
                when (index) {
                    0 -> {
                        previous = sum
                        false
                    }
                    else -> {
                        val temp = previous
                        previous = sum
                        temp < sum
                    }
                }
            }.partition { isIncreasing ->
                isIncreasing
            }.first.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day01/Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("day01/Day01")
    println("part1: ${part1(input)}")
    println("part2: ${part2(input)}")
}
