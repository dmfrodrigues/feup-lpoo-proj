package com.pacman.g60.View;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.pacman.g60.Model.MenuModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TerminalMenuView implements MenuView {
    
    public interface Loader {
        TerminalSprite[][] getOuterEdge();
        TerminalSprite[][] getInnerEdge();
    }
    
    private TerminalGUI terminalGUI;

    TerminalFont font;
    
    public TerminalMenuView(TerminalGUI terminalGUI) throws FileNotFoundException {
        this.terminalGUI = terminalGUI;
        TerminalFont.Loader loader = new TerminalFontLoaderStream(new FileInputStream("src/main/resources/lanterna-sprites/numbers-4-3.lan"));
        font = loader.getTerminalFont();
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

    @Override
    public void draw(MenuModel menu) throws IOException {
        terminalGUI.clear();
        MenuModel.Item selectedItem = menu.getSelectedItem();
        terminalGUI.drawString(0, 0, selectedItem.getText(), Color.WHITE, Color.BLACK);
        terminalGUI.refresh();
    }
}
