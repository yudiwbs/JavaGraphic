import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;


public class CobaSwing {

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
        f.add(new MyPanel());
        //pack semua komponen dalam panel
        f.pack();
        //set ukuran panel dalam pixel: lebar, tinggi
        f.setSize(500,500);
        f.setVisible(true);
    }
}

//custom panel
class MyPanel extends JPanel {

    public MyPanel() {
        //border berwarna hitam di panel
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    //event yang dipanggil saat Panel di-draw
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // koordinat 0,0 ada di kiri atas
        // Draw Text
        g.drawString("Hello, world!",10,20);
    }
}