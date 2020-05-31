package com.pacman.g60.Controller;


import com.pacman.g60.Application;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.View.FrameRateController;
import com.pacman.g60.View.Views.ArenaView;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class ArenaController extends Controller {
    private final ArenaView arenaView;
    private final ArenaModel arenaModel;
    private boolean win;
    private boolean over;
    private boolean mustContinueRunning = false;
    private final UpdateRateController rateController = new UpdateRateController(4);
    private final FrameRateController frameRateController  = new FrameRateController (60);
    private Stopwatch watch = new Stopwatch();

    public ArenaController(ArenaModel arenaModel, ArenaView arenaView){
        this.arenaModel = arenaModel;
        this.arenaView = arenaView;
        start();
    }
    
    private void start(){
        win = false;
        over = false;
        watch.reset();
    }
    
    public void run() throws IOException {
        arenaView.setArenaModel(arenaModel);
        
        rateController.start();
        frameRateController.start();
        watch.resume();
        
        boolean good = true;
        while(good){
            rateController.startFrame();
            frameRateController.startFrame();

            long numberUpdates = rateController.numberUpdatesInThisFrame();
            for(long i = 0; i < numberUpdates; ++i) {
                executeCommand(new UpdateAllEnemyPosCommand(this.arenaModel));
                executeCommand(new ApplyAllEffectCommand(this.arenaModel,false));
                executeCommand(new CheckForDeathCommand(this.arenaModel));
                executeCommand(new UpdateAllProjectilePosCommand(this.arenaModel));
            }
            while(true){
                ArenaView.COMMAND cmd = arenaView.pollCommand();
                if(cmd == null) break;
                switch (cmd) {
                    case ESC:
                    case P:
                        good = false; break;
                    case UP:
                        executeCommand(new MoveHeroUpCommand(this.arenaModel));
                        break;
                    case DOWN:
                        executeCommand(new MoveHeroDownCommand(this.arenaModel));
                        break;
                    case LEFT:
                        executeCommand(new MoveHeroLeftCommand(this.arenaModel));
                        break;
                    case RIGHT:
                        executeCommand(new MoveHeroRightCommand(this.arenaModel));
                        break;
                    case SPACEBAR:
                        executeCommand(new ApplyAllEffectCommand(this.arenaModel,true)); break;
                    case FIRE:
                        executeCommand(new FireBulletCommand(this.arenaModel)); break;

                }
            }
            if (arenaModel.getHero().getHealth() <= 0) { lose(); good = false; }
            if (!mustContinueRunning && !arenaModel.getShouldGameContinue()) { win(); good = false; }
            
            arenaView.setTime(watch.poll());
            arenaView.clear();
            arenaView.draw();
            arenaView.refresh();
        }
        
        watch.stop();
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

    public Duration getTime() { return watch.poll(); }
}
