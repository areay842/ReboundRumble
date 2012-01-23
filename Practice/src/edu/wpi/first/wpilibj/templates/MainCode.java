package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class MainCode extends IterativeRobot
{
    private Joystick joy1;
    private Joystick joy2;
    private RobotDrive drive;
    private RobotDrive shooter;
    
    Solenoid ballRelease = new Solenoid(3, 1);
    Solenoid bridge = new Solenoid(3, 2);

    boolean fire;
    
    public void robotInit()
    {
        try
        {
            joy1 = new Joystick(1);
            joy2 = new Joystick(2);
            drive = new RobotDrive(5,2,3,4);
            shooter = new RobotDrive(6,10,7,9);
        }
        catch(Exception e)
        {
            System.out.println("robotInit error: "+e);
        }
    }
    public void autonomousPeriodic() {
      System.out.println(" Autonomous Periodic Section.");
    }
    public void teleopPeriodic()
    {
        System.out.println("Teleop mode engaged.");

        //Drive commands
        try
        {  
            drive.tankDrive(joy1, joy2);
            Timer.delay(0.005);
        }
        catch(Exception e)
        {
            System.out.println("drive error: "+e);
        }
        //Shooter Commands
        double lshooter, rshooter, twist, input;
        try
        {
            input = -joy2.getZ();
            twist = joy2.getThrottle();
            if(input>1.0)
                input=1.0;
            lshooter = (input+1)/2;
            rshooter = (input+1)/2;
            
            if(twist>.55)
                rshooter = rshooter;
                
            System.out.println("lshooter value: "+lshooter);
            shooter.tankDrive(lshooter, rshooter);
        }
        catch(Exception e)
        {
            System.out.println("shooter error: "+e);
        }
        //Ball release commands
        try
        {
            if(joy2.getRawButton(1)==true)
                ballRelease.set(true);
                Timer.delay(1.0);
                ballRelease.set(false);
        }
        catch(Exception e)
        {
            System.out.println("ball release error: "+e);
        }
    }
}