package org.firstinspires.ftc.teamcode.DriveSubsystem

import com.bylazar.configurables.annotations.Configurable

@Configurable
object DriveVars {
    //kalman filter trust values for odometry
    @JvmField var trustDeadX = 1.0
    @JvmField var trustDeadY = 1.0
    @JvmField var trustDeadHeading = 0.005
    //kalman filter trust values for LL
    @JvmField var trustLLX = 1.0
    @JvmField var trustLLY = 1.0
    @JvmField var trustLLHeading = 0.005
}