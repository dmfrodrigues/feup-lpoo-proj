package com.pacman.g60.Controller;

import com.pacman.g60.Application;
import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.Elements.*;
import com.pacman.g60.Model.Position;

import java.util.List;


public class MoveHeroCommand implements Command {
    private ArenaModel arenaModel;
    private Application.Direction dir;

    public MoveHeroCommand(ArenaModel arenaModel, Application.Direction dir){this.arenaModel = arenaModel; this.dir = dir;}

    @Override
    public void execute() {
        Position newPos = new Position(0,0);
        switch (this.dir)
        {
            case UP:
            {
                newPos = this.arenaModel.getHero().moveUp();
                break;
            }
            case DOWN:
            {
                newPos = this.arenaModel.getHero().moveDown();
                break;
            }
            case LEFT:
            {
                newPos = this.arenaModel.getHero().moveLeft();
                break;
            }
            case RIGHT:
            {
                newPos = this.arenaModel.getHero().moveRight();
                break;
            }
        }
        Hero hero = arenaModel.getHero();

        if (this.arenaModel.isPositionAvailable(newPos,hero))
        {
            List<Element> elemsInNewPos = arenaModel.getElemFromPos(newPos);
            if (elemsInNewPos != null)
            {
                for (Element elemInNewPos : elemsInNewPos)
                {
                    if (elemInNewPos instanceof Collectable) arenaModel.removeElement(elemInNewPos);

                    if (elemInNewPos instanceof Coin) hero.incCoins();

                    if (elemInNewPos instanceof HealthPotion) hero.updateHealth(((HealthPotion)elemInNewPos).getHealth());

                    if (elemInNewPos instanceof Bullet) hero.incAmmo();

                    if (elemInNewPos instanceof Weapon) hero.setWeapon((Weapon) elemInNewPos);

                }
            }




            Position currentHeroPos = hero.getPos();
            this.arenaModel.updateMapKey(currentHeroPos,newPos,hero);
            this.arenaModel.getHero().updatePos(newPos);
        }

    }
}
