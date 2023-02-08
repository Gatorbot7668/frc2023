package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ConveyerbeltSubsystem;

public class ConveyerbeltCommand extends CommandBase {
    private final ConveyerbeltSubsystem m_conveyer;
    private final double m_speed;

    public ConveyerbeltCommand(ConveyerbeltSubsystem conveyer, double speed) {
        m_conveyer = conveyer;
        m_speed = speed;
        addRequirements(m_conveyer);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_conveyer.setSpeed(m_speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_conveyer.setSpeed(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
        
    }
}
