import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EncryptionAlgorithm crypt = new EncryptionAlgorithm();

        System.out.println("Introduce el texto a cifrar:");
        String Plaintext = sc.nextLine();

        System.out.println("Introduce la clave:");
        String key = sc.nextLine();

        String encryptedText = crypt.encrypt(Plaintext, key);
        System.out.println("Texto cifrado: " + encryptedText);

        String decryptedText = crypt.uncrypt(encryptedText, key);
        System.out.println("Texto descifrado: " + decryptedText);
    }
}
