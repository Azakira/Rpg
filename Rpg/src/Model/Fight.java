package Model;
import Field.Containables.Actors.Character;

public class Fight {
	
	private static int playerVictory;
	private static int botVictory;
	
	private Character player;
	private Character bot;
	
	
	public Fight(Character player, Character bot) {
		this.player = player;
		this.bot = bot;
	}
	

	public static int getPlayerVictory() {
		return playerVictory;
	}

	public static void setPlayerVictory(int playerVictory) {
		Fight.playerVictory = playerVictory;
	}

	public static int getBotVictory() {
		return botVictory;
	}

	public static void setBotVictory(int botVictory) {
		Fight.botVictory = botVictory;
	}


	public Character getPlayer() {
		return player;
	}


	public void setPlayer(Character player) {
		this.player = player;
	}


	public Character getBot() {
		return bot;
	}


	public void setBot(Character bot) {
		this.bot = bot;
	}

}
