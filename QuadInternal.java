package tuc.ece.plh211.quadtree;

public class QuadInternal extends QuadNode{
	private QuadNode northWest;
	private QuadNode northEast;
	private QuadNode southWest;
	private QuadNode southEast;
	
	public QuadInternal(int xmin, int xmax, int ymin, int ymax) {
		super(xmin, xmax, ymin, ymax);
		this.northWest = null;
		this.northEast = null;
		this.southWest = null;
		this.southEast = null;
	}

	public QuadNode getNorthWest() {
		return northWest;
	}

	public void setNorthWest(QuadNode northWest) {
		this.northWest = northWest;
	}

	public QuadNode getNorthEast() {
		return northEast;
	}

	public void setNorthEast(QuadNode northEast) {
		this.northEast = northEast;
	}

	public QuadNode getSouthWest() {
		return southWest;
	}

	public void setSouthWest(QuadNode southWest) {
		this.southWest = southWest;
	}

	public QuadNode getSouthEast() {
		return southEast;
	}

	public void setSouthEast(QuadNode southEast) {
		this.southEast = southEast;
	}
	
}
