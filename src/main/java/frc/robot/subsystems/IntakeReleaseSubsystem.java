package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeReleaseSubsystem extends SubsystemBase {
  PWMSparkMax m_rintake;

  public IntakeReleaseSubsystem() {
    // The device is Spark, but for some reason PWMVictorSPX works better
    m_rintake = new PWMSparkMax(Constants.intake_drop_PORT);
  }

  public void setSpeed(double speed) {
    SmartDashboard.putNumber("intake", speed);
    m_rintake.set(speed); // the % output of the motor, between -1 and 1
  }

  // This method will be called once per scheduler run
  @Override
  public void periodic() {
  }
}
