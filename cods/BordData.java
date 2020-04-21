
public class BordData implements Observer{
   
    int[][] CellaValue = new int[3][3];
    private int k=100;
    public BordData() {
        for (int n = 0; n < 3; n++) {
            for(int i = 0; i < 3; i++){
                CellaValue[n][i]=k++;
            }
        }
    }
    
    public boolean CheckWining() {
        boolean temp=false;
        if(CellaValue[0][0]==CellaValue[0][1] && CellaValue[0][1]==CellaValue[0][2])
            temp=true;
        if(CellaValue[1][0]==CellaValue[1][1] && CellaValue[1][1]==CellaValue[1][2])
            temp=true;
        if(CellaValue[2][0]==CellaValue[2][1] && CellaValue[2][1]==CellaValue[2][2])
            temp=true;
        
        if(CellaValue[0][0]==CellaValue[1][0] && CellaValue[1][0]==CellaValue[2][0])
            temp=true;
        if(CellaValue[0][1]==CellaValue[1][1] && CellaValue[1][1]==CellaValue[2][1])
            temp=true;
        if(CellaValue[0][2]==CellaValue[1][2] && CellaValue[1][2]==CellaValue[2][2])
            temp=true;
        
        if(CellaValue[0][0]==CellaValue[1][1] && CellaValue[1][1]==CellaValue[2][2])
            temp=true;
        if(CellaValue[0][2]==CellaValue[1][1] && CellaValue[1][1]==CellaValue[2][0])
            temp=true;
        
        
        return temp;
    }

    @Override
    public void update(int xp, int yp,int event) {
        CellaValue[xp][yp]=event;
        
    }
    
    
    
    
    
    
 
}