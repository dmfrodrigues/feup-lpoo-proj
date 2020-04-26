public class Color {
    int r, g, b;
    public Color(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public Color(String hex) throws IllegalArgumentException {
        if(hex.length() != 7) throw new IllegalArgumentException("Argument has wrong length");
        if(hex.charAt(0) != '#') throw new IllegalArgumentException("Argument has missing initial '#'");
        this.r = Integer.parseInt(hex.substring(1, 3));
        this.g = Integer.parseInt(hex.substring(3, 5));
        this.b = Integer.parseInt(hex.substring(5, 7));
    }
    public int getR(){ return r; }
    public int getG(){ return g; }
    public int getB(){ return b; }
}
