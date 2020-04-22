import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CellButton extends JButton implements Observer {
	private Player p0 = new Player(1);
	private Player p1 = new Player(2);
	private BordData data;
	private LinkedList<Player> L = new LinkedList<Player>();
	private JButton[][] arrayBtn = new JButton[3][3];
	private int xp = 15;
	private int yp = 15;
	private String value;
	private JButton undoButton = new JButton("Undo");
	private Player curnetPlayer;

	public CellButton(BordData aData) {
		curnetPlayer = p0;
		L.addLast(p0);
		data = aData;
		value = " ";
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println("undo");
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
		JButton butt = new JButton(xp + Integer.toString(yp) + value);
		butt.setName("n+Integer.toString(i)+value");
		butt.setBackground(Color.GRAY);
		butt.setLocation(xp, yp);
		butt.setPreferredSize(new Dimension(100, 100));
		arrayBtn[xp][yp] = butt;
		return arrayBtn[xp][yp];
	}

	public void buttonActionListner(JButton butt) {
		butt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buttonText = ((JButton) e.getSource()).getText();
				int d = Integer.parseInt(buttonText.substring(0, 1));
				int f = Integer.parseInt(buttonText.substring(1, 2));
				
				data.changeData(d, f, curnetPlayer);
				arrayBtn[d][f].setEnabled(false);
				
//				changButtonColor(curnetPlayer, arrayBtn[d][f]);
				new AnimatedCellButton(arrayBtn[d][f]);
				// shakeCellButton(arrayBtn[d][f]);
				if (data.CheckWiner() == true) {// if we have a winer disable all buttons
					buttonDisable();
				}
				else
				{
					curnetPlayer = nextPlayer();
				}
				
				
				
			}
		});
	}

	public void undo() {
		if (L.getLast() == p0 && p0.undocheck == 0 && p0.undo < 3) {
			p0.undo++;
			p0.undocheck = 1;
			p1.undocheck = 0;
			L.removeLast();
			int[] postion = data.setPrevious();
			arrayBtn[postion[0]][postion[1]].setEnabled(true);
		} else if (L.getLast() == p1 && p1.undocheck == 0 && p1.undo < 3) {
			p1.undo++;
			p1.undocheck = 1;
			p0.undocheck = 0;
			L.removeLast();
			int[] postion = data.setPrevious();
			arrayBtn[postion[0]][postion[1]].setEnabled(true);
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
		// if the bore is full no winer
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
				arrayBtn[n][i].setEnabled(false);
			}
		}
	}
	
	public JButton getUndoButton()
	{
		return undoButton;
	}
	
    public JButton[][] getButtons()
    {
		return arrayBtn;
    }

	// public void changButtonColor(Player p, JButton b) {
	// if (p == p0) {
//	 b.setBackground(Color.green);
//	 b.setOpaque(true);
//	 b.setBorderPainted(true);
//	 b.repaint();
	// }
	// if (p == p1) {
//	 b.setBackground(Color.RED);
//	 b.setOpaque(true);
//	 b.setBorderPainted(true);
//	 b.repaint();
	// }
	//
	// }

	// @Override
	// public void notifyObservers(int xp, int yp, Player event) {
	// data.update(xp, yp, event);
	//
	// }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		data = (BordData) o;
		for (int n = 0; n < 3; n++) {
			for (int i = 0; i < 3; i++) {
				if(data.getData()[n][i] != null)
				{
					if (data.getData()[n][i].id == 1)
					{
						arrayBtn[n][i].setBackground(Color.green);
						arrayBtn[n][i].setOpaque(true);
						arrayBtn[n][i].setBorderPainted(true);
					}
					if (data.getData()[n][i].id == 2)
					{
						arrayBtn[n][i].setBackground(Color.RED);
						arrayBtn[n][i].setOpaque(true);
						arrayBtn[n][i].setBorderPainted(true);
					}
					
				}
				else {
					arrayBtn[n][i].setForeground(Color.GRAY);
					arrayBtn[n][i].setOpaque(false);
					arrayBtn[n][i].setBackground(Color.BLACK);
				}

			}
		}
	}

}