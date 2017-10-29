package bwi.prog.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Class to support reading of .in/ .out files for testing
 * @author JW
 *
 */
public class TestNGSupport {
		String className;
		String dirName;
		
		public TestNGSupport(String className, String dirName){
			this.className=className;
			this.dirName=dirName;
		}
		
	  private String readInOut(String fileName){
		  
			byte[] encoded=new byte[]{};
			try {
				encoded = Files.readAllBytes(Paths.get(fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new String(encoded);
			  
		  }
	  
	  public Object[][] getDataProvider(){
		  //find all in and out files for this class
		  File dir = new File(dirName);
		  //all in files
		  FilenameFilter filter= new FilenameFilter() { 
		         		public boolean accept(File dir, String filename)
		         			{ return (filename.startsWith(className+"_")&&filename.endsWith(".in")); }
		  			} ; 
	  	  File[] files=dir.listFiles(filter);
	  	  ArrayList<Object[]> myIO=new ArrayList<Object[]>();
	  	  //add in and outfiles to as Object [] to ArrayList 
	  	  //for each element in Object Array read in/out
	  	  for (File f : files)
	  	  {
	  		String inFile=f.getAbsoluteFile().toString();
			  String outFile=inFile.replace(".in",".out");
			  
			  myIO.add(new Object[]{readInOut(inFile),readInOut(outFile)});
	  	  }
	 
	  	  //and save it to an arraylist
		  	Object[][] myArr=new Object[myIO.size()][];
		  	myIO.toArray(myArr);
		  	return myArr;
	  }
	  

}
