import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.crypto.*;
import javax.crypto.spec.*;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.*;
import org.bouncycastle.*;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class clubmix {
public static void main(String args[]) throws FileNotFoundException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
{
	
	Scanner in = new Scanner(new FileReader("../data/myfile.txt"));
	  StringBuilder sb = new StringBuilder();
	  while(in.hasNext()) {
	      sb.append(in.next());
	  }
	  
	  String outString = sb.toString();
	  //System.out.println(outString); //messahe
	  Scanner in1 = new Scanner(new FileReader("../data/randombytes.txt"));
	  StringBuilder sb1 = new StringBuilder();
	  while(in1.hasNext()) {
	      sb1.append(in1.next());
	  }
	  
	  String outString1 = sb1.toString();
	  //System.out.println(outString1);
	  //System.out.println(outString1); //rnaodmbytes
	  Scanner in2 = new Scanner(new FileReader("../data/randomiv1.txt"));
	  StringBuilder sb2 = new StringBuilder();
	  while(in2.hasNext()) {
	      sb2.append(in2.next());
	  }
	  
	  String outString2 = sb2.toString();
	  System.out.println("The initialization vector is: "+outString2); //randomiv
	  byte[]  resBuf = new byte[8];
	   new Random().nextBytes(resBuf);
	   String  resStr = new String(Hex.encode(resBuf));
	  byte[] plainTextByte = outString.getBytes("UTF8");
	  SecretKeySpec skeySpec1 = new SecretKeySpec(outString1.getBytes("UTF-8"), "AES");
		byte[] encryptedBytes = encrypt_ecb(plainTextByte, skeySpec1);

		String encryptedText = new String(encryptedBytes, "UTF8");
		System.out.println("Encrypted Text After Encryption in ECB: " + encryptedText);

		byte[] decryptedBytes = decrypt_ecb(encryptedBytes, skeySpec1);
		String decryptedText = new String(decryptedBytes, "UTF8");
		System.out.println("Decrypted Text After Decryption in ECB: " + decryptedText);
aesencrypt aes1=new aesencrypt();
long start=System.nanoTime();
String cipher = aes1.encrypt(outString,outString1,resStr);
long end=System.nanoTime();
long difference=end-start;
difference/=1000000;
System.out.println("Encrypted Text After Encryption in CBC: "+cipher);
String original=aes1.decrypt(resStr, outString1, cipher);
System.out.println("Decrypted Text After Decryption in CBC: "+original);
in.close();
in1.close();
in2.close();

System.out.println("The time elapsed during the encryption process is:"+difference);
}
public String encrypt(String outString,String outString1,String outString2) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
    
    	System.out.println(outString2.length());
        IvParameterSpec iv = new IvParameterSpec(outString2.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(outString1.getBytes("UTF-8"), "AES");
 
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
 
        byte[] encrypted = cipher.doFinal(outString.getBytes());
        
        return Base64.encodeBase64String(encrypted);
    }//
    //return null;

public static String decrypt(String outString2,String outString1,String encrypted)throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    
        IvParameterSpec iv = new IvParameterSpec(outString2.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(outString1.getBytes("UTF-8"), "AES");
 
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
 
        return new String(original);
     //catch (Exception ex) {
       // ex.printStackTrace();
    //}
 
   // return null;
}
static byte[] encrypt_ecb(byte[] plainTextByte, Key key)
		throws NullPointerException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
	Cipher cipher = Cipher.getInstance("AES");
	cipher.init(Cipher.ENCRYPT_MODE, key);
	byte[] encryptedBytes = cipher.doFinal(plainTextByte);
	return encryptedBytes;
}

static byte[] decrypt_ecb(byte[] encryptedBytes, Key key)
		throws NullPointerException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
	Cipher cipher = Cipher.getInstance("AES");
	cipher.init(Cipher.DECRYPT_MODE, key);
	byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
	return decryptedBytes;
}
}
 // }

