import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        EncryptionAlgorithm crypt = new EncryptionAlgorithm();
/*
        System.out.println("Introduce el texto a cifrar:");
        String Plaintext = sc.nextLine();

        System.out.println("Introduce la clave:");
        String key = sc.nextLine();

        String encryptedText = crypt.encrypt(Plaintext, key);
        System.out.println("Texto cifrado: " + encryptedText);

        String decryptedText = crypt.decrypt(encryptedText, key);
        System.out.println("Texto descifrado: " + decryptedText);

 */

        System.out.println("Para cifar vamos a necesitar: \n"+
                "1- El archivo de texto que vayamos a cifrar (TextToEncrypt).\n"+
                "2- La calve que vamos a usar para encriptar que va a ser un String que usted va a introducir.\n"+
                "3- Un archivo de texto donde vamos a guardar el cifrado.");

        String inputFilePath = "src/TextToEncrypt";
        String outputFilePath = "src/EncryptedText";

        System.out.println("Introduce la clave:");
        String key = sc.nextLine();

        crypt.encryptFile(inputFilePath, outputFilePath, key);

        System.out.println("El archivo ha sido cifrado y guardado en: " + outputFilePath);

        crypt.deCryptFile(inputFilePath,outputFilePath,key);

        System.out.println("El archivo ha sido descifrado y guardado en: " + inputFilePath);

    }
}
