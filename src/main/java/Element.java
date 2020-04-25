public abstract class Element{
    protected Position position;

    public Element(Position position) {this.position = position;}

    public void setPosition(Position position)
    {
        this.position = position;
    }
    public Position getPosition()
    {
        return this.position;
    }
}
