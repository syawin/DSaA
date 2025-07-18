package graph

fun main() {
    // Create a 5x5 chess board for the Knight's Tour
    val boardSize = 5
    val startPosition = 0
    val knightsTour = KnightsTour(boardSize)

    // Display the adjacency matrix of the chess board
    println("Chess Board Adjacency Matrix:")
    knightsTour.displayBoard()

    // Attempt to find a knight's tour starting from square 0
    println("\nAttempting Knight's Tour from square $startPosition:")
    knightsTour.findTour(startPosition)

    println("\nTotal squares on board: ${knightsTour.getNumSquares()}")
}
