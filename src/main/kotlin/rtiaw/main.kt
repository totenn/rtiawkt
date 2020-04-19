package rtiaw

fun rayColor(r: Ray, world: Hittable): Vec3 {
    val rec = world.hit(r, 0.0, Double.POSITIVE_INFINITY)
    if (rec != null) {
        return 0.5 * (rec.normal + Vec3(1.0, 1.0, 1.0))
    }
    val unitDirection = unitVector(r.direction)
    val t = 0.5 * (unitDirection.y + 1.0)
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

    val world = HittableList(
        Sphere(Vec3(0.0, 0.0, -1.0), 0.5),
        Sphere(Vec3(0.0, -100.5, -1.0), 100.0)
    )

    for (j in imageHeight downTo 0) {
        System.err.print("\rScanlines remaining: $j ")
        for (i in 0 until imageWidth) {
            val u = i.toDouble() / imageWidth
            val v = j.toDouble() / imageHeight
            val r = Ray(origin, lowerLeftCorner + u * horizontal + v * vertical)

            val color = rayColor(r, world)

            color.writeColor(System.out)
        }
    }

    System.err.println("\nDone.")
}
