package com.pacman.g60.Model.Path_Calculation;

import com.pacman.g60.Model.Position;

public class BFSTieBreakerDiag implements BFSShortestPathStrategy.TieBreaker<Position> {
    private Position pos;
    public BFSTieBreakerDiag(Position pos){ this.pos = new Position(pos); }
    
    private Integer euclideanDistSqr(Position p, Position q){
        Integer Dx = p.getX() - q.getX();
        Integer Dy = p.getY() - q.getY();
        return Dx*Dx + Dy*Dy;
    }
    
    @Override
    public boolean untie(Position u, Position v) {
        return (euclideanDistSqr(u, pos) < euclideanDistSqr(v, pos));
    }
}
