import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        EncryptionAlgorithm crypt = new EncryptionAlgorithm();

        System.out.println("To encrypt, we will need: \n" +
                "1- The text file we want to encrypt (TextToEncrypt).\n" +
                "2- The key we will use for encryption, which will be a String you will enter.\n" +
                "3- A text file where we will save the encrypted text (EncryptedText).\n" +
                "4- A text file where we will save the decrypted text (TextDecrypted).");

        String TextToEncrypt = "src/Text/TextToEncrypt";
        String EncryptedText = "src/Text/EncryptedText";

        /*
         Let's set a condition for the user, where we will explicitly ask them to enter a key that is alphabetical, avoiding numbers or other characters.
         */
        String key;
            while (true) {
                System.out.println("Enter the key:");
                key = sc.nextLine();
                /*
                We validate that the key only contains letters using the `.matches` method.
                This method compares the characters of the key with the characters we specify within the list, where they can match one or more times, hence we use the `+` sign.
                 */
                if (key.matches("[a-zA-Z]+")) {
                    break;
                } else {
                    System.out.println("The entered key contains numbers or special characters. Please enter only letters.");
                }
            }


        crypt.enCryptFile(TextToEncrypt, EncryptedText, key);
        System.out.println("The file has been encrypted and saved to: " + EncryptedText);

        String TextDecrypted = "src/Text/TextDecrypted";
        crypt.deCryptFile(EncryptedText, TextDecrypted, key);

        System.out.println("The file has been decrypted and saved to: " + TextDecrypted);

    }
}
