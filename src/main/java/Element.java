public abstract class Element {
    private Position pos;
    public Element(Position pos){
        this.pos = pos;
    }

    public Position getPos(){
        return new Position(pos);
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

    public abstract int getMatrixValue();
}
