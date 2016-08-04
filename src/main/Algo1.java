package main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.xml.crypto.Data;

import org.omg.PortableServer.ImplicitActivationPolicyOperations;

import database.Coordinate;
import database.DB;
import functions.Func;


public class Algo1 {
	private static long startTime;
	private static long endTime;
	private static long totalTime;
	private static DB Data;
	private static ObjectInputStream objInput;
	
	
	
	public static void main(String[] args){
		//Clear Log File
		Func.clearLog();
		//Time Calculation
		
		
		/**
		 * Building Stage
		 */
		importData();
		
		exportData();
		
		Func.write("Starting K Experiment");
		for(int k=192;k<700;k++){
			Data.setK(k);
			Data.assignKArrays();
			//System.err.println("Testing K #"+k);
			startTime = new Date().getTime();;
			List<Coordinate> tmp = new ArrayList<Coordinate>();
			for(Coordinate cord : Data.getTestPoints()){
				Coordinate closest = Data.getClosestKIndexed(cord);
				tmp.add(closest);
				
			}
			endTime = new Date().getTime();;
			totalTime = (endTime-startTime);
			
			System.err.println(""+k+", "+totalTime+"");
			Func.csv(""+k+", "+totalTime+"");
		}
		
		
		Func.write("Starting Testing Points\r\n");
		//Go through TestPoints, and check closest point
		
		
		
		/*
		for(int k=0; k<tmp.size();k++){
			Func.write("Coordinate: "+Data.getTestPoints().get(k).toString() +" Closest: "+ tmp.get(k));
			
		}*/
		
		
		
		


		
		
		
		//Time Calculation
		
	
		System.err.println("Finished");
	}
	
	public static void exportData(){
		try{
			String fileName = "Data.cer";
			FileOutputStream output = new FileOutputStream(fileName);
			ObjectOutputStream objoutput = new ObjectOutputStream(output);
			objoutput.writeObject(Data);
			objoutput.close();
			
			System.err.println("Exported Data to "+fileName);
		}
		catch(Exception e){
			System.err.println("Could not export database\n"+e.toString());
		}
	}
	
	public static void importData(){
		try{
			String fileName = "Data.cer";
			FileInputStream input = new FileInputStream(fileName);
			objInput = new ObjectInputStream(input);
			Data = (DB) objInput.readObject();
		}
		catch(Exception e){
			System.err.println("Failed to import database\n"+e.toString());
			Data = new DB(100);
		}
	}

}
