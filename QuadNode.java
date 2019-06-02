package tuc.ece.plh211.quadtree;

public abstract class QuadNode {
	private int xmin, xmax;
	private int ymin, ymax;
	
	public QuadNode() {
		xmin = xmax = ymin = ymax = 0;
	}

	public QuadNode(int xmin, int xmax, int ymin, int ymax) {
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
	}
	
	
	public void setDistances(int xmin, int xmax, int ymin, int ymax) {
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
	}

	public int getXmin() {
		return xmin;
	}

	public int getXmax() {
		return xmax;
	}

	public int getYmin() {
		return ymin;
	}

	public int getYmax() {
		return ymax;
	}

	public boolean isEmpty() {
		return false;
	}
	

}
