package com.pacman.g60.View;

import java.io.InputStream;
import java.util.Scanner;

public class TerminalFontLoaderStream implements TerminalFont.Loader {
    private TerminalFont terminalFont;
    public TerminalFontLoaderStream(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream);
        String line = scanner.nextLine();
        Integer W = Integer.parseInt(line.split(" ")[0]);
        Integer H = Integer.parseInt(line.split(" ")[1]);
        terminalFont = new TerminalFont(W, H);
        line = scanner.nextLine();
        Integer N = Integer.parseInt(line);
        for(Integer n = 0; n < N; ++n) {
            line = scanner.nextLine();
            Character c = line.charAt(0);
            TerminalFont.TerminalCharacter tchar = new TerminalFont.TerminalCharacter(W, H);
            for (Integer y = 0; y < H; ++y) {
                line = scanner.nextLine();
                if (line.length() != W) throw new IllegalArgumentException("Invalid file content");
                for (Integer x = 0; x < W; ++x)
                    tchar.setChar(x, y, line.charAt(x));
            }
            terminalFont.setCharacter(c, tchar);
        }
    }

    @Override
    public TerminalFont getTerminalFont() {
        return terminalFont;
    }
}
