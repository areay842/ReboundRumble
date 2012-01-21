package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;

public class MainCode extends IterativeRobot
{
    private Joystick joy1;
    private RobotDrive drive;
    
    public void robotInit()
    {
        try
        {
            joy1 = new Joystick(1);
            drive = new RobotDrive(1,2,3,4);
        }
        catch(Exception e)
        {
            System.out.println("error: "+e);
        }
    }
    public void autonomousPeriodic() {
        System.out.println("Periodic Section.");
    }

    public void teleopPeriodic() {
        try
        {
            System.out.println("Teleop mode engaged.");
            drive.arcadeDrive(joy1);
            Timer.delay(0.005);
        }
        catch(Exception e)
        {
            System.out.println("error2: "+e);
        }
    }   
}