package org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem

import dev.nextftc.core.commands.utility.InstantCommand
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem.IntakeHardware.setPower
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem.ShooterVars.targetVelocity

object ShooterCommands {
    val shoot = InstantCommand { setPower(targetVelocity) }

    val stopshoot = InstantCommand { setPower(0.0) }
}