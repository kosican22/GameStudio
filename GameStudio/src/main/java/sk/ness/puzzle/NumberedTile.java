package sk.ness.puzzle;

import java.io.Serializable;

public class NumberedTile extends Tile implements Serializable {
    private final int NUMBER;
    
    //public static setNumber()
    public NumberedTile(int num) {
		this.NUMBER = num;
	}
    
    public int getNumber() {
		return NUMBER;
	}

	@Override
	public String toString() {
		if (NUMBER>9) {
			return " " + NUMBER + " ";
		}else{
			return "  " + NUMBER + " ";
		}
	}
}
