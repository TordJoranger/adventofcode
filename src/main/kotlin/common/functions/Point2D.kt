package main.common.functions

import kotlin.math.atan2
import kotlin.math.sqrt

interface Point2D<N: Number, Self: Point2D<N, Self>> {
    val x: N
    val y: N

    fun offset(xOffset: N, yOffset: N): Self

    fun offset(vector: Self): Self = offset(vector.x, vector.y)

    /**
     * Returns the diff with the supplied coordinate as a new coordinate (representing the vector)
     */
    fun vector(to: Self): Self

    /**
     * Returns the Manhatten distance to the specified coordinate
     */
    fun manhattenDistance(to: Self): N

    /**
     * Returns the shortest Euclidean distance to the specified coordinate
     */
    fun distance(to: Self): Double {
        val deltaX = (x.toDouble() - to.x.toDouble())
        val deltaY = (y.toDouble() - to.y.toDouble())
        return sqrt(deltaX * deltaX + deltaY * deltaY)
    }

    /**
     * The inverse
     */
    fun inverted(): Self

    /**
     * Normalizes the coordinate by dividing both x and y by their greatest common divisor
     */
    fun normalized(): Self

    fun directNeighbourSequence(): Sequence<Self>

    fun indirectNeighbourSequence(): Sequence<Self>

    fun allNeighbourSequence(): Sequence<Self>

    /**
     * Returns the direct neighbours of this coordinate
     */
    val directNeighbours: List<Self>

    /**
     * Returns the indirect neighbours of this coordinate, defined as the diagonal neighbours
     */
    val indirectNeighbours: List<Self>

    val allNeighbours: List<Self>

    val isHorizontal: Boolean

    val isVertical: Boolean

    /**
     * Returns the angle between 0 and 2 * PI relative to the specified vector
     */


    operator fun get(index: Int): N {
        return when (index) {
            0 -> x
            1 -> y
            else -> throw IllegalArgumentException("Invalid index supplied")
        }
    }

    operator fun plus(other: Self): Self {
        return offset(other)
    }

    operator fun unaryMinus(): Self {
        return inverted()
    }

    operator fun minus(other: Self): Self {
        return offset(other.inverted())
    }

    operator fun times(other: Self): Self

    operator fun times(scalar: N): Self
}