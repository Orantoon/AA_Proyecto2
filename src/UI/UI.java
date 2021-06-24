package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class UI {
    //Frame and panels
    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    //Images
    private final JLabel bg = new JLabel(new ImageIcon("images/bgUI.png"));
    private final ImageIcon smallB = new ImageIcon("images/smallB.png");
    private final ImageIcon bigB = new ImageIcon("images/bigB.png");
    private final int[] smallSize = new int[]{96,53};
    private final int[] bigSize = new int[]{192,53};

    //Font
    private final Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Font/pixelmix.ttf"));

    //Text
    private final JLabel tNormal = new JLabel();
    private final JLabel tTitle = new JLabel();

    //Variables
    private String paragraph;

    private final int WindowSize = 768;

    public UI() throws IOException, FontFormatException {
        int Width = WindowSize + 14, Height = WindowSize + 35;

        frame.setTitle("Menu");
        frame.setSize(Width, Height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initPanelsFont(Width, Height);

        startScreen();

        frame.setVisible(true);
    }

    public void cleanFrame(){
        frame.getContentPane().removeAll();
        panel.removeAll();
    }

    public void refreshFrame(JLabel bgScreen){
        frame.add(panel);
        frame.add(bgScreen);

        frame.revalidate();
        frame.repaint();
    }

    public void initPanelsFont(int width, int height){
        panel.setBounds(0,0,width, height);
        panel.setLayout(null);
        panel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));

        bg.setBounds(-1,-3, WindowSize, WindowSize);
        bg.setLayout(null);

        tNormal.setFont(font.deriveFont(16f));
        tNormal.setForeground(Color.WHITE);
        tTitle.setFont(font.deriveFont(24f));
        tTitle.setForeground(Color.WHITE);
    }


    public void startScreen(){
        cleanFrame();

        //Text
        tTitle.setText("OCTOPUS ARENA");
        tTitle.setBounds(270,15, 400, 40);

        // / / / / / / / / / / / / / / / / / / / / / / / Action Listeners
        ActionListener EXIT = e -> System.exit(0);
        ActionListener Continue = e -> inscriptionScreen(100); //credits

        //Buttons
        int space = 70;

        JButton bStart = new JButton(smallB);
        bStart.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'> Start </p></html>");
        bStart.setHorizontalTextPosition(SwingConstants.CENTER);
        bStart.setBounds(335,215,smallSize[0],smallSize[1]);
        //bStart.addActionListener();

        JButton bNew = new JButton(bigB);
        bNew.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'> New Session </p></html>");
        bNew.setHorizontalTextPosition(SwingConstants.CENTER);
        bNew.setBounds(335 - smallSize[0]/2,215 + space,bigSize[0],bigSize[1]);
        bNew.addActionListener(Continue);

        JButton bExit = new JButton(smallB);
        bExit.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'> Exit </p></html>");
        bExit.setHorizontalTextPosition(SwingConstants.CENTER);
        bExit.setBounds(335,215 + space*2,smallSize[0],smallSize[1]);
        bExit.addActionListener(EXIT);

        //Add to Panel
        panel.add(tTitle);
        panel.add(bStart);
        panel.add(bNew);
        panel.add(bExit);

        refreshFrame(bg);
    }

    public void inscriptionScreen(int credits){ //2
        cleanFrame();

        //Text
        tTitle.setText("REGISTER");
        tTitle.setBounds(315,15, 400, 40);

        tNormal.setText("Credits: " + credits);
        tNormal.setBounds(600, 15, 300, 30);

        int space = 70;

        //Text Area
        JTextArea textArea = new JTextArea("Write your description here...");
        textArea.setFont(font); textArea.setFont(textArea.getFont().deriveFont(16f));
        textArea.setLineWrap(true); textArea.setWrapStyleWord(true);
        textArea.setBounds(234,100,300,200);


        // / / / / / / / / / / / / / / / / / / / / / / / Action Listeners
        ActionListener Continue = e -> {
            paragraph = textArea.getText();

            if (paragraph.length() <= 10){
                textArea.setText("Your description must be at lest 10 characters long...");
                return;
            }

            String nickname = "John", ticket = "0001A";
            nicknameScreen(credits, nickname, ticket);

        };


        //Buttons
        JButton getText = new JButton(bigB);
        getText.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'> Continue </p></html>");
        getText.setHorizontalTextPosition(SwingConstants.CENTER);
        getText.setBounds(335 - smallSize[0]/2,100 + space*3,bigSize[0],bigSize[1]);
        getText.addActionListener(Continue);


        panel.add(tTitle);
        panel.add(tNormal);
        panel.add(textArea);
        panel.add(getText);

        refreshFrame(bg);
    }

    public void nicknameScreen(int credits, String nickname, String ticket){ //3
        cleanFrame();

        int space = 70;

        //Text
        tTitle.setText("<html>Welcome to the family<br><center>" + nickname + "!</center></html>");
        tTitle.setBounds(200,30, 600, 60);

        JLabel tNick = new JLabel("<html>Nickname:" + nickname + "</html>");
        tNick.setFont(font.deriveFont(16f)); tNick.setForeground(Color.WHITE);
        tNick.setBounds(200, 30+space, 300, 30);

        tNormal.setText("<html>Next Ticket:" + ticket + "</html>");
        tNormal.setBounds(200, 30+(int)(space*1.5), 300, 30);

        JLabel tCredits = new JLabel("Credits: " + credits);
        tCredits.setFont(font.deriveFont(16f)); tCredits.setForeground(Color.WHITE);
        tCredits.setBounds(600, 15, 300, 30);

        // / / / / / / / / / / / / / / / / / / / / / / / Action Listeners
        ActionListener Continue = e -> {
            scrollScreen();
        };

        //Buttons
        JButton bContinue = new JButton(bigB);
        bContinue.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'> Continue </p></html>");
        bContinue.setHorizontalTextPosition(SwingConstants.CENTER);
        bContinue.setBounds(335 - smallSize[0]/2,100 + space*3,bigSize[0],bigSize[1]);
        bContinue.addActionListener(Continue);

        panel.add(tTitle);
        panel.add(tNick);
        panel.add(tNormal);
        panel.add(tCredits);
        panel.add(bContinue);

        refreshFrame(bg);
    }

    public void scrollScreen(){ //4
        cleanFrame();

        //Text

        //Buttons


        refreshFrame(bg);
    }

    public void checkInScreen(){ //5

    }
    
    public void fightResultsScreen(){ //7

    }



    public static void main(String[] args) throws IOException, FontFormatException {
        new UI();
    }

}
