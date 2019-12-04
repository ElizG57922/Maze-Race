package mazerace;

import java.awt.Color;
import java.awt.Graphics2D;

public class Gameboard {
	private int board[][];
	private Player player;
	private int levelNumber;

	
	public Gameboard() {
		board = Level.level0;
		player = new Player();
		levelNumber = 0;
	}
	public int getRows() {
		return board[0].length;
	}
	public int getCols() {
		return board.length;
	}
	public int getFill(int r, int c) {
		return board[r][c];
	}
   
    public Player getPlayer() {
    	return player;
    }
    
    public void drawBlocks(Graphics2D g2d, int w, int h) {
		int c = getCols(); // for the sake of readability
		int r = getRows();
    	for(int i = 0; i < c; i ++) { //blocks
			for(int j = 0; j < r; j ++) {
				if(getFill(i, j) == 0) {
					g2d.setColor(Color.WHITE);
					g2d.fillRect((int)(j * (h / (float)(c + 1))) + 2 * j,
							(int)(i * (w / (float)(r)) - i / 2), w / r, h / c);
				}
				else if(getFill(i, j) == 1) {
					g2d.setColor(Color.BLACK);
					g2d.fillRect((int)(j * (h / (float)(c + 1))) + 2 * j,
							(int)(i * (w / (float)(r)) - i / 2), w / r, h / c);
				}
				else if(getFill(i, j) == 2) {
					g2d.setColor(Color.GREEN);
					g2d.fillRect((int)(j * (h / (float)(c + 1))) + 2 * j,
							(int)(i * (w / (float)(r)) - i / 2), w / r, h / c);
				}
			}
		}
    }
    public void checkIfFinished() {
    	if(getFill(player.y, player.x) == 2) {
    		player = new Player();
    		switch(levelNumber) {
    		case 0:
    			board = Level.level1;
    			break;
    		case 1:
    			board = Level.level2;
    			break;
    		default:
    			board = Level.generateRandomLevel(board);
    		}
    		levelNumber ++;
    	}
    }
    public int getLevelNumber() {
    	return levelNumber;
    }
    
    public class Player {
    	private int x;
    	private int y;
    	
    	public Player() {
    		x = 0;
    		y = 0;
    	}
    		
    	public void moveLeft() {
    		if(x > 0 && !(getFill(y, x - 1) == 1)) {
   		    	x -= 1;
    		}
    	}
        public void moveRight() {
       		if(x < board[0].length - 1 && !(getFill(y, x + 1) == 1)) {
       			    x += 1;
        	}
    	}
    	public void moveDown() {
    		if(y < board.length - 1 && !(getFill(y + 1, x) == 1)) {
           		y += 1;
    		}
    	}
    	public void moveUp() {
    		if(y > 0 && !(getFill(y - 1, x) == 1)) {
           		y -= 1;
    		}
    	}
   
		public void draw(Graphics2D g2d, int w, int h, int r, int c) {
			g2d.setColor(Color.RED);
			g2d.fillRect((int)(x * (w / (float)r)), (int)(y * (h / (float)c)), w / r, h / c);
		}
    }
}
