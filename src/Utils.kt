import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.math.abs

/**
 * Reads lines from the given input txt file.
 */
fun readInput(parent:String, name: String) = File(parent, "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)


class Point(val X:Int, val Y: Int)
{

    fun neighbors(): List<Point> =
        listOf(
            Point(X , Y + 1),
            Point(X , Y - 1),
            Point(X + 1 , Y),
            Point(X - 1 , Y)
        )
}
class PointValue(val position:Point, val value: Int)

class Line(val startPoint:Point, val endPoint: Point)
{
    fun isHorizontal() = startPoint.Y == endPoint.Y
    fun isVertical() = startPoint.X == endPoint.X
    fun isDiagonal45Degree() = abs(startPoint.X - endPoint.X) == abs(startPoint.Y - endPoint.Y)
}