/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends Command {
  
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-orion");
  double tv = NetworkTableInstance.getDefault().getTable("limelight-orion").getEntry("tv").getDouble(0);
  double tx = NetworkTableInstance.getDefault().getTable("limelight-orion").getEntry("tx").getDouble(0);
  double ty = NetworkTableInstance.getDefault().getTable("limelight-orion").getEntry("ty").getDouble(0);
  double ta = NetworkTableInstance.getDefault().getTable("limelight-orion").getEntry("ta").getDouble(0);

  boolean limelightHasTarget = false;
  double targetArea = 8; //this is our desired ta value from the limelight
  double steerK = .5; //how hard we wanna turn towards the target
  double driveK = .5; //how speedy fast we wanna go towards the target

  public Limelight() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double steerAdjust = steerK * tx;
    double driveAdjust = (targetArea - ta) * driveK;

    if(tv > 0){
      limelightHasTarget = true;
    }else{
      limelightHasTarget = false;
    }

    if(limelightHasTarget){
      //use drive cmd for sully here, passing in steerAdjust and driveAdjust
      //might not need the drive adjust tbh, just use the driveK for pwr
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
