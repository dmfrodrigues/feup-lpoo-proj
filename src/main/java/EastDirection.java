public class EastDirection implements Direction {
    @Override
    public Direction rotateRight() { return new SouthDirection(); }

    @Override
    public Direction rotateLeft() { return new NorthDirection(); }

    @Override
    public Position forward() {
        return null;
    }
}
