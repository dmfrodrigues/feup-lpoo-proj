/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Controller;

import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;

public class MoveHeroUpCommand extends MoveHeroCommand {

    public MoveHeroUpCommand(ArenaModel arenaModel) {
        super(arenaModel);
    }

    @Override
    public void execute() {
        Position newPos = this.arenaModel.getHero().moveUp();

        processMovementConsequences(newPos);
    }
}
