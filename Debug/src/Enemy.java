package src;

//Info EnemyColor: 1 - EnemyColor, 0 - BackgroundColor

import ledControl.BoardController;
import java.util.Arrays;
import java.util.*;
public class Enemy {

	private int x_Size;
	private boolean direction = false;//false rechts rum
	private BoardController controller = BoardController.getBoardController();
	private int x;
	private int y;
	private int[] RGB = new int[3];
	private int[] enemyColor;
	private boolean debug = Main.debug;

	public void initEnemyColor() {
		for (int i = 0; i < enemyColor.length; i++) {
			this.enemyColor[i] = 1;
		}
	}

	public Enemy(int x_Size, String color) {
		this.x_Size = x_Size;
		this.enemyColor = new int[this.x_Size];
		if (color.equals("Blue")) {
			RGB[0] = 0; 
			RGB[1] = 0;
			RGB[2] = 100;
			initEnemyColor();
		}
		else if (color.equals("Red")) {
			RGB[0] = 100; 
			RGB[1] = 0;
			RGB[2] = 0;
			initEnemyColor();
		}
		else if (color.equals("Green")) {
			RGB[0] = 0; 
			RGB[1] = 100;
			RGB[2] = 0;
			initEnemyColor();
		}
		else if (color.equals("Yellow")) {
			RGB[0] = 100;
			RGB[1] = 100;
			RGB[2] = 0;
			initEnemyColor();
		}
		else if (color.equals("Cyan")) {
			RGB[0] = 0;
			RGB[1] = 100;
			RGB[2] = 92;
			initEnemyColor();
		}
		else if (color.equals("Black")) {
			RGB[0] = 0;
			RGB[1] = 0;
			RGB[2] = 0;
			initEnemyColor();
		}
		else {
			System.err.println("Die übergebene Farbe für den Gegner ist ungültig");
		}
	}

	public int[] getRGB() {
		return this.RGB;
	}

	// Gibt den gewünschten Enemy zurück
	public Enemy getEnemy() {
		return this;
	}

	//Zeichnet den Übergebenen Gegner auf das Spielfeld
	public void draw(int x, int y) {
		//		controller = BoardController.getBoardController();
		if (debug == false) {
		for (int i = 0; i < x_Size; i++) {
			if (enemyColor[i] == 1) {
				controller.setColor(i + x, y, RGB[0], RGB[1], RGB[2]);
			}
			else {
				controller.setColor(i + x, y, 0, 0, 0);
				}
			}
		}
		else {
			for (int i = 0; i < x_Size; i++) {
				if (enemyColor[i] == 1) {
					controller.setColor(i + x, y, RGB[0], RGB[1], RGB[2]);
				}
				else {
					controller.setColor(i + x, y, 100, 100, 100);
					}
				}
		}
		this.x = x;
		this.y = y;
		controller.updateBoard();
	}
	//pos[0] = Position X
	//pos[1] = Position Y
	//pos[3] = Position des letzten Punkts
	public int[] getCurrentPosition() {
		int[] pos = new int[3];
		pos[0] = this.x;
		pos[1] = this.y;
		pos[2] = this.x + this.x_Size;
		//		System.out.println("Der Anfang ist an der X-Koordinate:" + pos[0]);
		//		System.out.println("Die Y- Koordinate des Objektes lautet:" + pos[1]);
		//		System.out.println("Das Ende des Gegners ist an der X-Koordinate " + pos[2]);
		return pos;

	}


	public int getLength() {
		return this.x_Size;
	}

	//Bewegt den übergebenen Gegner von links nach rechts
	public void moveEnemy(int step) {

		//Linksbewegung
		if (direction) {

			if (x == 0) {

				direction = !direction;
				x+= step;
			}
			else {

				x-=step;
			}
		}
		else {
			//Rechtsbewegung
			if (x + x_Size == 12 ) {

				direction = !direction;
				x-= step;
			}
			else {

				x+=step;
			}

		}
		draw(direction);
	}

	public void draw(boolean fill) {
		if  (debug == false) {
		for (int i = 0; i < x_Size; i++) {
			if (enemyColor[i] == 1) {
				controller.setColor(i + x, y, RGB[0], RGB[1], RGB[2]);
			}
			else {
				controller.setColor(i + x, y, 0, 0, 0);
			}
			//			 controller.setColor(i, y, RGB[0], RGB[1], RGB[2]);
		}
		if (fill == true) {

			controller.setColor(x + x_Size, y, 0, 0, 0);
		}
		else {

			controller.setColor(x -1, y, 0, 0, 0);
			}
		}
		else {
			for (int i = 0; i < x_Size; i++) {
				if (enemyColor[i] == 1) {
					controller.setColor(i + x, y, RGB[0], RGB[1], RGB[2]);
				}
				else {
					controller.setColor(i + x, y, 100, 100, 100);
				}
				//			 controller.setColor(i, y, RGB[0], RGB[1], RGB[2]);
			}
			if (fill == true) {

				controller.setColor(x + x_Size, y, 100, 100, 100);
			}
			else {

				controller.setColor(x -1, y, 100, 100, 100);
				}
		}
	}
	
	public boolean hit(int x,int y) {


		if (y == this.y && x >= this.x && x < this.x + x_Size) {
			int[] temp = controller.getColorAt(x, y);
			if (temp[0] == this.RGB[0] && temp[1] == this.RGB[1] &&temp[2] == this.RGB[2]) {

				int offset = x - this.x;
				enemyColor[offset] = 0;
				shrink();
				return true;
			}
			else {

				return false;
			}
		}
		else {

			return false;
		}
	}
	private void shrink() {

		while (enemyColor.length > 0 && enemyColor[0] == 0) {

			enemyColor = Arrays.copyOfRange(enemyColor, 1, enemyColor.length);
			x_Size = enemyColor.length;
		}
		while (enemyColor.length > 0 && enemyColor[enemyColor.length -1] == 0) {

			enemyColor = Arrays.copyOfRange(enemyColor, 0, enemyColor.length -1);
			x_Size = enemyColor.length;
		}
	}
}