// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends CommandBase {
    private final ElevatorSubsystem m_lift;
    private XboxController.Axis m_downAxis;
    private XboxController m_driverController;
    private XboxController.Axis m_upAxis;

    public ElevatorCommand(ElevatorSubsystem lift, XboxController driverController,
                        XboxController.Axis downAxis, XboxController.Axis upAxis) {
        m_lift = lift;
        m_driverController = driverController;
        m_downAxis = downAxis;
        m_upAxis = upAxis;
        addRequirements(m_lift);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double speed = - m_driverController.getRawAxis(m_downAxis.value) +
                m_driverController.getRawAxis(m_upAxis.value);
        m_lift.setSpeed(speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_lift.setSpeed(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
