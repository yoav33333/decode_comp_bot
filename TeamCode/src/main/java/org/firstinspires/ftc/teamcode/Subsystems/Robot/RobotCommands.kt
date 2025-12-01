package org.firstinspires.ftc.teamcode.Subsystems.Robot

import dev.nextftc.core.commands.conditionals.IfElseCommand
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.ParallelRaceGroup
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.commands.utility.LambdaCommand
import dev.nextftc.core.commands.utility.NullCommand
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem.IntakeCommands
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem.IntakeCommands.outtake
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem.IntakeCommands.stopIntake
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem.IntakeHardware
import org.firstinspires.ftc.teamcode.Subsystems.SpindexerSubsystem.SpindexerCommands.runIntakeSeq
import org.firstinspires.ftc.teamcode.Subsystems.SpindexerSubsystem.SpindexerHardware.getColorInIntake
import org.firstinspires.ftc.teamcode.Subsystems.SpindexerSubsystem.SpindexerHardware.isFull
import org.firstinspires.ftc.teamcode.Util.UtilCommands.ParallelRaceGroupKill
import kotlin.time.Duration.Companion.seconds

object RobotCommands {
    val intakeCommand =
        SequentialGroup(
        ParallelRaceGroupKill(
            IntakeCommands.intake,
            runIntakeSeq
            ),
            IfElseCommand(
                {isFull()},
                SequentialGroup(
                    outtake,
                    Delay(.8.seconds),
                    stopIntake
                ),
                NullCommand()
            )
        )

}