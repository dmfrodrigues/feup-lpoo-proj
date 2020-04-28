import java.util.List;

public interface Node {
    void setPath(Node path);
    Node getPath();
    void setDist(Integer dist);
    Integer getDist();
    void setAdj(List<? extends Node> adj);
    List<? extends Node> getAdj();
}
