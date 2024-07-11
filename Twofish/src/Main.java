import twofish.Twofish;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import twofish.aewordchange;
import twofish.EncryptApp;


//author https://github.com/mycielski
//link https://github.com/mycielski/twofish-in-java
//mengakses 3 November 2022

public class Main {

   public static void main(String[] args) throws Exception {
//            EncryptApp modifiedtwofish = new EncryptApp();
//            modifiedtwofish.setVisible(true);


        String a = aewordchange.katabit("nama saya kevin".getBytes());
        String b = aewordchange.katabit("namb saya kevin".getBytes());
        String c = "nama saya kevin saya tinggal di batam maukah engkau pergi dan jalan";
        String c2 = "naoa saya kevin saya tinggal di batam maukah engkau pergi dan jalan";
        String key = aewordchange.getMd5("kevin");
        System.out.println(a);
        System.out.println(b);
        
        byte[] d = Twofish.twofishECBEncrypt(c.getBytes(StandardCharsets.UTF_8), key);
        String e = aewordchange.katabit(d);
        String f = new String(d, StandardCharsets.UTF_8);
        String f3 = bytesToHex(d);
        String f2 = convertStringToBinary(f);
        
        byte[] d2 = Twofish.twofishECBEncrypt(c2.getBytes(), key);
        String e2 = aewordchange.katabit(d2);
        String g = new String(d2);
        String g3 = bytesToHex(d2);
        String g2 = convertStringToBinary(g);
        int count=0;
        int max = f2.length();
        int min = g2.length();
        if(max < min){
            int temp = max;
            max = min;
            min = temp;
        }
        for(int k=0; k<max; k++){
            if(k < min){
                if(f2.charAt(k) != g2.charAt(k)){
                count++;
                }
            }else{
                count++;
            }

        }

        System.out.println("");
        System.out.println(f);
        System.out.println(f2);
        System.out.println(f3);
        System.out.println(g);
        System.out.println(g2);
        System.out.println(g3);
        System.out.println(count);
        System.out.println(f2.length());
        System.out.println(g2.length());
        System.out.println(max);
        System.out.println("");
        String nonAsciiString = "Karakter non-ASCII: \u00A9";
        System.out.println(convertStringToBinary("a"));
        byte[] nonAsciiBytes = { 72, 101, 108, 108, 111, (byte) 195, (byte) 169 }; // Example bytes with non-ASCII character "é"

        String convertedString = new String(nonAsciiBytes, StandardCharsets.UTF_8);

        System.out.println("Converted String: " + convertedString);
        System.out.println(new String(convertedString));
        
        
        System.out.println(bytesToHex(nonAsciiBytes));
        String hexString = bytesToHex(d); // "Hello World" in hexadecimal

        // Convert hex to decimal
        BigInteger decimalValue = new BigInteger(hexString, 16);

        // Convert decimal to string
        String stringValue = new String(decimalValue.toByteArray());

        System.out.println("Hexadecimal: " + hexString);
        System.out.println("String: " + stringValue);
        
    }
    
    public static String convertStringToBinary(String input) {

        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
                            .replaceAll(" ", "0")                         // zero pads
            );
        }
        return result.toString();

    }

        
        // Helper method to convert bytes to hexadecimal string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexStringBuilder = new StringBuilder();
        for (byte b : bytes) {
            hexStringBuilder.append(String.format("%02X", b));
        }
        return hexStringBuilder.toString();
    }
    
    
}
