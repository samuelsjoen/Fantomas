package no.uib.inf101.sem2.fantomas.controller;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import no.uib.inf101.sem2.fantomas.model.GameState;
import no.uib.inf101.sem2.fantomas.midi.FantomasSong;
import no.uib.inf101.sem2.fantomas.view.FantomasView;

public class FantomasController implements KeyListener {

    ControllableFantomasModel model;
    FantomasView fantomasView;
    FantomasSong song;

    public FantomasController(ControllableFantomasModel model, FantomasView fantomasView) {
        this.model = model;
        this.fantomasView = fantomasView;
        this.song = new FantomasSong();
        fantomasView.addKeyListener(this);
        fantomasView.setFocusable(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Pauses or unpauses the music
        if (e.getKeyCode() == KeyEvent.VK_M) {
            song.doPauseMidiSounds();
        } else if (e.getKeyCode() == KeyEvent.VK_U) {
            song.doUnpauseMidiSounds();
        }

        // Actions for keys pressed when the game state is active
        else if (model.getGameState() == GameState.ACTIVE_GAME) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                model.movePlayer(0, -1);
                model.rotatePlayer('L');
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                model.movePlayer(0, 1);
                model.rotatePlayer('R');
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                model.movePlayer(1, 0);
                model.rotatePlayer('D');
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                model.movePlayer(-1, 0);
                model.rotatePlayer('U');
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                model.setGameState(GameState.PAUSE);
                song.doPauseMidiSounds();
            } else if (e.getKeyCode() == KeyEvent.VK_E) {
                model.viewPainting();
            }
            fantomasView.repaint();
        }

        // Actions for keys pressed when the game state is at the start screen
        else if (model.getGameState() == GameState.START_SCREEN) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                model.clearBoard();
                model.setGameState(GameState.ACTIVE_GAME);
            }
            fantomasView.repaint();
        }

        // Actions for keys pressed when the game is over
        else if (model.getGameState() == GameState.GAME_OVER) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                model.setGameState(GameState.START_SCREEN);
            }
            fantomasView.repaint();
        }

        // Actions for keys pressed when the game state is in pause
        else if (model.getGameState() == GameState.PAUSE) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                model.setGameState(GameState.ACTIVE_GAME);
                song.doUnpauseMidiSounds();
            }
            fantomasView.repaint();
        }

        else if (model.getGameState() == GameState.PAINTINGVIEW) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                model.setGameState(GameState.ACTIVE_GAME);
            }
            fantomasView.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

}