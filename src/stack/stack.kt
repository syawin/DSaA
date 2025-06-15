@file:Suppress("ktlint:standard:filename", "unused")

package stack

import queue.Dequeue
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

// Reversing a String with Stacks
// Stack push/pop = O(1)
internal class StackX(
    private val maxSize: Int,
) {
    private val stackArray = CharArray(this.maxSize)
    private var top = -1

    fun push(c: Char) {
        stackArray[++top] = c
    }

    fun pop(): Char = stackArray[top--]

    fun peek(): Char = stackArray[top]

    fun peekN(n: Int): String = stackArray[n].toString()

    val isEmpty: Boolean
        get() = top == -1

    fun size(): Int = top + 1

    fun displayStack(s: String?) {
        print(s)
        print("Stack (bottom->top):\t")
        for (i in 0 until size()) {
            print(peekN(i) + " ")
        }
        println()
    }
}

internal class StackXDQ(
    maxSize: Int,
) {
    private val dequeue = Dequeue(maxSize)

    fun push(l: Long) {
        dequeue.insertLeft(l)
    }

    fun pop(): Long = dequeue.removeLeft()

    fun peek(): Long {
        val temp = dequeue.removeLeft()
        dequeue.insertLeft(temp)
        return temp
    }

    fun size(): Long = dequeue.size().toLong()

    val isEmpty: Boolean
        get() = dequeue.size() == 0
}

// Reverse a string using a stack
internal class Reverser(
    private val input: String,
) {
    private var output: String? = null

    fun doReverse(): String? {
        val stackSize = input.length
        val stackX = StackX(stackSize)
        for (element in input) {
            stackX.push(element)
        }
        output = ""
        while (!stackX.isEmpty) {
            val c = stackX.pop()
            output += c
        }
        return output
    }
}

// Use a stack to match brackets
internal class BracketChecker(
    private val input: String,
) {
    fun check() {
        val stackSize = input.length
        val stackX = StackX(stackSize)
        for (i in 0 until stackSize) {
            val c = input[i]
            when (c) {
                '{', '[', '(' -> stackX.push(c)
                '}', ']', ')' ->
                    if (!stackX.isEmpty) {
                        val pop = stackX.pop()
                        if ((c == '}' && pop != '{') ||
                            (c == ']' && pop != '[') ||
                            (c == ')' && pop != '(')
                        ) {
                            println("Error: $c at $i")
                        }
                    } else {
                        println("Error: $c at $i")
                    }

                else -> {}
            }
        }
        if (!stackX.isEmpty) {
            println("Error: missing closing delimiter")
        }
    }
}

internal object IOUtility {
    @JvmStatic
    @Throws(IOException::class)
    fun readNextString(): String {
        val inputStreamReader = InputStreamReader(System.`in`)
        val bufferedReader = BufferedReader(inputStreamReader)
        return bufferedReader.readLine()
    }
}

internal object ReverserApp {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        var input: String
        var output: String?
        while (true) {
            print("Enter a string:")
            System.out.flush()
            input = IOUtility.readNextString()
            if (input == "") break

            val reverser = Reverser(input)
            output = reverser.doReverse()
            println("Reversed:\t$output")
        }
    }
}

internal object BracketsApp {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        var input: String
        while (true) {
            print("\tEnter string containing delimiters:\t")
            System.out.flush()
            input = IOUtility.readNextString()
            if (input == "") {
                break
            }
            val checker = BracketChecker(input)
            checker.check()
        }
    }
}
