package src;

public class Colors {

	String name;
	int r;
	int g;
	int b;
	public static Colors backGround = new Colors("BackGround", 100,100,100);
	public static Colors black = new Colors("Black", 0,0,0);
	public static Colors wallColor = new Colors("WallColor", 100,90,43);

	public Colors(String name, int r, int g, int b) {
		
		this.name = name;
		this.r = r;
		this.b = b;
		this.g= g;
	}
	public int[] getRGB(){
		
		int[] rgb = new int[] {r,g,b};
		return rgb;
	}
}
