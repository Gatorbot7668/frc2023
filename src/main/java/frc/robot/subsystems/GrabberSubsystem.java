// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GrabberSubsystem extends SubsystemBase {
  PWMVictorSPX m_motor;
  private int m_count = 0;
  /** Creates a new GrabberSubsystem. */
  public GrabberSubsystem() {
  // The device is Spark, but for some reason PWMVictorSPX works better
    m_motor = new PWMVictorSPX(Constants.GRABBER_PORT);
    //open();
  }

  public void close() {
    m_motor.set(0.2);
    // m_count = 30;
  }
  
  public void open() {
    m_motor.set(-0.2);
  }
  
  public void stop(){
    m_motor.set(0);
  }

  @Override
   public void periodic() {
    /* 
    // This method will be called once per scheduler run
    if (m_count > 0) {
      if (m_count > 1) {
        m_motor.set(0.25);
      } else {
        m_motor.set(0);
      }
      m_count = m_count - 1;
    }
    */
  }
}
