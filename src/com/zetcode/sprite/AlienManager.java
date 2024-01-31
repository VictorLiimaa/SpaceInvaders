package com.zetcode.sprite;
import java.util.List;
import com.zetcode.Commons;


public class AlienManager implements Runnable {

    private List<Alien> aliens;
    private int direction;
    private boolean inGame;

    public AlienManager(List<Alien> aliens, int direction, boolean inGame) {
        this.aliens = aliens;
        this.direction = direction;
        this.inGame = inGame;
    }

    @Override
    public void run() {
        while (inGame) {
            updateAliens();
            try {
                Thread.sleep(50); // Ajuste conforme necessário
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateAliens() {
        for (Alien alien : aliens) {
            int x = alien.getX();
            if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction != -1) {
                direction = -1;
                moveAliensDown();
            }
            if (x <= Commons.BORDER_LEFT && direction != 1) {
                direction = 1;
                moveAliensDown();
            }
            alien.act(direction);
        }
    }

    private void moveAliensDown() {
        for (Alien a : aliens) {
            a.setY(a.getY() + Commons.GO_DOWN);
        }
    }
}

