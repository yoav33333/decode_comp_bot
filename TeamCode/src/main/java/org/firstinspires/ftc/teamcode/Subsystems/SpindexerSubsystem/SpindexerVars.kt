package org.firstinspires.ftc.teamcode.Subsystems.SpindexerSubsystem

import com.bylazar.configurables.annotations.Configurable

@Configurable
object SpindexerVars {
    @JvmField var p = 0.0
    @JvmField var i = 0.0
    @JvmField var d = 0.0
    @JvmField var f = 0.0
    @JvmField var targetPosition = 0.0
    @JvmField var degreesPerSlot = 30
    @JvmField var intakeSlot = 0
    @JvmField var transferSlot = 1
    @JvmField var spinDelay = 0.1

}