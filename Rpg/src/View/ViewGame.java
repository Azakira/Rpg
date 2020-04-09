package View;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Field.Floor;
import Field.Containables.Ground;
import Field.Containables.Ladder;
import Field.Containables.Wall;
import Field.Containables.Actors.Hero;
import Field.Containables.Actors.Mobs.Mob;
import Model.Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
 
public class ViewGame extends JPanel implements Observer, KeyListener{

	
	public static final int size = 20;
	private Game game;
	
	public ViewGame(Game game){
		this.game = game;
	    JFrame fenetre = new JFrame();
	    this.setName("Rpg");
//	    this.setPreferredSize(new Dimension((size+2)*Floor.WIDTH, (size+2)*Floor.HEIGHT));
	    this.setVisible(true);
	}


  @Override
  public void paintComponent(Graphics g) {
	  super.paintComponent(g);
	  if (this.game.readyToDisplay == false) {
		  return;
	  }
	  Floor floor = this.game.getCurrentFloor();
	  for(int i = 0; i<floor.HEIGHT; i++) {
		  for(int j = 0; j<floor.WIDTH; j++) {
			  
			  //if Wall
			  if (floor.getGrid(j,i) instanceof Wall) {
				  g.setColor(Color.DARK_GRAY);
				  g.fillRect((size+2)*j,(size+2)*i,size,size);
			  }
			  
			  //if Ground
			  if (floor.getGrid(j,i) instanceof Ground) {
				  
				  //if there is something on the Ground
				  if(( (Ground) floor.getGrid(j,i)).isOccupied()) {
					  
					  //if there is the Hero on the Ground
					  if(( (Ground) floor.getGrid(j,i)).getOnGround() instanceof Hero) {
						  g.setColor(Color.DARK_GRAY);
						  g.drawRect((size+2)*j,(size+2)*i,size,size);
						  g.setColor(Color.YELLOW);
						  g.fillRect((size+2)*j,(size+2)*i,size,size);
					  }
					  
					  //if there is a Mob on the Ground
					  if(( (Ground) floor.getGrid(j,i)).getOnGround() instanceof Mob) {
						  g.setColor(Color.DARK_GRAY);
						  g.drawRect((size+2)*j,(size+2)*i,size,size);
						  g.setColor(Color.RED);
						  g.fillRect((size+2)*j,(size+2)*i,size,size);
					  }
					  
					  //if there is a Ladder on the Ground
					  if(( (Ground) floor.getGrid(j,i)).getOnGround() instanceof Ladder) {
						  g.setColor(Color.DARK_GRAY);
						  g.drawRect((size+2)*j,(size+2)*i,size,size);
						  g.setColor(Color.GREEN);
						  g.fillRect((size+2)*j,(size+2)*i,size,size);
					  }
					  
					  //end of the "Ground occupied" cases
					  
				  } else { //if there is nothing on the ground
					  g.setColor(Color.LIGHT_GRAY);
					  g.drawRect((size+2)*j,(size+2)*i,size,size);
//					  g.drawString(Integer.toString(((Ground)floor.getGrid(j,i)).getNbRoom()),(size+2)*j,(size+2)*i+10); //Draw nbRoom
				  }
			  }
				  
		  }
	  }
  }
  

	@Override
	public void keyPressed(KeyEvent key) {
		int codeDeLaTouche = key.getKeyCode();
       
      switch (codeDeLaTouche) // Les valeurs sont contenue dans KeyEvent. Elles commencent par VK_ et finissent par le nom de la touche
      {
          case KeyEvent.VK_UP: // si la touche enfoncée est celle du haut
              game.hero.moveUp();
              break;
          case KeyEvent.VK_LEFT: // si la touche enfoncée est celle de gauche
          	game.hero.moveLeft();
              break;
          case KeyEvent.VK_RIGHT: // si la touche enfoncée est celle de droite
          	game.hero.moveRight();
              break;
          case KeyEvent.VK_DOWN: // si la touche enfoncée est celle du bas
          	game.hero.moveDown();
              break;
      }
		
	}
  
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

  @Override
  public void update(Observable arg0, Object arg1) {
	  this.repaint();
  }



	
}
  

    
    
