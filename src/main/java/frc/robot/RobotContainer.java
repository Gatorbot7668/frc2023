package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveManuallyCommand;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.PhotonVision;
import frc.robot.commands.Seek;
import frc.robot.commands.ShowTargetsCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.IntakeReleaseSubsystem;
import frc.robot.subsystems.IntakeWheelsSubsystem;
import frc.robot.subsystems.ConveyerbeltSubsystem;
import frc.robot.commands.IntakeReleaseCommand;
import frc.robot.commands.IntakeWheelsCommand;
import frc.robot.commands.Auto;
import frc.robot.commands.ConveyerbeltAuto;
import frc.robot.commands.ConveyerbeltCommand;

public class RobotContainer {// The robot's subsystems and commands are defined here...
  private final XboxController m_controller = new XboxController(0);
  private final DriveTrainSubsystem m_driveTrainSubsystem = new DriveTrainSubsystem();
  private final IntakeReleaseSubsystem m_rintake = new IntakeReleaseSubsystem ();
  private final ConveyerbeltSubsystem m_conveyer = new ConveyerbeltSubsystem ();
  private final IntakeWheelsSubsystem m_wintake = new IntakeWheelsSubsystem ();
  private final Auto m_autocommand = new Auto(m_driveTrainSubsystem, m_conveyer);
  private final Seek m_seek = new Seek(m_driveTrainSubsystem, -.1, 40);
  public static GrabberSubsystem m_grabber = new GrabberSubsystem();
  public final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();
 
 // private final FlapsSubsystem m_flaps = new FlapsSubsystem();
 
  public RobotContainer() {
    // One day we should switch to using
    // https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
    m_driveTrainSubsystem.setDefaultCommand(new DriveManuallyCommand(m_driveTrainSubsystem, m_controller,
        XboxController.Axis.kRightX, XboxController.Axis.kLeftY));

    new JoystickButton(m_controller, XboxController.Button.kStart.value)
      .onTrue(new IntakeReleaseCommand(m_rintake, .57, 20));

    new JoystickButton(m_controller, XboxController.Button.kBack.value)
      .onTrue(new IntakeReleaseCommand(m_rintake, -.55, 20));
     
    new JoystickButton(m_controller, XboxController.Button.kRightBumper.value)
      .onTrue(new ConveyerbeltAuto(m_conveyer, -0.9, 80));

    new JoystickButton(m_controller, XboxController.Button.kB.value)
      .onTrue(new ConveyerbeltAuto(m_conveyer, 0.7, 80))
      .onFalse(new ConveyerbeltCommand(m_conveyer, 0));

    new JoystickButton(m_controller, XboxController.Button.kLeftBumper.value)
      .onTrue(new IntakeWheelsCommand(m_wintake, -.4, 30));

    new JoystickButton(m_controller, XboxController.Button.kA.value)
      .whileTrue(new ElevatorCommand(m_elevatorSubsystem, 0.5, 0.5));

    new JoystickButton(m_controller, XboxController.Button.kX.value)
    //  .onTrue(new LiftCommand(m_lift, .7, 40));
      .whileTrue(new ShowTargetsCommand());

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
    return m_autocommand;  
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