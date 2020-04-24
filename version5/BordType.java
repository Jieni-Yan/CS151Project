import javax.swing.JButton;

public interface BordType {
	public void buttonActionListner(JButton butt);
	public JButton creatButton(int xp, int yp);
	public void undo();
	public Player nextPlayer();
	public void buttonDisable();
	public JButton getUndoButton();
	public JButton[][] getButtons();
	public Player getPlayer1();
	public Player getPlayer2();
}
