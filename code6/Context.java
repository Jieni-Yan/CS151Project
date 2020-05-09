import javax.swing.JButton;

public class Context {

    private int xp;
    private int yp;
    private String value;

    public AnimatableButtonCreator executeStrategy(AnimatableButtonCreator strategy){
        strategy.createButton( xp,  yp, value);
       return strategy;
    }
}
