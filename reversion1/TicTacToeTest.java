
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TicTacToeTest {
   
    public static void main(String[] args)  {
        BordData data = new BordData();
        CellButton Darr=new CellButton(data);
        data.addObserver(Darr);
        // the frame that contains the components
        JFrame frame = new JFrame("GridLayoutTest from JCG");
        
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Undo");
        JMenuItem undo = new JMenuItem("Undo");
        menu.add(undo);
        menubar.add(menu);
        frame.setJMenuBar(menubar);
        
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		if(Darr.getUndo(Darr.isPlayer1()) < 3)
            		{
            			Darr.addUndo(Darr.isPlayer1());
            			data.setPrevious();
                		Darr.playerUndo();
            		}
            }
        });
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set the size of the frame
        frame.setSize(500,500);
         
        // set the rows and cols of the grid, as well the distances between them
        GridLayout grid = new GridLayout(3, 3, 3, 2);
        // what layout we want to use for our frame
        frame.setLayout(grid);
        for (int n = 0; n < 3; n++) {
            for(int i = 0; i < 3; i++){
            		frame.add(Darr.getButtons()[n][i]);
            	}
        	}
        
        frame.pack();
        frame.setVisible(true);
      }
       

}