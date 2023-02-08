package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LiftSubsystem;

public class LiftCommand extends CommandBase {
    private final LiftSubsystem m_lift;
    private final double m_speed;
    private double m_count;
    private final double m_limit;

    public LiftCommand(LiftSubsystem lift, double speed, double limit) {
        m_lift = lift;
        m_speed = speed;
        m_limit = limit;
        addRequirements(m_lift);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_count = 0;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_lift.setSpeed(m_speed);
        m_count = m_count + 1;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_lift.setSpeed(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return m_count == m_limit;
    }
}
