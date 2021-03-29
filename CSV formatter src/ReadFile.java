import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.ArrayList;

/**
 * 
 * @author Kamil Krauze - Student ID: 2414951
 *
 * @version 28/03/2021
 */
public class ReadFile {

	/**
	 * <p>Global ArrayLists that separately store the English & Spanish words, and their word classes.</p>
	 */
	private ArrayList<String> ENword = new ArrayList<String>();
	private ArrayList<String> ESword = new ArrayList<String>();
	private ArrayList<String> wordClass = new ArrayList<String>();
	
	/**
	 * <p>A method that reads in all of the lines of the specified csv file and stores each column per line to the array lists. Afterwards it creates a new file and writes to it per line.</p>
	 */
	public void ReadAndFormat()
	{
		FileReader fileReader;
		BufferedReader bufferedReader;
		String nextLine;
		
		FileOutputStream outputStream;
		PrintWriter printWriter;
		
		String[] splitln;
		String[] splitStr;
		
		try {
			fileReader = new FileReader("en-es_dictionary.csv");
			bufferedReader = new BufferedReader(fileReader);
			nextLine = bufferedReader.readLine();
			
			while(nextLine != null) {
				//System.out.println(nextLine);
				
				splitln = nextLine.split(",");
				
				splitln[0].trim();
				//System.out.println(splitln[0]);
				
				splitln[1] = splitln[1].replaceAll("\\{.*\\}","");
				splitln[1] = splitln[1].replaceAll("(\\[.*\\])|\"","");
				splitln[1] = splitln[1].replaceAll("(\\{.*\\})|\"","");
				splitln[1].trim();
				//System.out.println(splitln[1]);				
				
				splitStr = splitln[2].split("\s");
				splitStr[0] = splitStr[0].replaceAll("[{}]", "");
				splitln[0] = splitln[0].replaceAll("(\\{.*\\})|\"","");
				splitln[2] = splitStr[0];
				//System.out.println(splitln[2]);
				
				ENword.add(splitln[0]);
				ESword.add(splitln[1]);
				wordClass.add(splitln[2]);
				
				//System.out.print("\n");
				
				nextLine = bufferedReader.readLine();
			}
			bufferedReader.close();
			
			System.out.println(ENword.get(1));
			
			outputStream = new FileOutputStream("EN-ES-WC.csv");
			printWriter = new PrintWriter(outputStream);
			
			for (int j=0; j<(ENword.size()); j++)
			{				
				System.out.println(ENword.get(j)+","+ESword.get(j)+","+wordClass.get(j));
				printWriter.println(ENword.get(j)+","+ESword.get(j)+","+wordClass.get(j));
			}
			printWriter.close();
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static void main (String[] Args)
	{
		ReadFile readF = new ReadFile();
		
		readF.ReadAndFormat();
	}
}
