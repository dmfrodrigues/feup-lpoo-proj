public class WestDirection implements Direction {
    private DynamicElement dynamicElement;

    public WestDirection(DynamicElement dynamicElement)
    {
        this.dynamicElement = dynamicElement;
    }

    @Override
    public void rotateRight() {
        this.dynamicElement.changeDirection(new NorthDirection(this.dynamicElement));
    }

    @Override
    public void rotateLeft() {
        this.dynamicElement.changeDirection(new SouthDirection(this.dynamicElement));
    }

    @Override
    public Position forward() {
        Position currentPos = dynamicElement.getPosition();
        Integer currentX = currentPos.getX();
        Integer currentY = currentPos.getY();
        Position newPos = new Position(--currentX, currentY);
        return newPos;
    }
}
