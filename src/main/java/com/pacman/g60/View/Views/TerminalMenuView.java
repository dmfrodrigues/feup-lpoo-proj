package com.pacman.g60.View.Views;

import com.pacman.g60.Model.Models.MenuModel;
import com.pacman.g60.Model.Position;
import com.pacman.g60.Model.Models.TextModel;
import com.pacman.g60.View.Color;
import com.pacman.g60.View.Font.TerminalFont;
import com.pacman.g60.View.GUI.TerminalGUI;

import java.util.List;

public class TerminalMenuView extends MenuView {
    
    private final TerminalGUI terminalGUI;
    TerminalFont font;
    TerminalTextView textView;
    
    public TerminalMenuView(TerminalGUI terminalGUI, TerminalFont font) {
        super(terminalGUI);
        this.terminalGUI = terminalGUI;
        this.font = font;
        textView = new TerminalTextView(terminalGUI, this.font);
    }
    
    int Wbutton;
    final int Wbutton_margin = 5;
    final int Wframe_margin = 10;
    
    int Htotal;
    final int Hbutton_margin = 0;
    final int Hframe_margin = 3;
    
    private void drawFrame(int x0, int y0){
        
        int Wtotal = Wbutton + 2*font.getW() + 2*Wframe_margin;
        int Wtotal_chars = Wtotal/font.getW();
        int Htotal_chars = Htotal/font.getH();
        
        TextModel textModel = new TextModel("");
        textView.setTextModel(textModel);
        
        textModel.setText("╔" + "═".repeat(Wtotal_chars-2) + "╗");
        textModel.setPosition(new Position(x0, y0));
        textView.draw();
        
        for(int y = 1; y < Htotal_chars-1; ++y) {
            textModel.setText("║");
            
            textModel.setPosition(new Position(x0, y0+y*font.getH()));
            textView.draw();

            textModel.setPosition(new Position(x0 + (Wtotal_chars-1)*font.getW(), y0+y*font.getH()));
            textView.draw();
        }

        textModel.setText("╚" + "═".repeat(Wtotal_chars-2) + "╝");
        textModel.setPosition(new Position(x0, y0+(Htotal_chars-1)*font.getH()));
        textView.draw();
    }
    
    boolean frame = false;
    private void drawItem(int x0, int y0, int ymin, int ymax, MenuModel.Item item, int index){

        final int Hbutton = 3*font.getH();
        
        int x1 = x0                                  + (frame ? font.getW() + Wframe_margin : 0);
        int y1 = y0 + index*(Hbutton+Hbutton_margin) + (frame ? font.getH() + Hframe_margin : 0);
        
        int Wbutton_chars = Wbutton/font.getW();
        int Hbutton_chars = Hbutton/font.getH();

        TextModel textModel = new TextModel("");
        textView.setTextModel(textModel);

        textModel.setText("┌" + "─".repeat(Wbutton_chars-2) + "┐");
        textModel.setPosition(new Position(x1, y1));
        if(ymin <= textModel.getPosition().getY() && textModel.getPosition().getY() <= ymax) textView.draw();

        textModel.setText("│");
        
        for(int y = 1; y < Hbutton_chars-1; ++y) {
            textModel.setPosition(new Position(x1, y1+y*font.getH()));
            if(ymin <= textModel.getPosition().getY() && textModel.getPosition().getY() <= ymax) textView.draw();
            
            textModel.setPosition(new Position(x1 + (Wbutton_chars-1)*font.getW(), y1+y*font.getH()));
            if(ymin <= textModel.getPosition().getY() && textModel.getPosition().getY() <= ymax) textView.draw();
        }

        textModel.setText("└" + "─".repeat(Wbutton_chars-2) + "┘");
        textModel.setPosition(new Position(x1, y1+(Hbutton_chars-1)*font.getH()));
        if(ymin <= textModel.getPosition().getY() && textModel.getPosition().getY() <= ymax) textView.draw();

        int x2 = x1 + (Wbutton - textView.getStringWidth (item.getText()))/2;
        int y2 = y1 + (Hbutton - textView.getStringHeight(item.getText()))/2;
        if(item.isSelected()) {
            for (int x = x1 + font.getW(); x < x1 + (Wbutton_chars - 1) * font.getW(); ++x) {
                for (int y = y1 + font.getH(); y < y1 + (Hbutton_chars - 1) * font.getH(); ++y) {
                    if(ymin <= y && y <= ymax)
                        terminalGUI.drawCharacter(x, y, ' ', Color.WHITE, Color.DARK_GREY);
                }
            }
            textModel.setBackgroundColor(Color.DARK_GREY);
        }
        textModel.setPosition(new Position(x2, y2));
        textModel.setText(item.getText());
        textModel.setForegroundColor((item.getEnabled() ? Color.WHITE : Color.GREY));
        if(ymin <= textModel.getPosition().getY() && textModel.getPosition().getY() <= ymax) textView.draw();
    }
    
    @Override
    public void draw() {
        MenuModel menu = getMenuModel();

        frame = menu.hasFrame();
        
        Wbutton = 0;
        Htotal  = 0;
        for(final MenuModel.Item i: menu.getItems()) {
            Wbutton = Math.max(Wbutton, 2*font.getW() + 2*Wbutton_margin + textView.getStringWidth(i.getText()));
            Htotal += textView.getStringHeight(i.getText()) + Hbutton_margin + 2*font.getH();
        }
        if(!menu.getItems().isEmpty()) Htotal -= Hbutton_margin;
        if(frame) {
            Htotal  += 2 * font.getH() + 2 * Hframe_margin;
        }
        int Wtotal = Wbutton + (frame ? 2*font.getW() + 2*Wframe_margin : 0 );
        
        int x0 = (int)(terminalGUI.getW()*menu.getRelativePosition().getX());
        switch(menu.getHorizontalAlign()){
            case LEFT: break;
            case CENTER: x0 = x0 - Wtotal/2; break;
            case RIGHT: x0 = x0 - Wtotal; break;
        }

        int y0 = (int)(terminalGUI.getH()*menu.getRelativePosition().getY());
        switch(menu.getVerticalAlign()){
            case TOP: break;
            case CENTER: y0 = y0 - Htotal/2; break;
            case BOTTOM: y0 = y0 - Htotal; break;
        }
        
        Htotal = Math.min(Htotal, terminalGUI.getH() - y0);
        if(frame) Htotal -= Htotal % font.getH();
        
        List<MenuModel.Item> items = menu.getItems();

        final int Hbutton = 3*font.getH();
        final int ymin = (frame ? y0+font.getH() : y0 );
        final int ymax = (frame ? y0+Htotal-font.getH()-1 : terminalGUI.getH()-1);
        
        int i_selected;
        i_selected = 0;
        while (!items.get(i_selected).isSelected()) ++i_selected;
        int y_selected = y0 + i_selected*(Hbutton+Hbutton_margin) + (frame ? font.getH() + Hframe_margin : 0 );
        int y_selected_bottom = y_selected + Hbutton - 1;
        int delta_y = Math.max(0, y_selected_bottom - ymax);
        
        for(int i = 0; i < items.size(); ++i){
            drawItem(x0, y0-delta_y, ymin, ymax, items.get(i), i);
        }

        if(frame) {
            drawFrame(x0, y0);
        }
    }
}
