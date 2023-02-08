package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeReleaseSubsystem;

public class IntakeReleaseCommand extends CommandBase {
    private final IntakeReleaseSubsystem m_rintake;
    private final double m_speed;
    private final int m_limit;
    private int m_count;

    public IntakeReleaseCommand(IntakeReleaseSubsystem intake, double speed, int limit) {
        m_rintake = intake;
        m_speed = speed;
        m_limit = limit;
        addRequirements(m_rintake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_count = 0;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_rintake.setSpeed(m_speed);
        m_count = m_count + 1;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_rintake.setSpeed(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return m_count == m_limit;
    }
}
