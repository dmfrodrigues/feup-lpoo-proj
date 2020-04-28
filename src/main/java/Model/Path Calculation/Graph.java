import java.util.List;

public interface Graph<T> {
    void addNode(T node);
    void addEdge(T source, T dest) throws IllegalArgumentException;
    List<T> getAdj(T node);
    List<T> getNodes();
}
