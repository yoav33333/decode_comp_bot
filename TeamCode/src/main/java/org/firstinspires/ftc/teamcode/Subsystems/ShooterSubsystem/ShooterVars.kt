package org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem

import com.bylazar.configurables.annotations.Configurable

@Configurable
object ShooterVars {
    @JvmField var shootPowMul = 0.0
    @JvmField var hoodMul = 0.0
    @JvmField var p = 0.0
    @JvmField var i = 0.0
    @JvmField var d = 0.0
    @JvmField var targetVelocity = 0.0
    @JvmField var deltaThreshold = 200.0

}