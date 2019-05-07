import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.BorderLayout.PAGE_START;


//extend JFrame
public class CobaAnimasi extends JFrame {

    private MyPanel2 panel2;

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
        panel2 = new MyPanel2();
        panel2.setPreferredSize(new Dimension(300, 100));


        //panel yg berisi textbox untuk mengatur ukuran lingkaran
        //flowlayout artinya komponen diletakan dari kiri ke kanan
        //banyak layout yg lain: gridlayout, boxlayout dsb silakan digoogling
        JPanel ukuranPanel = new JPanel(new FlowLayout());
        //komponen label
        JLabel labelSetUkuran = new JLabel();
        labelSetUkuran.setText("Ukuran Lingkaran?");
        //tambah label ke panel
        ukuranPanel.add(labelSetUkuran);
        //textfield untuk menerima input
        JTextField fieldUkuranLingkaran = new JTextField(5);
        //add ke panel, karena layoutnya flow, maka akan ditambah di sebelah kanan label
        fieldUkuranLingkaran.setText(Integer.toString(panel2.ukuranLingkaran)); //set dengan value awal
        ukuranPanel.add(fieldUkuranLingkaran);

        //button yang jika ditekan akan mengubah ukuran lingkaran
        JButton btnSetUkuran = new JButton("Set Ukuran");
        ukuranPanel.add(btnSetUkuran);

        //event yang akan dipanggil saat button diklik
        btnSetUkuran.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //ambil dari field isian, convert ke int
                panel2.ukuranLingkaran = Integer.valueOf(fieldUkuranLingkaran.getText());
                panel2.repaint(); //redraw panel
                requestFocus(); // change the focus to JFrame to receive KeyEvent
            }
        });



        //=============

        //panel yg berisi button untuk menggerakan ke kiri dan kanan
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnLeft = new JButton("Geser Kiri");
        btnPanel.add(btnLeft);
        JButton btnRight = new JButton("Geser Kanan");
        btnPanel.add(btnRight);
        JLabel labelInfo = new JLabel();
        labelInfo.setText("Anda juga bisa gunakan panah kiri dan kanan");
        btnPanel.add(labelInfo);


        //tombol kiri di tekan, geser posisi
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                panel2.posX = panel2.posX - 10;
                panel2.repaint(); //redraw panel
                requestFocus();  // change the focus to JFrame to receive KeyEvent
            }
        });


        //tombol kiri di tekan, geser posisi
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                panel2.posX = panel2.posX + 10;
                panel2.repaint(); //redraw panel
                requestFocus();  // change the focus to JFrame to receive KeyEvent
            }
        });


        // penangan jika panah ditekan..
        // "super" JFrame fires KeyEvent
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                switch(evt.getKeyCode()) {
                    //panah kiri
                    case KeyEvent.VK_LEFT:
                        panel2.posX = panel2.posX - 10; //harusnya jangan duplikasi dgn button sih.
                        repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        panel2.posX = panel2.posX + 10;
                        repaint();
                        break;
                }
            }
        });



        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        //tambah panel2 di tengah
        cp.add(panel2, BorderLayout.CENTER);
        //tambah panel berisi button di bagian bawah (selatan)

        cp.add(btnPanel, BorderLayout.PAGE_END);
        cp.add(ukuranPanel, BorderLayout.PAGE_START);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        requestFocus();
    }
}

class MyPanel2 extends JPanel {

    //atribut lingkaran
    public int ukuranLingkaran=75;
    public int posX = 350;
    public int posY = 50;

    public MyPanel2() {
        //border berwarna hitam di panel
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(1000,200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        // koordinat 0,0 ada di kiri atas
        // Draw Text
        g.drawString("Hello, world!",10,20);
        // x kiri atas, y kiri atas, lebar, tinggi  (jika lebar==tinggi artinya lingkaran)
        g.fillOval(posX, posY,  ukuranLingkaran,ukuranLingkaran);

    }
}