import java.util.Random;

public class Player implements Runnable{
    private final String player;
    private final Game game;
    private Random rand;

    public Player(String player, Game game) {
        this.player = player;
        this.game = game;
        this.rand = new Random();
    }

    @Override
    public void run() {
        while (!game.isGameOver()) {
            game.play(player);
            try {
                Thread.sleep(rand.nextInt(800, 1200));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
