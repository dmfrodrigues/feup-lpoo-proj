package com.pacman.g60.Controller;

import com.pacman.g60.Application;

import com.pacman.g60.Model.Elements.Bullet;
import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;

public class FireBulletCommand implements Command {
    private ArenaModel arenaModel;

    public FireBulletCommand(ArenaModel arenaModel) {
        this.arenaModel = arenaModel;
    }

    @Override
    public void execute() {
        Hero hero = arenaModel.getHero();
        int heroAmmo = hero.getAmmo();

        if (heroAmmo > 0)
        {
            Application.Direction heroDir = hero.getDirection();

            Application.Direction bulletDir = heroDir;
            Position currentPos = hero.getPos();
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

            Bullet bullet = new Bullet(desiredPos);
            bullet.setDir(heroDir);
            bullet.setMoving(true);

            if (arenaModel.isPositionAvailable(desiredPos,bullet))
            {
                   arenaModel.addElement(bullet);
            }
        }
    }
}
