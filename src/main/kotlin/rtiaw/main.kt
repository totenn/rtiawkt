package rtiaw

import kotlin.math.sqrt

fun hitSphere(center: Vec3, radius: Double, r: Ray): Double {
    val oc = r.origin - center
    val a = r.direction.lengthSquared()
    val halfB = dot(oc, r.direction)
    val c = oc.lengthSquared() - radius * radius
    val discriminant = halfB * halfB - a * c
    return if (discriminant < 0) -1.0 else (-halfB - sqrt(discriminant)) / a
}

fun rayColor(r: Ray): Vec3 {
    var t = hitSphere(Vec3(0.0, 0.0, -1.0), 0.5, r)
    if (t > 0.0) {
        val N = unitVector(r.at(t) - Vec3(0.0, 0.0, -1.0))
        return 0.5 * Vec3(N.x + 1, N.y + 1, N.z + 1)
    }
    val unitDirection: Vec3 = unitVector(r.direction)
    t = 0.5 * (unitDirection.y + 1.0)
    return (1.0 - t) * Vec3(1.0, 1.0, 1.0) + t * Vec3(0.5, 0.7, 1.0)
}

fun main(args: Array<String>) {
    val imageWidth = 200
    val imageHeight = 100
    println("P3\n${imageWidth} $imageHeight \n255")
    val lowerLeftCorner = Vec3(-2.0, -1.0, -1.0)
    val horizontal = Vec3(4.0, 0.0, 0.0)
    val vertical = Vec3(0.0, 2.0, 0.0)
    val origin = Vec3(0.0, 0.0, 0.0)
    for (j in imageHeight downTo 0) {
        System.err.print("\rScanlines remaining: $j ")
        for (i in 0 until imageWidth) {
            val u = i.toDouble() / imageWidth
            val v = j.toDouble() / imageHeight
            val r = Ray(origin, lowerLeftCorner + u * horizontal + v * vertical)
            val color = rayColor(r)
            color.writeColor(System.out)
        }
    }
    System.err.println("\nDone.")
}
