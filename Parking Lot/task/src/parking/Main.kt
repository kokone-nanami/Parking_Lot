package parking

data class Car(val number: String, val color: String)

fun main() {
    val parkingLot = Array<Car?>(2) { null }
    parkingLot[0] = Car("test", "test")
    while (true) {
        val line = readLine() ?: break
        val cmd = line.split(" ")
        when (cmd[0]) {
            "park" -> {
                when {
                    parkingLot[0] == null -> {
                        val car = Car(cmd[1], cmd[2])
                        parkingLot[0] = car
                        println("${car.color} car parked in spot 1.")
                    }
                    parkingLot[1] == null -> {
                        val car = Car(cmd[1], cmd[2])
                        parkingLot[1] = car
                        println("${car.color} car parked in spot 2.")
                    }
                    else -> {
                        println("There is no space available.")
                    }
                }
            }
            "leave" -> {
                val num = cmd[1].toInt() - 1
                if (parkingLot[num] == null)
                    println("There is no car in spot ${num+1}.")
                else {
                    parkingLot[num] = null
                    println("Spot ${num+1} is free.")
                }
            }
            else -> {}
        }
    }
}
