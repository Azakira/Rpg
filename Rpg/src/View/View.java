package View;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Field.Floor;
import Model.Game;


public class View {
	
	public final int WIDTH = (ViewGame.size+3)*Floor.WIDTH;
	public final int HEIGHT = (ViewGame.size+4)*Floor.HEIGHT;
	//public final static String path = "D:\\Linux\\POGL\\Coltexpress\\src\\game\\Images\\";
	
    private JFrame frame;

    public ViewGame vGame; //TODO: make it private after testing
    
   
    public View(Game game) {
    	game.view = this;
    	frame = new JFrame();
    	frame.setTitle("Rpg");
//		frame.setLayout(new BorderLayout());
    	frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		vGame = new ViewGame(game);
		frame.add(vGame); //frame.add(vGame, BorderLayout.WEST);
		
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
    }
}