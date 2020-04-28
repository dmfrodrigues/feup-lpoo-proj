package Model;

import Model.Effect.DamageEffect;

public class Ghost extends Enemy{
    public Ghost(Position pos){
        super(pos, new DamageEffect());
    }
}
