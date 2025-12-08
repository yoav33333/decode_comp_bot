package org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem

import dev.nextftc.core.components.Component
import dev.nextftc.hardware.impl.MotorEx
import dev.nextftc.hardware.impl.ServoEx
import org.firstinspires.ftc.teamcode.Subsystems.Robot.MyTelemetry
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem.ShooterVars.deltaThreshold
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem.ShooterVars.hoodMul
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem.ShooterVars.shootPowMul
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem.ShooterVars.targetVelocity
import org.firstinspires.ftc.teamcode.Util.VelocityPid

object ShooterHardware: Component {
    val shooterMotor1 = lazy{MotorEx("shooterMotor1")}
    val shooterMotor2 = lazy{MotorEx("shooterMotor2").reversed()}
    val hoodServo1 = lazy{ ServoEx("hoodServo1")}
    val hoodServo2 = lazy{ ServoEx("hoodServo2")}
    val controller = VelocityPid(
        ShooterVars.p,
        ShooterVars.i,
        ShooterVars.d
    )
    fun setHoodPosition(position: Double) {
        hoodServo1.value.position = position
        hoodServo2.value.position = 1-position
    }
    fun getHoodPosition(): Double {
        return hoodServo1.value.position
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
    fun shoot(distance: Double){
        setVelocity(distance*shootPowMul)
        setHoodPosition(distance*hoodMul)
    }

    override fun preInit() {
        controller.reset()

    }
    fun updatePID(){
        controller.setTolerance(deltaThreshold)
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