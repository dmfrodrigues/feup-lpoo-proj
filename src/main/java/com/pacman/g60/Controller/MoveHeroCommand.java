package com.pacman.g60.Controller;

import com.pacman.g60.Application;
import com.pacman.g60.Model.ArenaModel;

public class MoveHeroCommand implements Command {
    private ArenaModel arenaModel;
    private Application.Direction dir;

    public MoveHeroCommand(ArenaModel arenaModel, Application.Direction dir){this.arenaModel = arenaModel; this.dir = dir;}

    @Override
    public void execute() {
        switch (this.dir)
        {
            case UP:
            {
                this.arenaModel.getHero().moveUp();
                break;
            }
            case DOWN:
            {
                this.arenaModel.getHero().moveDown();
                break;
            }
            case LEFT:
            {
                this.arenaModel.getHero().moveLeft();
                break;
            }
            case RIGHT:
            {
                this.arenaModel.getHero().moveRight();
                break;
            }
        }
    }
}
