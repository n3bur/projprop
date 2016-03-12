package domini;

/**
 * Class used to represent a Page in the Wikipedia
 * @author Aleix Pellisa Cortiella
 */

public class Page {
    private String id;

    /**
     * Constructor class
     * @param id Id of the Page
     */
    public Page(String id) {
        this.id = id;
    }

    /**
     * @return returns de id of the Page
     */
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Page page = (Page) o;

        return id.equals(page.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
