package View;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Field.Floor;
import Model.Game;


public class View extends JFrame implements KeyListener {
	
	public final int WIDTH = (ViewGame.size+3)*Floor.WIDTH;
	public final int HEIGHT = (ViewGame.size+4)*Floor.HEIGHT;
	//public final static String path = "D:\\Linux\\POGL\\Coltexpress\\src\\game\\Images\\";
	public Game game;


    public ViewGame vGame; //TODO: make it private after testing
    
   
    public View(Game game) {
    	this.game = game;
    	game.view = this;
    	this.setTitle("Rpg");
//		frame.setLayout(new BorderLayout());
    	this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		vGame = new ViewGame(game);
		this.add(vGame); //frame.add(vGame, BorderLayout.WEST);
		
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		addKeyListener(this);
		requestFocusInWindow();
    }


	@Override
	public void keyPressed(KeyEvent key) {
		int codeDeLaTouche = key.getKeyCode();
       
		switch (codeDeLaTouche) // Les valeurs sont contenue dans KeyEvent. Elles commencent par VK_ et finissent par le nom de la touche
		{
          case KeyEvent.VK_UP: 
              game.hero.moveUp();
              break;
          case KeyEvent.VK_LEFT:
          	game.hero.moveLeft();
              break;
          case KeyEvent.VK_RIGHT: 
          	game.hero.moveRight();
              break;
          case KeyEvent.VK_DOWN:
          	game.hero.moveDown();
              break;
          case KeyEvent.VK_SPACE:
        	game.hero.mine();
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
}