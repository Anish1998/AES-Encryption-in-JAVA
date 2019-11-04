import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.bouncycastle.util.encoders.Hex;



import com.sun.jna.platform.FileUtils;

public class randombytes {
   public static void main( String args[] )throws IOException {
	   byte[]  resBuf = new byte[16];
	   new Random().nextBytes(resBuf);
	   String  resStr = new String(Hex.encode(resBuf));
	   System.out.println(resStr);
	   int len = resStr.length();
	    byte[] data = new byte[16];
	    for (int i = 0; i < 32; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(resStr.charAt(i), 16) << 4)
	                             + Character.digit(resStr.charAt(i+1), 16));
	    }
	    System.out.println(data);
	    try {
            // Data is written into the file
			File file = new File("../data/randombytes.txt");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(resStr);
			fileWriter.flush();
			fileWriter.close();// file is being closed
		} catch (IOException e) {
			e.printStackTrace();
		}
	    /*try (FileOutputStream fos = new FileOutputStream("G:/datasec/AES/src/randombytes.txt")) {
	    	   fos.write(data);*/
	    	   //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
	    	}
			}
		
      
