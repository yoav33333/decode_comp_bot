package org.firstinspires.ftc.teamcode.Subsystems.SpindexerSubsystem

import com.qualcomm.hardware.rev.RevColorSensorV3
import dev.nextftc.core.components.Component
import dev.nextftc.ftc.ActiveOpMode.hardwareMap
import dev.nextftc.hardware.impl.CRServoEx
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit
import org.firstinspires.ftc.teamcode.Subsystems.Robot.MyTelemetry
import org.firstinspires.ftc.teamcode.Subsystems.SpindexerSubsystem.SpindexerVars.defaultColor
import org.firstinspires.ftc.teamcode.Subsystems.SpindexerSubsystem.SpindexerVars.greenRange
import org.firstinspires.ftc.teamcode.Subsystems.SpindexerSubsystem.SpindexerVars.intakeSlot
import org.firstinspires.ftc.teamcode.Subsystems.SpindexerSubsystem.SpindexerVars.purpleRange
import org.firstinspires.ftc.teamcode.Subsystems.SpindexerSubsystem.SpindexerVars.targetPosition
import org.firstinspires.ftc.teamcode.Util.AnglePID
import org.firstinspires.ftc.teamcode.Util.AxonEncoder
import org.firstinspires.ftc.teamcode.Util.SpindexerSlotState
import org.firstinspires.ftc.teamcode.Util.SpindexerTracker
import org.firstinspires.ftc.teamcode.Util.Util
//TODO: Tune PIDF values
//TODO: add color sensor integration
object SpindexerHardware: Component {
    val spindexerEncoder = lazy { AxonEncoder("spindexerEnc") }
    val spindexerServo1 = lazy { CRServoEx("spindexerServo1") }
    val spindexerServo2 = lazy { CRServoEx("spindexerServo2") }
    val colorSensor = lazy { hardwareMap.get(RevColorSensorV3::class.java, "intakeColor") }
    val tracker = SpindexerTracker()
    val anglePID = AnglePID(SpindexerVars.p, SpindexerVars.i, SpindexerVars.d, SpindexerVars.f)
    override fun preInit() {
        anglePID.reset()
        anglePID.setTolerance(5.0)
    }
    fun hasBallInIntake(): Boolean {
        return colorSensor.value.getDistance(DistanceUnit.MM) < SpindexerVars.distThreshold
    }
    fun isFull(): Boolean {
        return tracker.isFull()
    }
    fun getColorInIntake(): SpindexerSlotState {
        if (!hasBallInIntake()) {
            return SpindexerSlotState.EMPTY
        }
        val colors = colorSensor.value.normalizedColors
        val red = colors.red
        val blue = colors.blue
        val green = colors.green
        MyTelemetry.addData("Intake Color Sensor Red", red)
        MyTelemetry.addData("Intake Color Sensor Blue", blue)
        MyTelemetry.addData("Intake Color Sensor Green", green)
        return if (greenRange.inRange(red, green, blue)) {
            SpindexerSlotState.PURPLE
        } else if (greenRange.inRange(red, green, blue)) {
            SpindexerSlotState.GREEN
        } else {
            defaultColor
        }
    }
    fun checkIntakeColorAndUpdate(): Boolean{
        val color = getColorInIntake()
        if (color != SpindexerSlotState.EMPTY){
            tracker.set(intakeSlot, color)
            return true
        }
        return false
    }
    fun setPower(power: Double) {
        spindexerServo1.value.power = power
        spindexerServo2.value.power = -power
    }

    fun getPosition(): Double {
        return spindexerEncoder.value.getPosition()
    }

    fun moveStateToPosition(color: SpindexerSlotState, pos: Int) : Boolean{
        val steps = tracker.stepsToState(color, pos)
        if (steps == null) return false
        rotate(steps)
        return true
    }

    fun moveEmptyToIntakePosition(): Boolean {
        return moveStateToPosition(SpindexerSlotState.EMPTY, SpindexerVars.intakeSlot)
    }
    fun moveColorToTransferPosition(color: SpindexerSlotState): Boolean {
        return moveStateToPosition(color, SpindexerVars.transferSlot)
    }
    fun rotate(steps: Int) {
        targetPosition += Util.wrap360(steps * SpindexerVars.degreesPerSlot)
        tracker.rotate(steps)
    }
    fun isAtTargetPosition(): Boolean {
        return anglePID.atSetPoint()
    }
    fun updatePid() {
        anglePID.setPIDF(SpindexerVars.p, SpindexerVars.i, SpindexerVars.d, SpindexerVars.f)
        anglePID.setTargetPosition(targetPosition)
        setPower(anglePID.calculate(getPosition()))
    }
    override fun postUpdate() {
        updatePid()
        MyTelemetry.addData("Spindexer Position", getPosition())
        MyTelemetry.addData("Spindexer state", tracker.toString())
    }

}