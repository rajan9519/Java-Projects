package battleship;
import java.util.Scanner;
public class Player {
    private final Scanner scanner = new Scanner(System.in);
    private static int playerId = 0;
    private BattleShip battle;
    private char curRow;
    private int curCol;


    public Player() {
        playerId++;
        battle = new BattleShip();
    }

    public void start() {
        battle.printGrid();
        setShips();
    }
    public void firstShot() {

        String input = scanner.nextLine();
        while (!validCord(input)) {
            input = scanner.nextLine();
        }

        battle.makeMove(curRow-'A', curCol-1);
    }
    private void setShips() {
        for (Ship ship : Ship.values()) {
            System.out.println("Enter the coordinates of the " + ship.getType() + " (" + ship.getSize() + " cells):");
            while (!getCoords(ship.getSize(), ship.getType()));
            battle.printGrid();
        }
    }
    private boolean getCoords(int length, String name) {
        String[] input = scanner.nextLine().split("\\s+");

        boolean success = true;
        int len=0;
        int row1 = input[0].charAt(0) - 'A';
        int row2 = input[1].charAt(0) - 'A';
        int col1 = Integer.parseInt(input[0].substring(1));
        int col2 = Integer.parseInt(input[1].substring(1));

        if (input.length < 2) {
            System.out.println("Error: Invalid Coordinates");
        } else if(row1 == row2) {
            len = col1 - col2;
        } else if (col1 == col2) {
            len = row1 - row2;
        } else {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }

        len = Math.abs(len)+1;
//        System.out.println(len);
        if(len == length) {
            int rowStart = Math.min(row1,row2);
            int rowEnd = Math.max(row1,row2);
            int colStart = Math.min(col1,col2)-1;
            int colEnd = Math.max(col1,col2)-1;
            if(!battle.setShip(rowStart, rowEnd, colStart, colEnd)) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                success = false;
            }
        } else {
            System.out.println("Error! Wrong length of the "+name+"! Try again:");
            success = false;
        }
        return success;
    }
    private boolean validCord(String cord) {
        boolean valid = true;

        if(cord.length() == 0 || cord.length() < 2 || cord.length() > 3) {
            valid = false;
        } else {
            if(cord.charAt(0) < 'A' || cord.charAt(0) > 'J') {
                valid = false;
            } else {
                curRow = cord.charAt(0);
                curCol = Integer.parseInt(cord.substring(1));
                if (curCol < 1 || curCol > 10) {
                    valid = false;
                }
            }
        }
        if(!valid) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
        }
        return valid;
    }
    public void printGrid() {
        battle.printGrid();
    }
    public void printFogOfWar() {
        battle.printFogOfWar();
    }
    public boolean gameOver() {
        return battle.getTotalShips() == 0;
    }
}
