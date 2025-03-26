/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacijaZajednicki;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author vuk
 */
public class Primalac {
    private Socket socket;

    public Primalac(Socket socket) {
        this.socket = socket;
    }

    public Object prima() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new Exception("Greska u prijemu objekta: \n" + ex.getMessage());
        }
    }    
}
