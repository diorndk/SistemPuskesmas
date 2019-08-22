import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
 
public class NewClass {
 
  private int alpha = 255;
  private int increment = -5;
  private JLabel label = new JLabel("D:/Dio/PROJEK/gambar/klinik.jpg");
 
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
 
      public void run() {
        new NewClass().makeUI();
      }
    });
  }
 
  public void makeUI() {
    new Timer(80, new ActionListener() {
 
      public void actionPerformed(ActionEvent e) {
        alpha += increment;
        if (alpha >= 255) {
          alpha = 255;
          increment = -increment;
        }
        if (alpha <= 0) {
          alpha = 0;
          increment = -increment;
        }
        label.setForeground(new Color(0, 0, 0, alpha));
      }
    }).start();
 
    JFrame frame = new JFrame();
    frame.add(label);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}