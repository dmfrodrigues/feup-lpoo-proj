package com.pacman.g60.View;


import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;

public class LanternaGUI implements TerminalGUI {
    private Screen screen;
    private static final String LANTERNA_FONT = "Monospaced";
    private static final int LANTERNA_FONT_SIZE = 10;
    public LanternaGUI(){
        // Initialize screen
        try{
            Font font = new Font(LANTERNA_FONT, Font.PLAIN, LANTERNA_FONT_SIZE);
            SwingTerminalFontConfiguration cfg = SwingTerminalFontConfiguration.newInstance(font);

            DefaultTerminalFactory factory = new DefaultTerminalFactory();
            factory.setInitialTerminalSize(new TerminalSize(280, 80));
            factory.setTerminalEmulatorFontConfiguration(cfg);
            Terminal terminal = factory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void drawCharacter(Integer x, Integer y, Character c, Color f, Color b) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(new TextColor.RGB(f.getR(), f.getG(), f.getB()));
        graphics.setBackgroundColor(new TextColor.RGB(b.getR(), b.getG(), b.getB()));
        graphics.putString(x, y, String.valueOf(c));
    }

    @Override
    public void drawString(Integer x, Integer y, String s, Color f, Color b) {
        for(Integer xadd = 0; xadd < s.length(); ++xadd){
            drawCharacter(x+xadd, y, s.charAt(xadd), f, b);
        }
    }

    @Override
    public void clear(){
        screen.doResizeIfNecessary();
        screen.clear();
    }
    
    final static int REFRESH_ALL_COUNT = 200;
    int refresh_count = REFRESH_ALL_COUNT;
    @Override
    public void refresh() throws IOException {
        if(refresh_count >= REFRESH_ALL_COUNT){
            refresh_count = 0;
            screen.refresh(Screen.RefreshType.DELTA);
        } else{
            screen.refresh(Screen.RefreshType.COMPLETE);
        }
        ++refresh_count;
    }

    @Override
    public KeyStroke pollKey() throws IOException{
        return screen.pollInput();
    }

    @Override
    public void close() throws IOException{
        screen.close();
    }

    @Override
    public Integer getW() { return screen.getTerminalSize().getColumns(); }

    @Override
    public Integer getH() { return screen.getTerminalSize().getRows(); }
}
