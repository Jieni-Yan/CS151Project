import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CellButton implements Observer, BordType {

	Player p0 = new Player(1);
	Player p1 = new Player(2);
	BordData data = new BordData();
	LinkedList<Player> L = new LinkedList<Player>();
	JButton[][] arrayBtn = new JButton[3][3];
	int xp = 15;
	int yp = 15;
	String value;
	JButton undoButton = new JButton("undo");
	Player curnetPlayer;

	// int curentXPo;
	// int curentYPo;

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
//				curentXPo = d;
//				curentYPo = f;
				data.changeData(d, f, curnetPlayer);
				arrayBtn[d][f].setEnabled(false);

//				curnetPlayer = nextPlayer();
//				notifyObservers(d, f, curnetPlayer);
//				arrayBtn[d][f].setBackground(Color.red);
//				arrayBtn[d][f].setOpaque(true);
//				changButtonColor(curnetPlayer, arrayBtn[d][f]);

				// change the button name with players name
				if (curnetPlayer == p0)
					arrayBtn[d][f].setText(p0.name);
				if (curnetPlayer == p1)
					arrayBtn[d][f].setText(p1.name);
				// new AnimatedCellButton(arrayBtn[d][f]);
				// shakeCellButton(arrayBtn[d][f]);
//				if (data.CheckWiner() == true) {// if we have a winer disable all buttons
//					buttonDisable();
//				}
				if (data.CheckWiner() == true) {// if we have a winer disable all buttons
					buttonDisable();
				} else {
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
			curnetPlayer = p1;
			int[] postion = data.setPrevious();
			arrayBtn[postion[0]][postion[1]].setEnabled(true);
			arrayBtn[postion[0]][postion[1]].setText(String.valueOf(postion[0]) + postion[1]);
//			arrayBtn[curentXPo][curentYPo].setEnabled(true);
//			arrayBtn[curentXPo][curentYPo].setBackground(Color.GRAY);
//			arrayBtn[curentXPo][curentYPo].setText(String.valueOf(curentXPo) + curentYPo);// reset button name to
																							// previous
		} else if (L.getLast() == p1 && p1.undocheck == 0 && p1.undo < 3) {
			p1.undo++;
			p1.undocheck = 1;
			p0.undocheck = 0;
			L.removeLast();
			curnetPlayer = p0;
			int[] postion = data.setPrevious();
			arrayBtn[postion[0]][postion[1]].setEnabled(true);
			arrayBtn[postion[0]][postion[1]].setText(String.valueOf(postion[0]) + postion[1]);
//			arrayBtn[curentXPo][curentYPo].setEnabled(true);
//			arrayBtn[curentXPo][curentYPo].setBackground(Color.GRAY);
//			arrayBtn[curentXPo][curentYPo].setText(String.valueOf(curentXPo) + curentYPo);// reset button name to
																							// previous
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
//		if (L.size() == 9 && data.CheckWiner() == false) {// still error when the last button is for
//			buttonDisable(); // wining shows first game over ten display the winer
//			JOptionPane.showMessageDialog(new JFrame("massage"), "game over", "Backup problem",
//					JOptionPane.ERROR_MESSAGE);
//		}
		
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

//	public void changButtonColor(Player p, JButton b) {
//		if (p == p0) {
//			b.setBackground(Color.green);
//			b.setOpaque(true);
//			b.setBorderPainted(true);
//			b.repaint();
//			b.setEnabled(false);
//		}
//		if (p == p1) {
//			b.setBackground(Color.RED);
//			b.setOpaque(true);
//			b.setBorderPainted(true);
//			b.repaint();
//			b.setEnabled(false);
//		}
//
//	}

	// @Override
	// public void notifyObservers(int xp, int yp, Player event) {
	// data.update(xp, yp, event);
	//
	// }

	public JButton getUndoButton()
	{
		return undoButton;
	}
	
	public JButton[][] getButtons()
	{
		return arrayBtn;
	}
	
	public Player getPlayer1()
	{
		return p0;
	}
	
	public Player getPlayer2()
	{
		return p1;
	}
	
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
						arrayBtn[n][i].setText(p0.name);
					}
					if (data.getData()[n][i].id == 2)
					{
						arrayBtn[n][i].setBackground(Color.RED);
						arrayBtn[n][i].setOpaque(true);
						arrayBtn[n][i].setBorderPainted(true);
						arrayBtn[n][i].setText(p1.name);
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