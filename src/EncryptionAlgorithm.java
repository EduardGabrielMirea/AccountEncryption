import java.io.*;

public class EncryptionAlgorithm {

    /**
     * Este método se usa para leer el archivo de texto plano y pasarlo a otro archivo una vez encriptado.
     * @param TextToEncrypt Texto Plano Inicial
     * @param EncryptedText Texto Encriptado
     * @param key La clave que usamos para encriptar/desencriptar.
     * @throws IOException Si ocurre un error de entrada/salida al manipular archivos.
     */

    public void enCryptFile(String TextToEncrypt, String EncryptedText, String key) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(TextToEncrypt));
             PrintWriter pw = new PrintWriter(new FileWriter(EncryptedText))) {

            // El bucle while se utiliza para leer línea por línea el archivo y escribirlas en otro archivo de forma encriptada usando el método encrypt.
            // El bucle terminará cuando ya no queden líneas por leer.

            String line;
            while ((line = br.readLine()) != null) {
                String encryptedLine = encrypt(line, key);
                pw.println(encryptedLine);
            }
        }
    }

    /**
     * Este método se usa para leer el archivo encriptado y pasarlo a otro archivo una vez desencriptado.
     * @param EncryptedText Texto Encriptado
     * @param TextDecrypted Texto Plano Desencriptado
     * @param key La clave que usamos para encriptar/desencriptar.
     * @throws IOException Si ocurre un error de entrada/salida al manipular archivos.
     */

    public void deCryptFile(String EncryptedText, String TextDecrypted, String key) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(EncryptedText));
             PrintWriter pw = new PrintWriter(new FileWriter(TextDecrypted))) {

            // El bucle while se utiliza para leer línea por línea el archivo encriptado y escribirlas en otro archivo de forma desencriptada usando el método decrypt.
            // El bucle terminará cuando ya no queden líneas por leer.

            String line;
            while ((line = br.readLine()) != null) {
                String decryptedLine = decrypt(line, key);
                pw.println(decryptedLine);
            }
        }
    }

    /**
     * Este método se usa para la encriptación de nuestro texto plano.
     * @param Plaintext Texto Plano que se desea encriptar
     * @param key La clave que se utiliza para encriptar/desencriptar.
     * @return El texto encriptado como resultado.
     */
    public String encrypt(String Plaintext, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyLength = key.length();
        /*
         * Guardamos la longitud de nuestra clave para usarla como posiciones de desplazamiento posteriormente.
         */

        /*
         * Con un bucle 'for' vamos a guardar cada carácter de nuestro texto plano en una variable tipo char.
         */

        for (int i = 0; i < Plaintext.length(); i++) {

            char letter = Plaintext.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            /*
             * Usamos un condicional para indicar que si nuestro carácter es una letra, se encriptará.
             * En caso contrario, es decir, si es un número u otro carácter, se incorporará al texto sin cifrar.
             */
            if (Character.isLetter(letter)) {

                /*
                 * Primero averiguamos si la letra es mayúscula o minúscula y la guardamos en un char que usaremos para que el desplazamiento
                 * se genere de igual forma para mayúsculas y minúsculas. Vamos a seguir los valores de los caracteres ASCII.
                 */
                char base = Character.isUpperCase(letter) ? 'A' : 'a';
                char keyBase = Character.isUpperCase(keyChar) ? 'A' : 'a';

                /*
                 * keyPosition guarda el valor numérico que indica la posición relativa de la letra actual de la clave dentro del alfabeto.
                 *
                 * Restaremos el valor de la posición de nuestro carácter introducido en la clave menos el valor base que le corresponde según el ASCII.
                 */
                int keyPosition = keyChar - keyBase;

                /*
                 * Con este condicional buscamos si la letra de nuestro texto está dentro del rango de valores ASCII para conservar las letras con acentos y otros caracteres sin cifrar.
                 *
                 * Por ejemplo, la letra 'L' tiene ASCII=76 y buscamos que esté entre los valores ASCII=65 y ASCII=90 (excluyendo la letra 'ñ').
                 *
                 * Si tiene acento u cualquier otro carácter estaría fuera de ese rango y, por lo tanto, se cumpliría la condición para añadirse sin modificación al texto cifrado.
                 */

                if (letter < base || letter > base + Serviceable.alphabetSize - 1) {
                    encryptedText.append(letter);
                }else {
                    /*
                     * En caso de que sea una letra alfabética normal, la encriptará.
                     *
                     * Para encriptarla usaremos:
                     *   letter: será la letra de nuestro texto inicial.
                     *   base: será nuestro carácter de referencia en el alfabeto ('A' o 'a' dependiendo del ASCII).
                     *   keyPosition: es la posición en el alfabeto de nuestra clave.
                     *   Serviceable.alphabetSize: es el número de posiciones que tiene el alfabeto (26).
                     *
                     * Si desglosamos:
                     *   ((letter - base + keyPosition) % Serviceable.alphabetSize) + base = nuevo carácter.
                     *
                     *   - (letter - base + keyPosition): devuelve el desplazamiento de nuestra letra.
                     *     Resta la posición de nuestra letra del texto menos la posición base (que es 'A' o 'a' dependiendo del ASCII)
                     *     para convertir la letra en una posición de 0 a 25, más el desplazamiento obtenido a partir de la clave.
                     *
                     *   - % Serviceable.alphabetSize: devuelve el número de posiciones que tiene el alfabeto (26).
                     *
                     *   - + base: añade base al resultado final para convertir el valor de la letra de nuevo a un valor ASCII.
                     */

                    char cryptedLetter = (char) ((((letter - base) + keyPosition) % Serviceable.alphabetSize ) + base);
                    encryptedText.append(cryptedLetter);
                }
            } else {
                encryptedText.append(letter);  // Mantener caracteres no alfabéticos
            }
        }
        return encryptedText.toString();
    }

    /**
     * Este método lo usaremos para el desencriptado de nuestro archivo de texto encriptado.
     * @param encryptedText Texto encriptado
     * @param key La clave que usamos para encriptar/desencriptar.
     * @return El texto desencriptado como resultado.
     */

    public String decrypt(String encryptedText, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < encryptedText.length(); i++) {

            char lettter = encryptedText.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            if (Character.isLetter(lettter)) {

                char base = Character.isUpperCase(lettter) ? 'A' : 'a';
                char keyBase = Character.isUpperCase(keyChar) ? 'A' : 'a';

                int keyPosition = keyChar - keyBase;

                if (lettter < base || lettter > base + Serviceable.alphabetSize - 1) {
                    decryptedText.append(lettter);
                }else {
                    char decryptedLetter = (char) ((lettter - base - keyPosition + Serviceable.alphabetSize) % Serviceable.alphabetSize + base);
                    decryptedText.append(decryptedLetter);
                }
            } else {
                decryptedText.append(lettter);
            }
        }
        return decryptedText.toString();
    }
}

