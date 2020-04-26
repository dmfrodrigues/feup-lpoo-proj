public class WestDirection implements Direction {
    @Override
    public Direction rotateRight(){ return new NorthDirection(); }

    @Override
    public Direction rotateLeft(){ return new SouthDirection(); }

    @Override
    public Position forward() {
        return null;
    }
}
