package View;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Game;

import java.awt.Color;
 
public class ViewGame extends JFrame{
	
  public ViewGame(Game game){       
    JFrame fenetre = new JFrame();
    this.setTitle("Rpg");
    this.setSize(400, 250);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setAlwaysOnTop(true);
    
    
  }
  
}
    
    
