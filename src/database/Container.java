package database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Container implements Serializable{
	List<Coordinate> cords = null;
	public Container(){
		//Create Array
		cords = new ArrayList<Coordinate>();
	}
	
	
	public void addCoordinate(Coordinate cord){
		this.cords.add(cord);
	}
	
	public List<Coordinate> getCords(){
		return Collections.unmodifiableList(this.cords);
	}
	
	
	
}
