package sk.ness.minesweeper.core;

import java.util.Random;

/**
 * Field represents playing field and game logic.
 */
public class Field {
    /**
     * Playing field tiles.
     */
    private final Tile[][] tiles;

    /**
     * Field row count. Rows are indexed from 0 to (rowCount - 1).
     */
    private final int rowCount;

    /**
     * Column count. Columns are indexed from 0 to (columnCount - 1).
     */
    private final int columnCount;

    /**
     * Mine count.
     */
    private final int mineCount;

    /**
     * Game state.
     */
    private GameState state = GameState.PLAYING;

    /**
     * Constructor.
     *
     * @param rowCount    row count
     * @param columnCount column count
     * @param mineCount   mine count
     */
    public Field(int rowCount, int columnCount, int mineCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.mineCount = mineCount;
        tiles = new Tile[rowCount][columnCount];

        //generate the field content
        generate(); // dame miny a dame cisla polickam
    }

    public Tile getTile(int row, int column) {
    	return tiles[row][column]; 
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

	public GameState getState() {
		return state;
	}

	/**
	 * ziskanie poctu neoznacenych min
	 * @return
	 */
	public int getRemainingMineCount() {
		return getMineCount() - getNumberOf(Tile.State.MARKED);
	}
	
	/**
     * Opens tile at specified indeces.
     *
     * @param row    row number
     * @param column column number
     */
	   public void openTile(int row, int column) {
	        Tile tile = tiles[row][column];
	        if (tile.getState() == Tile.State.CLOSED) { //pre otvorenu alebo oznacenu dlazdicu, nema zmysel ju otvarat znova
	            tile.setState(Tile.State.OPEN);
	            if (tile instanceof Mine) {
	                state = GameState.FAILED;
	                return;
	            }
	            // uz vieme, ze to nie je Mina, ale moze to bhyt Clue != 0
	            if( ((Clue) tile).getValue()==0){
	            	openAdjacentTiles(row, column);
	            }
	            //
	            if (isSolved()) {
	                state = GameState.SOLVED;
	                return;
	            }
	        }
	    }
	
    /**
     * Marks tile at specified indeces.
     *
     * @param row    row number
     * @param column column number
     */
    public void markTile(int row, int column) {
    	if (getTile(row,column).getState() == Tile.State.CLOSED)
    		getTile(row,column).setState(Tile.State.MARKED);
    	else
    	if (getTile(row,column).getState() == Tile.State.MARKED)
    		getTile(row,column).setState(Tile.State.CLOSED);
    	
        // throw new UnsupportedOperationException("Method markTile not yet implemented");
    }

    /**
     * Generates playing field.
     */
    private void generate() { // rozmiestni miny po hracej ploche
        // throw new UnsupportedOperationException("Method generate not yet implemented");
    	int n = 0; // pocet bomb ktory som dal
    	Random generator = new Random();
    	while (n <= this.mineCount) {
    	    int r = generator.nextInt(this.rowCount);
    	    int c = generator.nextInt(this.columnCount);
    	    if (tiles[r][c] == null) {
    	    	tiles[r][c] = new Mine();
    	    	n++;
    	    }    	    
    	}
    	
    	// napise cisla jednotlivym polickam
    	for(int r = 0; r < this.rowCount; r++ )
			for(int c = 0; c < this.columnCount;c++)
				if( tiles[r][c] == null ){
					tiles[r][c] = new Clue( this.countAdjacentMines(r, c) );
				}     	
    }

    /**
     * Returns true if game is solved, false otherwise.
     *
     * @return true if game is solved, false otherwise
     */
    private boolean isSolved() {
        // throw new UnsupportedOperationException("Method isSolved not yet implemented");
    	if (getRowCount()*getColumnCount() - getNumberOf(Tile.State.OPEN) == getMineCount())
    		return true;
    	else
    		return false;
    }
    
    // vrati pocet otvorenych policok
    private int getNumberOf(Tile.State state) {
    	int otvorene = 0;
    	for(int r = 0; r < this.rowCount; r++ )
			for(int c = 0; c < this.columnCount;c++)
				if (tiles[r][c].getState() == state)
					otvorene++;
	    return otvorene;		
    }

    /**
     * Returns number of adjacent mines for a tile at specified position in the field.
     *
     * @param row    row number.
     * @param column column number.
     * @return number of adjacent mines.
     */
    private int countAdjacentMines(int row, int column) { // nezavolam ju ked som na mine
        int count = 0;
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            int actRow = row + rowOffset;
            if (actRow >= 0 && actRow < rowCount) {
                for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                    int actColumn = column + columnOffset;
                    if (actColumn >= 0 && actColumn < columnCount) {
                        if (tiles[actRow][actColumn] instanceof Mine) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }
    
    private void openAdjacentTiles(int row, int column){
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            int actRow = row + rowOffset;
            if (actRow >= 0 && actRow < rowCount) {
                for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                    int actColumn = column + columnOffset;
                    if (actColumn >= 0 && actColumn < columnCount) {
                    	openTile(actRow, actColumn);
                    }
                }
            }
        }
    }    
    
//    private void openAdjacentTiles(int row, int column) {
//    	for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
//    		int actRow = row + rowOffset;
//    		if (actRow >= 0 && actRow < rowCount) {
//    			for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
//    				int actColumn = column + columnOffset;
//    				if (actColumn >= 0 && actColumn < columnCount)
//    					if (rowOffset != 0 || columnOffset != 0) {// vyhodime (0,0)
//    						openTile(actRow, actColumn);
//    						Tile t = getTile(actRow, actColumn);
//    						if (t instanceof Clue) {
//    							Clue k =  (Clue) t;
//    							if (k.getValue() == 0)
//    								openAdjacentTiles(actRow, actColumn);
//    						}
//    					}
//    			}
//    		}
//    	}
//    }
}