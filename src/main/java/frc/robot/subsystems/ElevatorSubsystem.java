// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
  PWMVictorSPX m_motor;
  SlewRateLimiter speedFilter = new SlewRateLimiter(0.5);
  public ElevatorSubsystem() {
    // The device is Spark, but for some reason PWMVictorSPX works better
    m_motor = new PWMVictorSPX(Constants.ELEVATOR_PORT);
  }

  public void setSpeed(double speed) {
    // SmartDashboard.putNumber("elevator speed", speed);
    m_motor.set(speedFilter.calculate(speed));
  }

  // This method will be called once per scheduler run
  @Override
  public void periodic() {
  }
}