import java.io.*;
public class EncryptionAlgorithm {

    /**
     *
     * Introduce el texto a cifrar:
     * Buenas, que tal?
     * Introduce la clave:
     * bum
     * Texto cifrado: IACAGQ, OHK GGJ?
     * Texto descifrado: BA1:-?, =A1 @-8?
     *
     * @param Plaintext
     * @param key
     * @return
     */

    public String encrypt(String Plaintext, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < Plaintext.length(); i++) {
            char letter = Plaintext.charAt(i);
            if (Character.isLetter(letter)) {
                char base = Character.isUpperCase(letter) ? 'A' : 'a';
                int keyPosition = Character.toUpperCase(key.charAt(i % keyLength)) - 'A';
                char cryptedLetter = (char) ((letter - base + keyPosition) % Serviceable.alphabetSize + base);
                encryptedText.append(cryptedLetter);
            } else {
                encryptedText.append(letter);  // Mantener caracteres no alfabéticos
            }
        }
        return encryptedText.toString();
    }

    public String decrypt(String encryptedText, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < encryptedText.length(); i++) {
            char lettter = encryptedText.charAt(i);
            if (Character.isLetter(lettter)) {
                char base = Character.isUpperCase(lettter) ? 'A' : 'a';
                int keyPosition = Character.toUpperCase(key.charAt(i % keyLength)) - 'A';
                char decryptedLetter = (char) ((lettter - base - keyPosition + Serviceable.alphabetSize) % Serviceable.alphabetSize + base);
                decryptedText.append(decryptedLetter);
            } else {
                decryptedText.append(lettter);  // Mantener caracteres no alfabéticos
            }
        }
        return decryptedText.toString();
    }

    public void encryptFile(String inputFilePath, String outputFilePath, String key) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
             PrintWriter pw = new PrintWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                String encryptedLine = encrypt(line, key);
                pw.println(encryptedLine);
            }
        }
    }

    public void deCryptFile(String inputFilePath, String outputFilePath, String key) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(outputFilePath));
             PrintWriter pw = new PrintWriter(new FileWriter(inputFilePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                String decryptedLine = decrypt(line, key);
                pw.println(decryptedLine);
            }
        }
    }
}

