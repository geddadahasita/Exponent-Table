//John Chapin:  john.chapin@lcps.org

import javax.swing.JFrame;

public class RocketDriver {

  public static void main(String[] args) {
	  long startTime = System.currentTimeMillis();


    JFrame frame = new JFrame("Falcon9");
    frame.setSize(1000, 1000);
    frame.setLocation(430, 0);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new RocketPanel());
    frame.setVisible(true);
  }
}


