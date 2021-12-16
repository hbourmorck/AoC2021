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


class Point2D(val X:Int, val Y: Int)
{

    override fun equals(other: Any?): Boolean {
        if(other is Point2D)
        {
            return other.Y == this.Y && other.X == this.X
        }
        return false
    }

    fun neighbors(withDiagonal: Boolean = false): List<Point2D> =
        when {
            withDiagonal -> listOf(
                Point2D(X, Y + 1),
                Point2D(X, Y - 1),
                Point2D(X + 1, Y),
                Point2D(X - 1, Y),
                Point2D(X-1, Y-1),
                Point2D(X-1, Y+1),
                Point2D(X+1, Y+1),
                Point2D(X-1, Y+1)
            )
            else -> listOf(
                Point2D(X, Y + 1),
                Point2D(X, Y - 1),
                Point2D(X + 1, Y),
                Point2D(X - 1, Y)
            )
        }
}
class PointValue(val position:Point2D, val value: Int)

class Line(val startPoint:Point2D, val endPoint: Point2D)
{
    fun isHorizontal() = startPoint.Y == endPoint.Y
    fun isVertical() = startPoint.X == endPoint.X
    fun isDiagonal45Degree() = abs(startPoint.X - endPoint.X) == abs(startPoint.Y - endPoint.Y)
}