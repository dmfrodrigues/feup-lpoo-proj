public class NorthDirection implements Direction {
    private DynamicElement dynamicElement;

    public NorthDirection(DynamicElement dynamicElement)
    {
        this.dynamicElement = dynamicElement;
    }

    @Override
    public void rotateRight() {
        this.dynamicElement.changeDirection(new EastDirection(this.dynamicElement));
    }

    @Override
    public void rotateLeft() {
        this.dynamicElement.changeDirection(new WestDirection(this.dynamicElement));
    }

    @Override
    public Position forward() {
        Position currentPos = dynamicElement.getPos();
        Integer currentX = currentPos.getX();
        Integer currentY = currentPos.getY();
        Position newPos = new Position(currentX, --currentY);
        return newPos;
    }
}
