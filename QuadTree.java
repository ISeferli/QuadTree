package tuc.ece.plh211.quadtree;

import javafx.geometry.Point2D;

public class QuadTree {
	private QuadNode root;
	private int succesfullSearch;
	private int unsuccesfullSearch;
	private int numberOfAccesses;
	boolean result;


	public QuadTree() {
		this.root = null;
		this.succesfullSearch = 0;
		this.unsuccesfullSearch = 0;
		this.numberOfAccesses = 0;
	}

	public QuadNode getRoot() {
		return root;
	}
	
	public void insert(Point2D coor, int value, int distance) {
		if (coor == null) throw new java.lang.NullPointerException("called insertNode() with a null Point2D");

		this.root = insertNode(this.getRoot(), coor, value, distance);
	}
	
	
	public QuadNode insertNode(QuadNode node, Point2D coor, int value, int distance) {
		
		if (node == null) {  //If there isn't any node in the tree
			//We make an internal node which shows to four different leaves at first
			node = new QuadInternal(0, distance, 0, distance); //Here we have distance as the node is null
			((QuadInternal) node).setNorthWest(new QuadLeaf(0, distance/2, 0, distance/2)); //The only time that distance has any meaning as the other times we use the variables x, y
			((QuadInternal) node).setNorthEast(new QuadLeaf(distance/2, distance, 0, distance/2));
			((QuadInternal) node).setSouthWest(new QuadLeaf(0, distance/2, distance/2, distance));
			((QuadInternal) node).setSouthEast(new QuadLeaf(distance/2, distance, distance/2, distance));
		}
		
		int val; //Variable that will hold the pre-existing value
		Point2D point = null; //Variable that will hold the coordinates of the existing value
		
		if(coor.getX() <= (node.getXmin()+node.getXmax())/2 && coor.getY() <= (node.getYmin()+node.getYmax())/2) {
			
			if(!((QuadInternal)node).getNorthWest().isEmpty()){
			
				if(((QuadInternal)node).getNorthWest() instanceof QuadInternal) {
					insertNode(((QuadInternal)node).getNorthWest(), coor, value, distance);

				}else{
					val = ((QuadLeaf) ((QuadInternal)node).getNorthWest()).getValue();
					point = ((QuadLeaf) ((QuadInternal)node).getNorthWest()).getCoordinates();
					
					if(coor == point) {
						return node;
					}
					
					((QuadInternal)node).setNorthWest(new QuadInternal(node.getXmin(), (node.getXmin()+node.getXmax())/2, node.getYmin(), (node.getYmin()+node.getYmax())/2));
					
					int xOffset = (((QuadInternal)node).getNorthWest().getXmin() + ((QuadInternal)node).getNorthWest().getXmax())/2;
					int yOffset =  (((QuadInternal)node).getNorthWest().getYmin() + ((QuadInternal)node).getNorthWest().getYmax())/2;
					
					((QuadInternal) ((QuadInternal) node).getNorthWest()).setNorthWest(new QuadLeaf(node.getXmin(), xOffset, node.getYmin(), yOffset));
					((QuadInternal) ((QuadInternal) node).getNorthWest()).setNorthEast(new QuadLeaf(xOffset, node.getXmax()/2, node.getYmin(), yOffset));
					((QuadInternal) ((QuadInternal) node).getNorthWest()).setSouthWest(new QuadLeaf(node.getXmin(), xOffset, yOffset, node.getYmax()/2));
					((QuadInternal) ((QuadInternal) node).getNorthWest()).setSouthEast(new QuadLeaf(xOffset, node.getXmax()/2, yOffset, node.getYmax()/2));
					
					insertNode(((QuadInternal) node).getNorthWest(), coor, value, distance);
					insertNode(((QuadInternal) node).getNorthWest(), point, val, distance);
					
				}
				
			}else {
				((QuadInternal)node).setNorthWest(new QuadLeaf(coor, value, node.getXmin(), (node.getXmin()+node.getXmax())/2, node.getYmin(), (node.getYmin()+node.getYmax())/2));
			}
			
		} else if(coor.getX() > node.getXmin()+(node.getXmax()-node.getXmin())/2 && coor.getY() <= node.getYmin()+(node.getYmax()-node.getYmin())/2) {
			
			if(!((QuadInternal)node).getNorthEast().isEmpty()){
				
				if(((QuadInternal)node).getNorthEast() instanceof QuadInternal) {
					insertNode(((QuadInternal)node).getNorthEast(), coor, value, distance);

				}else{
					val = ((QuadLeaf) ((QuadInternal)node).getNorthEast()).getValue();
					point = ((QuadLeaf) ((QuadInternal)node).getNorthEast()).getCoordinates();
					((QuadInternal)node).setNorthEast(new QuadInternal((node.getXmin()+node.getXmax())/2, node.getXmax(), node.getYmin(), (node.getYmin()+node.getYmax())/2));
					
					if(coor == point) {
						return node;
					}
					
					int xOffset = (((QuadInternal)node).getNorthEast().getXmin() + ((QuadInternal)node).getNorthEast().getXmax())/2;
					int yOffset =  (((QuadInternal)node).getNorthEast().getYmin() + ((QuadInternal)node).getNorthEast().getYmax())/2;
					
					((QuadInternal) ((QuadInternal) node).getNorthEast()).setNorthWest(new QuadLeaf(((QuadInternal)node).getNorthEast().getXmin(), xOffset, ((QuadInternal)node).getNorthEast().getYmin(), yOffset));
					((QuadInternal) ((QuadInternal) node).getNorthEast()).setNorthEast(new QuadLeaf(xOffset, ((QuadInternal)node).getNorthEast().getXmax(), ((QuadInternal)node).getNorthEast().getYmin(), yOffset));
					((QuadInternal) ((QuadInternal) node).getNorthEast()).setSouthWest(new QuadLeaf(((QuadInternal)node).getNorthEast().getXmin(), xOffset, yOffset, ((QuadInternal)node).getNorthEast().getYmax()));
					((QuadInternal) ((QuadInternal) node).getNorthEast()).setSouthEast(new QuadLeaf(xOffset, ((QuadInternal)node).getNorthEast().getXmax(), yOffset, ((QuadInternal)node).getNorthEast().getYmax()));
					
					insertNode(((QuadInternal) node).getNorthEast(), coor, value, distance);
					insertNode(((QuadInternal) node).getNorthEast(), point, val, distance);
				}
				
			}else {
				((QuadInternal)node).setNorthEast(new QuadLeaf(coor, value, (node.getXmin()+node.getXmax())/2, node.getXmax(), node.getYmin(), (node.getYmin()+node.getYmax())/2));
			}
			
		} else if(coor.getX() <= node.getXmin()+(node.getXmax()-node.getXmin())/2 && coor.getY() > node.getYmin()+(node.getYmax()-node.getYmin())/2) {
			
			if(!((QuadInternal)node).getSouthWest().isEmpty()){
				
				if(((QuadInternal)node).getSouthWest() instanceof QuadInternal) {
					insertNode(((QuadInternal)node).getSouthWest(), coor, value, distance);
					
				}else{
					val = ((QuadLeaf) ((QuadInternal)node).getSouthWest()).getValue();
					point = ((QuadLeaf) ((QuadInternal)node).getSouthWest()).getCoordinates();
					((QuadInternal)node).setSouthWest(new QuadInternal(node.getXmin(), (node.getXmin()+node.getXmax())/2, (node.getYmin()+node.getYmax())/2, node.getYmax()));

					if(coor == point) {
						return node;
					}
					
					int xOffset = (((QuadInternal)node).getSouthWest().getXmin() + ((QuadInternal)node).getSouthWest().getXmax())/2;
					int yOffset =  (((QuadInternal)node).getSouthWest().getYmin() + ((QuadInternal)node).getSouthWest().getYmax())/2;
					
					((QuadInternal) ((QuadInternal) node).getSouthWest()).setNorthWest(new QuadLeaf(((QuadInternal)node).getSouthWest().getXmin(), xOffset, ((QuadInternal)node).getSouthWest().getYmin(), yOffset));
					((QuadInternal) ((QuadInternal) node).getSouthWest()).setNorthEast(new QuadLeaf(xOffset, ((QuadInternal)node).getSouthWest().getXmax(), ((QuadInternal)node).getSouthWest().getYmin(), yOffset));
					((QuadInternal) ((QuadInternal) node).getSouthWest()).setSouthWest(new QuadLeaf(((QuadInternal)node).getSouthWest().getXmin(), xOffset,  yOffset, ((QuadInternal)node).getSouthWest().getYmax()));
					((QuadInternal) ((QuadInternal) node).getSouthWest()).setSouthEast(new QuadLeaf(xOffset, ((QuadInternal)node).getSouthWest().getXmax(), yOffset, ((QuadInternal)node).getSouthWest().getYmax()));
					
					insertNode(((QuadInternal) node).getSouthWest(), coor, value, distance);
					insertNode(((QuadInternal) node).getSouthWest(), point, val, distance);
				}
				
			}else {
				((QuadInternal)node).setSouthWest(new QuadLeaf(coor, value, node.getXmin(), (node.getXmin()+node.getXmax())/2, (node.getYmin()+node.getYmax())/2, node.getYmax()));
			}
			
		} else if(coor.getX() > node.getXmin()+(node.getXmax()-node.getXmin())/2 && coor.getY() > node.getYmin()+(node.getYmax()-node.getYmin())/2) {
			
			if(!((QuadInternal) node).getSouthEast().isEmpty()){
				
				if(((QuadInternal)node).getSouthEast() instanceof QuadInternal) {
					insertNode(((QuadInternal)node).getSouthEast(), coor, value, distance);

				}else{
					val = ((QuadLeaf) ((QuadInternal)node).getSouthEast()).getValue();
					point = ((QuadLeaf) ((QuadInternal)node).getSouthEast()).getCoordinates();
					((QuadInternal)node).setSouthEast(new QuadInternal((node.getXmin()+node.getXmax())/2, node.getXmax(),(node.getYmin()+node.getYmax())/2, node.getYmax()));
					
					if(coor == point) {
						return node;
					}
					
					int xOffset = (((QuadInternal)node).getSouthEast().getXmin() + ((QuadInternal)node).getSouthEast().getXmax())/2;
					int yOffset =  (((QuadInternal)node).getSouthEast().getYmin() + ((QuadInternal)node).getSouthEast().getYmax())/2;
					
					((QuadInternal) ((QuadInternal) node).getSouthEast()).setNorthWest(new QuadLeaf(((QuadInternal)node).getSouthEast().getXmin(), xOffset, ((QuadInternal)node).getSouthEast().getYmin(), yOffset));
					((QuadInternal) ((QuadInternal) node).getSouthEast()).setNorthEast(new QuadLeaf(xOffset, ((QuadInternal)node).getSouthEast().getXmax(), ((QuadInternal)node).getSouthEast().getYmin(), yOffset));
					((QuadInternal) ((QuadInternal) node).getSouthEast()).setSouthWest(new QuadLeaf(((QuadInternal)node).getSouthEast().getXmin(), xOffset, yOffset, ((QuadInternal)node).getSouthEast().getYmax()));
					((QuadInternal) ((QuadInternal) node).getSouthEast()).setSouthEast(new QuadLeaf(xOffset, ((QuadInternal)node).getSouthEast().getXmax(), yOffset, ((QuadInternal)node).getSouthEast().getYmax()));
					
					insertNode(((QuadInternal) node).getSouthEast(), coor, value, distance);
					insertNode(((QuadInternal) node).getSouthEast(), point, val, distance);
				}
				
			}else {
				((QuadInternal)node).setSouthEast(new QuadLeaf(coor, value, node.getXmin()+node.getXmax()/2, node.getXmax(),(node.getYmin()+node.getYmax())/2, node.getYmax()));
			}
		}
	
		return node;
	}
	
	public int getNumberOfAccesses() {
		return numberOfAccesses;
	}

	public void setNumberOfAccesses(int numberOfAccesses) {
		this.numberOfAccesses = numberOfAccesses;
	}

	public int getSuccesfullSearch() {
		return succesfullSearch;
	}

	public void setSuccesfullSearch(int succesfullSearch) {
		this.succesfullSearch = succesfullSearch;
	}

	public int getUnsuccesfullSearch() {
		return unsuccesfullSearch;
	}

	public void setUnsuccesfullSearch(int unsuccesfullSearch) {
		this.unsuccesfullSearch = unsuccesfullSearch;
	}

	public boolean searchQuad(QuadNode node, Point2D coor) {		
		//If the tree is empty then it means that we have an unsuccessful search
		if(node==null) {
			this.unsuccesfullSearch++;
			result = false;
			return result;
		}
		
		if(coor.getX() <= (node.getXmin()+node.getXmax())/2 && coor.getY() <= (node.getYmin()+node.getYmax())/2) {
			//We search in the NorthWest nodes with that condition
			if(((QuadInternal)node).getNorthWest() instanceof QuadInternal) {
				this.numberOfAccesses++;
				searchQuad(((QuadInternal)node).getNorthWest(), coor);
			}else if (((QuadInternal)node).getNorthWest() instanceof QuadLeaf) {
				
				if(((QuadLeaf) ((QuadInternal)node).getNorthWest()).getCoordinates() == coor) {
					this.succesfullSearch++;
					result = true;
					return result;
				}else {
					this.unsuccesfullSearch++;
					result = false;
					return result;
				}
				
			}
		} else if(coor.getX() > node.getXmin()+(node.getXmax()-node.getXmin())/2 && coor.getY() <= node.getYmin()+(node.getYmax()-node.getYmin())/2) {
			//We search in the NorthEast nodes
			if(((QuadInternal)node).getNorthEast() instanceof QuadInternal) {
				this.numberOfAccesses++;
				searchQuad(((QuadInternal)node).getNorthEast(), coor);
			}else if (((QuadInternal)node).getNorthEast() instanceof QuadLeaf) {
				
				if(((QuadLeaf) ((QuadInternal)node).getNorthEast()).getCoordinates() == coor) {
					this.succesfullSearch++;
					result = true;
					return result;
				}else {
					this.unsuccesfullSearch++;
					result = false;
					return result;
				}
			}
		} else if(coor.getX() <= node.getXmin()+(node.getXmax()-node.getXmin())/2 && coor.getY() > node.getYmin()+(node.getYmax()-node.getYmin())/2) {
			//We search in the SouthWest nodes
			if(((QuadInternal)node).getSouthWest() instanceof QuadInternal) {
				this.numberOfAccesses++;
				searchQuad(((QuadInternal)node).getSouthWest(), coor);
			}else if (((QuadInternal)node).getSouthWest() instanceof QuadLeaf) {
				
				if(((QuadLeaf) ((QuadInternal)node).getSouthWest()).getCoordinates() == coor) {
					this.succesfullSearch++;
					result = true;
					return result;
				}else {
					this.unsuccesfullSearch++;
					result = false;
					return result;
				}
			}
		} else if(coor.getX() > node.getXmin()+(node.getXmax()-node.getXmin())/2 && coor.getY() > node.getYmin()+(node.getYmax()-node.getYmin())/2) {
			//We search in the SouthEast nodes
			if(((QuadInternal)node).getSouthEast() instanceof QuadInternal) {
				this.numberOfAccesses++;
				searchQuad(((QuadInternal)node).getSouthEast(), coor);
			}else if (((QuadInternal)node).getSouthEast() instanceof QuadLeaf) {
				
				if(((QuadLeaf) ((QuadInternal)node).getSouthEast()).getCoordinates() == coor) {
					this.succesfullSearch++;
					result = true;
					return result;
				}else {
					this.unsuccesfullSearch++;
					result = false;
					return result;
				}
			}
		}
		
		return result;
	}
	
}
