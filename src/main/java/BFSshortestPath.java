import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSshortestPath implements ShortestPath {
    @Override
    public void calcPath(Graph graph, Node start) {
        List<Node> nodes = (List<Node>) graph.getNodes();

        for (Node node : nodes)
        {
            node.setDist(Integer.MAX_VALUE);
            node.setPath(null);
        }

        start.setDist(0);
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty())
        {
            Node currentNode = queue.poll();
            List<Node> adjacent = (List<Node>) currentNode.getAdj();
            for (Node adjNode : adjacent)
            {
                if (adjNode.getDist() == Integer.MAX_VALUE)
                {
                    queue.add(adjNode);
                    adjNode.setDist(currentNode.getDist() + 1);
                    adjNode.setPath(currentNode);
                }
            }
        }
    }
}
