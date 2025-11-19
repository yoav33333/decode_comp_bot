package org.firstinspires.ftc.teamcode.Util

object Util {
    fun wrap360(angle: Double): Double {
        return ((angle % 360) + 360) % 360
    }
    fun wrap360(angle: Int): Int {
        return ((angle % 360) + 360) % 360
    }
}