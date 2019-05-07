import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//extend JFrame
public class CobaAnimasi extends JFrame {

    private MyPanel3 panel3;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CobaAnimasi();
            }
        });
    }


    public CobaAnimasi() {
        setTitle("Judul");

        //panel tempat lingkaran di gambar
        panel3 = new MyPanel3();
        panel3.setPreferredSize(new Dimension(300, 100));


        //panel yg berisi textbox untuk mengatur ukuran lingkaran
        JPanel ukuranPanel = new JPanel(new FlowLayout());
        //komponen label
        JLabel labelSetUkuran = new JLabel();
        labelSetUkuran.setText("Ukuran Lingkaran?");
        ukuranPanel.add(labelSetUkuran);

        //textfield untuk menerima input
        JTextField fieldUkuranLingkaran = new JTextField(5);
        fieldUkuranLingkaran.setText(Integer.toString(panel3.ukuranLingkaran)); //set dengan value awal
        ukuranPanel.add(fieldUkuranLingkaran);

        //button yang jika ditekan akan mengubah ukuran lingkaran
        JButton btnSetUkuran = new JButton("Set Ukuran");
        ukuranPanel.add(btnSetUkuran);

        //event yang akan dipanggil saat button diklik, nilai yg diinput user dimasukkan ke panel
        //refresh untuk menggambar lingkaran yg baru
        btnSetUkuran.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //ambil dari field isian, convert ke int
                panel3.ukuranLingkaran = Integer.valueOf(fieldUkuranLingkaran.getText());
                panel3.repaint(); //redraw panel
                requestFocus(); // change the focus to JFrame to receive KeyEvent
            }
        });


        //panel button
        //panel yg berisi button untuk menggerakan ke kiri dan kanan
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnLeft = new JButton("Geser Kiri");
        btnPanel.add(btnLeft);
        JButton btnRight = new JButton("Geser Kanan");
        btnPanel.add(btnRight);
        JLabel labelInfo = new JLabel();
        labelInfo.setText("Anda juga bisa gunakan panah kiri dan kanan");
        btnPanel.add(labelInfo);

        // event saat button ditekan
        //tombol kiri di tekan, geser posisi
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                panel3.posX = panel3.posX - 10;
                panel3.repaint(); //redraw panel
                requestFocus();  // change the focus to JFrame to receive KeyEvent
            }
        });


        //tombol kiri di tekan, geser posisi
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                panel3.posX = panel3.posX + 10;
                panel3.repaint(); //redraw panel
                requestFocus();  // change the focus to JFrame to receive KeyEvent
            }
        });


        // penangan jika panah di keyboard ditekan..
        // "super" JFrame fires KeyEvent
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                switch(evt.getKeyCode()) {
                    //panah kiri
                    case KeyEvent.VK_LEFT:
                        panel3.posX = panel3.posX - 10; //masih duplikasi dgn button sih.
                        repaint();
                        break;
                    //panah kanan
                    case KeyEvent.VK_RIGHT:
                        panel3.posX = panel3.posX + 10;
                        repaint();
                        break;
                }
            }
        });

        //tambah panel ke frame
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        //tambah panel2 di tengah
        cp.add(panel3, BorderLayout.CENTER);
        //tambah panel berisi button di bagian bawah (selatan)
        cp.add(btnPanel, BorderLayout.PAGE_END);
        cp.add(ukuranPanel, BorderLayout.PAGE_START);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        requestFocus();
    }
}

class MyPanel3 extends JPanel {
    //atribut lingkaran
    public int ukuranLingkaran=75;
    public int posX = 350;
    public int posY = 50;

    public MyPanel3() {
        //border berwarna hitam di panel
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(1000,200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(posX, posY,  ukuranLingkaran,ukuranLingkaran);

    }
}