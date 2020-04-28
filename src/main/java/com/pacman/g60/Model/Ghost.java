package com.pacman.g60.Model;


import com.pacman.g60.Model.Effect.DamageEffect;

public class Ghost extends Enemy{
    public Ghost(Position pos){
        super(pos, new DamageEffect());
    }
}
