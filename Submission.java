import java.util.Scanner; //Imports the scanner class

public class Submission {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int battery = sc.nextInt(); //This allows the user to enter an input a value for Battery, Depth and Width
        int depth = sc.nextInt();
        int width = sc.nextInt();

        int[][] board = new int[depth][width]; //Creates a 2D array of size Depth x Width for the board
        for (int i = 0; i < depth; i++) { //This loop fills the array with the input values
            for (int j = 0; j < width; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        int answer = bestGold(board, battery); //Calls the bestGold method and stores the result in the answer
        System.out.println(answer); //Prints the answer
    }

    private static int maxGold(int[][] board, int[][][] memo, int y, int x, int b) { //This is the recursion with memoized to find the max gold variables
        if (b == 0) return 0; //Base case and if the battery is empty, return 0
        if (memo[y][x][b] != 0) return memo[y][x][b]; //you return the value of the saved value
        int best = 0; //best result
        int mineHere = board[y][x] + maxGold(board, memo, y, x, b - 1); //option 1 mine in the same cell
        if (mineHere > best) best = mineHere;
        if (x > 0) {
            int left = board[y][x - 1] + maxGold(board, memo, y, x - 1, b - 1); //option 2 mine in the left cell
            if (left > best) best = left;
        }
        if (x < board[0].length - 1) {
            int right = board[y][x + 1] + maxGold(board, memo, y, x + 1, b - 1); //option 3 mine in the right cell
            if (right > best) best = right;
        }
        if (y < board.length - 1) {
            int down = board[y + 1][x] + maxGold(board, memo, y + 1, x, b - 1); //option 4 mine in the down cell
            if (down > best) best = down;
        }
        return memo[y][x][b] = best; //return the best result
    }

    private static int bestGold(int[][] board, int battery, int[][][] memo, int width, int depth) { //tries every possible position
        int best = 0;
        for (int x = 0; x < width; x++) { //tries every column in the top row, so all valid starting positions
            int startingGold = board[0][x];
            int remainingBattery = battery - 1; //subtract 1 battery as we start on a square it counts as the first move

            int extraGold = maxGold(board, memo, 0, x, remainingBattery); //maximum gold from the starting point using remaining battery
            best = Math.max(best, startingGold + extraGold); //adds starting gold to the future gold and compares it to the current best
        }
        return best;
    }

    private static int bestGold(int[][] board, int battery) { //Don't have to pass the memo array manually
        int answer = bestGold(board, battery, new int[board.length][board[0].length][battery + 1], board[0].length, board.length); //creates a new array for the memo
        return answer; //returns the answer from memo to main
    }
}