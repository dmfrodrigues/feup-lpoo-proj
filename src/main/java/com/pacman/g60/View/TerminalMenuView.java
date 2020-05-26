package com.pacman.g60.View;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.pacman.g60.Model.MenuModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TerminalMenuView implements MenuView {
    
    public interface Loader {
        TerminalSprite[][] getOuterEdge();
        TerminalSprite[][] getInnerEdge();
    }
    
    private TerminalGUI terminalGUI;
    TerminalFont font;
    TerminalTextView textView;
    
    public TerminalMenuView(TerminalGUI terminalGUI) throws FileNotFoundException {
        this.terminalGUI = terminalGUI;
        TerminalFont.Loader loader = new TerminalFontLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/numbers-4-3.lan"));
        font = loader.getTerminalFont();
        textView = new TerminalTextView(terminalGUI, font);
    }
    
    @Override
    public COMMAND pollCommand() throws IOException {
        KeyStroke key = terminalGUI.pollKey();
        if(key == null) return null;
        if(key.getKeyType() == KeyType.ArrowUp  ) return COMMAND.UP;
        if(key.getKeyType() == KeyType.ArrowDown) return COMMAND.DOWN;
        if(key.getKeyType() == KeyType.Enter    ) return COMMAND.ENTER;
        if(key.getKeyType() == KeyType.Escape   ) return COMMAND.EXIT;
        if(key.getKeyType() == KeyType.EOF      ) return COMMAND.EXIT;
        return null;
    }

    final int Wbutton = 12;
    final int Wbutton_margin = 1;
    final int Wmenu_margin = 2;
    final int Wtotal = Wbutton+2*Wbutton_margin+2+Wmenu_margin+2;

    final int Hbutton = 1;
    final int Hbutton_margin = 0;
    final int Hmenu_margin = 1;
    int Htotal;
    int x0;
    int y0;

    private void drawFrame(MenuModel menu){
        textView.draw(x0, y0, "╔");
        textView.draw(x0+font.getW(), y0, "═".repeat(Wtotal-2));
        textView.draw(x0+(Wtotal-1)*font.getW(), y0, "╗");
        for(int y = 1; y < Htotal-1; ++y) {
            textView.draw(x0, y0+y*font.getH(), "║");
            textView.draw(x0+(Wtotal-1)*font.getW(), y0+y*font.getH(), "║");
        }

        textView.draw(x0, y0+(Htotal-1)*font.getH(), "╚");
        textView.draw(x0+font.getW(), y0+(Htotal-1)*font.getH(), "═".repeat(Wtotal-2));
        textView.draw(x0+(Wtotal-1)*font.getW(), y0+(Htotal-1)*font.getH(), "╝");
    }
    
    private void drawItem(MenuModel.Item item, int index){
        int x1 = x0 + (1 + Wmenu_margin)*font.getW();
        int y1 = y0 + (1 + Hmenu_margin + index*(Hbutton+2))*font.getH();
        textView.draw(x1, y1, "┌");
        textView.draw(x1+font.getW(), y1, "─".repeat(Wbutton));
        textView.draw(x1+(Wbutton+1)*font.getW(), y1, "┐");

        for(int y = 1; y < Hbutton+1; ++y) {
            textView.draw(x1, y1+y*font.getH(), "│");
            textView.draw(x1+(Wbutton+1)*font.getW(), y1+y*font.getH(), "│");
        }

        textView.draw(x1, y1+(Hbutton+1)*font.getH(), "└");
        textView.draw(x1+font.getW(), y1+(Hbutton+1)*font.getH(), "─".repeat(Wbutton));
        textView.draw(x1+(Wbutton+1)*font.getW(), y1+(Hbutton+1)*font.getH(), "┘");

        int x2 = x1 + (((Wbutton+2)*font.getW()) - textView.getStringWidth (item.getText()))/2;
        int y2 = y1 + (((Hbutton+2)*font.getH()) - textView.getStringHeight(item.getText()))/2;
        if(item.isSelected()) {
            for (int x = x1 + font.getW(); x < x1 + (Wbutton + 1) * font.getW(); ++x) {
                for (int y = y1 + font.getH(); y < y1 + (Hbutton + 1) * font.getH(); ++y) {
                    terminalGUI.drawCharacter(x, y, ' ', Color.WHITE, Color.GREY);
                }
            }
            textView.draw(x2, y2, item.getText(), Color.WHITE, Color.GREY);
        } else {
            textView.draw(x2, y2, item.getText());
        }
    }
    
    @Override
    public void draw(MenuModel menu) throws IOException {
        terminalGUI.clear();
        Htotal = (Hbutton+2)*menu.getItems().size() + 2*Hmenu_margin + 2;
        x0 = terminalGUI.getW()/2 - Wtotal*font.getW()/2;
        y0 = terminalGUI.getH()/2 - Htotal*font.getH()/2;
        drawFrame(menu);
        
        List<MenuModel.Item> items = menu.getItems();
        for(int i = 0; i < items.size(); ++i){
            drawItem(items.get(i), i);
        }
        
        terminalGUI.refresh();
    }
}
