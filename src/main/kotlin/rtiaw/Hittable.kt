package rtiaw

class HitRecord(val p: Vec3, val normal: Vec3, val t: Double, val frontFace: Boolean) {
    constructor(p: Vec3, t: Double, r: Ray, outwardNormal: Vec3) : this(
        p,
        if (dot(r.direction, outwardNormal) < 0) outwardNormal else -outwardNormal,
        t,
        dot(r.direction, outwardNormal) < 0
    )
}

interface Hittable {
    fun hit(r: Ray, tMin: Double, tMax: Double): HitRecord?
}
