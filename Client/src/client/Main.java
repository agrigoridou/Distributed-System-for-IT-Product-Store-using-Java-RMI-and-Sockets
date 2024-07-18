package client;

import user_management.UserManager;

import java.rmi.Naming;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            UserManager userManager = (UserManager) Naming.lookup("//localhost:1099/UserManager");
            Client client = new Client(userManager);
            client.start();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while starting the client", e);
        }
    }
}
