package rtiaw

import java.io.OutputStream
import kotlin.math.sqrt

class Vec3(var x: Double, var y: Double, var z: Double) {
    constructor() : this(0.0, 0.0, 0.0)
    constructor(e: Array<Double>) : this(e[0], e[1], e[2])

    operator fun unaryMinus() {
        this.x = -x
        this.y = -y
        this.z = -z
    }

    operator fun get(i: Int): Double {
        return when (i) {
            0 -> x
            1 -> y
            2 -> z
            else -> throw IndexOutOfBoundsException(i)
        }
    }

    operator fun minusAssign(v: Vec3) {
        this.x -= v.x
        this.y -= v.y
        this.z -= v.z
    }

    operator fun timesAssign(t: Double) {
        this.x *= t
        this.y *= t
        this.z *= t
    }

    operator fun divAssign(t: Double) {
        this *= 1 / t
    }

    fun length(): Double {
        return sqrt(lengthSquared())
    }

    fun lengthSquared(): Double {
        return x * x + y * y + z * z
    }

    fun writeColor(out: OutputStream) {
        out.write("${(255.999 * x).toInt()} ${(255.999 * y).toInt()} ${(255.999 * z).toInt()}\n".toByteArray())
    }

    override fun toString(): String {
        return "$x $y $z"
    }

    operator fun plus(v: Vec3): Vec3 {
        return Vec3(x + v.x, y + v.y, z + v.z)
    }

    operator fun minus(v: Vec3): Vec3 {
        return Vec3(x - v.x, y - v.y, z - v.z)
    }

    operator fun times(v: Vec3): Vec3 {
        return Vec3(x * v.x, y * v.y, z * v.z)
    }

    operator fun times(t: Double): Vec3 {
        return t * this
    }

    operator fun div(t: Double): Vec3 {
        return (1 / t) * this
    }
}

operator fun Double.times(v: Vec3): Vec3 {
    return Vec3(this * v.x, this * v.y, this * v.z)
}

fun dot(u: Vec3, v: Vec3): Double {
    return u.x * v.x + u.y * v.y + u.z * v.z
}

fun cross(u: Vec3, v: Vec3): Vec3 {
    return Vec3(
        u.y * v.z - u.z * v.y,
        u.z * v.x - u.x * v.z,
        u.x * v.y - u.y * v.x
    );
}

fun unitVector(v: Vec3): Vec3 {
    return v / v.length()
}
