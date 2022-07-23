//Programmer:  John Chapin      john.chapin@lcps.org
//
//Display and Robot libraries are from Thomas Jefferson High School's .jar libraries for JKarel
// the classpath must be adjusted to add xerceslmpl.jar and fcpsKarel9-07.jar

import edu.fcps.karel2.Display;
import edu.fcps.karel2.Robot;
import javax.swing.JOptionPane;

/**
 * A class to simulate projectile motion using the JKarel Robot class.
 * User can input initial velocity, height off the ground and initial angle to launch.
 * The program will output the calculated horizontal distance for 90 angles
 *  starting at 0 degrees and ending at 90 degrees.  It will then output the
 *  angle that resulted in the maximum horizontal distance, and the height and time.
 *  The class also displays the trajectory for this "best angle" as well as 15 degrees
 *  above and below the best angle.
 *
 */

public class CatapultShellExt
{
	/**
	 *  Method to return the X coordinate at the given time.
	 */
   private static double calculateX(double initVelocityX, double time)
   {
      return (initVelocityX * time);      
       // ****** STEP 1: Fill in the calculation
   }

   /**
    * Method to return the Y coordinate at the given time.
    */
   private static double calculateY(double initVelocityY, double time, double acceleration)
   {
      return (initVelocityY*time + 0.5*acceleration*time*time);
      // ******** STEP 1: Fill in the calculation
   }
   
   /**
    * Method to display the Robots at X and Y positions on a JKarel map based on
    * @param initialVelocity
    * @param accelerationGravity
    * @param angle
    * @param maxTime
    * @param groundHeight
    * 
    */
   private static void robotTrajectory (double initialVelocity, double accelerationGravity, double angle, double maxTime,double groundHeight)
   {
      double newY = 0;
      double newX = 0;
      double time = 0;
   
      double deltaTime = maxTime/100;
      double initialAngleRadians = angle *(Math.PI/180);
      double initialXVelocity = initialVelocity * Math.cos(initialAngleRadians);
      double initialYVelocity = initialVelocity * Math.sin(initialAngleRadians);
      
      
      
      //  ***** STEP 3: Fill in code to Draw Robots for each time interval
      while (newY >= groundHeight)
      {
         newX = calculateX(initialXVelocity, time);
         newY = calculateY(initialYVelocity, time, accelerationGravity);
         time = time + deltaTime;
         Robot hasi = new Robot((int)newX, (int)newY - (int)groundHeight, Display.EAST, 0);
      } 
   
      
      
      System.out.printf("ANGLE: %,1.2f   max distance: %,1.2f     time in the air %,1.2f",angle, newX,time);
      System.out.println("");
   } 
   
   public static void main(String[] args)
   
   {
     // initialize variables
      double time = 0.0;
      double deltaTime = .1;
      double initialAngleDegrees = Double.parseDouble((String)JOptionPane.showInputDialog("Please enter an initial angle"));
      double initialVelocity = Double.parseDouble((String)JOptionPane.showInputDialog("Please enter an initial velocity"));;
      double groundHeight =(-1)* Double.parseDouble((String)JOptionPane.showInputDialog("Please enter the height off the ground of the Catapult"));;
      
      double accelerationGravity = -9.8;
         
      double initialAngleRadians = initialAngleDegrees *(Math.PI/180);
   
      double initialXVelocity = initialVelocity * Math.cos(initialAngleRadians);
      double initialYVelocity = initialVelocity * Math.sin(initialAngleRadians);
      
      double newX = 0;
      double newY = 0;
      
      double maxX = 0;
      double maxY = 0;
      double maxTime = 0;
      double bestAngle = 0;
   
      
      //*****  STEP 2: Fill in code to Calculate best angle
      //use the board
      
      for(int testAngle =1; testAngle <= 90; testAngle++)
      {
      
         newX = 0;
         newY = 0;
         time = 0;
         initialAngleRadians = testAngle * (Math.PI/180);
         initialXVelocity = initialVelocity * Math.cos(initialAngleRadians);
         initialYVelocity = initialVelocity * Math.sin(initialAngleRadians);
         
         
         while (newY >= groundHeight)
         {
            newX = calculateX(initialXVelocity, time);
            newY = calculateY(initialYVelocity, time, accelerationGravity);
            time = time + deltaTime;
         
            if (newX > maxX)
            {
               maxX = newX;
               bestAngle = testAngle;
               maxTime = time;
            }   
            else if (newY > maxY)
            {
               maxY = newY;
            }   
         } // while 
           
       
      
      // *********  STEP 2: Fill in code to Test each angle
      
          
         
         //*********  STEP 1: Fill in code to calculate the distance traveled for the angle
        // and print
          
        
         System.out.printf("Angle: " + testAngle + "     Horizontal Distance: %,1.2f",newX);
         System.out.println(" ");
         
         //********* STEP 2: Fill in code to Keep track of the best angle
            
      }//for
      
      System.out.println(" *********************************************************");
      System.out.println("Best Angle: " + bestAngle + "     Horizontal Distance: " + maxX);
      System.out.printf("Max Height:  %,1.2f     Max time in the air: %,1.2f",maxY,maxTime);
      System.out.println("");
      System.out.println(" *********************************************************");
   
      //Set the display size based on max values
      Display.setSize((((int)maxX) + 12),(((int)maxY) +12));
      Display.setSpeed(10);
        
      // ********   STEP 3: Fill in code to Draw the Robots for the best trajectory and 15 degrees above and below
      
      robotTrajectory( initialVelocity, accelerationGravity, bestAngle, maxTime,groundHeight);
      robotTrajectory( initialVelocity, accelerationGravity, bestAngle + 15, maxTime,groundHeight);
      robotTrajectory( initialVelocity, accelerationGravity, bestAngle - 15, maxTime,groundHeight);
      robotTrajectory( initialVelocity, accelerationGravity, initialAngleDegrees, maxTime,groundHeight);
   
   
   
   
               
   
        
   }//main
}//Catapult
