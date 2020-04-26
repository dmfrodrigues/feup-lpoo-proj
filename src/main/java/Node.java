public class Node {
    public enum CellType
    {
        FREE,OBSTACLE,HERO
    }

    private CellType cellType;
    private Node path;
    private Integer dist;
    private Position position;

    public Node()
    {
        this.position = new Position(0,0);
        this.cellType = CellType.FREE;
        this.dist = Integer.MAX_VALUE;
        this.path = null;
    }

    public Node(CellType cellType, Node path, Integer dist, Position position)
    {
        this.cellType = cellType;
        this.path = path;
        this.dist = dist;
        this.position = position;
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



    public Integer getDist() {
        return dist;
    }

    public void setDist(Integer dist) {
        this.dist = dist;
    }

    public Node getPath() {
        return path;
    }

    public void setPath(Node path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        Node comp = (Node) o;

        return comp.path == this.path && comp.dist == this.dist && comp.cellType == this.cellType && comp.position == this.position;
    }
}
