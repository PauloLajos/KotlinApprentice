
//fun List<String>.toBulletedList(): String {
//fun List<Any>.toBulletedList(): String {
fun <T> List<T>.toBulletedList(): String {
    val separator = "\n - "
    return this.map { "$it" }.joinToString(separator, prefix =
    separator, postfix = "\n")
}

//fun main(args: Array<String>) {
fun main() {
    //println("Hello World!")
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    //println("Program arguments: ${args.joinToString()}")

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
}