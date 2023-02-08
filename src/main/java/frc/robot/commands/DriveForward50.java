package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveForward50 extends CommandBase {
  private final DriveTrainSubsystem m_driveTrain;
  private int m_limit;
  private int m_count;

  public DriveForward50(DriveTrainSubsystem driveTrain, int limit) {
    m_driveTrain = driveTrain;
    m_limit = limit;
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_count = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.manualDrive(0, .75); // Drive straight forward at 75%
    m_count++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.manualDrive(0, 0); // Stop the robot
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_count == m_limit;
  }
}