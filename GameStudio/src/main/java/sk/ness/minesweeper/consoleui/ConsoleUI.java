package sk.ness.minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sk.ness.minesweeper.Minesweeper;
import sk.ness.minesweeper.UserInterface;
import sk.ness.minesweeper.core.Clue;
import sk.ness.minesweeper.core.Field;
import sk.ness.minesweeper.core.GameState;
import sk.ness.minesweeper.core.Mine;
import sk.ness.minesweeper.core.Tile;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
    /** Playing field. */
    private Field field;
    
    /** Input reader. */
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    /**
     * Reads line of text from the reader.
     * @return line as a string
     */
    private String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }
    
    
    
    /* (non-Javadoc)
	 * @see minesweeper.consoleui.UserInterface#newGameStarted(minesweeper.core.Field)
	 */
    @Override // nekonecny cyklus vykreslovania hracieho pola a nacitavanie vstupu z klavesnice
	public void newGameStarted(Field field) {
        this.field = field;
        do {
            update(); // vykresli hracie pole
            processInput(); // nacitanie vstupu, oznacenie dlazdice
            // throw new UnsupportedOperationException("Resolve the game state - winning or loosing condition.");
            if (field.getState() == GameState.SOLVED) {
            	System.out.println("Vyhral si !");
            	System.exit(0);
            }
            if (field.getState() == GameState.FAILED) {
            	System.out.println("Prehral si !");
            	update();
            	System.exit(0);
            }
        } while(true);
    }
    
    /* (non-Javadoc)
	 * @see minesweeper.consoleui.UserInterface#update()
	 */
    @Override
	public void update() { // vykresli hracie pole
        // throw new UnsupportedOperationException("Method update not yet implemented");
    	System.out.print(" ");
    	for (int c = 0; c < field.getColumnCount(); c++)
    		System.out.printf("%3s",c);
    	System.out.println();
    	for (int r = 0; r < field.getRowCount(); r++) { // r riadky
    		System.out.printf("%c", 'A' + r);
        	for (int c = 0; c < field.getColumnCount(); c++) { // c stlpce
        	   Tile t = field.getTile(r, c);
        	   Tile.State s = field.getTile(r, c).getState();
               switch (s) {
                  case CLOSED: System.out.print("  -"); break;
                  case MARKED: System.out.print("  M"); break;
               }
               if (s==Tile.State.OPEN) {
            	   if (t instanceof Mine) System.out.print("  X");
            	   if (t instanceof Clue) {
            		   Clue k = (Clue) t;
          			   System.out.printf("  %d", k.getValue());
            	   }
               }
        	}
        	System.out.println();
    	}
    	System.out.println("Pocet neoznacenych min : " + field.getRemainingMineCount());
    	System.out.printf("Hrate %d sekund.%n", Minesweeper.getInstance().getPlayingSeconds());
    	System.out.print("(X) EXIT, (MA1) MARK, (OB4) OPEN : ");
    }
 
    private void handleInput(String input) throws WrongFormatException {
		Pattern p = Pattern.compile("X|M([A-Z])([0-9])|O([A-Z])([0-9])");
		Matcher matcher = p.matcher(input);
		int r, s;
		if (matcher.matches()) {
			switch (input.charAt(0)) {
			case 'X':
				System.exit(0);
				break;
			case 'M':
				r = (int) matcher.group(1).charAt(0) - 65;
				s = (int) matcher.group(2).charAt(0) - 48;
				field.markTile(r, s);
				break;
			case 'O':
				r = (int) matcher.group(3).charAt(0) - 65;
				s = (int) matcher.group(4).charAt(0) - 48;
				field.openTile(r, s);
				break;
			default:
				break;
			}
		} else {
			// System.out.println("Zle ste zadali.");
			throw new WrongFormatException("Zle ste zadali");
		}
    }
    
    /**
     * Processes user input.
     * Reads line from console and does the action on a playing field according to input string.
     */
    private void processInput() { //nacitanie vstupu, oznacenie dlazdice
        // throw new UnsupportedOperationException("Method processInput not yet implemented");
			String s1 = readLine();
			try {
                handleInput(s1);
			} catch(WrongFormatException e) {
				e.getMessage();
			}
    }
}
