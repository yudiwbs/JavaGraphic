import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;


public class CobaDraw {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {

        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new PanelDraw());
        //pack semua komponen dalam panel
        f.pack();
        //set ukuran panel dalam pixel: lebar, tinggi
        f.setSize(500,500);
        f.setVisible(true);
    }
}

class PanelDraw extends JPanel {

    public PanelDraw() {
        //border berwarna hitam di panel
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(1000,200);
    }

    //event yang dipanggil saat Panel di-draw
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // koordinat 0,0 ada di kiri atas
        // Draw Text
        g.drawString("Hello, world!",10,20);

        //set font
        g.setFont(new Font("Monospaced", Font.BOLD, 15));
        g.drawString("Hello, world 2!",100,20);

        // Draw line: x1,y1 ke x2,y2
        g.drawLine(30,30,70,70);

        //set warna
        g.setColor(Color.RED);

        //multi garis, buat bentuk caret ^
        int arrX[] = {40,50,60};  // koordinat X
        int arrY[] = {70,60,70};  // koordinat Y
        //parameter terakhir adalah jumlah point yg mau digunakan, bisa lebih sedikit dari jumlah elemen array
        g.drawPolyline(arrX, arrY, 3);


        // polygon berdasarkan titik, titik terakhir disambungkan dengan titik awal
        // bandingkan dengan drawPolyline
        int arrX2[] = {90,100,110};  // koordinat X
        int arrY2[] = {70,60,70};  // koordinat Y
        g.drawPolygon(arrX2,arrY2,3);

        // kotak
        // x kiri atas, y kiri atas, lebar, tinggi
        g.drawRect(10,100,100,50);

        //kotak dengan sudut yang melengkung.
        // dua parameter terakhir adalah lebar dan tinggi arch
        g.drawRoundRect(150, 100, 100, 100, 20, 20);


        g.setColor(Color.BLUE);

        // oval
        // x kiri atas, y kiri atas, lebar, tinggi  (jika lebar==tinggi artinya lingkaran)
        g.drawOval(10, 170, 20, 30);

        // lengkung (arc)
        // x kiri atas, y kiri atas, lebar, tinggi, sudut awal, sudut akhir
        g.drawArc(10,210, 30,30,90,180);

        //kotak dengan isi
        //jika ingin border dengan warna berbeda dengan isi, harus menggunakan drawrect+fillrect
        g.fillRect(10,250,100,50);

        //method lainnya: fillOval, fillArc, fillRoundRect, fillPolygon sama

        //set warna dengan RGB (red, green, blue)
        g.setColor(new Color(123, 111, 222));

        //rect dengan efek 3D
        g.fill3DRect(10,350,100,80,true);

    }
}
