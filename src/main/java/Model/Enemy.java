package Model;

import Model.Elements.DynamicElement;
import Model.Effect.Effect;

public abstract class Enemy extends DynamicElement {
    protected Effect effect;

    public Enemy(Position pos, Effect effect) {
        super(pos);
       this.effect = effect;
    }

}
