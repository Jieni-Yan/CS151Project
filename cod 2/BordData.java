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
  boolean temp1=false;
  int temp=100;
  
  if(CellaValue[0][0]!=null && CellaValue[0][1]!=null && CellaValue[0][2]!=null) {
  if(CellaValue[0][0].id==CellaValue[0][1].id && CellaValue[0][1].id==CellaValue[0][2].id) {
      temp=CellaValue[0][0].id;
      temp1=true;
  JOptionPane.showMessageDialog(new JFrame("massage"), "P"+String.valueOf(temp) +" wean!", "Backup problem",
          JOptionPane.ERROR_MESSAGE);
  }
  }
  
  if(CellaValue[1][0]!=null && CellaValue[1][1]!=null && CellaValue[1][2]!=null) {
  if(CellaValue[1][0].id==CellaValue[1][1].id && CellaValue[1][1].id==CellaValue[1][2].id) {
      temp=CellaValue[1][0].id;
      temp1=true;
  JOptionPane.showMessageDialog(new JFrame("massage"), "P"+String.valueOf(temp) +" wean!", "Backup problem",
          JOptionPane.ERROR_MESSAGE);
  }
  }
  
  if(CellaValue[2][0]!=null && CellaValue[2][1]!=null && CellaValue[2][2]!=null) {
  if(CellaValue[2][0].id==CellaValue[2][1].id && CellaValue[2][1].id==CellaValue[2][2].id) {
      temp=CellaValue[2][0].id;
      temp1=true;
  JOptionPane.showMessageDialog(new JFrame("massage"), "P"+String.valueOf(temp) +" wean!", "Backup problem",
          JOptionPane.ERROR_MESSAGE);
  }
  }
  
  if(CellaValue[0][0]!=null && CellaValue[1][0]!=null && CellaValue[2][0]!=null) {
  if(CellaValue[0][0].id==CellaValue[1][0].id && CellaValue[1][0].id==CellaValue[2][0].id) {
      temp=CellaValue[0][0].id;
      temp1=true;
  JOptionPane.showMessageDialog(new JFrame("massage"), "P"+String.valueOf(temp) +" wean!", "Backup problem",
          JOptionPane.ERROR_MESSAGE);
  }
  }
  

  if(CellaValue[0][1]!=null && CellaValue[1][1]!=null && CellaValue[2][1]!=null) {
  if(CellaValue[0][1].id==CellaValue[1][1].id && CellaValue[1][1].id==CellaValue[2][1].id) {
      temp=CellaValue[0][1].id;
      temp1=true;
  JOptionPane.showMessageDialog(new JFrame("massage"), "P"+String.valueOf(temp) +" wean!", "Backup problem",
          JOptionPane.ERROR_MESSAGE);
  }
  }
  

  if(CellaValue[0][2]!=null && CellaValue[1][2]!=null && CellaValue[2][2]!=null) {
  if(CellaValue[0][2].id==CellaValue[1][2].id && CellaValue[1][2].id==CellaValue[2][2].id) {
      temp=CellaValue[0][2].id;
      temp1=true;
  JOptionPane.showMessageDialog(new JFrame("massage"), "P"+String.valueOf(temp) +" wean!", "Backup problem",
          JOptionPane.ERROR_MESSAGE);
  }
  }
  

  if(CellaValue[0][0]!=null && CellaValue[1][1]!=null && CellaValue[2][2]!=null) {
  if(CellaValue[0][0].id==CellaValue[1][1].id && CellaValue[1][1].id==CellaValue[2][2].id) {
      temp=CellaValue[0][0].id;
      temp1=true;
  JOptionPane.showMessageDialog(new JFrame("massage"), "P"+String.valueOf(temp) +" wean!", "Backup problem",
          JOptionPane.ERROR_MESSAGE);
  }
  }
  

  if(CellaValue[0][2]!=null && CellaValue[1][1]!=null && CellaValue[2][0]!=null) {
  if(CellaValue[0][2].id==CellaValue[1][1].id && CellaValue[1][1].id==CellaValue[2][0].id) {
      temp=CellaValue[0][2].id;
      temp1=true;
  JOptionPane.showMessageDialog(new JFrame("massage"), "P"+String.valueOf(temp) +" wean!", "Backup problem",
          JOptionPane.ERROR_MESSAGE);
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