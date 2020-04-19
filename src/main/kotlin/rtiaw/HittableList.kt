package rtiaw

class HittableList() : Hittable {
    val objects: MutableList<Hittable> = mutableListOf()

    constructor(vararg objects: Hittable) : this() {
        for (`object` in objects) {
            add(`object`)
        }
    }

    fun clear() {
        objects.clear()
    }

    fun add(`object`: Hittable) {
        objects.add(`object`)
    }

    override fun hit(r: Ray, tMin: Double, tMax: Double): HitRecord? {
        var tempRec: HitRecord?
        var closestSoFar = tMax
        var rec: HitRecord? = null

        for (`object` in objects) {
            tempRec = `object`.hit(r, tMin, closestSoFar)
            if (tempRec != null) {
                closestSoFar = tempRec.t
                rec = tempRec
            }
        }

        return rec
    }
}
