import java.util.ArrayList;
import java.util.List;

public class AdjacencyGraph implements Graph {
    private List<AdjacencyNode> nodes;

    public AdjacencyGraph(List<AdjacencyNode> nodes)
    {
        this.nodes = nodes;
        findAdjacents();
    }

    private void findAdjacents()
    {
        for (AdjacencyNode node : nodes)
        {
            Position firstPos = node.getPosition();
            int firstX = firstPos.getX();
            int firstY = firstPos.getY();

            List<AdjacencyNode> adj = new ArrayList<>();
            for (AdjacencyNode node2 : nodes)
            {
                Position secondPos = node2.getPosition();
                int secondX = secondPos.getX();
                int secondY = secondPos.getY();

                int diffX = firstX - secondX;
                int diffY = firstY - secondY;

                boolean isAdjacent = ((Math.abs(diffX) == 1) || (Math.abs(diffY) == 1));

                if (isAdjacent) adj.add(node2);
            }
            node.setAdj(adj);
        }
    }

    @Override
    public List<? extends Node> getNodes() {
        return nodes;
    }
}
