package graph

/**
 * KnightsTour represents a Knight's Tour problem on a chess board.
 * It creates a graph where each vertex is a square on the chess board,
 * and edges represent valid knight moves between squares.
 */
class KnightsTour(
    val boardSize: Int,
) {
    private val numSquares = boardSize * boardSize
    private val chessBoard = Graph(numSquares)

    // Set of all possible knight moves as vector offsets
    private val moves =
        setOf(
            Point(+2, +1),
            Point(+2, -1),
            Point(-2, +1),
            Point(-2, -1),
            Point(+1, +2),
            Point(+1, -2),
            Point(-1, +2),
            Point(-1, -2),
        )

    init {
        setupBoard()
    }

    /**
     * Sets up the chess board by creating vertices for each square
     * and adding edges for valid knight moves between squares.
     */
    private fun setupBoard() {
        for (i in 0 until numSquares) {
            chessBoard.addVertex(i.toString())
        }

        for (i in 0 until numSquares) {
            val origin = i.toPoint(boardSize)
            val destinations = getValidMoves(origin)
            destinations.forEach {
                chessBoard.addEdge(i, it.toIndex(boardSize))
            }
        }
    }

    /**
     * Calculates all valid knight moves from a given position on the board.
     *
     * @param origin The starting position as a Point(x,y)
     * @return A sequence of valid destination Points
     */
    private fun getValidMoves(origin: Point): Sequence<Point> =
        moves
            .asSequence()
            .map { origin + it }
            .filter { it.x in 0 until boardSize && it.y in 0 until boardSize }

    /**
     * Attempts to find a knight's tour starting from the specified square.
     *
     * @param startIndex The starting square (0-based index)
     */
    fun findTour(startIndex: Int) {
        chessBoard.knightsTour(startIndex)
    }

    /**
     * Displays the adjacency matrix of the chess board graph.
     */
    fun displayBoard() {
        chessBoard.displayAdjacencyMatrix()
    }

    /**
     * Returns the number of squares on the board.
     */
    fun getNumSquares(): Int = chessBoard.numVertex
}
