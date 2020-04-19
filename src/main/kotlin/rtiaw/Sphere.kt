package rtiaw

import kotlin.math.sqrt

class Sphere(val center: Vec3, val radius: Double) : Hittable {
    override fun hit(r: Ray, tMin: Double, tMax: Double): HitRecord? {
        val oc = r.origin - center
        val a = r.direction.lengthSquared()
        val halfB = dot(oc, r.direction)
        val c = oc.lengthSquared() - radius * radius
        val discriminant = halfB * halfB - a * c

        if (discriminant > 0) {
            val root = sqrt(discriminant)
            var temp = (-halfB - root) / a
            if (temp < tMax && temp > tMin) {
                val t = temp
                val p = r.at(t)
                val outwardNormal = (p - center) / radius
                return HitRecord(p, t, r, outwardNormal)
            }
            temp = (-halfB + root) / a
            if (temp < tMax && temp > tMin) {
                val t = temp
                val p = r.at(t)
                val outwardNormal = (p - center) / radius
                return HitRecord(p, t, r, outwardNormal)
            }
        }
        return null
    }
}
