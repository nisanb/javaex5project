package database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import functions.Func;

/**
 * @author nisan
 *
 */

public class DB  implements Serializable{
	/**
	 * 	Variables Declaration
	 */
	private static final long serialVersionUID = -1874088804886228454L;
	private static final int NumberOfCoords = 100000;
	private static final int PointsToTest = 10000;
	private List<Coordinate> cordsList;
	private List<Coordinate> testPoints;
	private Container[][] cordsKSet;
	private int k;
	/**
	 * Basic DB Constructor
	 */
	public DB() {
		this.CreateDB();
		this.k=0;

	}
	/**
	 * Full DB Constructor
	 * @param k
	 */
	public DB(int k) {
		this.k=k;
		this.setCordsKArray(new Container[this.getK()][this.getK()]);
		this.CreateDB();
		
		
		if(isKMethod()){
			this.assignKArrays();
		}
		Random r = new Random();
		double x = r.nextDouble();
		double y = r.nextDouble();
		
	}

	public void printDistance() {
		Coordinate previous = null;
		for (Coordinate cord : getCordsList()) {
			if (previous == null) {
				previous = cord;
				continue;
			}
			Func.write("The distance between " + previous + " and " + cord + " is " + previous.getDistance(cord));
			previous = cord;
		}
	}

	/**
	 * Initiation of DB Creation
	 */
	public void CreateDB() {

		//Initiate Set
		this.setCordsList(Func.createList(NumberOfCoords));
		this.setTestPoints(Func.createList(PointsToTest));
		
	}

	// **GETTERS AND SETTERS**//

	/**
	 * @return the cordsSet
	 */
	public List<Coordinate> getCordsList() {
		return cordsList;
	}

	/**
	 * @param cordsSet
	 *            the cordsSet to set
	 */
	public void setCordsList(List<Coordinate> cordsList) {
		this.cordsList = cordsList;
	}

	/**
	 * @param testPoints
	 *            the cordsSet to set
	 */
	public void setTestPoints(List<Coordinate> testPoints) {
		this.testPoints = testPoints;
	}

	
	/**
	 * Insert new coordinate to Database
	 * 
	 * @param cord
	 */
	public void insertCord(Coordinate cord) {
		this.getCordsList().add(cord);
	}
	
	/**
	 * Returns if DB uses K and Array in order to store data
	 * @return
	 */
	public boolean isKMethod(){
		if(this.k>0) return true;
		return false;
	}

	/**
	 * @param cord
	 * @return closest Coordinate to the given coordinate
	 */
	public Coordinate getClosestTrivial(Coordinate cord) {

		double dist = 1;
		// cord - given test point
		Coordinate closest = new Coordinate();
		
		for(Coordinate tester : getCordsList()){
			
			double tmp = tester.getDistance(cord);
			if(tmp<dist){
				dist=tmp;
				closest=tester;
			}
		}
		
		
		
		return closest;
		
	}
	
	public Coordinate getClosestKIndexed(Coordinate cord){
		int kx = (int) (cord.getX()*(double)this.k);
		int ky = (int) (cord.getY()*(double)this.k);
		
		Coordinate found = null, tmpfound=null;
		double least = 1/(double)k;
		int range = 1, flag =1;
		
		//Keep searching while not found
		while(flag == 1){
			
			
			for(int i=kx-range;i<=kx+range;i++){
				for(int j=ky-range;j<=ky+range;j++){

					//I am now in the exact square!
					if(i<0 || j<0 || i>=this.getK() || j>=this.getK()) continue;
					if(getCordsKArray()[i][j]==null) continue;
					if(getCordsKArray()[i][j].getCords()==null) continue;
					
					for(Coordinate tmp : getCordsKArray()[i][j].getCords()){
						
						double dist = cord.getDistance(tmp);
					
						if(dist<least){
							//System.err.println(tmp+" "+cord+" "+dist);
							least=dist;
							tmpfound=tmp;
						}
					}


				}
			}
			if(tmpfound!=null){
				//Func.write("Found! "+tmpfound.toString());
				flag=0;
				found=tmpfound;
			}
			//System.err.println(range);
			range++;
			if(range>=this.getK()) break;
		}
		
		
		return found;
	}
	
	/**
	 * Get K Value
	 * @return
	 */
	public int getK(){
		return this.k;
	}
	
	public void assignKArrays(){
		for(Coordinate cord :this.getCordsList()){
			int kx = (int) (cord.getX()*this.getK());
			int ky = (int) (cord.getY()*this.getK());
			
			if(this.getCordsKArray()[kx][ky]==null) this.getCordsKArray()[kx][ky]=new Container();
			this.getCordsKArray()[kx][ky].addCoordinate(cord);

		}
	}
	
	/**
	 * Set K Value
	 * @param k
	 */
	public void setK(int k){
		this.setCordsKArray(new Container[k][k]);
		System.err.println("Initizlized K size: "+getCordsKArray().length);
		this.k=k;
	}
	public Container[][] getCordsKArray(){
		return this.cordsKSet;
	}
	public void setCordsKArray(Container[][] cords){
		this.cordsKSet = cords;
	}
	public List<Coordinate> getTestPoints(){
		return this.testPoints;
	}
}
