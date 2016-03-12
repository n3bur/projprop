package persistencia;

/**
 * Just a custom exception for when the format of a file is not what was expected.
 * @author Oriol Munoz Princep
 */
public class IncorrectFormatException extends Exception {
    private static final String message = "El format del fitxer no és el correcte." +
            "Potser no era el que li has dit al programa que era?";

    public IncorrectFormatException() {
        super(message);
    }
}
