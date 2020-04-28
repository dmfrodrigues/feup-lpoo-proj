import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyGraph<T> implements Graph<T> {
    Map<T,List<T>> adj;

    public AdjacencyGraph()
    {
        this.adj = new HashMap<>();
    }

    @Override
    public void addNode(T node) {
        adj.put(node,new ArrayList<>());
    }

    @Override
    public void addEdge(T source, T dest) throws IllegalArgumentException {
        if  (!adj.containsKey(source) || !adj.containsKey(dest)) throw new IllegalArgumentException("Node does not exist.\n");
        adj.get(source).add(dest);
        adj.get(dest).add(source);
    }

    @Override
    public List getAdj(T node) {
        return adj.get(node);
    }
}
