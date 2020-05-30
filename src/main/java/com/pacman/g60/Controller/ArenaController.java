package com.pacman.g60.Controller;


import com.pacman.g60.Application;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.View.Views.ArenaView;

import java.io.IOException;

public class ArenaController extends Controller {
    private ArenaView arenaView;
    private ArenaModel arenaModel;
    private boolean win;
    private boolean over;
    private boolean mustContinueRunning = false;
    private UpdateRateController rateController = new UpdateRateController(4);

    public ArenaController(ArenaModel arenaModel, ArenaView arenaView){
        this.arenaModel = arenaModel;
        this.arenaView = arenaView;
        start();
    }
    
    private void start(){
        win = false;
        over = false;
        arenaView.start();
    }
    
    public void run() throws IOException {
        arenaView.setArenaModel(arenaModel);
        
        rateController.start();
        
        while(true){
            rateController.startFrame();

            long numberUpdates = rateController.numberUpdatesInThisFrame();
            for(long i = 0; i < numberUpdates; ++i) {
                executeCommand(new UpdateAllEnemyPosCommand(this.arenaModel));
                executeCommand(new CheckHeroAdjacencyCommand(this.arenaModel));
                executeCommand(new CheckForDeathCommand(this.arenaModel));
            }
            while(true){
                ArenaView.COMMAND cmd = arenaView.pollCommand();
                if(cmd == null) break;
                switch (cmd) {
                    case ESC:
                    case P:
                        return;
                    case UP:
                        executeCommand(new MoveHeroCommand(this.arenaModel, Application.Direction.UP));
                        break;
                    case DOWN:
                        executeCommand(new MoveHeroCommand(this.arenaModel, Application.Direction.DOWN));
                        break;
                    case LEFT:
                        executeCommand(new MoveHeroCommand(this.arenaModel, Application.Direction.LEFT));
                        break;
                    case RIGHT:
                        executeCommand(new MoveHeroCommand(this.arenaModel, Application.Direction.RIGHT));
                        break;
                    case SPACEBAR:
                        executeCommand(new AttackCommand(this.arenaModel)); break;
                    case FIRE:
                        executeCommand(new FireBulletCommand(this.arenaModel)); break;

                }
            }
            if (arenaModel.getHero().getHealth() <= 0) { lose(); return; }
            if (!mustContinueRunning && !arenaModel.getShouldGameContinue()) { win();return; }
            
            arenaView.clear();
            arenaView.draw();
            arenaView.refresh();
        }
    }
    
    private void lose(){
        win = false;
        over = true;
    }
    
    private void win(){
        win = true;
        over = true;
    }

    public boolean isOver() { return over; }

    public boolean isWin() { return win; }
    
    public void executeCommand(Command command)
    {
        command.execute();
    }


    public void continueRunning() {
        mustContinueRunning = true;
    }
}
