import javax.swing.*;
import java.util.Observable;
/**
 * this class is creates a  using 3X3 array of Player to store the data from TicTacToeTest bored
 *
 */
public class BoardData extends Observable {
    private Player[][] cellValue = new Player[3][3];  // making a 2D array TicTaeToe Game Board of size 3 by 3
    private int pxp, pyp;
    /**
     * Contractor to initialize all the array with null value
     */
    public BoardData() {
        for (int n = 0; n < 3; n++) {
            for (int i = 0; i < 3; i++) {
                cellValue[n][i] = null;   // initially, all the values of cells in the board are empty i.e. null
            }
        }
    }
    /**
     * method to check the winer. for every move it checks the array for different wining condition
     * @return true if their is a winer or false if no winer
     */
    public boolean CheckWinner() {
        boolean temp1 = false;  // initially, there is no winner so temp1 is false
        String temp = "";

        for (int i = 0; i < 3; i++) {
            // checking if the values in rows are null or not
            if (cellValue[i][0] != null && cellValue[i][1] != null && cellValue[i][2] != null) {
                // checking if the ids of the rows are equal to each other or not
                if (cellValue[i][0].id == cellValue[i][1].id && cellValue[i][1].id == cellValue[i][2].id) {
                    temp = cellValue[i][0].name;  // assign the name of that each cell in a row to a variable called temp
                    temp1 = true;  // if ids found equal, change temp1 to true

                    // display the message if the player has won the game
                    JOptionPane.showMessageDialog(new JFrame("message"), temp + " win!",
                            "Backup problem", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            // checking if the values in columns are null or not
            if (cellValue[0][i] != null && cellValue[1][i] != null && cellValue[2][i] != null) {
                // checking if the ids of the columns are equal to each other or not
                if (cellValue[0][i].id == cellValue[1][i].id && cellValue[1][i].id == cellValue[2][i].id) {
                    temp = cellValue[0][i].name;  // assign the name of that each cell in a column to a variable called temp
                    temp1 = true;   // if ids found equal, change temp1 to true

                    // display the message if the player has won the game
                    JOptionPane.showMessageDialog(new JFrame("message"), temp + " win!",
                            "Backup problem", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }

        // checking if the values in the diagonal of the same board are null or not
        if (cellValue[0][0] != null && cellValue[1][1] != null && cellValue[2][2] != null) {
            // checking if the ids in this diagonal are equal to each other or not
            if (cellValue[0][0].id == cellValue[1][1].id && cellValue[1][1].id == cellValue[2][2].id) {
                temp = cellValue[0][0].name;  // assign the name of that each cell in a diagonal to a variable called temp
                temp1 = true;   // if ids found equal, change temp1 to true

                // display the message if the player has won the game
                JOptionPane.showMessageDialog(new JFrame("message"), temp + " win!", "Backup problem",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

        // checking if the values in another diagonal of the same board are null or not
        if (cellValue[0][2] != null && cellValue[1][1] != null && cellValue[2][0] != null) {
            // checking if the ids in this diagonal are equal to each other or not
            if (cellValue[0][2].id == cellValue[1][1].id && cellValue[1][1].id == cellValue[2][0].id) {
                temp = cellValue[0][2].name;  // assign the name of that each cell in a diagonal to a variable called temp
                temp1 = true;  // if ids found equal, change temp1 to true

                // display the message if the player has won the game
                JOptionPane.showMessageDialog(new JFrame("message"), temp + " win!", "Backup problem",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

        return temp1;   // if there is a winner, temp1 returns true, otherwise false
    }
    /**
     * checks if their is a tie between players 
     * @return true if tie or false if no tie
     */
    public boolean CheckGameOver() {
        boolean temp1 = true; 
        for(int i = 0; i < cellValue.length; i++)
        {
            for(int j = 0; j < cellValue[0].length; j++)
            {
                if(cellValue[i][j] == null)
                    temp1 = false;
            }
        }
        return temp1;

    }
    /**
     * an accessors method 
     * @return cellValue returns the entire board
     */
    public Player[][] getData() {
        return cellValue;   // returns the entire board
    }
    /**
     * 
     * @return position ,array of button positions
     */
    public int[] setPrevious() {
        int[] position = new int[2];
        cellValue[pxp][pyp] = null;
        position[0] = pxp;
        position[1] = pyp;
        setChanged();
        notifyObservers();
        return position;
    }
    /**
     * Updates the array content with new player
     * @param xp the row value in the array
     * @param yp  column value in the array
     * @param event the value of array needs to updated
     */
    public void changeData(int xp, int yp, Player event) {
        cellValue[xp][yp] = event;
        pxp = xp;
        pyp = yp;
        setChanged();
        notifyObservers();
    }
}
