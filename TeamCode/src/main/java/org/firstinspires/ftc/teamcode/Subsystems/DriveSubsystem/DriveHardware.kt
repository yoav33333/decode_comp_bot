package org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem

import PoseKalmanFilter
import com.pedropathing.geometry.Pose
import com.pedropathing.math.Vector
import dev.nextftc.core.components.Component
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem.DriveVars.trustDeadHeading
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem.DriveVars.trustDeadX
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem.DriveVars.trustDeadY
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem.DriveVars.trustLLHeading
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem.DriveVars.trustLLX
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem.DriveVars.trustLLY
import org.firstinspires.ftc.teamcode.Pedro.Tuning.follower
import org.firstinspires.ftc.teamcode.Subsystems.LL.LimeLight
import org.firstinspires.ftc.teamcode.Subsystems.Robot.RobotVars
import org.firstinspires.ftc.teamcode.Subsystems.Robot.RobotVars.vectorFromTarget

object DriveHardware: Component {
    val filter = PoseKalmanFilter(Pose(0.0, 0.0, 0.0),
        trustDeadX, trustDeadY, trustDeadHeading,
        trustLLX, trustLLY, trustLLHeading)

    fun getPoseEstimate(): Pose {
        return follower.pose
    }
    fun setPoseEstimate(pose: Pose) {
        follower.pose = pose
    }
    fun updatePoseEstimate(aprilTagPose: Pose?) {
        filter.predict(getPoseEstimate())

        // Update with AprilTag when available
        val aprilTagPose = aprilTagPose // may be null
        if (aprilTagPose != null && !filter.isOutlier(aprilTagPose)) {
            filter.update(aprilTagPose)
        }

        // Get fused pose - this is your robot's position
        setPoseEstimate(filter.getPose())
    }


    override fun postInit() {

    }
    override fun postUpdate() {

        updatePoseEstimate(LimeLight.getPose())
        vectorFromTarget = getPoseEstimate().asVector.minus(RobotVars.goalPos)

    }
}