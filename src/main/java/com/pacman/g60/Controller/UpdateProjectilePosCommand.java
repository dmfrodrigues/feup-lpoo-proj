package com.pacman.g60.Controller;


import com.pacman.g60.Application;
import com.pacman.g60.Model.Elements.Bullet;
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
        Position desiredPos = null;
        switch(bulletDir)
        {
            case UP:
            {
                desiredPos = new Position(currentPos.getX(),currentPos.getY() - 1);
                break;
            }
            case DOWN:
            {
                desiredPos = new Position(currentPos.getX(),currentPos.getY() + 1);
                break;
            }
            case LEFT:
            {
                desiredPos = new Position(currentPos.getX() - 1,currentPos.getY());
                break;
            }
            case RIGHT:
            {
                desiredPos = new Position(currentPos.getX() + 1,currentPos.getY());
                break;
            }
        }

        if (arenaModel.isPositionAvailable(desiredPos,bullet))
        {
            bullet.setPos(desiredPos);
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
