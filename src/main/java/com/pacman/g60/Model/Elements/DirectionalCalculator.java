/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Model.Elements;

import com.pacman.g60.Application;
import com.pacman.g60.Model.Position;

public class DirectionalCalculator {
    private Application.Direction direction;
    private Position position;

    public DirectionalCalculator(Application.Direction direction, Position position) {
        this.direction = direction;
        this.position = position;
    }

    public Position calcNextPos()
    {
        Position result = null;
        Position currentPos = position;

        switch(direction)
        {
            case UP:
            {
                result = new Position(currentPos.getX(),currentPos.getY() - 1);
                break;
            }
            case DOWN:
            {
                result = new Position(currentPos.getX(),currentPos.getY() + 1);
                break;
            }
            case LEFT:
            {
                result = new Position(currentPos.getX() - 1,currentPos.getY());
                break;
            }
            case RIGHT:
            {
                result = new Position(currentPos.getX() + 1,currentPos.getY());
                break;
            }
        }
        return result;
    }
}
