package org.firstinspires.ftc.teamcode.OpModes

import dev.nextftc.bindings.BindingManager
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.ftc.components.LoopTimeComponent
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.IntakeSubsystem.IntakeHardware
import org.firstinspires.ftc.teamcode.Pedro.Constants
import org.firstinspires.ftc.teamcode.Robot.MyTelemetry
import org.firstinspires.ftc.teamcode.Util.LoopTimer

open class MegiddoOpMode: NextFTCOpMode() {
    init {
        addComponents(
            BindingsComponent,
            BulkReadComponent,
            LoopTimer,
            MyTelemetry,
            PedroComponent(Constants::createFollower),
            IntakeHardware
        )
    }
}