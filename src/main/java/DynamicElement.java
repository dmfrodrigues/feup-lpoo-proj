public abstract class DynamicElement extends Element {
    protected Direction direction;

    public DynamicElement(Position position)
    {
        super(position);
        this.direction = new SouthDirection(this);
    }

    public void changeDirection(Direction direction)
    {
        this.direction = direction;
    }
}
