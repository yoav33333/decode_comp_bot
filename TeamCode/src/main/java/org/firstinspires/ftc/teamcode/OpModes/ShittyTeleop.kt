package org.firstinspires.ftc.teamcode.OpModes

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.ftc.Gamepads
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem.DriveCommands
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem.IntakeCommands
import org.firstinspires.ftc.teamcode.Subsystems.Robot.AllianceColor
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem.ShooterCommands

@TeleOp
class ShittyTeleop: MegiddoOpMode(AllianceColor.RED) {
    init {
        Gamepads.gamepad2.leftBumper
            .whenBecomesTrue(IntakeCommands.intake)
            .whenBecomesFalse(IntakeCommands.stopIntake)

    }
    override fun onStartButtonPressed() {
        DriveCommands.driverControlled.schedule()
    }
}