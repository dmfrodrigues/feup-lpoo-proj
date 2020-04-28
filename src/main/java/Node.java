import java.util.List;

public interface Node {
    void setPath(Node path);
    Node getPath();
    void setDist(Integer dist);
    Integer getDist();
    List<Node> getAdj();
}
