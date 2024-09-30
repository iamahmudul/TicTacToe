import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Game {
    private final Board board;
    private boolean isGameOver;
    private final ReentrantLock lock;
    private final Condition playerX;
    private final Condition playerO;
    private boolean xInPlay;

    public Game() {
        this.board = new Board();
        this.isGameOver = false;
        this.lock = new ReentrantLock();
        playerX = lock.newCondition();
        playerO = lock.newCondition();
        xInPlay = true;
    }

    public void play(String player) {
        lock.lock();
        try {
            // Guard if game is over
            if (isGameOver) {
                return;
            }
            // wait if other is playing
            if (player.equals("X")) {
                while (!xInPlay && !isGameOver) {
                    playerX.await();
                }
            } else {
                while (xInPlay && !isGameOver) {
                    playerO.await();
                }
            }
            // safety check if game is over after waiting
            if (isGameOver) {
                return;
            }
            System.out.println("Player: " + player);
            board.nextMove(player);
            board.printMatrix();
            GameStatus boardStatus = board.checkStatus();
            if (boardStatus == GameStatus.GAME_OVER) {
                isGameOver = true;
                System.out.println("Player " + player + " wins");
                playerX.signalAll();
                playerO.signalAll();
                return;
            } else if (boardStatus == GameStatus.DRAW) {
                isGameOver = true;
                System.out.println("Game draw");
                playerX.signalAll();
                playerO.signalAll();
                return;
            }
            xInPlay = !xInPlay;
            // wakeup call
            if (xInPlay) {
                playerX.signal();
            } else {
                playerO.signal();
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}
