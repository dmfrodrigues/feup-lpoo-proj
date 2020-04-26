public  class DynamicElement extends Element {

    protected Direction direction;

    public DynamicElement(Position position)
    {
        super(position);
        this.direction = Direction.RIGHT;
    }

    public void changeDirection(Direction direction)
    {
        this.direction = direction;
    }

}
