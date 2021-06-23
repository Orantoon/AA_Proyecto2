package UI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UI {
    //Frame and panels
    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    //Images
    private final JLabel bg = new JLabel(new ImageIcon("images/uibg.png"));
    private final ImageIcon smallB = new ImageIcon("images/button3.png");


    //Font
    private final Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Font/pixelmix.ttf"));

    //Text
    private final JLabel[] tNormal = new JLabel[10];
    private final JLabel[] tShadow = new JLabel[10];

    private final int WindowSize = 768;

    public UI() throws IOException, FontFormatException {
        int Width = WindowSize + 14, Height = WindowSize + 35;

        frame.setTitle("Menu");
        frame.setSize(Width, Height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initPanels(Width, Height);

        //frame.add(bg);

        start();
        //updateFrame???

        frame.setVisible(true);
    }

    public void cleanFrame(){
        frame.getContentPane().removeAll();
        panel.removeAll();
    }

    public void refreshFrame(){
        frame.revalidate();
        frame.repaint();
    }

    public void initPanels(int width, int height){
        panel.setBounds(0,0,width, height);
        panel.setLayout(null);
        panel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));

        bg.setBounds(-1,-3, WindowSize, WindowSize);
        bg.setLayout(null);
    }

    public void start(){
        cleanFrame();

        //Start things


        JButton bStart = new JButton(smallB);
        bStart.setText("<html><p style='text-align:top' color='white' size='+2' face='pixelmix Regular'> Start </p></html>");

        //bStart.setVerticalTextPosition(); //SwingConstants.CENTER
        bStart.setHorizontalTextPosition(SwingConstants.CENTER);
        //bStart.setText("Start");
        bStart.setBounds(302,220,96,53);
        panel.add(bStart);

        frame.add(panel);
        frame.add(bg);

        refreshFrame();
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        new UI();
    }

}
