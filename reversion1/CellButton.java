import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;  
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CellButton extends JButton implements Observer{
    private BordData data;
    private JButton[][] arrayBtn = new JButton [3][3];
    private int xp=15;
    private int yp=15;
    private String value;
    
    private int player,player1Undo, player2Undo;



    public CellButton(BordData bordData) {
        value=" ";
        player = 1;
        data = bordData;
        player1Undo = 0;
        player2Undo = 0;
        		
        
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
                             
                             if(isPlayer1() == 1)
                            	 	data.changeData(d, f, 1);
                             else
                            	 	data.changeData(d, f, 2);
                             arrayBtn[d][f].setBackground(Color.red);
                             arrayBtn[d][f].setOpaque(true);
                             player++;
                         }
                     }
                 );
         }
       }
   }
    
    public JButton[][] getButtons(){
    		return arrayBtn;
    }
    
    public int isPlayer1()
    {
    		if(player % 2 == 1)
    			return 1;
    		else
    			return 2;
    }

    public void playerUndo()
    {
    		player -= 1;
    }
    
    public int getUndo(int player)
    {
    		if(player == 1)
    			return player1Undo;
    		else 
    			return player2Undo;
    }
    
    public void addUndo(int player)
    {
    		if(player == 1)
    			player1Undo++;
    		else 
    			player2Undo++;
    }
    
    
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		data = (BordData) o;
		for(int n = 0; n < 3; n++)
		{
			for(int i = 0; i < 3; i++)
			{
				
				if(data.getData()[n][i] == 1)
				{
					arrayBtn[n][i].setForeground(Color.PINK);
						
				}
				else if(data.getData()[n][i] == 2)
					arrayBtn[n][i].setForeground(Color.BLUE);
				else
				{
					arrayBtn[n][i].setForeground(Color.GRAY);
					arrayBtn[n][i].setOpaque(false);
					arrayBtn[n][i].setBackground(Color.red);
				}
					
			}
		}
	}
    
    

}