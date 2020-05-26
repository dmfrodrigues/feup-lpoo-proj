package com.pacman.g60.Model;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MenuModel {
    
    public static abstract class Item implements Cloneable {
        private MenuModel parent;
        private String text;
        private Integer id;
        
        @Override
        public Object clone() {
            try {
                return getClass().getDeclaredConstructor(MenuModel.class, Integer.class, String.class).newInstance(parent, id, text);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        }

        public Item(MenuModel parent, Integer id, String text){
            this.parent = parent;
            this.text = text;
            this.id = Integer.valueOf(id);
        }

        public void setParent(MenuModel parent) {
            this.parent = parent;
        }
        
        public final String getText() { return text; }
        //public final void setText(String text) { this.text = text; }

        public final Integer getId() { return id; }
        //public final void setId(Integer id) { this.id = id; }

        public boolean isSelected() {
            return (id.equals(parent.getSelectedItem().getId()));
        }
        
    }
    
    public static class NormalItem extends Item {
        public NormalItem(MenuModel parent, Integer id, String text){
            super(parent, id, text);
        }
    }
    
    List<Item> items = new ArrayList<>();
    int selected;
    
    public MenuModel(){}

    public MenuModel(MenuModel menuModel) {
        this();
        for(Item item: menuModel.items) {
            Item item_ = (Item)item.clone();
            item_.setParent(this);
            append(item_);
        }
        selected = menuModel.selected;
    }


    public List<Item> getItems() {
        return items;
    }

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
