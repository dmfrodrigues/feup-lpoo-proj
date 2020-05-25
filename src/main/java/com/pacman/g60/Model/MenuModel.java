package com.pacman.g60.Model;

import java.util.ArrayList;
import java.util.List;

public class MenuModel {

    public static abstract class Item {
        private String text;
        private Integer id;
        
        public final String getText() { return text; }
        public final void setText(String text) { this.text = text; }

        public final Integer getId() { return id; }
        public final void setId(Integer id) { this.id = id; }
    }
    
    public static class NormalItem extends Item {
        public NormalItem(Integer id, String text){
            setId(id);
            setText(text);
        }
    }
    
    List<Item> items = new ArrayList<>();
    int selected;
    
    public MenuModel(){}

    public void append(MenuModel.Item item) {
        items.add(item);
        if(items.size() == 1) selected = 0;
    }
    
    public void selectAbove(){
        if(items.size() == 0) throw new ArrayIndexOutOfBoundsException();
        --selected;
        if(selected < 0) selected = items.size()-1;
    }
    
    public void selectBelow(){
        if(items.size() == 0) throw new ArrayIndexOutOfBoundsException();
        ++selected;
        if(selected >= items.size()) selected = 0;
    }
    
    public Item getSelectedItem() {
        return items.get(selected);
    }
}
