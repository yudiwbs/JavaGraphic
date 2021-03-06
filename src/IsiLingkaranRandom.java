import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//extend JFrame
public class IsiLingkaranRandom extends JFrame {

    private MyPanelRandom panel2;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new IsiLingkaranRandom();
            }
        });
    }

    //constructor
    public IsiLingkaranRandom() {
        setTitle("Swing plus Custom Panel");

        //panel tempat lingkaran digambar
        panel2 = new MyPanelRandom();
        panel2.setPreferredSize(new Dimension(500, 200));


        //=============

        //panel yg berisi button
        //menggunakan flow layout, artinya komponen akan diletakan dari kiri kanan lalu atas ke bawah
        //swing menyediakan banyak layout lain: GridLayout, BoxLayout
        JPanel btnPanel = new JPanel(new FlowLayout());

        //komponen button, yang juga standard Swing
        JButton btnGambar = new JButton("Tambah Lingkaran!");
        btnPanel.add(btnGambar);
        //tambah aksi jika tombol ditekan
        btnGambar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                panel2.tambahLingkaran();
                panel2.repaint(); // gambar ulang panel
                requestFocus();
            }
        });

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        //tambah panel2 di tengah
        cp.add(panel2, BorderLayout.CENTER);
        //tambah panel berisi button di bagian bawah (selatan)
        cp.add(btnPanel, BorderLayout.PAGE_END);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        requestFocus();
    }
}

//custom panel untuk menggambar lingkaran
class MyPanelRandom extends JPanel {

    ArrayList<Lingkaran>  alLingkaran = new ArrayList();

    public MyPanelRandom() {
        //border berwarna hitam di panel
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(500,200);
    }

    public void tambahLingkaran() {
        int maxX = 400;
        int maxY = 180;
        Lingkaran  lk = new Lingkaran();
        lk.posX = (int)(Math.random() * ((maxX - 0) + 1)) + 0;
        lk.posY = (int)(Math.random() * ((maxY - 0) + 1)) + 0;
        alLingkaran.add(lk);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        for (Lingkaran l:alLingkaran) {
            g.fillOval(l.posX, l.posY, l.ukuranLingkaran, l.ukuranLingkaran);
        }
    }
}


class Lingkaran {
    //atribut lingkaran
    public static int ukuranLingkaran = 20;
    public int posX;
    public int posY;

}

