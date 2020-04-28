import java.util.List;

public class AdjacencyGraph implements Graph {
    private List<Node> nodes;

    @Override
    public List<Node> getNodes() {
        return nodes;
    }
}
