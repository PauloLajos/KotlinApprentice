
//fun List<String>.toBulletedList(): String {
//fun List<Any>.toBulletedList(): String {
fun <T> List<T>.toBulletedList(): String {
    val separator = "\n - "
    return this.map { "$it" }.joinToString(separator, prefix =
    separator, postfix = "\n")
}

// 1
class Mover<T>(
// 2
    thingsToMove: List<T>,
    val truckHeightInInches: Int = (12 * 12)
) {
    // 3
    private var thingsLeftInOldPlace = mutableListOf<T>()
    private var thingsInTruck = mutableListOf<T>()
    private var thingsInNewPlace = mutableListOf<T>()
    // 4
    init {
        thingsLeftInOldPlace.addAll(thingsToMove)
    }
    // 5
    /*fun moveEverythingToTruck() {
        while (thingsLeftInOldPlace.count() > 0) {
            val item = thingsLeftInOldPlace.removeAt(0)
            thingsInTruck.add(item)
            println("Moved your $item to the truck!")
        }
    }*/
    fun moveEverythingToTruck() {
        while (thingsLeftInOldPlace.count() > 0) {
            val item = thingsLeftInOldPlace.removeAt(0)
            if (item is BreakableThing) {
                if (!item.isBroken) {
                    thingsInTruck.add(item)
                    println("Moved your $item to the truck!")
                } else {
                    println("Could not move your $item to the truck")
                }
            } else {
                thingsInTruck.add(item)
                println("Moved your $item to the truck!")
            }
        }
    }
    // 6
    fun moveEverythingIntoNewPlace() {
        while (thingsInTruck.count() > 0) {
            val item = thingsInTruck.removeAt(0)
            thingsInNewPlace.add(item)
            println("Moved your $item into your new place!")
        }
    }
    // 7
    fun finishMove() {
        println("OK, we finished! We were able to move your:${thingsInNewPlace.toBulletedList()}")
    }
}

class CheapThing(val name: String) {
    override fun toString(): String {
        return name
    }
}

class BreakableThing(
    val name: String,
    var isBroken: Boolean = false
) {
    fun smash() {
        isBroken = true
    }
    override fun toString(): String {
        return name
    }
}


//fun main(args: Array<String>) {
fun main() {
    //val names: List<String> = listOf("Bob", "Carol", "Ted", "Alice")
    val names = listOf("Bob", "Carol", "Ted", "Alice")
    println("Names: $names")
    val firstName = names.first()

    //val firstInt: Int = names.first()

    println(firstName)

    //val things = mutableListOf(1, 2)
    //val things: MutableList<Any> = mutableListOf(1, 2)
    val things = mutableListOf<Any>(1, 2)
    things.add("Steve")
    //println("Things: $things")
    println("Things: ${things.toBulletedList()}")


    val map = mapOf(
        Pair("one", 1),
        Pair("two", "II"),
        Pair("three", 3.0f)
    )
    val valuesForKeysWithE = map.keys
        .filter { it.contains("e") }
        .map { "Value for $it: ${map[it]}" }
    //println("Values for keys with E: $valuesForKeysWithE")
    println("Values for keys with E: ${valuesForKeysWithE.toBulletedList()}")

    println("Names: ${names.toBulletedList()}")

    val cheapThings = listOf(
        CheapThing("Cinder Block table"),
        CheapThing("Box of old books"),
        CheapThing("Ugly old couch")
    )
    val cheapMover = Mover(cheapThings)
    cheapMover.moveEverythingToTruck()
    cheapMover.moveEverythingIntoNewPlace()
    cheapMover.finishMove()

    val television = BreakableThing("Flat-Screen Television")
    val breakableThings = listOf(
        television,
        BreakableThing("Mirror"),
        BreakableThing("Guitar")
    )
    val expensiveMover = Mover(breakableThings)
    expensiveMover.moveEverythingToTruck()

    television.smash()

    expensiveMover.moveEverythingIntoNewPlace()
    expensiveMover.finishMove()
}