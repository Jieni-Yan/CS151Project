
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TicTacToeTest extends Thread{
    static JPanel controlPanel=new JPanel();
    static int bordType;
    public static void main(String[] args)  {
        
//creat two bord view object
        CellButton Darr=new CellButton();
        AnimatedCellButton Darr2=new AnimatedCellButton();

        // the game frame thread
        final Thread boredFrame = new Thread(new Runnable() {          
            public void run() {
                  Thread.yield();

                JFrame frame = new JFrame("Tic-Tac-Toe game");
                
                JMenuBar menubar = new JMenuBar();
                JMenu menu = new JMenu("undo");
                if(bordType == 1) {
                menu.add(Darr.undoButton);                
                }
                if(bordType == 2) {
                    menu.add(Darr2.undoButton);                
                    }                
                menubar.add(menu);
                frame.setJMenuBar(menubar);                               
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500,500);
                GridLayout grid = new GridLayout(3, 3, 3, 2);
                frame.setLayout(grid);               
                for (int n = 0; n < 3; n++) {
                    for(int i = 0; i < 3; i++){                            
                    if(bordType == 1) {
                        frame.add(Darr.arrayBtn[n][i]);                 
                    }
                    if(bordType == 2) {
                        frame.add(Darr2.arrayBtn[n][i].getButton());             
                        }                              
                    }}
                frame.pack();
                frame.setVisible(true);
             
            }
          });
        
        
        //the initial display thread
        final Thread startFrame = new Thread(new Runnable() {
            public void run() {
              try {  
               
                  JFrame frame1 = new JFrame("initalizeng the game");
                  frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                 
                  frame1.setSize(200,500);
                  frame1.setLayout(new GridLayout(2, 2, 1, 1));                  
                  JLabel  player1name= new JLabel("Player 1 name: ", JLabel.RIGHT);
                  JLabel  player2name = new JLabel("Player 2 name: ", JLabel.CENTER);
                  final JTextField name1 = new JTextField(6);
                  final JTextField name2 = new JTextField(6);      
                  JButton submitButton = new JButton("submit");                 
                  submitButton.addActionListener(new ActionListener() {
                     public void actionPerformed(ActionEvent e) {                    
                        boredFrame.start(); 
                        frame1.setVisible(false);      
                     }
                  }); 
                  
                  JButton bordType1 = new JButton("Bord type 1");
                  JButton bordType2 = new JButton("Bord type 2"); 
                  
                  bordType1.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent e) { 
                          bordType1.setBackground(Color.red);
                          bordType1.setOpaque(true);
                          bordType2.setBackground(Color.lightGray);
                          bordType2.setOpaque(true);
                          bordType1.repaint();
                          bordType2.repaint();
                          bordType=1;
                          Darr.p0.name=name1.getText();
                          Darr.p1.name=name2.getText();
                             
                      }
                   }); 
                 
                  bordType2.addActionListener(new ActionListener() {
                      public void actionPerformed(ActionEvent e) {  
                          bordType2.setBackground(Color.red);
                          bordType2.setOpaque(true);
                          bordType1.setBackground(Color.lightGray);
                          bordType1.setOpaque(true);
                          bordType2.repaint();
                          bordType1.repaint();
                          bordType=2;
                          Darr2.p0.name=name1.getText();
                          Darr2.p1.name=name2.getText();
                           
                      }
                   }); 
                  
                  
                  controlPanel.add(bordType1);
                  controlPanel.add(bordType2);
                  controlPanel.add(player1name);
                  controlPanel.add(name1);
                  controlPanel.add(player2name);       
                  controlPanel.add(name2);
                  controlPanel.add(submitButton);
                  frame1.add(controlPanel);
                  frame1.setVisible(true);                                                    
                  Thread.sleep(100000);
              } catch (InterruptedException ex) { }            
            }
          });
        startFrame.start();       
        
       
        
        
        
      
      }
    
}