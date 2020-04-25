public abstract class Element {
    private Position pos;
    public Element(Position pos){
        this.pos = pos;
    }

    public Position getPos(){
        return new Position(pos);
    }
}
