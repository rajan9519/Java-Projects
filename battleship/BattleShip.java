package battleship;

public class BattleShip extends Grid{
    private int totalShips;
    BattleShip() {
        super();
        totalShips = 0;
    }

    private boolean canPlaced(int rowStart, int rowEnd, int colStart, int colEnd) {
        for (int row = rowStart; row <= rowEnd; row++) {
            for (int col = colStart; col <= colEnd; col++) {
                if (isOccupied(row, col) || isOccupied(row+1,col) || isOccupied(row-1,col) || isOccupied(row,col+1) || isOccupied(row,col-1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSank(int row, int col) {
        return !isOccupied(row, col) && !isOccupied(row+1,col) && !isOccupied(row-1,col) && !isOccupied(row,col+1) && !isOccupied(row,col-1);
    }

    private boolean isOccupied(int row, int col) {
        if(row < 0 || row > 9 || col < 0 || col > 9) {
            return false;
        }
        return getEle(row, col) == 'O';
    }

    public boolean setShip(int rowStart, int rowEnd, int colStart, int colEnd) {

        if (canPlaced(rowStart, rowEnd, colStart, colEnd)) {
            for (int row = rowStart; row <= rowEnd; row++) {
                for (int col = colStart; col <= colEnd; col++) {
                    setEle(row, col, 'O');
                    totalShips++;
                }
            }
            return true;
        }
        return false;
    }

    public void makeMove(int row, int col) {
        if (getEle(row, col) == 'O') {

            setEle(row, col, 'X');
            printFogOfWar();

            if (isSank(row, col)) {
                System.out.println("You sank a ship! Specify a new target:");
            } else {
                System.out.println("You hit a ship! Try again:");
            }
            totalShips--;
        } else if (getEle(row, col) == 'X') {
            System.out.println("You Already hit that Target! Try again:");
            printFogOfWar();
        } else {
            setEle(row, col, 'M');
            printFogOfWar();
            System.out.println("You missed. Try again:");
        }
    }
    public int getTotalShips() {
        return totalShips;
    }
}