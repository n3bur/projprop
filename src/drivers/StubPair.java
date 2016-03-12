package drivers;

/**
 * Created by pau on 19/04/15.
 */
public class StubPair<F, S> {
    private F f;
    private S s;

    public StubPair() {}
    public StubPair(F first, S second) { f = first; s = second; }

    public void SetFirst(F first) { f = first; }
    public void SetSecond(S second) { s = second; }

    public F GetFirst()  { return f; }
    public S GetSecond() { return s; }
}
