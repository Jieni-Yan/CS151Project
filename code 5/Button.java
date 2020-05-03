import javax.swing.*;
import java.awt.*;
/**
 * this class creates a button with shaking property
 */
public class Button {
    public JButton button;
    public String buttonName;
    public int shakingTime = 0;
    /**
     * a contractor to create a new JButton
     * @param str the value of button
     */
    public Button(String str) {
        buttonName = str;
        button = new JButton(buttonName);
    }
    /**
     *  a contractor to change current button name
     * @param b a JButton sours of the update
     */
    public Button(JButton b) {
        this.button = b;
        this.buttonName = b.getName();
    }

    /**
     * accessors method 
     * @return button ,which is JButton
     */
    public JButton getButton() {
        return button;
    }

    /**
     * this method creates a thread to shake the button wit a delay of 75.
     */
    public void shakeButton() {
        final Point point = button.getLocation();
        final int delay = 75;
        /**
         *  Runnable interface which implemented to be executed by a thread.
         */
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < shakingTime; i++) {
                    try {

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                button.setLocation(new Point(point.x + 5, point.y));
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
                                button.setLocation(new Point(point.x - 5, point.y));
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
                        ex.printStackTrace();
                    }
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

}
