public class SouthDirection implements Direction {
    @Override
    public Direction rotateRight(){ return new WestDirection(); }

    @Override
    public Direction rotateLeft(){ return new EastDirection(); }

    @Override
    public Position forward() {
        return null;
    }
}
