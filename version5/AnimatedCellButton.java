import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class AnimatedCellButton implements Observer, BordType {

	Player p0 = new Player(1);
	Player p1 = new Player(2);
	BordData data = new BordData();
	LinkedList<Player> L = new LinkedList<Player>();
	Button[][] arrayBtn = new Button[3][3];
	int xp = 15;
	int yp = 15;
	String value;
	JButton undoButton = new JButton("Undo");
	Player curnetPlayer;

	public AnimatedCellButton(BordData aData) {
		curnetPlayer = p0;
		L.addLast(p0);
		data = aData;
		value = " ";
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("undo");
				undo();
			}
		});

		for (int n = 0; n < 3; n++) {
			xp = 15;
			yp += 80;
			for (int i = 0; i < 3; i++) {
				buttonActionListner(creatButton(n, i));
			}
		}
	}
	

	public JButton creatButton(int xp, int yp) {
		Button butt = new Button(xp + Integer.toString(yp) + value);
		butt.getButton().setName("n+Integer.toString(i)+value");
		butt.getButton().setBackground(Color.GRAY);
		butt.getButton().setLocation(xp, yp);
		butt.getButton().setPreferredSize(new Dimension(100, 100));
		arrayBtn[xp][yp] = butt;
		return arrayBtn[xp][yp].getButton();
	}

	public void buttonActionListner(JButton butt) {
		butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buttonText = ((JButton) e.getSource()).getText();
				int d = Integer.parseInt(buttonText.substring(0, 1));
				int f = Integer.parseInt(buttonText.substring(1, 2));
				// curentXPo = d;
				// curentYPo = f;

				data.changeData(d, f, curnetPlayer);
				arrayBtn[d][f].getButton().setEnabled(false);

				if (data.CheckWiner() == true) {// if we have a winer disable all buttons
					buttonDisable();
				} else {
					curnetPlayer = nextPlayer();
				}

				// shaking button bay seating time 100
				arrayBtn[d][f].shkingTime = 100;
				// change the button name with players name
//				if (curnetPlayer == p0)
//					arrayBtn[d][f].getButton().setText(p0.name);
//				if (curnetPlayer == p1)
//					arrayBtn[d][f].getButton().setText(p1.name);
				// arrayBtn[d][f].getButton().repaint();
				arrayBtn[d][f].shakeButton();
			}
		});

	}

	public void undo() {
		if (L.getLast() == p0 && p0.undocheck == 0 && p0.undo < 3) {
			p0.undo++;
			p0.undocheck = 1;
			p1.undocheck = 0;
			L.removeLast();
			curnetPlayer = p1;
			int[] postion = data.setPrevious();
			arrayBtn[postion[0]][postion[1]].getButton().setEnabled(true);
			arrayBtn[postion[0]][postion[1]].shkingTime = 0;
			arrayBtn[postion[0]][postion[1]].getButton().setText(String.valueOf(postion[0])+postion[1]);
		} else if (L.getLast() == p1 && p1.undocheck == 0 && p1.undo < 3) {
			p1.undo++;
			p1.undocheck = 1;
			p0.undocheck = 0;
			L.removeLast();
			curnetPlayer = p0;
			int[] postion = data.setPrevious();
			arrayBtn[postion[0]][postion[1]].getButton().setEnabled(true);
			arrayBtn[postion[0]][postion[1]].shkingTime = 0;
			arrayBtn[postion[0]][postion[1]].getButton().setText(String.valueOf(postion[0])+postion[1]);
		} else {
			JOptionPane.showMessageDialog(new JFrame("massage"), "You can't undo", "Inane error",
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
			p1.undocheck = 0;
		}
		if (next == p1) {
			p0.undocheck = 0;
		}
		
		if (L.size() == 10) {
			buttonDisable();
			JOptionPane.showMessageDialog(new JFrame("massage"), "game over", "Backup problem",
					JOptionPane.ERROR_MESSAGE);
		}

		return next;
	}

	public void buttonDisable() {

		for (int n = 0; n < 3; n++) {
			for (int i = 0; i < 3; i++) {
				arrayBtn[n][i].getButton().setEnabled(false);
			}
		}
	}

	public JButton getUndoButton()
	{
		return undoButton;
	}
	
	public JButton[][] getButtons()
    {
		JButton[][] jButtons = new JButton[3][3];
		for (int n = 0; n < 3; n++) {
			for (int i = 0; i < 3; i++) {
				jButtons[n][i] = arrayBtn[n][i].getButton();
			}
		}
		return jButtons;
    }
	
	public Player getPlayer1()
	{
		return p0;
	}
	
	public Player getPlayer2()
	{
		return p1;
	}
	
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		data = (BordData) o;
		for (int n = 0; n < 3; n++) {
			for (int i = 0; i < 3; i++) {
				if (data.getData()[n][i] != null) {
					if (data.getData()[n][i].id == 1) {
						arrayBtn[n][i].getButton().setBackground(Color.GREEN);
						arrayBtn[n][i].getButton().setOpaque(true);
						arrayBtn[n][i].getButton().setBorderPainted(true);
						System.out.println(p0.name);
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
