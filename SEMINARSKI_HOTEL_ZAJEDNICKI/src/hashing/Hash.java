/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author vuk
 */
public class Hash {

    public static String hesirajLozinku(String lozinka) throws NoSuchAlgorithmException {
        String salt = "xR!9@B2#dL$7Zm&Q";
        MessageDigest md=MessageDigest.getInstance("SHA-256");
        String kombinacija=lozinka+salt;
        byte[] kript=md.digest(kombinacija.getBytes());
        return Base64.getEncoder().encodeToString(kript);
    }    
}
