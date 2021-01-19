package com.psl.oms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author rancel
 *
 */

public class BillGenerator {
	public void writeFile(String s,int pid)
	{
		String filename="C:\\Users\\rance\\Desktop\\psl\\JavaAssignment\\Order_Management_System\\src\\com\\psl\\oms\\Files\\"+pid+".txt";
		try {
		      File myObj = new File(filename);
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
	}
	try {
	      FileWriter myWriter = new FileWriter(filename);
	      myWriter.write(s);
	      myWriter.close();
	      System.out.println("Successfully wrote to the file.");
	      } catch (IOException e) {
	    System.out.println("An error occurred.");
	    e.printStackTrace();
	    }
}
}
