package database;

import java.io.Serializable;

import functions.Func;

public class Coordinate implements Serializable{


	private static final long serialVersionUID = 5314840777500815517L;
	private double x;
	private double y;
	public Coordinate(){
		this.setX(0);
		this.setY(0);
	}
	
	public Coordinate(double x, double y){
		this.setX(x);
		this.setY(y);
	}
	
	
	/**
	 * @param cord
	 * @return closest Coordinate to the given coordinate
	 */
	public Double getDistance(Coordinate cord){
		if(cord==null) return 0.0;
		double dx = (this.getX()-cord.getX());
		double dy = (this.getY()-cord.getY());
		double dist = Math.sqrt(dx*dx+dy*dy);
		//Func.write("Distance Test\r\n-----------------\r\nCord1: "+this.toString()+"\r\nCord 2:"+cord.toString()+"\r\nDistance: "+dist);
		return dist;
		
	}	



	
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public void print(){
		Func.write("Coordinate [x=" + x + ", y=" + y + "]\n");
	}

	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + "]";
	}
	
	
	
	
}
