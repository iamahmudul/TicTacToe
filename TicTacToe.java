public class TicTacToe {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        Thread playerX = new Thread(new Player("X", game));
        Thread playerO = new Thread(new Player("O", game));
        playerX.start();
        playerO.start();
        playerX.join();
        playerO.join();
    }
}
