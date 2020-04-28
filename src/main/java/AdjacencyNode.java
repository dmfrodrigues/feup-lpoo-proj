import java.util.ArrayList;
import java.util.List;

public class AdjacencyNode implements Node {
    public enum CellType
    {
        FREE,OBSTACLE,ENEMY
    }

    private CellType cellType;
    private AdjacencyNode path;
    private Integer dist;
    private Position position;
    private List<AdjacencyNode> adj;

    public AdjacencyNode()
    {
        this.position = new Position(0,0);
        this.cellType = CellType.FREE;
        this.dist = Integer.MAX_VALUE;
        this.path = null;
        this.adj = new ArrayList<>();
    }

    public AdjacencyNode(CellType cellType, AdjacencyNode path, Integer dist, Position position)
    {
        this.cellType = cellType;
        this.path = path;
        this.dist = dist;
        this.position = position;
        this.adj = new ArrayList<>();
    }


    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<AdjacencyNode> getAdj()
    {
        return adj;
    }

    public Integer getDist() {
        return dist;
    }

    public void setDist(Integer dist) {
        this.dist = dist;
    }

    public void setPath(AdjacencyNode path)
    {
        this.path = path;
    }

    public AdjacencyNode getPath() {
        return path;
    }

    public void setPath(AdjacencyNode path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        AdjacencyNode comp = (AdjacencyNode) o;

        return comp.path == this.path && comp.dist == this.dist && comp.cellType == this.cellType && comp.position == this.position;
    }
}
