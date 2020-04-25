import java.util.ArrayList;
import java.util.List;

public class ArenaModel {
    private int W, H;
    private List<StaticElement> listStaticElements;
    private List<DynamicElement> listDynamicElements;

    public ArenaModel(int W, int H){
        listStaticElements = new ArrayList<>();
        listDynamicElements = new ArrayList<>();
    }

    public void addStaticElement(StaticElement staticElement){
        listStaticElements.add(staticElement);
    }

    public ArrayList<Element> getElements(){
        ArrayList<Element> res = new ArrayList<>();
        res.addAll(listStaticElements);
        res.addAll(listDynamicElements);
        return res;
    }
}
