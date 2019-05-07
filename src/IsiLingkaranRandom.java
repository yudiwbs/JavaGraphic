import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//extend JFrame
public class IsiLingkaranRandom extends JFrame {

    private MyPanelRandom panel2;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SwingTambahCustomPanel();
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

        //komponen label, JLabel adalah komponen standard Swing.
        JLabel labelInfo = new JLabel();
        labelInfo.setText("Klik button berikut untuk memperbesar");
        //tambah label ke panel
        btnPanel.add(labelInfo);

        //komponen button, yang juga standard Swing
        JButton btnGambar = new JButton("Perbesar!");
        btnPanel.add(btnGambar);
        //tambah aksi jika tombol ditekan
        btnGambar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                panel2.ukuranLingkaran = 200; //perbesar ukuran
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

    //atribut lingkaran
    public int ukuranLingkaran=75;
    public int posX = 200;
    public int posY = 50;

    public MyPanelRandom() {
        //border berwarna hitam di panel
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(500,200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(posX, posY,  ukuranLingkaran,ukuranLingkaran);
    }
}

