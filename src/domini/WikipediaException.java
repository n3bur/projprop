package domini;

/**
 * Exception for the invalid operations in Wikipedia
 * @author Aleix Pellisa Cortiella
 */
public class WikipediaException extends RuntimeException{

    /**
     * Constructor class
     * @param nameException Name of the exception
     */
    public WikipediaException(String nameException) {
        super(nameException);
    }
}
