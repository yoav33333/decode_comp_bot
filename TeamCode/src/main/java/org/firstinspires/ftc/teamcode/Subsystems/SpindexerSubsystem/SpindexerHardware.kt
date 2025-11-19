package org.firstinspires.ftc.teamcode.Subsystems.SpindexerSubsystem

import dev.nextftc.core.components.Component
import dev.nextftc.hardware.impl.CRServoEx
import dev.nextftc.hardware.impl.ServoEx

object SpindexerHardware: Component {
    val spindexerServo1 = lazy { CRServoEx("spindexerServo1") }
}