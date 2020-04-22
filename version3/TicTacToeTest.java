
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.GridLayout;

public class TicTacToeTest {

	public static void main(String[] args) {
		BordData data = new BordData();
        CellButton Darr=new CellButton(data);
        data.addObserver(Darr);

		// the frame that contains the components
		JFrame frame = new JFrame("GridLayoutTest from JCG");

		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Undo");
		menu.add(Darr.getUndoButton());
		menubar.add(menu);
		frame.setJMenuBar(menubar);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set the size of the frame
		frame.setSize(500, 500);

		// set the rows and cols of the grid, as well the distances between them
		GridLayout grid = new GridLayout(3, 3, 3, 2);
		// what layout we want to use for our frame
		frame.setLayout(grid);
		// frame.add(Darr.undoButton);

		for (int n = 0; n < 3; n++) {
			for (int i = 0; i < 3; i++) {
				frame.add(Darr.getButtons()[n][i]);
			}
		}
		frame.pack();
		frame.setVisible(true);
	}

}