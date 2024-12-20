import java.util.*;

    public class SnakeGame {
        private static final int WIDTH = 20;
        private static final int HEIGHT = 20;
        private static final char SNAKE_HEAD = 'O';
        private static final char SNAKE_BODY = 'o';
        private static final char FOOD = '*';
        private static final char EMPTY = ' ';
        private static final char BORDER = '#';

        private Deque<int[]> snake = new LinkedList<>();
        private int[] food = new int[2];
        private char direction = 'R'; // U (Up), D (Down), L (Left), R (Right)
        private boolean gameOver = false;

        public static void main(String[] args) {
            SnakeGame game = new SnakeGame();
            game.start();
        }

        public void start() {
            initializeGame();
            Scanner scanner = new Scanner(System.in);

            while (!gameOver) {
                printGrid();
                System.out.println("Move (WASD): ");
                char input = scanner.next().toUpperCase().charAt(0);
                if (input == 'W' || input == 'A' || input == 'S' || input == 'D') {
                    changeDirection(input);
                }
                moveSnake();
                checkGameOver();
            }
            System.out.println("Game Over!");
        }

        private void initializeGame() {
            int[] initPos = {HEIGHT / 2, WIDTH / 2};
            snake.add(initPos);
            generateFood();
        }

        private void generateFood() {
            Random rand = new Random();
            int foodX, foodY;
            do {
                foodX = rand.nextInt(HEIGHT - 2) + 1; // Avoid placing food on borders
                foodY = rand.nextInt(WIDTH - 2) + 1;
            } while (isSnake(foodX, foodY));
            food[0] = foodX;
            food[1] = foodY;
        }

        private boolean isSnake(int x, int y) {
            for (int[] s : snake) {
                if (s[0] == x && s[1] == y) {
                    return true;
                }
            }
            return false;
        }

        private void printGrid() {
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    if (i == 0 || i == HEIGHT - 1 || j == 0 || j == WIDTH - 1) {
                        System.out.print(BORDER);
                    } else if (i == snake.peekFirst()[0] && j == snake.peekFirst()[1]) {
                        System.out.print(SNAKE_HEAD);
                    } else if (isSnake(i, j)) {
                        System.out.print(SNAKE_BODY);
                    } else if (i == food[0] && j == food[1]) {
                        System.out.print(FOOD);
                    } else {
                        System.out.print(EMPTY);
                    }
                }
                System.out.println();
            }
        }

        private void changeDirection(char newDirection) {
            if (newDirection == 'W' && direction != 'D') {
                direction = 'U';
            } else if (newDirection == 'S' && direction != 'U') {
                direction = 'D';
            } else if (newDirection == 'A' && direction != 'R') {
                direction = 'L';
            } else if (newDirection == 'D' && direction != 'L') {
                direction = 'R';
            }
        }

        private void moveSnake() {
            int[] head = snake.peekFirst();
            int newHeadX = head[0];
            int newHeadY = head[1];

            switch (direction) {
                case 'U': newHeadX--; break;
                case 'D': newHeadX++; break;
                case 'L': newHeadY--; break;
                case 'R': newHeadY++; break;
            }

            if (newHeadX == food[0] && newHeadY == food[1]) {
                snake.addFirst(new int[]{newHeadX, newHeadY});
                generateFood();
            } else {
                snake.addFirst(new int[]{newHeadX, newHeadY});
                snake.removeLast();
            }
        }

        private void checkGameOver() {
            int[] head = snake.peekFirst();
            if (head[0] == 0 || head[0] == HEIGHT - 1 || head[1] == 0 || head[1] == WIDTH - 1) {
                gameOver = true;
            }

            for (int i = 1; i < snake.size(); i++) {
                if (Arrays.equals(snake.toArray(new int[0][])[i], head)) {
                    gameOver = true;
                    break;
                }
            }
        }
    }