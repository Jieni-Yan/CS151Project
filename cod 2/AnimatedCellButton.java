import java.awt.Point;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class AnimatedCellButton {
    
    public AnimatedCellButton(JButton button) {
        shakeCellButton( button) ;
    }
    private void shakeCellButton(JButton button) {
        Point point = button.getLocation();
        int delay = 100;
        Runnable r = new Runnable() {
          @Override
          public void run() {
            for (int i = 0; i < 100; i++) {
              try {
                  
                  
                  SwingUtilities.invokeLater(new Runnable() {
                      @Override
                      public void run() {
                        button.setLocation(new Point(point.x + 3, point.y));
                      }
                    });

                Thread.sleep(delay);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                      button.setLocation(point);
                    }
                  });
                Thread.sleep(delay);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                      button.setLocation(new Point(point.x - 3, point.y));
                    }
                  });
                Thread.sleep(delay);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                      button.setLocation(point);
                    }
                  });
                Thread.sleep(delay);
              } catch (InterruptedException ex) {
                System.out.println("animation error");
              }
            }
          }
        };
        Thread ButtonTrad = new Thread(r);
        ButtonTrad.start();
      }
    
    
}
