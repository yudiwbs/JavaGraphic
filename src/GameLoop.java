import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;


public class GameLoop extends JFrame {

    private MyPanelLoop panelLoop;
    private boolean running;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameLoop();  //init
            }
        });
    }

    //constructor
    public GameLoop() {
        setTitle("Swing plus Custom Panel");

        //panel tempat lingkaran digambar
        panelLoop = new MyPanelLoop();
        panelLoop.setPreferredSize(new Dimension(500, 200));

        //panel yg berisi button
        //menggunakan flow layout, artinya komponen akan diletakan dari kiri kanan lalu atas ke bawah
        //swing menyediakan banyak layout lain: GridLayout, BoxLayout
        JPanel btnPanel = new JPanel(new FlowLayout());
        //komponen button, yang juga standard Swing
        JButton btnGambar = new JButton("Stop");
        btnPanel.add(btnGambar);
        //tambah aksi jika tombol ditekan
        btnGambar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                running = false; //
            }
        });

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        //tambah panel2 di tengah
        cp.add(panelLoop, BorderLayout.CENTER);
        //tambah panel berisi button di bagian bawah (selatan)
        cp.add(btnPanel, BorderLayout.PAGE_END);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        requestFocus();

        //loop forever
        long time = System.currentTimeMillis();
        running = true;
        while (running) {
            panelLoop.update();
            panelLoop.repaint();
            long sleep = 16 - (System.currentTimeMillis() - time);
            time += 16;
            try {
                Thread.sleep(10);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}

//custom panel
class MyPanelLoop extends JPanel {

    //atribut lingkaran
    public int ukuranLingkaran=75;
    public int posX = 200;
    public int posY = 50;

    public MyPanelLoop() {
        //border berwarna hitam di panel
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void update() {
        posX = posX + 10;
        if (posX > 30) {
            posX = 0;
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    //event yang dipanggil saat Panel di-draw
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(posX, posY,  ukuranLingkaran,ukuranLingkaran);
    }
}