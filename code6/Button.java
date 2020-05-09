import javax.swing.*;
import java.awt.*;

public class Button {
    public JButton button;
    public String buttonName;
    public int shakingTime = 0;

    public Button(String str) {
        buttonName = str;
        button = new JButton(buttonName);
    }

    public JButton getButton() {
        return button;
    }

    public Button(JButton b) {
        this.button = b;
        this.buttonName = b.getName();
    }

    public void shakeButton() {
        final Point point = button.getLocation();
        final int delay = 75;
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
