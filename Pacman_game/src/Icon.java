/**
 * @author Amanpreet Gill
 *
 */
public class Icon implements IconADT 
	{
	
	private BinarySearchTree tree;
	private int width, height, id;
	private String type;
	private Position pos;
	
	public Icon (int id, int width, int height, String type, Position pos) 
		{
		//Initialize all configs...
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		this.tree = new BinarySearchTree();
	}
	
	private boolean findPixel (Position p)
		{
		Pixel pix = tree.get(tree.getRoot(), p);
		if (pix == null) {
			return false;
		}
		return true;
	}
	//sets up type of icon
	public void setType (String type)
		{
		this.type = type;
	}
	//get gameboard width
	public int getWidth()
		{
		return width;
	}
	//gets height
	public int getHeight()
		{
		return height;
	}
	//gets type
	public String getType ()
		{
		return type;
	}
	//returns id
	public int getId()
		{
		return id;
	}
	//returns offset
	public Position getOffset ()
		{
		return pos;
	}
	//sets offset
	public void setOffset (Position value)
	{
		this.pos = value;
	}
	//adds pixels into gameboard
	public void addPixel (Pixel pix) throws DuplicatedKeyException
	{
		tree.put(tree.getRoot(), pix);
	}
	
	public boolean intersects (Icon otherIcon)
	{
		//store this Pixels coordinates
		int x = getOffset().xCoord();
		int y = getOffset().yCoord();
		//store params coordinates
		int X = otherIcon.getOffset().xCoord();
		int Y = otherIcon.getOffset().yCoord();
		
		Pixel smallest = null;
		//try catch
		try {
			smallest = otherIcon.tree.smallest(tree.getRoot());
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Go through tree starting smallest->largest
		while (tree.successor(tree.getRoot(), smallest.getPosition()) != null) {
			int x_val, y_val;
			x_val = smallest.getPosition().xCoord() + x - X;
			y_val = smallest.getPosition().yCoord() + y - Y;
			// Call findPixel on the passed figure using a new position
			if (otherIcon.findPixel(new Position(x_val, y_val))) {
				return true;
			}
			smallest = tree.successor(tree.getRoot(), smallest.getPosition());
		}

		return false;
	}

}
