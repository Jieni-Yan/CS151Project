import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AnimatedCellButton extends GameView implements Observer {

    private Player p0 = new Player(1);
    private Player p1 = new Player(2);
    private BoardData data;
    //    private LinkedList<Player> L = new LinkedList<Player>();
    private Button[][] arrayBtn = new Button[3][3];
    private int xp = 15;
    private int yp = 15;
    private String value;
    private JButton undoButton = new JButton("Undo");
//    private Player currentPlayer;

    public AnimatedCellButton(BoardData aData) {
        super(aData);
//        this.data = aData;
//        data.addObserver(this);
        board = new Board2();
        value = " ";
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.undo();
            }
        });

        for (int n = 0; n < 3; n++) {
            xp = 15;
            yp += 80;
            for (int i = 0; i < 3; i++) {
                board.buttonActionListener(board.createButton(n, i));

            }
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        data = (BoardData) o;
        for (int n = 0; n < 3; n++) {
            for (int i = 0; i < 3; i++) {
                if (data.getData()[n][i] != null) {
                    if (data.getData()[n][i].id == 1) {
                        arrayBtn[n][i].getButton().setBackground(Color.GREEN);
                        arrayBtn[n][i].getButton().setOpaque(true);
                        arrayBtn[n][i].getButton().setBorderPainted(true);
                        // System.out.println(p0.getName());
                        arrayBtn[n][i].getButton().setText(p0.name);
                    }
                    if (data.getData()[n][i].id == 2) {
                        arrayBtn[n][i].getButton().setBackground(Color.RED);
                        arrayBtn[n][i].getButton().setOpaque(true);
                        arrayBtn[n][i].getButton().setBorderPainted(true);
                        arrayBtn[n][i].getButton().setText(p1.name);
                    }

                } else {
                    arrayBtn[n][i].getButton().setForeground(Color.GRAY);
                    arrayBtn[n][i].getButton().setOpaque(false);
                    arrayBtn[n][i].getButton().setBackground(Color.BLACK);
                }

            }
        }
    }
}
