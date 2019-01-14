package src;

import ledControl.BoardController; 
import java.util.LinkedList;
import ledControl.gui.KeyBuffer;
import java.awt.event.KeyEvent;
import java.util.Random;


public class Main {

	private static BoardController controller = BoardController.getBoardController();
	private static int width = 12;
	static KeyBuffer buffer = controller.getKeyBuffer();
	public LinkedList<Enemy> enemyList;
	private static Enemy[] eN;
	private static Wall[] walls;
	
	public static Enemy[] getEnemies() {
		return eN;
	}


	public static void initializePlayField() {
//		controller = BoardController.getBoardController();
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 12; j++) {
				//	controller.setColor(i, j,Colors.backGround.r, Colors.backGround.g, Colors.backGround.b);
				//	controller.setColor(12 - i -1, 12 - j -1, Colors.backGround.r, Colors.backGround.g, Colors.backGround.b);
					controller.setColor(i, j,Colors.black.r, Colors.black.g, Colors.black.b);
					controller.setColor(12 - i -1, 12 - j -1, Colors.black.r, Colors.black.g, Colors.black.b);
					controller.updateBoard();
					
				}
			}
		}

	public static void clearPlayField() {
//		controller = BoardController.getBoardController();
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 12; j++) {
					controller.setColor(i, j, Colors.black.r, Colors.black.g, Colors.black.b);
					controller.setColor(12 - i -1, 12 - j -1, Colors.black.r, Colors.black.g, Colors.black.b);
					controller.updateBoard();
				}
			}
		}

	public static int getRandomNumber() {
		Random r = new Random();
		return r.nextInt(5);
	}
	public static BoardController getController() {
		return controller;
	}
	
	public static int checkColor(int x, int y) {
		// 0 - Void; 1 - Wall; 2 - Enemy
		if (!(x >= 0 && x < 11 && y >= 0 && y < 11)) {

			return 0;
		}

		int[] i = controller.getColorAt(x, y);
		if (i[0] == Colors.backGround.r && i[1] == Colors.backGround.g && i[2] == Colors.backGround.b) {
			return 0;
		}
		else if (i[0] == Colors.wallColor.r && i[1] == Colors.wallColor.g && i[2] == Colors.wallColor.b) {
			return 1;
		}
		else {
			return 2;
		}
	}

	public static void main(String[] args) {
	    
		//Test der Methoden
		initializePlayField();

		//Info: Der schwarze Gegner dient dem Testen, ob die übergebene Farbe gültig ist
		Enemy red = new Enemy(5, "Red");
		Enemy blue = new Enemy(2, "Blue");
		Enemy green = new Enemy(3, "Green");
//		Enemy black = new Enemy(4, "Black");
		Enemy cyan = new Enemy(6, "Cyan");
		Enemy yellow = new Enemy(4, "Yellow");
		eN = new Enemy[] {red,blue,green,cyan,yellow};
		
		//(x - Posititon, y-Position)
		red.draw(2, 2);
		blue.draw(7, 5);
		green.draw(5, 3);
//		black.draw(4, 10);
		cyan.draw(3, 0);
		yellow.draw(7, 4);


		Wall wall = new Wall(3);
		Wall wall2 = new Wall(3);
		wall.draw(6, 0);
		wall2.draw(6, 5);
		walls = new Wall[2];
		walls[0] = wall;
		walls[1] = wall2;
 	

		Projectile p = new Projectile(getRandomNumber());
		p.draw();
		MovingThreadTest move = new MovingThreadTest(eN, p, walls);
		move.start();
		
		controller.updateBoard();
		
		//Key Events
		
		while (true){
			KeyEvent event = buffer.pop();
			if (event != null){
				if (event.getID() == java.awt.event.KeyEvent.KEY_PRESSED)
					switch (event.getKeyCode()){
					case java.awt.event.KeyEvent.VK_SPACE:
						if (!p.isActive()) {
							p.setActive(true);
						}
					break;
					case java.awt.event.KeyEvent.VK_LEFT:
						p.moveLeft();
					break;
					case java.awt.event.KeyEvent.VK_F10: 
						System.err.println("Cheat aktiviert");
						move.stop(); 
						clearPlayField();
						
					case java.awt.event.KeyEvent.VK_F4:
						move.stop();
						controller.sleep(5000);
						throw new NullPointerException("Cheat Aktiviert");
					case java.awt.event.KeyEvent.VK_RIGHT:
						p.moveRight();
						break;
						default:
					}
				}
			} 
		}  
	} 
