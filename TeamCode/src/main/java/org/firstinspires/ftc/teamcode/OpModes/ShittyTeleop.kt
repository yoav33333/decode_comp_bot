package org.firstinspires.ftc.teamcode.OpModes

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.bindings.Button
import dev.nextftc.ftc.Gamepads
import org.firstinspires.ftc.teamcode.DriveSubsystem.DriveCommands
import org.firstinspires.ftc.teamcode.IntakeSubsystem.IntakeCommands

@TeleOp
class ShittyTeleop: MegiddoOpMode() {
    init {
        Gamepads.gamepad2.leftBumper
            .whenBecomesTrue(IntakeCommands.intake)
            .whenBecomesFalse(IntakeCommands.stopIntake)
    }
    override fun onStartButtonPressed() {
        DriveCommands.driverControlled.start()
    }
}