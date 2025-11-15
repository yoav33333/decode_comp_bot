package org.firstinspires.ftc.teamcode.Robot

import android.R
import com.bylazar.telemetry.JoinedTelemetry
import com.bylazar.telemetry.PanelsTelemetry
import dev.nextftc.core.components.Component
import dev.nextftc.ftc.ActiveOpMode
import java.util.Objects

object Telemetry : Component{
    val joinedTelemetry = lazy{JoinedTelemetry(PanelsTelemetry.ftcTelemetry, ActiveOpMode.telemetry)}
    val data: MutableMap<String, Objects> = MutableMapOf()
    override fun postUpdate() {
        data.forEach { (key, value) ->
            joinedTelemetry.value.addData(key, value)
        }
        joinedTelemetry.value.update()
        data.clear()
    }

}