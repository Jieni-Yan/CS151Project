import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

public class TicTacToeTest extends Thread {
    static JPanel controlPanel = new JPanel();
    static int boardType;

    public static void main(String[] args) {

        //create two board view object
        BoardData data = new BoardData();
        BoardType board1 = new CellButton(data);
        BoardType board2 = new AnimatedCellButton(data);

        // the game frame thread
        final Thread boardFrame = new Thread(new Runnable() {
            public void run() {
                Thread.yield();

                JFrame frame = new JFrame("Tic-Tac-Toe game");

                JMenuBar menubar = new JMenuBar();
                JMenu menu = new JMenu("Undo");
                if (boardType == 1) {
                    menu.add(board1.getUndoButton());
                }
                if (boardType == 2) {
                    menu.add(board2.getUndoButton());
                }
                menubar.add(menu);
                frame.setJMenuBar(menubar);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 500);
                GridLayout grid = new GridLayout(3, 3, 3, 2);
                frame.setLayout(grid);
                for (int n = 0; n < 3; n++) {
                    for (int i = 0; i < 3; i++) {
                        if (boardType == 1) {
                            frame.add(board1.getButtons()[n][i]);
                        }
                        if (boardType == 2) {
                            frame.add(board2.getButtons()[n][i]);
                        }
                    }
                }
                frame.pack();
                frame.setVisible(true);

            }
        });


        //the initial display thread
        final Thread startFrame = new Thread(new Runnable() {
            public void run() {
                try {

                    JFrame frame1 = new JFrame("Initializing the game");
                    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame1.setSize(200, 500);
                    frame1.setLayout(new GridLayout(2, 2, 1, 1));
                    JLabel player1name = new JLabel("Player 1 name: ", JLabel.RIGHT);
                    JLabel player2name = new JLabel("Player 2 name: ", JLabel.CENTER);
                    final JTextField name1 = new JTextField(6);
                    final JTextField name2 = new JTextField(6);
                    JButton submitButton = new JButton("Submit");
                    submitButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            boardFrame.start();
                            frame1.setVisible(false);
                        }
                    });

                    JButton boardType1 = new JButton("Board type 1");
                    JButton boardType2 = new JButton("Board type 2");

                    boardType1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            boardType1.setBackground(Color.red);
                            boardType1.setOpaque(true);
                            boardType2.setBackground(Color.lightGray);
                            boardType2.setOpaque(true);
                            boardType1.repaint();
                            boardType2.repaint();
                            boardType = 1;
                            data.addObserver((Observer) board1);
                            board1.getPlayer1().name = name1.getText();
                            board1.getPlayer2().name = name2.getText();

                        }
                    });

                    boardType2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            boardType2.setBackground(Color.red);
                            boardType2.setOpaque(true);
                            boardType1.setBackground(Color.lightGray);
                            boardType1.setOpaque(true);
                            boardType2.repaint();
                            boardType1.repaint();
                            boardType = 2;
                            data.addObserver((Observer) board2);
                            board2.getPlayer1().name = name1.getText();
                            board2.getPlayer2().name = name2.getText();

                        }
                    });


                    controlPanel.add(player1name);
                    controlPanel.add(name1);
                    controlPanel.add(player2name);
                    controlPanel.add(name2);
                    controlPanel.add(boardType1);
                    controlPanel.add(boardType2);
                    controlPanel.add(submitButton);
                    frame1.add(controlPanel);
                    frame1.setVisible(true);
                    Thread.sleep(100000);
                } catch (InterruptedException ex) {
                }
            }
        });
        startFrame.start();


    }

}
