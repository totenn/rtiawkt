package rtiaw

class Ray(val origin: Vec3, val direction: Vec3) {
    constructor() : this(Vec3(), Vec3())

    fun at(t: Double): Vec3 {
        return origin + t * direction
    }
}
