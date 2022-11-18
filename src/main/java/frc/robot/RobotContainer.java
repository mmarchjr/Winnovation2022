// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Brush;

import frc.robot.commands.DriveRobot;
import frc.robot.commands.BrushCommand;
import frc.robot.commands.StopBrush;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //Subsystems
  public static Drivetrain m_Drivetrain = new Drivetrain();
  public static Brush m_Brush = new Brush();
  

  //Commands
  public static DriveRobot m_DriveRobot = new DriveRobot();
  public static BrushCommand m_BrushCommand = new BrushCommand();
  public static StopBrush m_StopBrush = new StopBrush();

  //OI
  public static XboxController xbox  = new XboxController(Constants.c_joystick);
  //getPOV can be used to find the ange value of the d-Pad on the xbox controller

  public static JoystickButton stopBrushButton = new JoystickButton(xbox, Constants.c_stopBrushMotor);
  public static JoystickButton runBrushButton = new JoystickButton(xbox, Constants.c_runBrush);
  
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
     
    // Configure the button bindings
    configureButtonBindings();
    //Is nessary, might have been the reason for the error "DifferntialDrive...Output not updated often enough"
    m_Drivetrain.setDefaultCommand(m_DriveRobot);
    //m_Brush.setDefaultCommand(m_BrushCommand);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
   //stopBrushButton.debounce(0.1, DebounceType.kBoth).toggleWhenActive(m_StopBrush);
   //runBrushButton.whenPressed(m_BrushCommand);

   stopBrushButton.debounce(0.1, DebounceType.kBoth).toggleWhenActive(new StartEndCommand(m_Brush::spinbrush, m_Brush::stopbrush, m_Brush));

// this works but wrong when you put in new stopbrush() and new brushcommand()

  }



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
 
}
