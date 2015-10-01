package sk.ness.minesweeper;

import sk.ness.minesweeper.consoleui.ConsoleUI;
import sk.ness.minesweeper.core.Field;
import sk.ness.minesweeper.swingui.SwingUI;

/**
 * Main application class.
 */
public class Minesweeper {
	long startMillis;
	/** User interface. */
	private UserInterface userInterface;
	private static Minesweeper instancia; // singleton
    private Settings setting;
	
	public Settings getSetting() {
		return setting;
	}

	public void setSetting(Settings setting) {
		this.setting = setting;
		this.setting.save();
	}

	/**
	 * Constructor.
	 */
	private Minesweeper() { // singleton
		instancia = this; //toto nechapem naco tu ma byt
		setting = Settings.load();
		userInterface = new SwingUI(); // implicitny konstruktor

//		Field field = new Field(9, 9, 10);
		newGame();
	}


	public static Minesweeper getInstance() { // singleton
		// navr�t�me objekt
		return instancia;
	}

	public int getPlayingSeconds() {
		return (int) (System.currentTimeMillis() - startMillis) / 1000;
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            arguments
	 */
	public static void spusti() {
		System.out.println("Ahoj " + System.getProperty("user.name"));
	    new Minesweeper();
	}
	
	public void newGame() {
        Field field = new Field(setting.getRowCount(), setting.getColumnCount(), setting.getMineCount());
        startMillis = System.currentTimeMillis();
        userInterface.newGameStarted(field);
    }
}
