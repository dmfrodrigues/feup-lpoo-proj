package com.pacman.g60.Controller;

import com.pacman.g60.Application;
import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.Elements.Coin;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Position;


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


            Element elemInNewPos = arenaModel.getElemFromPos(newPos);
            if (elemInNewPos instanceof Coin)
            {
                arenaModel.removeElement(elemInNewPos);
                hero.incCoins();
            }

            Position currentHeroPos = hero.getPos();
            this.arenaModel.updateMapKey(currentHeroPos,newPos,hero);
            this.arenaModel.getHero().updatePos(newPos);
        }

    }
}
