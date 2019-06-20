package View;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Model.Game;


public class View {
 
    private JFrame frame;

    private ViewGame vGame;
    //private ViewCommands vCommands;
    
    public final static String path = "D:\\Linux\\POGL\\Coltexpress\\src\\game\\Images\\";
    public View(Game game) {
    	frame = new JFrame();
    	frame.setTitle("Rpg");
		frame.setLayout(new BorderLayout());
		vGame = new ViewGame(game);
		
		//commands = new ViewCommands(game);
		
		
		frame.setSize(1425, 550);
		frame.add(vGame, BorderLayout.WEST);
		//frame.add(vCommands, BorderLayout.EAST);
		
		//frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
    }
}