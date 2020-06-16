package parking

data class Car(val number: String, val color: String)

class ParkingLot(size: Int) {
    private val spaces: Array<Car?> = Array(20) { null }

    private fun nextAvailable(): Int = spaces.indexOf(null)

    fun park(num: String, color: String) {
        when (val i = nextAvailable()) {
            -1 -> println("Sorry, the parking lot is full.")
            else -> {
                val car = Car(num, color)
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
}

fun main() {
    val parkingLot = ParkingLot(20)
    while (true) {
        val line = readLine()!!
        if (line == "exit")
            break
        val cmd = line.split(" ")
        when (cmd[0]) {
            "park" -> {
                parkingLot.park(cmd[1], cmd[2])
            }
            "leave" -> {
                parkingLot.leave(cmd[1].toInt())
            }
            else -> throw IllegalArgumentException()
        }
    }
}
