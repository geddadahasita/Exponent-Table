//John Chapin:  Modified for colors, shuffle, class lab, Threads
//Chris Schroeder: Modified to update the Swing UI on demand instead of using two threads and timers.
//John Chapin: Modified to draw a Rocket

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.Random;

public class RocketPanel extends JPanel {

  private BufferedImage myImage;
  private int scale = 10;
  private final int screenSize = 1000;
  private final int frameRate = 10;
  private final int delayLength = 1000/frameRate;


  public RocketPanel() {

    //Initial set up the graphics area
    getPanelGraphics();
	//drawRocket(1000, 50, 5);
	//public RocketCalc(double aCalcsPerSecond,  double aMass,double aAltitude, double aBurnTime, 
	//double aTotalFuelConsumption,double atotalThrust, RocketPanel aPanel)

    RocketCalc nami = new RocketCalc(10, 540000, 0, 162, 398000, 6806000, this);

    Thread rocketThread = new Thread(nami);
    
    rocketThread.start();
    

    // 1 *************  Draw the rocket 20 times  increasing the height by 500 meters

  }//end of the RocketPanel Constructor
  
  
  /**
   * Implement the drawPanel method, hiding the Swing UI elements from the RocketCalc object
   *
   */
  public void updateRocket(int aAltitude, double aVelocity, double time)  {
      // draw the Rocket and then sleep for "delay length" 
	  //2 ******************   Input CODE to draw and sleep
	  
	  try {
	      SwingUtilities.invokeAndWait(new Runnable() {
	        @Override
	        public void run() {
	          drawRocket(aAltitude,aVelocity, time);
	          try {
	            Thread.sleep(delayLength-5);
	          } catch (InterruptedException ex) {
	            Thread.currentThread().interrupt();
	          }
	        }
	      });
	    } catch (Exception e) {
	      e.printStackTrace();
	    }	   


  }

  public void paintComponent(Graphics g) {
    g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
  }

  
  /**
   * Get the graphics area we are painting to. If the image area does not exists, then create it first
   * @return Graphics area to pain to....
   */
  
  private Graphics getPanelGraphics(){
	    //set up the buffer
		  
		  
	    if (null == myImage){ 
	      myImage = new BufferedImage(screenSize, screenSize, BufferedImage.TYPE_INT_RGB);
	    }
	    int w = screenSize;
	    int h = screenSize; 
	    
	    //Create a graduated background from blue to white
	    
	    Graphics2D g2d = (Graphics2D) myImage.getGraphics();;
	    
	    //sunset background
	    Color color1 = new Color(200,75,255);//purple
	    Color color2 = new Color(250,150,50);//orange 

	    GradientPaint gp = new GradientPaint(
	            0, 0, color1,
	            0, h, color2);

	    g2d.setPaint(gp);
	    
	    //Paint the screen with the graduated background to "clear" the screen 
	    //for the new changes
	    g2d.fillRect(0, 0, w, h);
	    
	    return g2d;
	    
	  }
	  
	 
	  //******************************************************************
	       // String formattedAltitude = String.format("%.1f", AltitudeInKM);


  /**
   * Draw the rocket on the panel
   */
  private void drawRocket(int aAltitude, double aVelocity, double time) {
  {
    // Paste background on screen to "clear it"
   
	  
	  Graphics graphics = getPanelGraphics();
	  
     //*************************   
    // 4 ***************** Create a INT screenAltitude and adjust the aAltitude to fit on screen
    // *******************  dividing the Altitude by 10 is good
    int screenAltitude = aAltitude / scale;
 
    
     
    
    
    // Create and initialize r,g,b
      int red = 0;
      int green = 0;
      int blue = 0;
      
      // resetting the size to see the rocket
      
      //for(int t = 7000; t < 30000; t += 19000) {
      if(aAltitude > 7000) {
    	  scale = 30;
      double adjustedAltitude = aAltitude/scale; 
      	if(aAltitude > 26000) {
      		scale = 70;
      		double secondAdjustedAltitude = aAltitude/scale;
      	if(aAltitude > 67000) {
      		scale = 90;
      		double thirdAdjustedAltitude = aAltitude/scale;
      		}
      	}
      }
      //YPositionOfClouds
      
      Color white = new Color (255, 255, 255);
      graphics.setColor(white);

     // 5 ******************   Display the Velocity and the altitude
     // set the font for the velocity and altitude
     // graphics.setFont(new Font("Sans Serif", Font.PLAIN, 12));
      //setting font 
      graphics.drawString("Velocity: " + aVelocity, 100, 460);
      graphics.drawString("Altitude: " + aAltitude, 100, 490);
      graphics.drawString("Time: " + time,  100,  520);
      
      // 6 *****************  Draw velocity and Altitude and time Strings
      //convert velocity from meters to Kilometers
      
     // 7 **********     Draw the clouds
     
      Color gray = new Color(200, 200, 200);
      graphics.setColor(gray);
      
      int cloudAltitude = 5000/scale;
      int cloudCount = 15;
      int YPositionOfClouds = (screenSize)/scale * 3;
      int widthOfClouds = screenSize/(cloudCount*2);
      int heightOfClouds = (int)(widthOfClouds/2);

      for (int i = 0; i <= cloudCount; i ++)
      {
    	graphics.fillOval(i * (widthOfClouds + 10) * 2 , YPositionOfClouds, widthOfClouds, heightOfClouds);  
    	graphics.fillOval(i * (widthOfClouds + 10) * 2 , YPositionOfClouds+5, widthOfClouds+5, heightOfClouds+5);  
    	//graphics.fillOval(i * (widthOfClouds + 10) * 2,  YPositionOfClouds + 10,  widthOfClouds + 30,  heightOfClouds + 10);

      }
      
      // 8 ************ Draw rocket (with just a rectangle and a nose-cone)
      
     /* if(aAltitude > YPositionOfClouds) {
    	  scale = 30;
       double adjustedAltitude = aAltitude/scale; 
     }*/
      //***********  FILL IN VALUES
      
      
      int rocketLength = 1600/scale;
    		  
      int rectHeight = rocketLength;
      int rectWidth = rocketLength/5;
      int rectX = screenSize/2 - (rectWidth/2);
      int rectY = (screenSize-rocketLength) - screenAltitude;
      int baseOfRocket = rectY + rocketLength; 
      int noseWidth = rectWidth + 1;
      int noseHeight = rectHeight * 2;
      int noseX = rectX - 1;
      int noseY = (rectY - noseHeight/2);
   
      
      
     // 9 *** draw nose and Rectangle
      graphics.setColor(new Color(50, 0, 50));
      graphics.fillRect(rectX, rectY, rectWidth, rectHeight);
      graphics.fillOval(noseX,  noseY,  noseWidth,  noseHeight);
      
      //  10 ***  Extension  - put letters on rocket
      
     /* 
      graphics.setColor(Color.WHITE);
      graphics.drawString("N",  (rectX * (scale/scale)) + (rectWidth/3), rectY + (100/scale));
      graphics.drawString("A", (rectX * (scale/scale)) + (rectWidth/3), rectY + (400/scale));
      graphics.drawString("M", (rectX * (scale/scale)) + (rectWidth/3), rectY + (700/scale));
      graphics.drawString("I",  (rectX * (scale/scale)) + (rectWidth/3), rectY + (1000/scale));
     */
      
 /*
      graphics.setColor(Color.WHITE);
      graphics.drawString("N",  495,  700);
      graphics.drawString("A", 495,  750);
      graphics.drawString("M", 495,  800);
      graphics.drawString("I", 497,  850);
*/

 //Fire at bottom of the rocket    
      int ovalWidth = noseWidth;
      
      int ovalX = noseX;
      int ovalY = noseY;
      int fireSize=ovalWidth;
      double[][]fire = new double[fireSize*10][fireSize];
      
      //set first row to 0 or 99 randomly
      for(int row=0;row<=0;row++){
          for(int col=0;col<fire[0].length;col++){
        	  if(Math.random()>.5)
        		  fire[row][col] = 99;
        	  else
        		  fire[row][col] = 0;
          }
      }

      //Set bottom to max or min;

    
      //average values with rows below top row
      for(int row=1;row<fire.length;row++){
    	  //don't calculate the outside cols
          for(int col=1;col<fire[0].length-1;col++){
        	  double aboveLeft = fire[row-1] [col-1];
        	  double above = fire[row-1][col];
        	  double aboveRight = fire[row-1][col+1];
        	  fire[row][col]= (aboveLeft+above+aboveRight)/3;
        	  
        	  double fireVal = fire[row][col];
        	  red = 255;
        	  green = 0;
        	  blue = 0;
        	  
        	  //255, 0, 0 is red
        	  //255,255,255 is white and 255,255,0 is yellow
        	  //255,0-255,0 is yellow to red
        	  
        	  // a fireval of 99 is white hot
        	  // a fireval of 0 is red
        	  
        	  if (fireVal>=50)//white (255,255,255) to yellow(255,255,0) - very hot
        	  {
        		  green = 255;
        		  blue = (int)((fireVal-50)/50*255);
        	  }
        	  else//yellow to red (255,255,0) to (255,0,0)
        	  {
        		  blue = 0;
        		  green = (int)(fireVal/50*255);
        	  }
   		   //graphics.setColor(new Color(255,255-(int)(fire[row][col]*255/99),0));
   		   
   		   //if the fireval is less than 20 don't show any color (it is too cool)
   		   //this give the cone effect
          graphics.setColor(new Color (red,green,blue));
   		   if(fireVal>20)
           graphics.fillRect(ovalX+(col), (baseOfRocket)+(row), 2,2);
        	 
          }// Columns -for loop average values and draw rect after average
      }// rows - average values
    
    
//redraw the screen
    repaint();
  	}
  } 
