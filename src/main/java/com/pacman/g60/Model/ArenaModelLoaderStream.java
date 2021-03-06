/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Model;

import com.pacman.g60.Model.Elements.*;
import com.pacman.g60.Model.Models.ArenaModel;

import java.io.InputStream;
import java.util.Scanner;

public class ArenaModelLoaderStream implements ArenaModel.Loader {
    private class Factory {
        private Position pos;
        public Factory(Position pos){ this.pos = new Position(pos); }
        public Element factoryMethod(Character c) throws IllegalArgumentException {
            switch(c){
                case 'W': return new Wall(pos);
                case 'H': return new Hero(pos);
                case 'G': return new Ghost(pos);
                case 'O': return new Ogre(pos);
                case 'C': return new Coin(pos);
                case 'P': return new HealthPotion(pos);
                case 'S': return new Sword(pos);
                case 'B': return new Bullet(pos);
                case 'M': return new Mummy(pos);
                case 'V': return new Guard(pos, new VerticalGuardMovementStrategy(pos));
                case 'R': return new Guard(pos, new HorizontalGuardMovementStrategy(pos));
                case ' ': return null;
                default: throw new IllegalArgumentException("Unknown character '" + c + "'");
            }
        }
    }

    private ArenaModel arenaModel;

    public ArenaModelLoaderStream(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        String[] firstLine = scanner.nextLine().split(" ");
        Integer W = Integer.parseInt(firstLine[0]);
        Integer H = Integer.parseInt(firstLine[1]);
        arenaModel = new ArenaModel(W, H);
        Position pos;
        for(Integer y = 0; y < H; ++y){
            String line = scanner.nextLine();
            for(Integer x = 0; x < W; ++x){
                ArenaModelLoaderStream.Factory factory = new ArenaModelLoaderStream.Factory(new Position(x, y));
                Element element = factory.factoryMethod(line.charAt(x));
                if(element != null) arenaModel.addElement(element);
            }
        }
    }

    @Override
    public ArenaModel getArenaModel() {
        return arenaModel;
    }
}
