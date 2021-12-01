package day02

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val position = Position()
        input.map(String::getCommand).forEach(position::updatePosition)
        return position.getAnswer()
    }

    fun part2(input: List<String>): Int {
        val position = Position()
        input.map(String::getCommand).forEach(position::updatePosition)
        return position.getAnswer()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day02/Day02_test")
    println(part2(readInput("day02/Day02_test")))
    //check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("day02/Day02")
    //println("part1: ${part1(input)}")
    println("part2: ${part2(input)}")
}

class Position {
    private var dept = 0
    private var distance = 0
    private var aim = 0

    fun getAnswer() = dept * distance

    fun updatePosition(command: Command) = when(command.action) {
        Action.UP -> {
            this.aim -= command.repetitions
        }
        Action.DOWN -> {
            this.aim += command.repetitions
        }
        Action.FORWARD -> {
            this.distance += command.repetitions
            this.dept += this.aim * command.repetitions
        }
        else -> {}
    }
}

data class Command(val action: Action, val repetitions: Int)

enum class Action(val tag: String) {
    FORWARD("forward"),UP("up"),DOWN("down"), UNKNOWN("unknown");
}

 fun String.getCommand(): Command {
     val cmdString = this.substringBefore(' ')
     val repetitions = substringAfter(' ').toIntOrNull() ?: 0
     return when (cmdString) {
         Action.FORWARD.tag -> Command(Action.FORWARD, repetitions)
         Action.UP.tag -> Command(Action.UP, repetitions)
         Action.DOWN.tag -> Command(Action.DOWN, repetitions)
         else -> Command(Action.UNKNOWN, 0)
     }
 }
