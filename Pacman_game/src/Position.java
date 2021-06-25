/**
 * @author Amanpreet Gill
 *
 */
public class Position {
	private int x, y;
	
	public Position (int x, int y) {
		this.x = x;
		this.y = y;	
	}
	//get x coordinate
	public int xCoord() {
		return this.x;
	}
	//get y coordinate
	public int yCoord() {
		return this.y;
	}
	/* Compares this Position with p using row order
	 * – y < y′, or
	 *	– y = y′
	 *	and x < x′
	 */
	public int compareTo (Position p) {
		if (y > p.yCoord()) 
			return 1;
		else if (y == p.yCoord() && x > p.xCoord()) 
			return 1;
		else if (y < p.yCoord()) 
			return -1;
		else if (y == p.yCoord() && x < p.xCoord()) 
			return -1;
		else if (y == p.yCoord() && x == p.xCoord()) 
			return 0;
		else 
			return (Integer) null;
	}
}
