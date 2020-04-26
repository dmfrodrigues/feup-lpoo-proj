public class Wall extends StaticElement {
    public Wall(Position pos){
        super(pos);
    }

    @Override
    public boolean isObstacle() {
        return true;
    }

    @Override
    public boolean isEnemy() {
        return false;
    }
}
