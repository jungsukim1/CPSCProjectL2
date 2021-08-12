package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CSVReader {
	private ArrayList<String[]> DOUBLEHIT = new ArrayList<String[]>();
	private ArrayList<String[]> DOUBLESTAND = new ArrayList<String[]>();
	private ArrayList<String[]> MULTIHIT = new ArrayList<String[]>();
	private ArrayList<String[]> MULTISTAND = new ArrayList<String[]>();
	private ArrayList<String[]> SINGLEHIT = new ArrayList<String[]>();
	private ArrayList<String[]> SINGLESTAND = new ArrayList<String[]>();
/**
 * This is a constructor method to call up reader methods
 * "DH" calls up doubleHitReader()
 * "DS" calls up doubleStandReader()
 * "MH" calls up multiHitReader()
 * "MS" calls up multiStandReader()
 * "SH" calls up singleHitReader()
 * "SS" calls up singleStandReader()
 * Any other string input calls up singleStandReader as default
 * @param tableName ("DH", "DS", "MH", "MS", "SH", "SS")
 * @throws IOException
 */
	public CSVReader(String tableName) throws IOException {
		if (tableName == "DH") {
			doubleHitReader();
		}
		else if (tableName == "DS") {
			doubleStandReader();
		}
		else if (tableName == "MH") {
			multiHitReader();
		}
		else if (tableName == "MS") {
			multiStandReader();
		}
		else if (tableName == "SH") {
			singleHitReader();
		}
		else if (tableName == "SS") {
			singleStandReader();
		}
		else {
			singleStandReader();
		}
	}
	
	/**
	 * Method to read DoubleHit csv file
	 * Stored in DOUBLEHIT ArrayList
	 * @throws IOException
	 */
	public void doubleHitReader() throws IOException {
		String[] inner;
		String line = "";
		InputStream DoubleHit = getClass().getClassLoader().getResourceAsStream("DoubleHit.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(DoubleHit));
		while ((line = reader.readLine()) != null) {
			inner = line.split(",");
			DOUBLEHIT.add(inner);
			}
		}
	public void doubleStandReader() throws IOException {
		String[] inner;
		String line = "";
		InputStream DoubleStand = getClass().getClassLoader().getResourceAsStream("DoubleStand.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(DoubleStand));
		while ((line = reader.readLine()) != null) {
			inner = line.split(",");
			DOUBLESTAND.add(inner);
		}
	}
	/**
	 * Method to read MultiHit csv file
	 * Stored in MULTIHIT ArrayList
	 * @throws IOException
	 */
	public void multiHitReader() throws IOException {
		String[] inner;
		String line = "";
		InputStream MultiHit = getClass().getClassLoader().getResourceAsStream("MultiHit.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(MultiHit));
		while ((line = reader.readLine()) != null) {
			inner = line.split(",");
			MULTIHIT.add(inner);
		}
	}
	/**
	 * Method to read MultiStand csv file
	 * Stored in MULTISTAND ArrayList
	 * @throws IOException
	 */
	public void multiStandReader() throws IOException {
		String[] inner;
		String line = "";
		InputStream MultiStand = getClass().getClassLoader().getResourceAsStream("MultiStand.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(MultiStand));
		while ((line = reader.readLine()) != null) {
			inner = line.split(",");
			MULTISTAND.add(inner);
		}
	}
	/**
	 * Method to read SingleHit csv file
	 * Stored in SINGLEHIT ArrayList
	 * @throws IOException
	 */
	public void singleHitReader() throws IOException {
		String[] inner;
		String line = "";
		InputStream SingleHit = getClass().getClassLoader().getResourceAsStream("SingleHit.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(SingleHit));
		while ((line = reader.readLine()) != null) {
			inner = line.split(",");
			SINGLEHIT.add(inner);
		}
	}
	/**
	 * Method to read SingleStand csv file
	 * Stored in SINGLESTAND ArrayList
	 * @throws IOException
	 */
	public void singleStandReader() throws IOException {
		String[] inner;
		String line = "";
		InputStream SingleStand = getClass().getClassLoader().getResourceAsStream("SingleStand.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(SingleStand));
		while ((line = reader.readLine()) != null) {
			inner = line.split(",");
			SINGLESTAND.add(inner);
		}
	}
	/**
	 * Getter Method for DoubleHit
	 * Returns ArrayList<String[]>
	 */
	public ArrayList<String[]> getDOUBLEHIT() {
		return DOUBLEHIT;
	}
	/**
	 * Getter Method for DoubleStand
	 * Returns ArrayList<String[]>
	 */
	public ArrayList<String[]> getDOUBLESTAND() {
		return DOUBLESTAND;
	}
	/**
	 * Getter Method for MultiHit
	 * Returns ArrayList<String[]>
	 */
	public ArrayList<String[]> getMULTIHIT() {
		return MULTIHIT;
	}
	/**
	 * Getter Method for MultiStand
	 * Returns ArrayList<String[]>
	 */
	public ArrayList<String[]> getMULTISTAND() {
		return MULTISTAND;
	}
	/**
	 * Getter Method for SingleHit
	 * Returns ArrayList<String[]>
	 */
	public ArrayList<String[]> getSINGLEHIT() {
		return SINGLEHIT;
	}
	/**
	 * Getter Method for SingleStand
	 * Returns ArrayList<String[]>
	 */
	public ArrayList<String[]> getSINGLESTAND() {
		return SINGLESTAND;
	}
}	

