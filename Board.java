import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Board {
    private final int SQUARE_SIZE = 3;
    private final String EMPTY = "_";
    Random random = new Random();
    private final String[][] board = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };

    public Board() {}

    public void nextMove(String player) {
        getNextEmptyCell().ifPresent(nextCell -> {
            board[nextCell[0]][nextCell[1]] = player;
        });
    }

    public Optional<int[]> getNextEmptyCell() {
        List<int[]> emptyCells = new ArrayList<>();
        for (int i = 0; i < SQUARE_SIZE; i++) {
            for (int j = 0; j < SQUARE_SIZE; j++) {
                if (board[i][j].equals(EMPTY)) {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }

        if (emptyCells.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(emptyCells.get(random.nextInt(emptyCells.size())));
    }

    public GameStatus checkStatus() {
        // Row
        for (int i = 0; i < 3; i++) {
            String a = board[i][0];
            String b = board[i][1];
            String c = board[i][2];

            if (!a.equals(EMPTY) && a.equals(b) && b.equals(c)) {
                return GameStatus.GAME_OVER;
            }
        }

        // Col
        for (int i = 0; i < 3; i++) {
            String a = board[0][i];
            String b = board[1][i];
            String c = board[2][i];

            if (!a.equals(EMPTY) && a.equals(b) && b.equals(c)) {
                return GameStatus.GAME_OVER;
            }
        }

        // LT to BR diagonal
        String a = board[0][0];
        String b = board[1][1];
        String c = board[2][2];

        if (!a.equals(EMPTY) && a.equals(b) && b.equals(c)) {
            return GameStatus.GAME_OVER;
        }

        // RT to LB diagonal
        String d = board[0][2];
        String e = board[1][1];
        String f = board[2][0];

        if (!d.equals(EMPTY) && d.equals(e) && e.equals(f)) {
            return GameStatus.GAME_OVER;
        }

        for (int i = 0; i < SQUARE_SIZE; i++) {
            for (int j = 0; j < SQUARE_SIZE; j++) {
                if (board[i][j].equals(EMPTY)) {
                    return GameStatus.RUNNING;
                }
            }
        }

        return GameStatus.DRAW;
    }

    public void printMatrix() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
