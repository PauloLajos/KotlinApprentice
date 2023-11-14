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
    println("Things: $things")
}