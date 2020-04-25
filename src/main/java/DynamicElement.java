public  class DynamicElement extends Element {

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

    public void rotateRight() { this.direction.rotateRight();}

    public void rotateLeft() {this.direction.rotateLeft();}

    public Position forward() {return this.direction.forward();}
}
