
public class Pixel {
	private Position p;
	private int color;
	public Pixel() {
		// TODO Auto-generated constructor stub
	}
	public Pixel(Position p, int color) {
		this.p = p;
		this.color = color;
	}
	public Position getPosition() {
		return this.p;
	}
	public int getColor() {
		return this.color;
	}
}
