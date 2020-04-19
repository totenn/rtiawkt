package rtiaw

class Ray(var origin: Vec3, var direction: Vec3) {
    constructor() : this(Vec3(), Vec3())

    fun at(t: Double): Vec3 {
        return origin + t * direction
    }
}
