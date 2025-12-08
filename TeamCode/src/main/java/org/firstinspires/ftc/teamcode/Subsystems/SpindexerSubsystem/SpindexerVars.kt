package org.firstinspires.ftc.teamcode.Subsystems.SpindexerSubsystem

import com.bylazar.configurables.annotations.Configurable
import org.firstinspires.ftc.teamcode.Util.ColorRange
import org.firstinspires.ftc.teamcode.Util.SpindexerSlotState

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
    @JvmField var spinDelay = 0.5
    @JvmField var purpleRange = ColorRange(0.0,0.0,0.0,0.0,0.0,0.0)
    @JvmField var greenRange = ColorRange(0.0,0.0,0.0,0.0,0.0,0.0)
    @JvmField var distThreshold = 30
    @JvmField var defaultColor = SpindexerSlotState.PURPLE
}