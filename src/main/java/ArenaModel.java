import java.util.ArrayList;
import java.util.List;

public class ArenaModel {
    private int W, H;
    private Hero hero = null;
    private List<StaticElement> listStaticElements;
    private List<DynamicElement> listDynamicElements;

    public ArenaModel(int W, int H){
        this.W = W;
        this.H = H;
        listStaticElements = new ArrayList<>();
        listDynamicElements = new ArrayList<>();
    }

    public void addHero(Hero hero){
        this.hero = hero;
        addDynamicElement(hero);
    }

    public void addStaticElement(StaticElement staticElement){
        listStaticElements.add(staticElement);
    }

    public void addDynamicElement(DynamicElement dynamicElement){ listDynamicElements.add(dynamicElement); }

    public List<DynamicElement> getDynamicElements()
    {
        return this.listDynamicElements;
    }

    public ArrayList<Element> getElements(){
        ArrayList<Element> res = new ArrayList<>();
        res.addAll(listStaticElements);
        res.addAll(listDynamicElements);
        return res;
    }

    public Graph<Position> getGraph()
    {
        boolean[][] obstacle = new boolean[W][H];
        for (final StaticElement e : listStaticElements)
        {
            if (e instanceof Wall) obstacle[e.getPos().getX()][e.getPos().getY()] = true;
        }

        Graph<Position> G = new AdjacencyGraph<>();
        for (int x = 0; x < W; ++x)
        {
            for (int y = 0; y < H; ++y)
            {
                if (!obstacle[x][y]) G.addNode(new Position(x,y));
            }
        }
        for (int x = 0; x < W; ++x)
        {
            for (int y = 0; y < H; ++y)
            {
                if (obstacle[x][y]) continue;
                if (x > 0 && !obstacle[x-1][y]) G.addEdge(new Position(x,y),new Position(x-1,y));
                if (y > 0 && !obstacle[x][y-1]) G.addEdge(new Position(x,y),new Position(x,y-1));
                if (x < W-1 && !obstacle[x+1][y]) G.addEdge(new Position(x,y),new Position(x+1,y));
                if (y < H-1 && !obstacle[x][y+1]) G.addEdge(new Position(x,y),new Position(x,y+1));
            }
        }
        return G;
    }


    public int getW() {
        return W;
    }

    public int getH() {
        return H;
    }

    public Hero getHero() { return hero; }
    
    public interface Loader {
        public ArenaModel getArenaModel();
    }
}
