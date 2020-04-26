
public abstract class Element {

    protected Position pos;
    public Element(Position pos){
        this.pos = pos;
    }

    public Position getPos(){
        return new Position(pos);
    }
    public void setPos(Position position)
    {
        this.pos = position;
    }
    /**
     * Override of equals. Also checks if they are the same class.
     * @param obj   Object to compare with
     * @return      True if they are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return (getClass().equals(obj.getClass()) &&
                pos.equals(((Element)obj).pos));
    }
}
