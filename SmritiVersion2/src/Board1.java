import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Board1 implements BoardType {
    private Player p0 = new Player(1);
    private Player p1 = new Player(2);
    private BoardData data = new BoardData();
    private LinkedList<Player> L = new LinkedList<Player>();
    private JButton[][] arrayBtn = new JButton[3][3];
    private int xp = 15;
    private int yp = 15;
    private String value = " ";
    //private JButton undoButton = new JButton("Undo");
    private Player currentPlayer;


    public JButton createButton(int xp, int yp) {
        JButton btn = new JButton(xp + Integer.toString(yp) + value);
        btn.setName("n+Integer.toString(i)+value");
        btn.setBackground(Color.GRAY);
        btn.setLocation(xp, yp);
        btn.setPreferredSize(new Dimension(100, 100));
        arrayBtn[xp][yp] = btn;
        return arrayBtn[xp][yp];

    }

    public void buttonActionListener(JButton btn) {
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String buttonText = ((JButton) e.getSource()).getText();
                int d = Integer.parseInt(buttonText.substring(0, 1));
                int f = Integer.parseInt(buttonText.substring(1, 2));

                currentPlayer = nextPlayer();
                data.changeData(d, f, currentPlayer);
                arrayBtn[d][f].setEnabled(false);

                if (data.getData()[d][f] != null) {
                    if (data.getData()[d][f].id == 1) {
                        arrayBtn[d][f].setBackground(Color.GREEN);
                        arrayBtn[d][f].setOpaque(true);
                        arrayBtn[d][f].setBorderPainted(true);
                        arrayBtn[d][f].setText(p0.name);
                    }
                    if (data.getData()[d][f].id == 2) {
                        arrayBtn[d][f].setBackground(Color.RED);
                        arrayBtn[d][f].setOpaque(true);
                        arrayBtn[d][f].setBorderPainted(true);
                        arrayBtn[d][f].setText(p1.name);
                    }

                } else {
                    arrayBtn[d][f].setForeground(Color.GRAY);
                    arrayBtn[d][f].setOpaque(false);
                    arrayBtn[d][f].setBackground(Color.BLACK);
                }

                // change the button name with players name
//                if (currentPlayer == p0) {
//                    arrayBtn[d][f].setText(p0.getName());
//                }
//                if (currentPlayer == p1) {
//                    arrayBtn[d][f].setText(p1.getName());
//                }

                if (data.CheckWinner()) {  // if we have a winner, disable all buttons
                    buttonDisable();
                    return;
                }
                if (L.size() == 10) {
                    buttonDisable();
                    JOptionPane.showMessageDialog(new JFrame("message"), "Game Over", "Backup Problem", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void undo() {
        if (currentPlayer == p0 && p0.undoCheck == 0 && p0.undo < 3) {
            p0.undo++;
            p0.undoCheck = 1;
            p1.undoCheck = 0;
            L.removeLast();
            // currentPlayer = p1;
            int[] position = data.setPrevious();
            arrayBtn[position[0]][position[1]].setEnabled(true);
            arrayBtn[position[0]][position[1]].setText(String.valueOf(position[0]) + position[1]);

        } else if (currentPlayer == p1 && p1.undoCheck == 0 && p1.undo < 3) {
            p1.undo++;
            p1.undoCheck = 1;
            p0.undoCheck = 0;
            L.removeLast();
            // currentPlayer = p0;
            int[] position = data.setPrevious();
            arrayBtn[position[0]][position[1]].setEnabled(true);
            arrayBtn[position[0]][position[1]].setText(String.valueOf(position[0]) + position[1]);

        } else {
            JOptionPane.showMessageDialog(new JFrame("message"), "You can't undo", "Inane error",
                    JOptionPane.ERROR_MESSAGE);
        }


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
//            buttonDisable();
//            JOptionPane.showMessageDialog(new JFrame("message"), "Game over", "Backup problem",
//                    JOptionPane.ERROR_MESSAGE);
//        }

        return next;
    }

    public void buttonDisable() {

        for (int n = 0; n < 3; n++) {
            for (int i = 0; i < 3; i++) {
                arrayBtn[n][i].setEnabled(false);
            }
        }
    }

    public JButton[][] getButtons() {
        return arrayBtn;
    }
}
