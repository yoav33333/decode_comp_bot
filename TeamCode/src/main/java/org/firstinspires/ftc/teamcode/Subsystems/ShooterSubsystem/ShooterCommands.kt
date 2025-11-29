package org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem

import dev.nextftc.core.commands.utility.InstantCommand

object ShooterCommands {
    fun setVelocity(velocity: Double) =
        InstantCommand{ ShooterHardware.setVelocity(velocity)}
    fun setHoodPosition(position: Double) =
        InstantCommand{ ShooterHardware.setHoodPosition(position)}
}