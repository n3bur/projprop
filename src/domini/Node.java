package domini;

/**
 * Shared class
 * @author GrupYoutube
 */
public abstract class Node
{
    public abstract String getId();
    public boolean equals(Object o) { return getId().equals(((Node) o).getId()); }
}