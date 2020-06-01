/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
//import java.util.Properties;
//import javax.mail.Authenticator;

/**
 *
 * @author anasc
 */
public class Session {
    private static int idUser;
   

    public static void start(int currentUserID) {
        idUser = currentUserID;
    }

    public static int getCurrentSession() {
        if (idUser != -1) {
            return idUser;
        } else {
            return 0;
        }
    }

    public static void close() throws Exception {
        if (idUser != -1) {
            idUser = -1;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }

    /*public static Session getInstance(Properties props, Authenticator authenticator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
