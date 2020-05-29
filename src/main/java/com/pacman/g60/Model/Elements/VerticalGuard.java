package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Position;

import java.util.LinkedList;
import java.util.Queue;

public class VerticalGuard extends Guard {

    public VerticalGuard(Position pos)
    {
        super(pos,new DamageEffect(1),10);
        initMovement(pos);
    }

    private void initMovement(Position initialPos)
    {
        Position pos1 = initialPos;
        Position pos2 = new Position(initialPos.getX(),initialPos.getY() - 1);
        Position pos3 = new Position(initialPos.getX(),initialPos.getY() - 2);
        Position pos4 = new Position(initialPos.getX(),initialPos.getY() - 1);

        Queue<Position> path = new LinkedList<>();
        path.add(pos1);
        path.add(pos2);
        path.add(pos3);
        path.add(pos4);
        this.movement = new GuardMovement(path);
    }

    public VerticalGuard(Position pos, Effect effect, Integer health) {
        super(pos, effect, health);
    }

}
