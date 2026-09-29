import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GitInitialize {

    public static void main(String[] args) {
        System.out.println(init(false, false, false, false));

        try {
            System.out.println(hashFile("Hello.txt"));
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }

    }

    // This init method does four things:
    // 1. Create a git/ directory if it doesn't already exist
    // 2. Create an objects/ directory inside of git/ if it doesn't already exist
    // 3. Create a file named index inside git/ if it does not already exist, with no extension
    // 4. Create a file named HEAD inside git/ if it does not already exist, with no extension
    // all these prarmeters represent whether each file/directory already exists in ., they will be
    // checked throughout the method and all start as false
    public static String init(boolean gitExists, boolean objectsExists, boolean indexExists,
            boolean HEADExists) {

        try {

            // initialize the git directory by first creating a File object called git
            File git = new File("git");

            // make sure a directory named git doesn't already exist
            // if it doesn't exist set gitExists to false
            // otherwise set gitExists to true
            if (!git.exists()) {
                gitExists = false;
                git.mkdir();
            } else {
                gitExists = true;
            }

            // initialize the objects directory inside of git by first creating a File Object called
            // objects
            File objects = new File(git, "objects");

            // make sure a directory named objects doesn't already exist
            // if it doesn't set objectsExists to false
            // otherwise set objectsExists to true
            if (!objects.exists()) {
                objectsExists = true;
                objects.mkdir();
            } else {
                objectsExists = true;
            }

            // initialize the index file by first creating a File object inside of git called index
            File index = new File(git, "index");

            // make sure a file named index doesn't already exist
            // if it doesn't exist set indexExists to false
            // otherwise set indexExists to true
            if (!index.exists()) {
                indexExists = false;
                index.createNewFile();
            } else {
                indexExists = true;
            }

            // initialize the HEAD file by first creating a File object inside of git called HEAD
            File HEAD = new File(git, "HEAD");

            // make sure a file named HEAD doesn't already exist
            // if it doesn't exist set HEADExists to false
            // otherwise set HEADExists to true
            if (!HEAD.exists()) {
                HEADExists = false;
                HEAD.createNewFile();
            } else {
                HEADExists = true;
            }

            // if all four of the boolean parameters are true return: Git Repository Already Exists
            // otherwise return: Git Repository Created
            if (gitExists == true && objectsExists == true && indexExists == true
                    && HEADExists == true) {
                return "Git Repository Already Exists";
            } else {
                return "Git Repository Created";
            }

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
            return "";
        }
    }

    /**
     * Reads the file at filePath and returns its SHA-256 hash as a lowercase 64-character
     * hexadecimal string.
     */
    public static String hashFile(String filePath) throws IOException {

        // Create the SHA-1 calculator
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("SHA-1 algorithm is not available");
            return "";
        }

        // create the file reader for filePath
        FileReader reader = new FileReader(filePath);

        int character;
        // while the current character being read is valid, convert it into a byte and digest that
        // specific byte
        while ((character = reader.read()) != -1) {
            digest.update((byte) character);
        }

        // close the reader
        reader.close();

        // finish hash calculations
        byte[] hashBytes = digest.digest();

        String hash = "";

        // write each hash byte into a String
        for (int i = 0; i < hashBytes.length; i++) {
            hash += String.format("%02x", hashBytes[i]);
        }

        return hash;
    }
}
