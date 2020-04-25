import javax.swing.*;
import java.util.LinkedList;

public class GameView {
    private Player p0 = new Player(1);
    private Player p1 = new Player(2);
    private BoardData data;
    private LinkedList<Player> L = new LinkedList<Player>();
    private int xp = 15;
    private int yp = 15;
    private String value;
    private JButton undoButton = new JButton("Undo");
    // private Player currentPlayer;
    BoardType board;

    public GameView(BoardData aData) {
//        currentPlayer = p0;
//        L.addLast(p0);
        this.data = aData;

//        value = " ";
//        undoButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                board.undo();
//            }
//        });
//
//        for (int n = 0; n < 3; n++) {
//            xp = 15;
//            yp += 80;
//            for (int i = 0; i < 3; i++) {
//                board.buttonActionListener(board.createButton(n, i));
//            }
//        }
    }

    public JButton[][] getButtons() {
        return board.getButtons();
    }

    public Player nextPlayer() {
        Player next = null;
        if (L.isEmpty()) {
            next = p0;
            L.addLast(p0);

        } else if (L.getLast() == p0) {
            next = p1;
            L.addLast(p1);
        } else if (L.getLast() == p1) {
            next = p0;
            L.addLast(p0);
        }
        // in case one of p1 or p2 is not undo all the time this two if statements are
        // used to reset the undo permeation
        if (next == p0) {
            p1.undoCheck = 0;
        }
        if (next == p1) {
            p0.undoCheck = 0;
        }

//        if (L.size() == 10) {
//            board.buttonDisable();
//            JOptionPane.showMessageDialog(new JFrame("message"), "Game over", "Backup problem",
//                    JOptionPane.ERROR_MESSAGE);
//        }

        return next;
    }

    public JButton getUndoButton() {
        return undoButton;
    }

    public Player getPlayer1() {
        return p0;
    }

    public Player getPlayer2() {
        return p1;
    }
}
