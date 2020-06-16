package parking

data class Car(val spot: Int, val number: String, val color: String)

class ParkingLot(size: Int) {
    private val spaces: Array<Car?> = Array(size) { null }

    private fun nextAvailable(): Int = spaces.indexOf(null)
    private fun empty(): Boolean = spaces.filterNotNull().isEmpty()

    val size = spaces.size

    fun park(num: String, color: String) {
        when (val i = nextAvailable()) {
            -1 -> println("Sorry, the parking lot is full.")
            else -> {
                val car = Car(i, num, color)
                spaces[i] = car
                println("${car.color} car parked in spot ${i+1}.")
            }
        }
    }
    fun leave(num: Int) {
        if (spaces[num - 1] == null)
            println("There is no car in spot $num.")
        else {
            spaces[num - 1] = null
            println("Spot $num is free.")
        }
    }
    fun status() {
        if (empty())
            println("Parking lot is empty.")
        else {
            for (i in spaces.indices)
                if (spaces[i] != null)
                    println("${i+1} ${spaces[i]!!.number} ${spaces[i]!!.color}")
        }
    }

    fun reg_by_color(color: String) {
        val res = spaces.filterNotNull().filter { it.color.toLowerCase() == color.toLowerCase() }.joinToString(", ") { it.number }
        if (res == "") println("No cars with color $color were found.")
        else println(res)
    }
    fun spot_by_color(color: String) {
        val res = spaces.filterNotNull().filter { it.color.toLowerCase() == color.toLowerCase() }.map { it.spot + 1 }.joinToString(", ")
        if (res == "") println("No cars with color $color were found.")
        else println(res)
    }
    fun spot_by_reg(number: String) {
        val res = spaces.filterNotNull().filter { it.number.toLowerCase() == number.toLowerCase() }.map { it.spot + 1 }.joinToString(", ")
        if (res == "") println("No cars with registration number $number were found.")
        else println(res)
    }
}

fun defaultAsNull() = println("Sorry, a parking lot has not been created.")

fun main() {
    var parkingLot: ParkingLot? = null
    while (true) {
        val line = readLine()!!
        if (line == "exit")
            break
        val cmd = line.split(" ")
        when (cmd[0]) {
            "create" -> {
                parkingLot = ParkingLot(cmd[1].toInt())
                println("Created a parking lot with ${parkingLot.size} spots.")
            }
            "park" -> parkingLot?.park(cmd[1], cmd[2]) ?: defaultAsNull()
            "leave" -> parkingLot?.leave(cmd[1].toInt()) ?: defaultAsNull()
            "status" -> parkingLot?.status() ?: defaultAsNull()
            "reg_by_color" -> parkingLot?.reg_by_color(cmd[1]) ?: defaultAsNull()
            "spot_by_color" -> parkingLot?.spot_by_color(cmd[1]) ?: defaultAsNull()
            "spot_by_reg" -> parkingLot?.spot_by_reg(cmd[1]) ?: defaultAsNull()
            else -> throw IllegalArgumentException()
        }
    }
}
