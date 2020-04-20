package rtiaw

class Camera(val lowerLeftCorner: Vec3, val horizontal: Vec3, val vertical: Vec3, val origin: Vec3) {
    constructor() : this(
        Vec3(-2.0, -1.0, -1.0),
        Vec3(4.0, 0.0, 0.0),
        Vec3(0.0, 2.0, 0.0),
        Vec3(0.0, 0.0, 0.0)
    )

    fun getRay(u: Double, v: Double): Ray {
        return Ray(origin, lowerLeftCorner + u * horizontal + v * vertical - origin)
    }
}
