package com.pacman.g60.Controller;

import com.pacman.g60.Application;
import com.pacman.g60.Model.ArenaModel;
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


        if (this.arenaModel.isPositionAvailable(newPos))
        {
            Position currentHeroPos = this.arenaModel.getHero().getPos();
            this.arenaModel.updateMapKey(currentHeroPos,newPos);
            this.arenaModel.getHero().updatePos(newPos);
        }

    }
}
