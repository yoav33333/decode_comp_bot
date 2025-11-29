package org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem

import dev.nextftc.control.builder.controlSystem
import dev.nextftc.core.components.Component
import dev.nextftc.hardware.controllable.RunToVelocity
import dev.nextftc.hardware.delegates.Velocity
import dev.nextftc.hardware.impl.MotorEx
import dev.nextftc.hardware.impl.ServoEx
import org.firstinspires.ftc.teamcode.Robot.MyTelemetry
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem.ShooterVars.targetVelocity
import org.firstinspires.ftc.teamcode.Util.VelocityPid
//TODO: Finish Shooter Hardware

object ShooterHardware: Component {
    val shooterMotor1 = lazy{MotorEx("shooterMotor1")}
    val shooterMotor2 = lazy{MotorEx("shooterMotor2").reversed()}
    val hoodServo = lazy{ ServoEx("hoodServo")}
    val controller = VelocityPid(
        ShooterVars.p,
        ShooterVars.i,
        ShooterVars.d
    )
    fun setHoodPosition(position: Double) {
        hoodServo.value.position = position
    }
    fun getHoodPosition(): Double {
        return hoodServo.value.position
    }
    fun setPower(power: Double) {
        shooterMotor1.value.power = power
        shooterMotor2.value.power = power
    }
    fun getVelocity(): Double {
        return shooterMotor1.value.velocity
    }
    fun getPosition(): Double {
        return shooterMotor1.value.currentPosition.toDouble()
    }

    fun setVelocity(velocity: Double) {
        targetVelocity = velocity
        controller.targetVelocity = velocity
    }
    fun atTargetVelocity(): Boolean {
        return controller.atSetpoint()
    }

    override fun preInit() {
        controller.reset()

    }
    fun updatePID(){
        controller.targetVelocity = targetVelocity
        controller.setPID(ShooterVars.p, ShooterVars.i, ShooterVars.d)
        setPower(controller.calculate(getVelocity()))
    }
    override fun postUpdate() {
        updatePID()
        MyTelemetry.addData("Shooter position", getPosition())
        MyTelemetry.addData("Shooter velocity", getVelocity())
        MyTelemetry.addData("Hood position", getHoodPosition())
    }

}