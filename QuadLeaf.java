package tuc.ece.plh211.quadtree;

import javafx.geometry.Point2D;

public class QuadLeaf extends QuadNode{
	private Point2D coordinates;
	private int value;
	
	public QuadLeaf() {
		coordinates = null;
		value = 0;
	}
	
	public QuadLeaf(int xmin, int xmax, int ymin, int ymax) {
		super(xmin, xmax, ymin, ymax);
		coordinates = null;
		value = 0;
	}
	
	public QuadLeaf(Point2D coordinates, int value, int xmin, int xmax, int ymin, int ymax) {
		super(xmin, xmax, ymin, ymax);
		this.coordinates = coordinates;
		this.value = value;
	}

	public Point2D getCoordinates() {
		return coordinates;
	}

	public int getValue() {
		return value;
	}
	
	
	//With this method we check if the value has a number or not...if it has it means that the node that has this value is preoccupied and cant accept another value
	public boolean isEmpty() {
		if(this.value == 0) {
			return true;
		}
		
		return false;
	}
		
}
