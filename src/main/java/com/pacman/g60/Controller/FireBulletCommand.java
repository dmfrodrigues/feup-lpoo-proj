package com.pacman.g60.Controller;

import com.pacman.g60.Application;

import com.pacman.g60.Model.Elements.Bullet;
import com.pacman.g60.Model.Elements.DirectionalCalculator;
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
            Position desiredPos = new DirectionalCalculator(bulletDir,currentPos).calcNextPos();

            Bullet bullet = new Bullet(desiredPos);


            if (arenaModel.isPositionAvailable(desiredPos,bullet))
            {
                bullet.setDir(heroDir);
                bullet.setMoving(true);
                arenaModel.addElement(bullet);
                hero.decAmmo();
            }
        }
    }
}
