import java.util.List;

public class AdjacencyGraph implements Graph {
    private List<AdjacencyNode> nodes;

    @Override
    public List<? extends Node> getNodes() {
        return nodes;
    }
}
