package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveManuallyCommand;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.GrabCommand;
import frc.robot.commands.PhotonVision;
import frc.robot.commands.Seek;
import frc.robot.commands.ShowTargetsCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.armDifferentialDrive;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.commands.ArmCommand;
import frc.robot.commands.ArmDiffUpCommand;
import frc.robot.commands.GrabOpenCommand;
import frc.robot.commands.ArmDownCommand;
import frc.robot.commands.ArmDiffUpCommand;

public class RobotContainer {// The robot's subsystems and commands are defined here...
  private final XboxController m_controller = new XboxController(0);
  private final DriveTrainSubsystem m_driveTrainSubsystem = new DriveTrainSubsystem();
  private final armDifferentialDrive m_armDiffSubsystem = new armDifferentialDrive();
  private final Seek m_seek = new Seek(m_driveTrainSubsystem, -.1, 40);
  public final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();
  public final GrabberSubsystem m_grabberSubsystem = new GrabberSubsystem();
  public final ArmSubsystem m_armSubsystem = new ArmSubsystem();
 
 // private final FlapsSubsystem m_flaps = new FlapsSubsystem();
 
  public RobotContainer() {
    // One day we should switch to using
    // https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
    //m_driveTrainSubsystem.setDefaultCommand(new DriveManuallyCommand(m_driveTrainSubsystem, m_controller,
      //  XboxController.Axis.kRightX, XboxController.Axis.kLeftY));
    m_elevatorSubsystem.setDefaultCommand(
      new ElevatorCommand(m_elevatorSubsystem, m_controller, XboxController.Axis.kLeftTrigger, XboxController.Axis.kRightTrigger));
      
    new JoystickButton(m_controller, XboxController.Button.kB.value)
      .whileTrue(new GrabCommand(m_grabberSubsystem));
      new JoystickButton(m_controller, XboxController.Button.kA.value)
      .whileTrue(new GrabOpenCommand(m_grabberSubsystem));


    new JoystickButton(m_controller, XboxController.Button.kX.value)
      .whileTrue(new ArmCommand(m_armSubsystem, 0.2, 0.5));

    new JoystickButton(m_controller, XboxController.Button.kX.value).whileTrue(new ArmDiffUpCommand(m_armDiffSubsystem,1));
    //new JoystickButton(m_controller, XboxController.Button.kX.value).whileTrue(new ArmDownCommand(m_armSubsystem, 0.2, 0.5));

    new JoystickButton(m_controller, XboxController.Button.kY.value)
    // .onTrue(new LiftCommand(m_lift, -.8, 40));
    // .whileTrue(m_seek);
      .whileTrue(new PhotonVision(m_driveTrainSubsystem));
    //.whileTrue(new Seek(m_seek, -.8, 40));

    // Add information to the Shuffleboard for monitoring and Troubleshooting
    Shuffleboard.getTab("DriveTrainDisplay");
    // This code adds values to the "DriveTrainDisplay" Tab on the Shuffleboard.
    // This code is dependent on two methods added to the RobotContainer to access
    // the data to be displayed
    Shuffleboard.getTab("DriveTrainDisplay").addNumber("Joystick-Y", this::getJoystickYValue)
        .withWidget(BuiltInWidgets.kNumberSlider).withPosition(6, 3);
    Shuffleboard.getTab("DriveTrainDisplay").addNumber("Joystick-X", this::getJoystickXValue)
        .withWidget(BuiltInWidgets.kNumberSlider).withPosition(6, 4);
    // This adds a command to the Shuffleboard
 // Shuffleboard.getTab("DriveTrainDisplay").add(new DriveForward50(m_driveTrainSubsystem)).withPosition(6, 0)
      //  .withSize(2, 2);
    }
 

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null; // m_autocommand;  
  }

  // These are used by the commands to add data to the shuffleboard (See the
  // RobotContainer Constructor)
  private double getJoystickXValue() {
    return m_controller.getRightX();
  }

  private double getJoystickYValue() {
    return -m_controller.getLeftY();
  }

  private double getLeftTriggerValue() {
    return m_controller.getLeftTriggerAxis();
  }
  
  private double getRightTriggerValue() {
    return m_controller.getRightTriggerAxis();
  }
}