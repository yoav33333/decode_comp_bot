package org.firstinspires.ftc.teamcode.Subsystems.SpindexerSubsystem

import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.commands.utility.InstantCommand
import dev.nextftc.core.commands.utility.LambdaCommand
import org.firstinspires.ftc.teamcode.Subsystems.Robot.RobotVars
import org.firstinspires.ftc.teamcode.Util.SpindexerSlotState
import kotlin.time.Duration.Companion.seconds

object SpindexerCommands {
    fun rotate(steps: Int) =
        InstantCommand{ SpindexerHardware.rotate(steps) }
    fun moveToIntakePosition() =
        InstantCommand{ SpindexerHardware.moveEmptyToIntakePosition() }
    fun moveToTransferPosition(color: SpindexerSlotState) =
        InstantCommand{ SpindexerHardware.moveColorToTransferPosition(color) }
    fun moveToTransferPositionLocking(color: SpindexerSlotState) = LambdaCommand()
        .setStart{ SpindexerHardware.moveColorToTransferPosition(color) }
        .setIsDone { SpindexerHardware.isAtTargetPosition()  }

    val transferAll =
        SequentialGroup(
            moveToTransferPositionLocking(RobotVars.randomization.value[0]),
            Delay(SpindexerVars.spinDelay.seconds),
            moveToTransferPositionLocking(RobotVars.randomization.value[1]),
            Delay(SpindexerVars.spinDelay.seconds),
            moveToTransferPositionLocking(RobotVars.randomization.value[2]),
            Delay(SpindexerVars.spinDelay.seconds),
        )
}