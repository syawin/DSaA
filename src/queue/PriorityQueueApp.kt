package queue

fun main() {
    // Test with integers
    val intQueue = PriorityQueue<Int>(5)
    intQueue.insert(30)
    intQueue.insert(50)
    intQueue.insert(10)
    intQueue.insert(60)
    intQueue.insert(5)

    println("Integer queue test:")
    println("Max value: ${intQueue.peekMax()}")
    println("Min value: ${intQueue.peekMin()}")

    print("Elements removed in order: ")
    while (!intQueue.isEmpty()) {
        val item = intQueue.remove()
        print("$item ")
    }
    println()

    // Test with strings
    val stringQueue = PriorityQueue<String>(5)
    stringQueue.insert("Orange")
    stringQueue.insert("Apple")
    stringQueue.insert("Banana")
    stringQueue.insert("Kiwi")
    stringQueue.insert("Pear")

    println("\nString queue test:")
    println("Max value: ${stringQueue.peekMax()}")
    println("Min value: ${stringQueue.peekMin()}")

    print("Elements removed in order: ")
    while (!stringQueue.isEmpty()) {
        val item = stringQueue.remove()
        print("$item ")
    }
    println()

    // Test with custom class
    data class Person(
        val name: String,
        val age: Int,
    ) : Comparable<Person> {
        override fun compareTo(other: Person): Int = this.age.compareTo(other.age)
    }

    val personQueue = PriorityQueue<Person>(5)
    personQueue.insert(Person("Alice", 30))
    personQueue.insert(Person("Bob", 25))
    personQueue.insert(Person("Charlie", 40))
    personQueue.insert(Person("Diana", 35))
    personQueue.insert(Person("Eve", 22))

    println("\nPerson queue test (ordered by age):")
    println("Oldest person: ${personQueue.peekMax()}")
    println("Youngest person: ${personQueue.peekMin()}")

    println("People removed in age order:")
    while (!personQueue.isEmpty()) {
        val person = personQueue.remove()
        println(person)
    }
}
