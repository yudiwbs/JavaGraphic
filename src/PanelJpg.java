import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PanelJpg {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        //Buat frame
        JFrame f = new JFrame("Coba Hello");
        //exit jika window ditututp
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //tambahkan panel
        f.add(new MyPanelJpg());
        //pack semua komponen dalam panel
        f.pack();
        //set ukuran panel dalam pixel: lebar, tinggi
        f.setSize(500,500);
        f.setVisible(true);
    }
}

//custom panel
class MyPanelJpg extends JPanel {
    private BufferedImage image;
    private String  lokasiFile = "C:\\tmp\\pohon.jpg"; //jpg atau png
    public MyPanelJpg() {
        //border berwarna hitam di panel
        setBorder(BorderFactory.createLineBorder(Color.black));
        try {
            image = ImageIO.read(new File(lokasiFile));
        } catch (IOException ex) {
            // handle exception...
            System.out.println("Error load file");
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    //event yang dipanggil saat Panel di-draw
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //parameter: image, x, y, width, height (scale), observer
        g.drawImage(image, 0, 0, 200,200, this);
    }
}