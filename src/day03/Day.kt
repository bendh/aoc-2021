package day03

import readInput

fun main() {
//    fun part1(input: List<String>): Int {
//        var gamma = ""
//        var epsilon = ""
//        val transposedData = mutableListOf<String>()
//        input[0].forEach { _ ->
//            transposedData.add("")
//        }
//        input.forEach { row ->
//            row.forEachIndexed { index, c ->
//                transposedData[index] = transposedData[index] + c
//            }
//        }
//        transposedData.forEach {
//            val ones = it.count { c -> c == '1'  }
//            val zeros = it.count { c -> c == '0'  }
//            if (ones > zeros) {
//                gamma += '1'
//                epsilon += '0'
//            } else {
//                gamma += '0'
//                epsilon += '1'
//            }
//        }
//        return gamma.toInt(2) * epsilon.toInt(2)
//    }

    fun getMsbForColumn(index: Int, data: List<List<Int>>, default: Int): Int {
        val partitioned = data.map {
            it[index]
        }.partition { it == 1 }
        if (partitioned.first.size == partitioned.second.size) return default
        return if(partitioned.first.size > partitioned.second.size) 1 else 0
    }
    fun getLsbForColumn(index: Int, data: List<List<Int>>, default: Int): Int {
        val partitioned = data.map {
            it[index]
        }.partition { it == 1 }
        if (partitioned.first.size == partitioned.second.size) return default
        return if(partitioned.first.size > partitioned.second.size) 0 else 1
    }

    fun filterLinesFor(data: List<List<Int>>, default: Int): Int {
        var index = 0
        var filteredLines = data
        while (filteredLines.size != 1) {
            val msb = if (default == 1) getMsbForColumn(index, filteredLines, default) else getLsbForColumn(index, filteredLines, default)
            filteredLines = filteredLines.filter { it[index] == msb }
            index++
        }
        return filteredLines[0].map{it.toString()}.joinToString(separator = "").toInt(2)
    }

    fun part2(input: List<String>): Int {
        val data = input.map { s ->
            s.chunked(1).map(String::toInt)
        }

        val oxigenRating = filterLinesFor(data, 1)
        val co2scrubbing = filterLinesFor(data, 0)
        return oxigenRating * co2scrubbing
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day03/Day_test")
    check(part2(testInput) == 230)

    val input = readInput("day03/Day")
    //println("part1: ${part1(input)}")
    println("part2: ${part2(input)}")
}
//
//class Diagnostic(val reportData: List<String>) {
//    private var gamma = ""
//    private var epsilon = ""
//    private val transposedData = mutableListOf<String>()
//
//    init{
//        reportData[0].forEach { _ ->
//            transposedData.add("")
//        }
//        reportData.forEach { row ->
//            row.forEachIndexed { index, c ->
//                transposedData[index] = transposedData[index] + c
//            }
//        }
//        transposedData.forEach {
//            val ones = it.count { c -> c == '1'  }
//            val zeros = it.count { c -> c == '0'  }
//            if (ones > zeros) {
//                gamma += '1'
//                epsilon += '0'
//            } else {
//                gamma += '0'
//                epsilon += '1'
//            }
//        }
//    }
//
//    fun powerConsumption() = gamma * epsilon
//
//}
//
//private operator fun String.times(other: String): Int {
//    return this.toInt(2) * other.toInt(2)
//}
