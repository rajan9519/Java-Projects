package battleship;

public class Grid {
    private char[][] grid;

    protected Grid() {
        grid = new char[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = '~';
            }
        }
    }

    protected void printGrid() {
        System.out.print("  ");

        for (int i = 1; i < 11; i++) {
            System.out.printf("%d ",i);
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%c ",'A'+i);
            for (int j = 0; j < 10; j++) {
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }
    protected void printFogOfWar() {
        System.out.print("  ");

        for (int i = 1; i < 11; i++) {
            System.out.printf("%d ",i);
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%c ",'A'+i);
            for (int j = 0; j < 10; j++) {
                if(grid[i][j] == 'O') {
                    System.out.print("~ ");
                } else {
                    System.out.print(grid[i][j]+" ");
                }
            }
            System.out.println();
        }
    }
    protected char getEle(int row, int col) {
        return grid[row][col];
    }
    protected void setEle(int row, int col, char c) {
        grid[row][col] = c;
    }
}
