package graph

data class Point(
    val x: Int = 0,
    val y: Int = 0,
)

operator fun Point.plus(other: Point) = Point(x + other.x, y + other.y)

fun Int.toPoint(boardSize: Int) = Point(this % boardSize, this / boardSize)

fun Point.toIndex(boardSize: Int) = y * boardSize + x
