import java.util.Random;
public class EncryptionAlgorithm {

    /**
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
        boolean nonAlphabetic = false;

        for (int i = 0; i < Plaintext.length(); i++) {
            char letra = Plaintext.charAt(i);
            int posicionClave = (key.charAt(i % keyLength)) - 'A';

            if (Character.isLetter(letra)) {
                char cryptedLetter = (char) ((letra - 'A' + posicionClave) % Serviceable.alphabetSize + 'A');
                encryptedText.append(cryptedLetter);
            } else {
                nonAlphabetic = true;
                encryptedText.append(letra);  // Mantener caracteres no alfabéticos
            }
        }

        if (!nonAlphabetic) {
            Random random = new Random();
            int randomPosition = random.nextInt(encryptedText.length());
            char randomNonAlphabetic = Serviceable.nonAlphabeticChars[random.nextInt(Serviceable.nonAlphabeticChars.length)];
            encryptedText.setCharAt(randomPosition, randomNonAlphabetic);
        }

        return encryptedText.toString();
    }

    public String uncrypt(String encryptedText, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < encryptedText.length(); i++) {
            char lettter = encryptedText.charAt(i);
            int positionKey = (key.charAt(i % keyLength)) - 'A';

            if (Character.isLetter(lettter)) {
                char decryptedLetter = (char) ((lettter - 'A' - positionKey + Serviceable.alphabetSize) % Serviceable.alphabetSize + 'A');
                decryptedText.append(decryptedLetter);
            } else {
                decryptedText.append(lettter);  // Mantener caracteres no alfabéticos
            }
        }
        return decryptedText.toString();
    }
}

