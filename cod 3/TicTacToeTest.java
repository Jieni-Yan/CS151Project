
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
public class TicTacToeTest {
   
    public static void main(String[] args)  {
        CellButton Darr=new CellButton();
        AnimatedCellButton Darr2=new AnimatedCellButton();
        
        // the frame that contains the components
        JFrame frame = new JFrame("GridLayoutTest from JCG");
        
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("undo");
        menu.add(Darr2.undoButton);
        //menu.add(Darr.undoButton);
        menubar.add(menu);
        frame.setJMenuBar(menubar);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set the size of the frame
        frame.setSize(500,500);
         
        // set the rows and cols of the grid, as well the distances between them
        GridLayout grid = new GridLayout(3, 3, 3, 2);
        // what layout we want to use for our frame
        frame.setLayout(grid);

       
        for (int n = 0; n < 3; n++) {
            for(int i = 0; i < 3; i++){
        frame.add(Darr2.arrayBtn[n][i].getButton());
       // frame.add(Darr.arrayBtn[n][i]);
            }}
        frame.pack();
        frame.setVisible(true);
      }
       

}