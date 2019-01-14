package src; 

import ledControl.BoardController;

public class MovingThreadTest extends Thread{
	
	private Wall[] walls;
	private Enemy[] enemies;
	private Projectile projectile;
	private BoardController controller = BoardController.getBoardController();
	private boolean debug = Main.debug;
	public MovingThreadTest(Enemy[] enemies, Projectile projectile, Wall[] walls) {
		this.projectile = projectile;
		this.enemies = enemies;
		this.walls = walls;

	}
	public void run() {
		//		controller = Main.getController();
		long step = 50;
		while (true) {
			long start = System.currentTimeMillis();

			for(int i = 0; i < enemies.length; i++) {

				enemies[i].moveEnemy(1);
			}
			shoot();
			controller.updateBoard();
			try {
				long s = step - (System.currentTimeMillis() - start);
				if (s > 0) {
					Thread.sleep(step - (System.currentTimeMillis() - start));
				}
			}
			catch(InterruptedException ie) {

				//ignore			
			}
		}
	}
	public void shoot() {
		if (debug == false) {
		if (projectile.isActive()) {
			
			int [] pos = projectile.getPosition();
			if (pos[1] >= 0) {
				if (getCollision(pos[0], pos[1]) == true) {

					controller.setColor(pos[0], pos[1], 0, 0, 0);
					pos[1] = 11;
					projectile.setPosition(pos);
					projectile.changeColor(Main.getRandomNumber());
					projectile.draw();
					projectile.setActive(false);
				}
				else {

					controller.setColor(pos[0], pos[1], 0, 0, 0);
					pos[1] = pos[1] - 1;
					projectile.setPosition(pos);
					projectile.draw();
				}
			}
			else {
				controller.setColor(pos[0], pos[1], 0, 0, 0);
				pos[1]= 11;
				projectile.setPosition(pos);
				projectile.changeColor(Main.getRandomNumber());
				projectile.draw();
				projectile.setActive(false);
			}
		}
		}
		else {
			if (projectile.isActive()) {
				
				int [] pos = projectile.getPosition();
				if (pos[1] >= 0) {
					if (getCollision(pos[0], pos[1]) == true) {

						controller.setColor(pos[0], pos[1], 100, 100, 100);
						pos[1] = 11;
						projectile.setPosition(pos);
						projectile.changeColor(Main.getRandomNumber());
						projectile.draw();
						projectile.setActive(false);
					}
					else {

						controller.setColor(pos[0], pos[1], 100, 100, 100);
						pos[1] = pos[1] - 1;
						projectile.setPosition(pos);
						projectile.draw();
					}
				}
				else {
					controller.setColor(pos[0], pos[1], 100, 100, 100);
					pos[1]= 11;
					projectile.setPosition(pos);
					projectile.changeColor(Main.getRandomNumber());
					projectile.draw();
					projectile.setActive(false);
				}
			}	
		}
	}
	public boolean getCollision(int x, int y) {
		int nextColor = 0;
		if (y - 1 >= 0) {
			nextColor = Main.checkColor(x, y -1);
			
		}
		if (nextColor != 0) {
			if (Main.checkColor(x, y - 1) == 0) {
				return false;
			}
			else if (Main.checkColor(x, y - 1) == 1) {
				for (Wall w : walls) {
					
					w.hit(x, y -1);
				}
				System.err.println("Kollision mit einer Wand.");
				return true;
			}
			else {
				System.err.println("Kollision mit einem Enemy");
				for (Enemy e : enemies) {
					
					e.hit(x, y -1);
				}

			}
			return true;
		}
		else {
			return false;
		}
	}
}