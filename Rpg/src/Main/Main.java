package Main;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Model.Game;
import View.View;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			
			Game game = new Game();
			View view = new View(game);
		});
	}
	

}
