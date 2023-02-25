package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrainSubsystem extends SubsystemBase {
  private final DifferentialDrive m_drive;
  // private final AHRS m_navx;

  public DriveTrainSubsystem() {
    /*
    WPI_VictorSPX frontLeft = new WPI_VictorSPX(Constants.FRONT_LEFT_TALON_ID);
    WPI_VictorSPX rearLeft = new WPI_VictorSPX(Constants.REAR_LEFT_TALON_ID);
 
    WPI_VictorSPX frontRight = new WPI_VictorSPX(Constants.FRONT_RIGHT_TALON_ID);
    WPI_VictorSPX rearRight = new WPI_VictorSPX(Constants.REAR_RIGHT_TALON_ID);
    */
    WPI_TalonSRX frontLeft = new WPI_TalonSRX(Constants.FRONT_LEFT_TALON_ID);
    WPI_TalonSRX rearLeft = new WPI_TalonSRX(Constants.REAR_LEFT_TALON_ID);
 
    WPI_TalonSRX frontRight = new WPI_TalonSRX(Constants.FRONT_RIGHT_TALON_ID);
    WPI_TalonSRX rearRight = new WPI_TalonSRX(Constants.REAR_RIGHT_TALON_ID);

    MotorControllerGroup left = new MotorControllerGroup(frontLeft, rearLeft);
    MotorControllerGroup right = new MotorControllerGroup(frontRight, rearRight);

    m_drive = new DifferentialDrive(left, right);
    m_drive.setDeadband(0.3);
    m_drive.setMaxOutput(0.55);

    // m_navx = new AHRS(SPI.Port.kMXP);

    frontLeft.configOpenloopRamp(.5);
    rearLeft.configOpenloopRamp(.5);
    frontRight.configOpenloopRamp(.5);
    rearRight.configOpenloopRamp(.5);
  }

  public void manualDrive(double turn, double move) {
     // Reduce speed, full 1.0 makes the motors unhappy 
    SmartDashboard.putNumber("move", move);
    SmartDashboard.putNumber("turn", turn);

    /*
    move = move * 0.55;
    turn = turn * 0.55;
    */
    m_drive.arcadeDrive(turn, move);
    // m_drive.feed();
  }

  public void resetGyro() {
    // m_navx.reset();
  }

  public double getCurrentHeading() {
    return 0;
    // return m_navx.getAngle();
  }

  // This method is called once per scheduler run
  @Override
  public void periodic() {
    /*
    SmartDashboard.putNumber("gyro angle", m_navx.getAngle());
    SmartDashboard.putNumber("gyro yaw", m_navx.getYaw());
    SmartDashboard.putBoolean("gyro calubrating", m_navx.isCalibrating());
    */
  }

}
  
  // Reset the shaft encoder value
 // if (resetEncoder == true) {
  //  m_drive.setSelectedSensorPosition(0); 
 // }