// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//git hub life

package frc.robot.commands;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class PhotonVision extends CommandBase {
  PhotonCamera camera = new PhotonCamera("OV5647");
  PIDController turnController = new PIDController(1.0/30, 0, 0);
  DriveTrainSubsystem m_driveTrainSubsystem;

  double POWER_THRESHOLD = 0.55;
  double MAX_ANGLE = 30;
  double STOP_ANGLE = 5;
  double MAX_POWER = 0.8;
  double SEEK_POWER = 0.8;

  public PhotonVision(DriveTrainSubsystem driveTrainSubsystem) {
    addRequirements(driveTrainSubsystem);
    m_driveTrainSubsystem = driveTrainSubsystem;
    turnController.setTolerance(STOP_ANGLE);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // NetworkTable ll = NetworkTableInstance.getDefault().getTable("photonvision");
    PhotonPipelineResult result = camera.getLatestResult();
    SmartDashboard.putNumber("num targets", result.getTargets().size());

    double turn = 0;
    double move = 0;
    if (result.hasTargets()) {
      PhotonTrackedTarget target = result.getBestTarget();
      SmartDashboard.putNumber("target ID", target.getFiducialId());
      SmartDashboard.putNumber("target yaw", target.getYaw());
      SmartDashboard.putNumber("target pitch", target.getPitch());

      // turn = -turnController.calculate(target.getYaw());
      double yaw = target.getYaw();
      /*
      if (Math.abs(yaw) > STOP_ANGLE) {
        double sign = yaw / Math.abs(yaw);
        turn = (MAX_POWER - POWER_THRESHOLD) / (MAX_ANGLE - STOP_ANGLE) * (yaw - sign *  MAX_ANGLE) + sign * 1;
      }
      */
      turn = turnController.calculate(yaw);
      if (turnController.atSetpoint()) {
        turn = 0;
      }
    } else {
      SmartDashboard.putNumber("target ID", -1);

      turn = SEEK_POWER;
    }
    m_driveTrainSubsystem.manualDrive(turn, move);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
