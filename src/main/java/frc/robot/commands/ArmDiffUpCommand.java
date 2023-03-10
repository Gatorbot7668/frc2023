// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.armDifferentialDrive;

public class ArmDiffUpCommand extends CommandBase {
  /** Creates a new ArmDiffUp. */
  private final armDifferentialDrive m_diffArm;
  private final double m_direction;

  public ArmDiffUpCommand(armDifferentialDrive diffArm, float direction) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_diffArm = diffArm;
    m_direction = direction;
    addRequirements(m_diffArm);
    m_diffArm.stopArm();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_diffArm.liftArm(m_direction);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_diffArm.stopArm();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
