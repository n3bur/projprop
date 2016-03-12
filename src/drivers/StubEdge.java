package drivers;

/**
 * Created by pau on 19/04/15.
 */
public class StubEdge {
    private StubPair<StubNode, StubNode> nodes;
    private float weight;

    public void setWeight(float w) {
        weight = w;
    }

    public float getWeight() {
        return weight;
    }

    public StubPair<StubNode, StubNode> getNodes() {
        return nodes;
    }
}
