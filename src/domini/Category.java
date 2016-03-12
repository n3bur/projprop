package domini;

/**
 * Class used to represent a Category in the Wikipedia
 * @author Aleix Pellisa Cortiella
 */

public class Category extends Node{
    private String id;

    /**
     * Constructor class
     * @param id Id of the Category
     */
    public Category(String id) {
        this.id = id;
    }

    /**
     * @return returns de id of the Category
     */
    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return id.equals(category.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
