// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// 2/10/23 --> first commit from new computer!!!!!!!!!
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
//import edu.wpi.first.wpilibj.motorcontrol.Talon;




public class GrabberSubsystem extends SubsystemBase {
  /** Creates a new grabber. */
  Compressor pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
  DoubleSolenoid pitchSolenoid;
  
  boolean pressureSwitch;
  public GrabberSubsystem() {
        pitchSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.PNEUMATICS_SOLENOID_DEPLOY, Constants.PNEUMATICS_SOLENOID_RETRACT);
    
    }
    


    
      public void pitchup(){
         pitchSolenoid.set(kForward);
    
      }
    
      public void pitchdown(){
        pitchSolenoid.set(kReverse);
      }
    
      public void CompressorOn(){
        pcmCompressor.enableDigital();
      }
      public void CompressorOff(){
        pcmCompressor.disable();
      }
    

  @Override
  public void periodic() {
    /*
        // This method will be called once per scheduler run
        pressureSwitch =     pcmCompressor.getPressureSwitchValue();
        if (pressureSwitch) {
          pcmCompressor.disable();
        }
        else {
          pcmCompressor.enableDigital();
        }
        */
    // This method will be called once per scheduler run
  }
  public void initDefaultCommand(){
    
  }
}
