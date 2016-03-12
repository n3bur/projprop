package domini;

/**
 * Shared class
 * @author GrupYoutube
 */
public class Edge
{
    float weight;
    public Edge() { weight = 0.0f; }
    public Edge(float weight) { this.weight = weight; }
    public float getWeight() { return weight; };
    public void  setWeight(float weight) {this.weight = weight; }

    //public boolean equals(Object o) { return getWeight() == ((Edge)o).getWeight(); }
}
