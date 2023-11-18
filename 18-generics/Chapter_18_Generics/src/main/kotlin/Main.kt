
//fun List<String>.toBulletedList(): String {
//fun List<Any>.toBulletedList(): String {
fun <T> List<T>.toBulletedList(): String {
    val separator = "\n - "
    return this.map { "$it" }.joinToString(separator, prefix =
    separator, postfix = "\n")
}

interface Checkable {
    fun checkIsOK(): Boolean
}

// 1
//class Mover<T>(
class Mover<T: Checkable>(
// 2
    thingsToMove: List<T>,
    val truckHeightInInches: Int = (12 * 12)
) {
    // 3
    private var thingsLeftInOldPlace = mutableListOf<T>()
    //private var thingsInTruck = mutableListOf<T>()
    private var thingsInTruck = mutableListOf<Any>()
    private var thingsInNewPlace = mutableListOf<T>()

    private var thingsWhichFailedCheck = mutableListOf<T>()
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
    /*fun moveEverythingToTruck() {
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
    }*/
    //fun moveEverythingToTruck() {
    fun moveEverythingToTruck(startingContainer: Container<T>?) {
        var currentContainer = startingContainer
        while (thingsLeftInOldPlace.count() > 0) {
            val item = thingsLeftInOldPlace.removeAt(0)
            if (item.checkIsOK()) {
                // 1
                if (currentContainer != null) {
                    // 2
                    if (!currentContainer.canAddAnotherItem()) {
                        moveContainerToTruck(currentContainer)
                        currentContainer = currentContainer.getAnother()
                    }
                    // 3
                    currentContainer.addItem(item)
                    println("Packed your $item!")
                } else {
                    // 4
                    thingsInTruck.add(item)
                    println("Moved your $item to the truck!")
                }
                thingsInTruck.add(item)
                println("Moved your $item to the truck!")
            } else {
                thingsWhichFailedCheck.add(item)
                println("Could not move your $item to the truck :[")
            }
        }
        currentContainer?.let { moveContainerToTruck(it) }
    }
    // 6
    /*fun moveEverythingIntoNewPlace() {
        while (thingsInTruck.count() > 0) {
            val item = thingsInTruck.removeAt(0)
            thingsInNewPlace.add(item)
            println("Moved your $item into your new place!")
        }
    }*/
    fun moveEverythingIntoNewPlace() {
        while (thingsInTruck.count() > 0) {
            val item = thingsInTruck.removeAt(0)
            if (item.checkIsOK()) {
                thingsInNewPlace.add(item)
                println("Moved your $item into your new place!")
            } else {
                thingsWhichFailedCheck.add(item)
                println("Could not move your $item into your new place :[")
            }
        }
    }
    // 7
    /*fun finishMove() {
        println("OK, we finished! We were able to move your:${thingsInNewPlace.toBulletedList()}")
    }*/
    fun finishMove() {
        println("OK, we finished! We were able to move your:${thingsInNewPlace.toBulletedList()}")
            if (thingsWhichFailedCheck.isNotEmpty()) {
                println("But we need to talk about your:${thingsWhichFailedCheck.toBulletedList()}")
            }
    }

    private fun moveContainerToTruck(container: Container<T>) {
        thingsInTruck.add(container)
        println("Moved a container with your ${container.contents().toBulletedList()} to the truck!")
    }
}

class CheapThing(val name: String): Checkable {
    override fun toString(): String {
        return name
    }
    override fun checkIsOK(): Boolean = true
}

class BreakableThing(
    val name: String,
    var isBroken: Boolean = false
): Checkable {
    fun smash() {
        isBroken = true
    }
    override fun toString(): String {
        return name
    }
    override fun checkIsOK(): Boolean {
        return !isBroken
    }
}

// 1
interface Container<T> {
    // 2
    fun canAddAnotherItem(): Boolean
    fun addItem(item: T)
    // 3
    fun canRemoveAnotherItem(): Boolean
    fun removeItem(): T
    // 4
    fun getAnother(): Container<T>
    // 5
    fun contents(): List<T>
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