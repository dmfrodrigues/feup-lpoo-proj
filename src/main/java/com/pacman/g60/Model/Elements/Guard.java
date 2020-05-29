package com.pacman.g60.Model.Elements;

import com.pacman.g60.Model.Effect.DamageEffect;
import com.pacman.g60.Model.Effect.Effect;
import com.pacman.g60.Model.Elements.Hierarchy.MeleeAttackerElement;
import com.pacman.g60.Model.Position;

import java.util.LinkedList;
import java.util.Queue;

public class Guard extends Enemy implements MeleeAttackerElement {
    private GuardMovement movement;

    public Guard(Position pos)
    {
        super(pos,new DamageEffect(1),10);
        initMovement();
    }

    private void initMovement()
    {
        Position pos1 = new Position(33,13);
        Position pos2 = new Position(33,12);
        Position pos3 = new Position(33,11);
        Position pos4 = new Position(33,12);

        Queue<Position> path = new LinkedList<>();
        path.add(pos1);
        path.add(pos2);
        path.add(pos3);
        path.add(pos4);
        movement = new GuardMovement(path);
    }

    public Guard(Position pos, Effect effect, Integer health) {
        super(pos, effect, health);
    }

    public Position getNextPos()
    {
        return movement.move();
    }
}
