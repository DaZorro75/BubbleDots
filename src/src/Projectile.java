package src;

import ledControl.BoardController; 
import ledControl.gui.KeyBuffer;

public class Projectile{

	private int x;
	private int y;
	private int[] RGB = new int[3];
	private BoardController controller = Main.getController();
	KeyBuffer buffer = controller.getKeyBuffer();
	private Enemy[] enemies = Main.getEnemies();
	private boolean active = false;
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Projectile(int i) {
		x = 5;
		y = 11;
		if (i == 0) {
			RGB[0] = 0; 
			RGB[1] = 0;
			RGB[2] = 100;
		}
		else if (i == 1) {
			RGB[0] = 100; 
			RGB[1] = 0;
			RGB[2] = 0;
		}
		else if (i == 2) {
			RGB[0] = 0; 
			RGB[1] = 100;
			RGB[2] = 0;
		}
		else if (i == 3) {
			RGB[0] = 100;
			RGB[1] = 100;
			RGB[2] = 0;
					
		}
		else if (i == 4) {
			RGB[0] = 0;
			RGB[1] = 100;
			RGB[2] = 92;
		}
		else {
			System.err.println("Die übergebene Farbe für das Projektil ist ungültig");
			RGB[0] = 100; 
			RGB[1] = 100;
			RGB[2] = 100;
		}
	}
	
	public void changeColor(int i) {
		if (i == 0) {
			RGB[0] = 0; 
			RGB[1] = 0;
			RGB[2] = 100;
		}
		else if (i == 1) {
			RGB[0] = 100; 
			RGB[1] = 0;
			RGB[2] = 0;
		}
		else if (i == 2) {
			RGB[0] = 0; 
			RGB[1] = 100;
			RGB[2] = 0;
		}
		else if (i == 3) {
			RGB[0] = 100;
			RGB[1] = 100;
			RGB[2] = 0;
					
		}
		else if (i == 4) {
			RGB[0] = 0;
			RGB[1] = 100;
			RGB[2] = 92;
		}
		else {
			System.err.println("Die übergebene Farbe für das Projektil ist ungültig");
			RGB[0] = 100; 
			RGB[1] = 100;
			RGB[2] = 100;
		}
		this.draw();
	}
	
	public int[] getPosition() {
		int[] pos = new int[2];
		pos[0] = this.x;
		pos[1] = this.y;
		return pos;
	}
	public void setPosition(int [] pos) {
		
		x = pos[0];
		y = pos[1];
	}

	public void draw() {
		controller.setColor(x, y, RGB[0], RGB[1], RGB[2]);
	}

	
	public void reverse() {
		if (this.y + 1 <= 11) {
			draw ();
			controller.setColor(this.x, this.y, 0, 0, 0);
			reverse();
		}
		else {
				draw();
				controller.setColor(this.x, 11, 0, 0, 0);
		}
	}
	
	public int getColor() {
		if (
			RGB[0] == 0 && 
			RGB[1] == 0 &&
			RGB[2] == 100
			) 
			{
			return 0;
			//Blau
		}
		else if (
			RGB[0] == 100 && 
			RGB[1] == 0 &&
			RGB[2] == 0
			)
			{
			return 1;
			//Rot
		}
		else if (
			RGB[0] == 0 && 
			RGB[1] == 100 &&
			RGB[2] == 0
			)
		{
			return 2;
			//Grün
		}
		else if (
			RGB[0] == 100 &&
			RGB[1] == 100 &&
			RGB[2] == 0
			)
		{
			return 3;
			//Gelb
		}
		else {
			return 4;
			//Cyan
		}
	}
	
	public Enemy findEnemy(int x, int y) {
		Enemy result = null;
		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i].getCurrentPosition()[1] == y) {
				result = enemies[i];
			}
		}
		return result;
	}
	
	public void moveLeft() {
		
		if (x > 0) {
			
			controller.setColor(this.x, this.y, 0, 0, 0);
			x = x - 1;
			draw(); 
		}
	}
	public void moveRight() {
		
		if (x < 11) {
			
			controller.setColor(this.x, this.y, 0, 0, 0);
			x = x + 1;
			draw(); 
		}
	}
}