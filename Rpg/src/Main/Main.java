package Main;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Model.Game;
import View.View;

/** class main qui instancie game et vieuw
@param string args
@return void
**/
public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			
			Game game = new Game();
			View view = new View(game);
			game.hero.moveDown();
			game.hero.moveDown();
			game.hero.moveDown();
			game.hero.moveDown();
			game.hero.moveDown();
			game.hero.mine();
			game.hero.moveDown();
			game.hero.mine();
			game.hero.moveDown();
			game.hero.mine();
			game.hero.moveDown();
		});
	}
	

}
