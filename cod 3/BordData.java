import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BordData implements Observer{

  
    player[][] CellaValue = new player[3][3];
   
    
    public BordData() {
        for (int n = 0; n < 3; n++) {
            for(int i = 0; i < 3; i++){
                CellaValue[n][i]=null;
            }
        }
    }
    
    
    
    
    public boolean CheckWiner() {
        boolean temp1 = false;
        int temp = 100;
        
        for(int i = 0; i < 3; i++)
        {
            if (CellaValue[i][0] != null && CellaValue[i][1] != null && CellaValue[i][2] != null) {
                if (CellaValue[i][0].id == CellaValue[i][1].id && CellaValue[i][1].id == CellaValue[i][2].id) 
                {
                    temp = CellaValue[i][0].id;
                    temp1 = true;
                    JOptionPane.showMessageDialog(new JFrame("massage"), "Player" + String.valueOf(temp) + " win!",
                            "Backup problem", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
            if (CellaValue[0][i] != null && CellaValue[1][i] != null && CellaValue[2][i] != null) {
                if (CellaValue[0][i].id == CellaValue[1][i].id && CellaValue[1][i].id == CellaValue[2][i].id) 
                {
                    temp = CellaValue[0][i].id;
                    temp1 = true;
                    JOptionPane.showMessageDialog(new JFrame("massage"), "Player" + String.valueOf(temp) + " win!",
                            "Backup problem", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }
        
        if (CellaValue[0][0] != null && CellaValue[1][1] != null && CellaValue[2][2] != null) {
            if (CellaValue[0][0].id == CellaValue[1][1].id && CellaValue[1][1].id == CellaValue[2][2].id) {
                temp = CellaValue[0][0].id;
                temp1 = true;
                JOptionPane.showMessageDialog(new JFrame("massage"), "Player" + String.valueOf(temp) + " win!",
                        "Backup problem", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        if (CellaValue[0][2] != null && CellaValue[1][1] != null && CellaValue[2][0] != null) {
            if (CellaValue[0][2].id == CellaValue[1][1].id && CellaValue[1][1].id == CellaValue[2][0].id) {
                temp = CellaValue[0][2].id;
                temp1 = true;
                JOptionPane.showMessageDialog(new JFrame("massage"), "Player" + String.valueOf(temp) + " win!",
                        "Backup problem", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        return temp1;
    }

  @Override
  public void update(int xp, int yp,player event) {
      CellaValue[xp][yp]=event;
      System.out.println( CellaValue[xp][yp].id);
  }
    
    
 
}