import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {

    public static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws UnsupportedEncodingException, SQLException {
        FileHandler fh;
        try {
            fh = new FileHandler("lab3.log");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String user = System.getenv("dbuser");
        String password = System.getenv("dbpassword");
        logger.addHandler(fh);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        logger.setUseParentHandlers(false);
        consoleHandler.setEncoding("UTF-8");
        logger.addHandler(consoleHandler);
        DataBaseManager dataBaseManager = new DataBaseManager(user, password);

        Scanner userScanner = new Scanner(System.in);
        String userInput;


        do {
            try {
                System.out.println("\nInput key for a condition: ");
                dataBaseManager.getKeys();
                userInput = userScanner.nextLine();
                if(userInput.equals("addkey")) {
                    System.out.println("Input key: ");
                    String key = userScanner.nextLine();
                    System.out.println("Input condition: ");
                    String value = userScanner.nextLine();
                    dataBaseManager.addKey(key, value);
                    continue;
                }
                dataBaseManager.queryWithCondition(userInput);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } while (!userInput.isEmpty());
    }
}
