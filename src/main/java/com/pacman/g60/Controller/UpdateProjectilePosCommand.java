package com.pacman.g60.Controller;


import com.pacman.g60.Application;
import com.pacman.g60.Model.Elements.Bullet;
import com.pacman.g60.Model.Elements.DirectionalCalculator;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Hierarchy.LivingElement;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;

import java.util.List;

public class UpdateProjectilePosCommand implements Command {
    private Bullet bullet;
    private ArenaModel arenaModel;

    public UpdateProjectilePosCommand(Bullet bullet, ArenaModel arenaModel)
    {
        this.bullet = bullet;
        this.arenaModel = arenaModel;
    }

    @Override
    public void execute() {
        Application.Direction bulletDir = bullet.getDirection();
        Position currentPos = bullet.getPos();
        Position desiredPos = new DirectionalCalculator(bulletDir,currentPos).calcNextPos();

        if (arenaModel.isPositionAvailable(desiredPos,bullet))
        {
            bullet.setPos(desiredPos);
            arenaModel.updateMapKey(currentPos,desiredPos,bullet);
        }
        else
        {
            List<Element> elemsInDesiredPos = arenaModel.getElemFromPos(desiredPos);
            for (Element elem : elemsInDesiredPos)
            {
                if (elem instanceof LivingElement)
                {
                    Command command = new ApplyEffectCommand(bullet.getEffect(),elem);
                    command.execute();
                }
            }
            arenaModel.removeElement(bullet);
        }
    }
}
