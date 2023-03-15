public class Main {

    public static final int SIZE = 10;
    public static final char puppy = 'Щ';
    public static final char empty = '-';
    public static final char cactus = '*';
    public static final char result = 'x';

    public static void main(String[] args) {

        char[][] field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = empty;
            }
        }
        field[0][0] = puppy;
        field[3][0] = cactus;
        field[4][0] = cactus;
        field[4][1] = cactus;
        field[6][1] = cactus;
        field[7][1] = cactus;
        field[3][2] = cactus;
        field[5][2] = cactus;
        field[9][2] = cactus;
        field[1][3] = cactus;
        field[6][4] = cactus;
        field[2][5] = cactus;
        field[5][5] = cactus;
        field[3][6] = cactus;
        field[6][6] = cactus;
        field[7][6] = cactus;
        field[8][6] = cactus;
        field[7][7] = cactus;
        field[7][8] = cactus;
        field[5][9] = cactus;
        field[6][9] = cactus;

        findPath(field, 8, 3);
    }

    public static char whereFrom(char[][] field, int x, int y, char[][] memory) {
        if (memory[x][y] != '?') {
            return memory[x][y];
        }
        if (x > 0) {
            int leftX = x - 1;
            int leftY = y;
            if (leftX == 0 && leftY == 0) {
                memory[x][y] = 'L';
                return 'L';
            }
            if (field[leftX][leftY] != cactus) {
                if (whereFrom(field, leftX, leftY, memory) != 'N') {
                    memory[x][y] = 'L';
                    return 'L';
                }
            }
        }
        if (y > 0) {
            int upX = x;
            int upY = y - 1;
            if (upX == 0 && upY == 0) {
                memory[x][y] = 'U';
                return 'U';
            }
            if (field[upX][upY] != cactus) {
                if (whereFrom(field, upX, upY, memory) != 'N') {
                    memory[x][y] = 'U';
                    return 'U';

                }
            }
        }
        memory[x][y] = 'N';
        return 'N';
    }

    public static void findPath(char[][] field, int x0, int y0) {
        boolean[][] path = new boolean[SIZE][SIZE];
        char[][] memory = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                memory[i][j] = '?';
            }
        }
        int x = x0;
        int y = y0;
        while (x != 0 || y != 0) {
            char direction = whereFrom(field, x, y, memory);
            if (direction == 'N') {
                System.out.println("Нет такого пути");
            } else if (direction == 'U') {
                path[x][y] = true;
                y -= 1;
            } else if (direction == 'L') {  //
                path[x][y] = true;  //
                x -= 1;
            }
        }
        for (y = 0; y < SIZE; y++) {
            for (x = 0; x < SIZE; x++) {
                if (x == x0 && y == y0) {
                    System.out.print('Ч' + " ");
                } else if (path[x][y]) {
                    System.out.print(result + " ");
                } else {
                    System.out.print(field[x][y] + " ");
                }
            }
            System.out.println();
        }
    }
}