package functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import database.Coordinate;

public final class Func {
	private static String outputFile = "output.txt";

	/**
	 * Private Constructor
	 */
	private Func() {
		// Empty Constructor - Static Class
	}

	/**
	 * 
	 * @param amount
	 * @param flag - 0=Without K, 1=With K
	 * @return
	 */
	public static List<Coordinate> createList(int amount) {
		// Declare
		List<Coordinate> thisArray = new ArrayList<Coordinate>();
		Random r = new Random();

		for (int i = 0; i < amount; i++) {
			Coordinate tmp = new Coordinate(r.nextDouble(), r.nextDouble());
			thisArray.add(tmp);

		}

		return thisArray;

	}

	public static void write(String msg) {
		PrintWriter out = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		try {
			out = new PrintWriter(new FileWriter(outputFile, true));
			out.write(dateFormat.format(date)+" - "+msg+"\r\n");

		} catch (Exception e) {
			System.err.println("Could not create log file");
			e.printStackTrace();
		} finally {
			out.close();
		}

	}
	public static void csv(String msg) {
		PrintWriter out = null;

		try {
			out = new PrintWriter(new FileWriter(outputFile, true));
			out.write(msg+"\r\n");

		} catch (Exception e) {
			System.err.println("Could not create log file");
			e.printStackTrace();
		} finally {
			out.close();
		}

	}
	public static void clearLog(){
		try{
			Files.delete(Paths.get(outputFile));
		}
		catch(Exception e){
			
		}
	}
}
