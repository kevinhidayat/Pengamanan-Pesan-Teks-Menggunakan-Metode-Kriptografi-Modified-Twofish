/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package twofish;

import java.awt.HeadlessException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class aewordchange {        
    
    public static String EncryptionLooping(String plaintext, String key){
        String penyimpanan = "HASIL";
        int spasi = 0;
        int jumlahkata = 0;
        StringTokenizer kalimat = new StringTokenizer(plaintext);
        double perhitungan;
        double jumlah = 0;
        try {
            char [] simpan = new char[plaintext.length()];
            char [] simpan2 = new char[16];
            for(int i=0 ; i<plaintext.length();i++){
                
                for(int j=0; j<plaintext.length(); j++){
                    if(plaintext.charAt(j) == ' '){
                        simpan[j] = ' ';
                    }
                    else if(i == j) {
                        int nambah = plaintext.charAt(j)+1;
                        simpan[j] = (char) nambah;
                    }
                    else simpan[j] = plaintext.charAt(j);
                    
                }

                // Dibagi menjadi 16 kata
//                int m = i/16;
//                int n = m*16;
//                int l = 0;
//                for(int j=n; j<n+16; j++){
//                    if(j>=plaintext.length()){
//                        simpan[l] = ' ';
//                        simpan2[l] = ' ';
//                    }else{
//                        if(plaintext.charAt(j) == ' '){
//                            simpan[l] = ' ';
//                            simpan2[l] = ' ';
//                        }
//                        else if(i == j){
//                            int nambah = plaintext.charAt(j)+1;
//                            simpan[l] = (char) nambah;
//                            simpan2[l] = plaintext.charAt(j);
//                        }
//                        else{
//                            simpan[l] = plaintext.charAt(j);
//                            simpan2[l] = plaintext.charAt(j);
//                        }
//                    }
//                    l++;
//                }


                String plaintextBerubah = new String(simpan);
                String plaintextBerubah2 = new String(simpan2);
                byte[] fileCiphertextOriginal = Twofish.twofishECBEncrypt(plaintext.getBytes(), key);
                byte[] fileCiphertext = Twofish.twofishECBEncrypt(plaintextBerubah.getBytes(), key);
                String katabit1 = katabit(fileCiphertextOriginal);
                String katabit2 = katabit(fileCiphertext);
                int count = 0;
                for(int k=0; k<katabit1.length(); k++){
                    if(katabit1.charAt(k) != katabit2.charAt(k)){
                        count++;
                    }
                }
                
                spasi=0;
                jumlahkata=0;
                for(int j=0; j<plaintext.length(); j++){
                    if(plaintext.charAt(j) == ' '){
                        spasi++;
                    }
                }
                perhitungan = (double) count *100 / katabit1.length();
                if(perhitungan != 0){
                    jumlah +=perhitungan;
                }
                
            }
            double ratarata = (double) jumlah/(plaintext.length()-spasi);

            penyimpanan += "\n"+"Jumlah kata : "+Integer.toString(kalimat.countTokens());
            penyimpanan += "\n"+"Jumlah huruf : "+(plaintext.length()-spasi);
            penyimpanan += "\n"+"Rata-rata avalanche effect : "+ratarata;
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(aewordchange.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return penyimpanan;
    }
    
    public static String katabit(byte[] temp){
        byte[] bytes = temp;

        StringBuilder bitSequence = new StringBuilder();
        for (byte b : bytes) {
            String binaryString = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            bitSequence.append(binaryString);
        }
        
        return bitSequence.toString();
    }
    
    public static String katastring(String temp){

        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (index < temp.length()) {
            String bits = temp.substring(index, index + 8);            
            int decimalValue = Integer.parseInt(bits, 2);
            stringBuilder.append((char) decimalValue);
            index += 8;
        }
        
        String output = stringBuilder.toString();
        
        return output;
    }
    
    public static String getMd5(String temp)
    {
        try {
            MessageDigest key = MessageDigest.getInstance("MD5");
            byte[] messageDigest = key.digest(temp.getBytes());
            BigInteger a = new BigInteger(1, messageDigest);

            String hash = a.toString(16);
            while (hash.length() < 32) {
                hash = "0" + hash;
            }
            return hash;
        }
 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
}
