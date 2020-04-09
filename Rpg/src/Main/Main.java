package Main;

import java.util.*;
import java.util.concurrent.TimeUnit;
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
	
	private static void wait(int sec) {
		try {
			TimeUnit.SECONDS.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			
			Game game = new Game();
			wait(1);
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
