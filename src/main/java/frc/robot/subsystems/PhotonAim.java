// package frc.robot.subsystems;

// import org.photonvision.PhotonCamera;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class PhotonAim extends SubsystemBase{
//     PhotonCamera camera =new PhotonCamera("photonvision");
    
//     m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
//     m_chooser.addOption("My Auto", kCustomAuto);
//     SmartDashboard.putData("Auto choices", m_chooser);
// }

// /**
// * This function is called every robot packet, no matter the mode. Use
// * this for items like diagnostics that you want ran during disabled,
// * autonomous, teleoperated and test.
// *
// * <p>This runs after the mode specific periodic functions, but before
// * LiveWindow and SmartDashboard integrated updating.
// */
// @Override
// public void periodic() {
// Update_Photon_Tracking();
// m_autoSelected = m_chooser.getSelected();
// }

// /**
// * This autonomous (along with the chooser code above) shows how to select
// * between different autonomous modes using the dashboard. The sendable
// * chooser code works with the Java SmartDashboard. If you prefer the
// * LabVIEW Dashboard, remove all of the chooser code and uncomment the
// * getString line to get the auto name from the text box below the Gyro
// *
// * <p>You can add additional auto modes by adding additional comparisons to
// * the switch structure below with additional strings. If using the
// * SendableChooser make sure to add them to the chooser code above as well.
// */




// /**
// * This function implements a simple method of generating driving and steering commands
// * based on the tracking data from a limelight camera.
// */
// public void Update_Photon_Tracking()
// {
//     // These numbers must be tuned for your Robot!  Be careful!
//     final double STEER_K = 0.03;                    // how hard to turn toward the target
//     final double DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
//     final double DESIRED_TARGET_AREA = 13.0;        // Area of the target when the robot reaches the wall
//     final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast

//     double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
//     double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
//     double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
//     double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

//     if (tv < 1.0)
//     {
//       m_LimelightHasValidTarget = false;
//       m_LimelightDriveCommand = 0.0;
//       m_LimelightSteerCommand = 0.0;
//       return;
//     }

//     m_LimelightHasValidTarget = true;

//     // Start with proportional steering
//     double steer_cmd = tx * STEER_K;
//     m_LimelightSteerCommand = steer_cmd;

//     // try to drive forward until the target area reaches our desired area
//     double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

//     // don't let the robot drive too fast into the goal
//     if (drive_cmd > MAX_DRIVE)
//     {
//       drive_cmd = MAX_DRIVE;
//     }
//     m_LimelightDriveCommand = drive_cmd;
// }

// // end of discord

