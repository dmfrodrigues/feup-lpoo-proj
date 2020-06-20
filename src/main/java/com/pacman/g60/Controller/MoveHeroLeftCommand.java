/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Controller;

import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;

public class MoveHeroLeftCommand extends MoveHeroCommand {

    public MoveHeroLeftCommand(ArenaModel arenaModel) {
        super(arenaModel);
    }

    @Override
    public void execute() {
        Position newPos = this.arenaModel.getHero().moveLeft();

        processMovementConsequences(newPos);
    }
}
