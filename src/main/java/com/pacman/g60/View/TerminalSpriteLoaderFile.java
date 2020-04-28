package com.pacman.g60.View;


import com.pacman.g60.Model.Color;

import java.io.InputStream;
import java.util.Scanner;

public class TerminalSpriteLoaderFile implements TerminalSprite.Loader{
    private TerminalSprite terminalSprite;
    public TerminalSpriteLoaderFile(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream);
        String firstLine = scanner.nextLine();
        Integer W = Integer.parseInt(firstLine.split(" ")[0]);
        Integer H = Integer.parseInt(firstLine.split(" ")[1]);
        terminalSprite = new TerminalSprite(W, H);
        for(Integer y = 0; y < H; ++y){
            String[] colors = scanner.nextLine().split(" ");
            if(colors.length != W) throw new IllegalArgumentException("Invalid file content");
            for(Integer x = 0; x < W; ++x)
                terminalSprite.setBackgroundColor(x, y, new Color(colors[x]));
        }
        for(Integer y = 0; y < H; ++y){
            String line = scanner.nextLine();
            if(line.length() != W) throw new IllegalArgumentException("Invalid file content");
            for(Integer x = 0; x < W; ++x)
                terminalSprite.setChar(x, y, line.charAt(x));
        }
        for(Integer y = 0; y < H; ++y){
            String[] colors = scanner.nextLine().split(" ");
            if(colors.length != W) throw new IllegalArgumentException("Invalid file content");
            for(Integer x = 0; x < W; ++x)
                terminalSprite.setForegroundColor(x, y, new Color(colors[x]));
        }
    }

    @Override
    public TerminalSprite getTerminalSprite() {
        return terminalSprite;
    }
}
