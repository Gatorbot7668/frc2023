// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Seek extends CommandBase {
  private final DriveTrainSubsystem m_driveTrainSubsystem;
  private int m_limit;
  private int m_count;
  
  /** Creates a new Seek. */
  public Seek(DriveTrainSubsystem driveTrainSubsystem, double timerCount, int seekSpeed) {
    m_driveTrainSubsystem = driveTrainSubsystem;
    m_limit = seekSpeed;
    addRequirements(m_driveTrainSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

 

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
//    System.out.println("Initialize");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
//    System.out.println("Execute");
    double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    double turn = 1.0;
    double move = 0.0;
    if (tv == 1.0) {
      double POWER_THRESHOLD = 0.55;  // motor stop working when power is less than this

      {
        double x_abs_error = Math.abs(tx);
        double x_sign = tx / x_abs_error;
        double STOP_ANGLE = 5;   // angle when to stop
        double START_ANGLE = 25;  // angle when to start
        if (x_abs_error > STOP_ANGLE) {
          turn = 1.0 * ((1 - POWER_THRESHOLD) * (x_abs_error - STOP_ANGLE) / (START_ANGLE - STOP_ANGLE) + POWER_THRESHOLD) * x_sign;
        } else {
          turn = 0;

          double TARGET_AREA = 18;
          double TARGET_ERROR = 3;
          double SLOW_DOWN_DISTRANCE = 10;
          double error = ta - TARGET_AREA;  // positive when too far, negative - overshot (too close)
          double y_abs_error = Math.abs(error);
          NetworkTable ll = NetworkTableInstance.getDefault().getTable("limelight");
          SmartDashboard.putNumber("ye", y_abs_error);
          double y_sign = error / y_abs_error;
          double MAX_SPEED = 1.0;
          if (y_abs_error > SLOW_DOWN_DISTRANCE) {
            move = MAX_SPEED * y_sign;
          } else if (y_abs_error > TARGET_ERROR) {
            move = MAX_SPEED * ((1 - POWER_THRESHOLD) * (y_abs_error - TARGET_ERROR) /
                                (SLOW_DOWN_DISTRANCE - TARGET_ERROR) + POWER_THRESHOLD) * y_sign;
          } else {
            move = 0;
          }        
        }
      }
    }
    m_driveTrainSubsystem.manualDrive(turn, move);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
//    System.out.println("end");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
