package tuc.ece.plh211.main;

import java.util.Random;
import javafx.geometry.Point2D;
import tuc.ece.plh211.kdtree.KD_Tree;
import tuc.ece.plh211.quadtree.QuadTree;

public class Main {
	private static int x;
	private static int y;
	private static int M;
	private static int N = 524288;  //According to the exercise N=2^16, 65536 but Quad Tree has stack overflow problems sometimes with that N and we used 2^19 
	private static int searches = 100;
	private static Point2D point;
	private static Point2D[] helper; //We want an array to save the elements that there are in the tree so that we can have a result for the successful searches

	public static void main(String[] args) {
/*Each time for different M number of nodes we make a different tree with the help of kdtreeMainHelp() function 
  where we take the number M and use it over and over again to make the tree and then search for different values */
     
          System.out.println("-------------------QUAD TREE--------------------");
            //For M>50000 sometimes it has stack overflow errors and so we use N = 2^19, KD Tree works fine with N = 2^16
          M = 1000; 
          helper = new Point2D[M];
          quadTreeHelp(M);
          M = 10000;
        helper = new Point2D[M];
            quadTreeHelp(M);
            M = 30000;
        helper = new Point2D[M];
            quadTreeHelp(M);
            M = 50000;
        helper = new Point2D[M];
            quadTreeHelp(M);
            M = 70000;		
            helper = new Point2D[M];
            quadTreeHelp(M);
            M = 100000;
        helper = new Point2D[M];
            quadTreeHelp(M);	

	}	
  
  
  
	//Method that we use to create a random point and not write the same code again and again
	public static Point2D createPointMain() {		
        Random rand = new Random();

		x = rand.nextInt(N);  
    	y = rand.nextInt(N);
    	point = new Point2D(x, y);
    	return point;
	}
	
  
  
	//Using this method so that we won't write the same code over and over again
	public static void quadTreeHelp(double m2) {
		QuadTree qTree = new QuadTree();

		for(int i=0; i< m2; i++) {
			point = createPointMain();
			helper[i] = point;
			
			int value;
			value = (int)(Math.random() * 50 + 1); //We get a random value with range 1 - 50
			qTree.insert(point, value, N);
		}
		
	      System.out.println("For M=" +M);
	        System.out.println("Elements that exist in the tree");
	    	boolean check;
	    	double accesses=0;
	    	int k = 0;
	    	
			while(qTree.getSuccesfullSearch()<searches) {
				point = helper[k];
				qTree.setNumberOfAccesses(0);
				check = qTree.searchQuad(qTree.getRoot(), point);
				
				if(check==true) {
	        		accesses += qTree.getNumberOfAccesses();
	        		k++;
	        	}else if(check==false){
	        		accesses += 0;
	        	}
			}
	        System.out.println("Average number of successful accesses: "+accesses/searches);

	        qTree.setUnsuccesfullSearch(0);
			while(qTree.getUnsuccesfullSearch()<searches) {
			point = createPointMain();
			qTree.setNumberOfAccesses(0);
        	check = qTree.searchQuad(qTree.getRoot(), point);
        	
        	if(check==true) {
        		accesses += 0;
        	}else if(check==false){
        		accesses += qTree.getNumberOfAccesses();
        	}
		}
		
        System.out.println("Average number of unsuccessful accesses: "+accesses/searches);
		}
