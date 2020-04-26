import java.util.ArrayList;
import java.util.List;

public class ArenaModel {
    private int W, H;
    private Hero hero = null;
    private List<StaticElement> listStaticElements;
    private List<DynamicElement> listDynamicElements;

    public ArenaModel(int W, int H){
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

    public ArrayList<Element> getElements(){
        ArrayList<Element> res = new ArrayList<>();
        res.addAll(listStaticElements);
        res.addAll(listDynamicElements);
        return res;
    }


    public int getW() {
        return W;
    }

    public int getH() {
        return H;
    }

    public Hero getHero() { return hero; }

}
