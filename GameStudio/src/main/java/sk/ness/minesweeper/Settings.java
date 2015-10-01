package sk.ness.minesweeper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Settings implements Serializable {

	public static final Settings BEGINNER = new Settings(9, 9, 10);
	public static final Settings INTERMEDIATE = new Settings(16, 16, 40);
	public static final Settings EXPERT = new Settings(16, 30, 99);

	private static String SETTING_FILE = System.getProperty("user.home") + System.getProperty("file.separator")
			+ "minesweeper.settings";

	private final int rowCount, columnCount, mineCount;

	public Settings(int rowCount, int columnCount, int mineCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Settings))
			return false;

		return !(this.rowCount != ((Settings) obj).rowCount || this.columnCount != ((Settings) obj).columnCount
				|| this.mineCount != ((Settings) obj).mineCount);

	}

	@Override
	public int hashCode() {
		return this.rowCount * this.rowCount * this.mineCount;
	}

	public void save() {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(SETTING_FILE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
				fos.close();
			} catch (IOException e) {
				// nerob nic
			}
		}
	}

	public static Settings load() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(SETTING_FILE));
			return (Settings) ois.readObject();
		} catch (IOException e) {
			return BEGINNER; 
		} catch (ClassNotFoundException e){
			return BEGINNER;
		}finally {		
			try { if (ois != null)
				ois.close();
			} catch (IOException e) {
				// nerob nic
			}
		}
	}
//   public static void main(String[] args) {
//	   Settings var = new Settings(9,9,10);
//	   var.save();
//   }
   
}