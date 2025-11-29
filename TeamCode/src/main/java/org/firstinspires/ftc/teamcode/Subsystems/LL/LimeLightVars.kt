package org.firstinspires.ftc.teamcode.Subsystems.LL

import com.bylazar.configurables.annotations.Configurable
import com.pedropathing.geometry.Pose
import org.firstinspires.ftc.teamcode.Util.Util.mmToInches

@Configurable
object LimeLightVars {
    @JvmField var aprilTagPipeline = 0
    @JvmField var centerOfRotationOffset = Pose(mmToInches(68.0), mmToInches(28.06))
    @JvmField var offsetFromAxis = mmToInches(54.5)

}