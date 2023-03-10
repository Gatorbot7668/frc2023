// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class armDifferentialDrive extends SubsystemBase {
  /** Creates a new armDifferentialDrive. */
  // private final DifferentialDrive _diffArmDrive;
  // private final MotorControllerGroup _diffArmGroup;
  private final WPI_TalonSRX _armChain;
  private final WPI_TalonSRX _armSpool;

  // private final AHRS m_navx;
  public armDifferentialDrive() {
    _armChain = new WPI_TalonSRX(Constants.ARM_PORT);
    _armSpool = new WPI_TalonSRX(Constants.SPOOL_PORT);

// _diffArmGroup = new MotorControllerGroup(_armSpool,_armChain);
// _diffArmDrive = new DifferentialDrive(_armChain, _armSpool);
// _diffArmDrive.setDeadband(.3);
// _diffArmDrive.setMaxOutput(.55);


  }

  public void liftArm(double speed){
    // _armChain.set(0.2);
    _armSpool.set(speed * 0.35);
    
    // chain = Constants.ARM_CHAIN_RATIO*chain;
    // spool = Constants.ARM_SPOOL_RATIO*spool;

    // _diffArmDrive.tankDrive(chain, spool);
    
  }
  public void stopArm(){
    // _armChain.set(0);
    _armSpool.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   
  }
}
