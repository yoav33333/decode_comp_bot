package org.firstinspires.ftc.teamcode.IntakeSubsystem

import dev.nextftc.core.commands.utility.InstantCommand
import org.firstinspires.ftc.teamcode.IntakeSubsystem.IntakeHardware.setPower
import org.firstinspires.ftc.teamcode.IntakeSubsystem.IntakeVars.intakePower

object IntakeCommands {
    val intake = InstantCommand{setPower(intakePower)}
    val stopIntake = InstantCommand { setPower(0.0) }
}