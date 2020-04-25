import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Board2 implements BoardType {
    private Player p0 = new Player(1);
    private Player p1 = new Player(2);
    //    private Player[][] cellValue = new Player[3][3];
    private BoardData data = new BoardData();
    private LinkedList<Player> L = new LinkedList<Player>();
    private Button[][] arrayBtn = new Button[3][3];
    private int xp = 15;
    private int yp = 15;
    private String value = " ";
    //    private JButton undoButton = new JButton("Undo");
    private Player currentPlayer;

//    public Board2() {
//        currentPlayer = p0;
//        L.addLast(p0);
//    }

    public JButton createButton(int xp, int yp) {
        Button btn = new Button(xp + Integer.toString(yp) + value);
        btn.getButton().setName("n+Integer.toString(i)+value");
        btn.getButton().setBackground(Color.GRAY);
        btn.getButton().setLocation(xp, yp);
        btn.getButton().setPreferredSize(new Dimension(100, 100));
        arrayBtn[xp][yp] = btn;
        return arrayBtn[xp][yp].getButton();
    }

    public void buttonActionListener(JButton butt) {
        butt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String buttonText = ((JButton) e.getSource()).getText();
                int d = Integer.parseInt(buttonText.substring(0, 1));
                // System.out.println("d: " + d);
                int f = Integer.parseInt(buttonText.substring(1, 2));
                // System.out.println("f: " + f);

                currentPlayer = nextPlayer();
                data.changeData(d, f, currentPlayer);
                arrayBtn[d][f].getButton().setEnabled(false);
                arrayBtn[d][f].shakingTime = 100;
                arrayBtn[d][f].shakeButton();

                if (data.getData()[d][f] != null) {
                    if (data.getData()[d][f].id == 1) {
                        System.out.println("player 0 is: " + data.getData()[d][f].name);
                        String temp = data.getData()[d][f].name;
                        arrayBtn[d][f].getButton().setText(temp);
                        arrayBtn[d][f].getButton().setBackground(Color.GREEN);
                        arrayBtn[d][f].getButton().setOpaque(true);
                        arrayBtn[d][f].getButton().setBorderPainted(true);

//                        System.out.println("player 0 name is: " + cellValue[d][f].name);

                    }
                    if (data.getData()[d][f].id == 2) {
                        arrayBtn[d][f].getButton().setBackground(Color.RED);
                        arrayBtn[d][f].getButton().setOpaque(true);
                        arrayBtn[d][f].getButton().setBorderPainted(true);
                        arrayBtn[d][f].getButton().setText(p1.name);
//                        arrayBtn[d][f].getButton().setName(data.getData()[d][f].name);
                    }

                } else {
                    arrayBtn[d][f].getButton().setForeground(Color.GRAY);
                    arrayBtn[d][f].getButton().setOpaque(false);
                    arrayBtn[d][f].getButton().setBackground(Color.BLACK);
                }

                if (data.CheckWinner()) {// if we have a winner, disable all buttons
                    buttonDisable();
                    return;

                }
                if (L.size() == 10) {
                    buttonDisable();
                    JOptionPane.showMessageDialog(new JFrame("message"), "Game Over", "Backup Problem", JOptionPane.ERROR_MESSAGE);
                }

                // shaking button bay seating time 100
                // arrayBtn[d][f].shakingTime = 100;

                arrayBtn[d][f].shakeButton();

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
            int[] postion = data.setPrevious();
            arrayBtn[postion[0]][postion[1]].getButton().setEnabled(true);
            arrayBtn[postion[0]][postion[1]].shakingTime = 0;
            arrayBtn[postion[0]][postion[1]].getButton().setText(String.valueOf(postion[0]) + postion[1]);
        } else if (currentPlayer == p1 && p1.undoCheck == 0 && p1.undo < 3) {
            p1.undo++;
            p1.undoCheck = 1;
            p0.undoCheck = 0;
            L.removeLast();
            // currentPlayer = p0;
            int[] postion = data.setPrevious();
            arrayBtn[postion[0]][postion[1]].getButton().setEnabled(true);
            arrayBtn[postion[0]][postion[1]].shakingTime = 0;
            arrayBtn[postion[0]][postion[1]].getButton().setText(String.valueOf(postion[0]) + postion[1]);
        } else {
            JOptionPane.showMessageDialog(new JFrame("message"), "You can't undo", "Inane error", JOptionPane.ERROR_MESSAGE);
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
                arrayBtn[n][i].getButton().setEnabled(false);
            }
        }
    }

    public JButton[][] getButtons() {
        JButton[][] jButtons = new JButton[3][3];
        for (int n = 0; n < 3; n++) {
            for (int i = 0; i < 3; i++) {
                jButtons[n][i] = arrayBtn[n][i].getButton();
            }
        }
        return jButtons;
    }
}
