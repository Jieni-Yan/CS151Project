import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;  
import java.awt.event.*;  

import javax.swing.JButton;

public class CellButton implements Observebl{
     BordData data=new BordData();
     JButton[][] arrayBtn=new JButton [3][3];
    int xp=15;
    int yp=15;
    String value;
    

    public CellButton() {
        value=" ";
       
       for (int n = 0; n < 3; n++) {
           xp=15;
           yp+=80;
         for(int i = 0; i < 3; i++){
             arrayBtn[n][i] = new JButton(n+Integer.toString(i)+value);
             arrayBtn[n][i].setName("n+Integer.toString(i)+value");
             arrayBtn[n][i].setBackground(Color.GRAY);
            // arrayBtn[n][i].setOpaque(true);
             arrayBtn[n][i].setLocation(xp,yp);
             arrayBtn[n][i].setPreferredSize(new Dimension(100, 100));
             
             arrayBtn[n][i].addActionListener(
                     new ActionListener() {
                         public void actionPerformed(ActionEvent e) {
                             String buttonText = ((JButton) e.getSource()).getText();
                             int d=Integer.parseInt(buttonText.substring(0, 1));
                             int f=Integer.parseInt(buttonText.substring(1,2));

                             notifyObservers(d,f,0);
                             arrayBtn[d][f].setBackground(Color.red);
                              arrayBtn[d][f].setOpaque(true);
                             //arrayBtn[d][f].repaint();
                         }
                     }
                 );
             //arrayBtn[n][i].setBorderPainted(false);
       }
   }
   }
    @Override
    public void notifyObservers(int xp,int yp,int event) {
        data.update(xp, yp, event);
        
    }
    
    

}