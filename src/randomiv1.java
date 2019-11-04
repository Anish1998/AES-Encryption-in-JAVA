import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.bouncycastle.util.encoders.Hex;

public class randomiv1 {
   public static void main( String args[] ) {
	   byte[]  resBuf = new byte[8];
	   new Random().nextBytes(resBuf);
	   String  resStr = new String(Hex.encode(resBuf));
	   System.out.println(resStr);
	   int len = resStr.length();
	    byte[] data = new byte[8];
	    System.out.println(resStr);
	    for (int i = 0; i < 16; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(resStr.charAt(i), 16) << 4)
	                             + Character.digit(resStr.charAt(i+1), 16));
	    }
	    System.out.println(data);
	    try {
            // Data is written into the file
			File file = new File("../data/randomiv1.txt");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(resStr);
			fileWriter.flush();
			fileWriter.close();// file is being closed
		} catch (IOException e) {
			e.printStackTrace();
		}
		   
		}
   }      
