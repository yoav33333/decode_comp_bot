package org.firstinspires.ftc.teamcode.OpModes

import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem.IntakeHardware
import org.firstinspires.ftc.teamcode.Pedro.Constants
import org.firstinspires.ftc.teamcode.Robot.MyTelemetry
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem.ShooterHardware
import org.firstinspires.ftc.teamcode.Subsystems.SpindexerSubsystem.SpindexerHardware
import org.firstinspires.ftc.teamcode.Subsystems.TransferSubsystem.TransferHardware
import org.firstinspires.ftc.teamcode.Util.LoopTimer

open class MegiddoOpMode: NextFTCOpMode() {
    init {
        addComponents(
            BindingsComponent,
            BulkReadComponent,
            LoopTimer,
            MyTelemetry,
            PedroComponent(Constants::createFollower),
            IntakeHardware,
            TransferHardware,
            SpindexerHardware,
            ShooterHardware
        )
    }
}