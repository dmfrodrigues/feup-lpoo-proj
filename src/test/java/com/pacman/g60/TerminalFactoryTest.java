package com.pacman.g60;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Position;
import com.pacman.g60.View.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

public class TerminalFactoryTest {
    
    @Mock
    TerminalGUI terminalGUI = Mockito.mock(TerminalGUI.class);

    ArgumentCaptor<Integer> xCaptor = ArgumentCaptor.forClass(Integer.class);

    ArgumentCaptor<Integer> yCaptor = ArgumentCaptor.forClass(Integer.class);

    ArgumentCaptor<Character> cCaptor = ArgumentCaptor.forClass(Character.class);

    ArgumentCaptor<Color> fCaptor = ArgumentCaptor.forClass(Color.class);

    ArgumentCaptor<Color> bCaptor = ArgumentCaptor.forClass(Color.class);
    
    private class ArgHolder{
        private final Integer x, y;
        private final Character c;
        private final Color f, b;

        private ArgHolder(Integer x, Integer y, Character c, Color f, Color b) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.f = f;
            this.b = b;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj.getClass() != getClass()) return false;
            ArgHolder a = (ArgHolder)obj;
            return  x.equals(a.x) &&
                    y.equals(a.y) &&
                    c.equals(a.c) &&
                    f.equals(a.f) &&
                    b.equals(a.b);
        }

        @Override
        public String toString() {
            return x + " " + y + " -" + c + "- " + f + " " + b;
        }
    }
    
    @Test
    public void test(){
        TerminalFactory factory = new TerminalFactory(terminalGUI);
        TerminalArenaView arenaView = factory.createArenaView();
        ArenaModel arenaModel = new ArenaModel(10, 10);
        arenaModel.addElement(new Hero(new Position(2, 2)));
        try {
            arenaView.draw(arenaModel);
        }catch(Exception e){
            fail();
        }
        Mockito.verify(terminalGUI, times(324)).drawCharacter(
                xCaptor.capture(),
                yCaptor.capture(),
                cCaptor.capture(),
                fCaptor.capture(),
                bCaptor.capture()
        );
        
        List<Integer> x = xCaptor.getAllValues();
        List<Integer> y = yCaptor.getAllValues();
        List<Character> c = cCaptor.getAllValues();
        List<Color> f = fCaptor.getAllValues();
        List<Color> b = bCaptor.getAllValues();
        List<ArgHolder> argHolders = new ArrayList<>();
        for(int i = 0; i < x.size(); ++i){
            argHolders.add(new ArgHolder(
                    x.get(i), 
                    y.get(i), 
                    c.get(i), 
                    f.get(i), 
                    b.get(i)
            ));
        }
        
        assertEquals(324, argHolders.size());
        
        try {
            Mockito.when(terminalGUI.pollKey()).thenReturn(null);
            assertEquals(null, arenaView.pollCommand());
            Mockito.when(terminalGUI.pollKey()).thenReturn(new KeyStroke(KeyType.ArrowUp));
            assertEquals(ArenaView.COMMAND.UP, arenaView.pollCommand());
            Mockito.when(terminalGUI.pollKey()).thenReturn(new KeyStroke(KeyType.ArrowDown));
            assertEquals(ArenaView.COMMAND.DOWN, arenaView.pollCommand());
            Mockito.when(terminalGUI.pollKey()).thenReturn(new KeyStroke(KeyType.ArrowRight));
            assertEquals(ArenaView.COMMAND.RIGHT, arenaView.pollCommand());
            Mockito.when(terminalGUI.pollKey()).thenReturn(new KeyStroke(KeyType.ArrowLeft));
            assertEquals(ArenaView.COMMAND.LEFT, arenaView.pollCommand());
            Mockito.when(terminalGUI.pollKey()).thenReturn(new KeyStroke(KeyType.Escape));
            assertEquals(ArenaView.COMMAND.EXIT, arenaView.pollCommand());
            Mockito.when(terminalGUI.pollKey()).thenReturn(new KeyStroke('a', false, false));
            assertEquals(null, arenaView.pollCommand());
        } catch(Exception e){
            fail();
        }
        
    }
}
