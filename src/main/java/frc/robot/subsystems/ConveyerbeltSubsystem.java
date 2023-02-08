package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ConveyerbeltSubsystem extends SubsystemBase {
  MotorControllerGroup m_conveyer;

  public ConveyerbeltSubsystem() {
    // The device is Spark, but for some reason PWMVictorSPX works better
    PWMVictorSPX m_leftc = new PWMVictorSPX(Constants.RAMP_LEFT);
    PWMVictorSPX m_rightc = new PWMVictorSPX(Constants.RAMP_RIGHT);
    m_leftc.setInverted(true);
    m_conveyer = new MotorControllerGroup(m_rightc, m_leftc);


  }


  public void setSpeed(double speed) {
    SmartDashboard.putNumber("conveyer", speed);
    m_conveyer.set(speed); // the % output of the motor, between -1 and 1
  }

  // This method will be called once per scheduler run
  @Override
  public void periodic() {
  }
}
