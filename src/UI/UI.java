package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class UI {
    //Frame and panels
    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    //Images
    private final JLabel bg = new JLabel();

    private final ImageIcon normalbg = new ImageIcon("images/bgUI.png");
    private final ImageIcon startbg = new ImageIcon("images/startBG.png");
    private final ImageIcon scrollbg = new ImageIcon("images/scrollBG.png");
    private final ImageIcon regbg = new ImageIcon("images/register.png");
    private final ImageIcon databg = new ImageIcon("images/data.png");
    private final ImageIcon checkbg = new ImageIcon("images/checkin.png");
    private final ImageIcon loginbg = new ImageIcon("images/logInBG.png");
    private final ImageIcon endgamebg = new ImageIcon("images/winBG.png");

    private final ImageIcon smallB = new ImageIcon("images/smallB.png");
    private final ImageIcon bigB = new ImageIcon("images/bigB.png");
    private final int[] smallSize = new int[]{96,53};
    private final int[] bigSize = new int[]{192,53};

    //Font
    private final Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Font/pixelmix.ttf"));

    //Text
    private final JLabel tTitle = new JLabel();
    private final JLabel tNormal = new JLabel();
    private final JLabel tNick = new JLabel();
    private final JLabel tCredits = new JLabel();

    //Variables
    private String paragraph;
    private String nickname;
    private int credits;
    private boolean done = false;

    private final int WindowSize = 768;

    public UI() throws IOException, FontFormatException {
        int Width = WindowSize + 14, Height = WindowSize + 35;

        frame.setTitle("Menu");
        frame.setSize(Width, Height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initPanelsFontImgs(Width, Height);

        startScreen();

        frame.setVisible(true);
    }

    public void cleanFrame(){
        frame.getContentPane().removeAll();
        panel.removeAll();
    }

    public void refreshFrame(ImageIcon imageBG){
        bg.setIcon(imageBG);
        frame.add(panel);
        frame.add(bg);

        frame.revalidate();
        frame.repaint();
    }

    public void initPanelsFontImgs(int width, int height){
        panel.setBounds(0,0,width, height);
        panel.setLayout(null);
        panel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));

        bg.setBounds(-1,-3, WindowSize, WindowSize); bg.setLayout(null);

        tTitle.setFont(font.deriveFont(24f)); tTitle.setForeground(Color.WHITE);
        tNormal.setFont(font.deriveFont(16f)); tNormal.setForeground(Color.WHITE);
        tCredits.setFont(font.deriveFont(16f)); tCredits.setForeground(Color.WHITE);
        tNick.setFont(font.deriveFont(16f)); tNick.setForeground(Color.WHITE);
    }


    public void startScreen(){ //1
        cleanFrame();

        //Text
        tTitle.setText("OCTOPUS ARENA");
        tTitle.setBounds(270,30, 400, 40);

        // / / / / / / / / / / / / / / / / / / / / / / / Action Listeners
        ActionListener EXIT = e -> System.exit(0);
        ActionListener Register = e -> registerScreen();
        ActionListener LogIn = e -> logInScreen();

        //Buttons
        int space = 70;

        JButton bLogIn = new JButton(bigB);
        bLogIn.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'> Log In </p></html>");
        bLogIn.setHorizontalTextPosition(SwingConstants.CENTER);
        bLogIn.setBounds(335 - smallSize[0]/2,215,bigSize[0],bigSize[1]);
        bLogIn.addActionListener(LogIn);

        JButton bReg = new JButton(bigB);
        bReg.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'> Register </p></html>");
        bReg.setHorizontalTextPosition(SwingConstants.CENTER);
        bReg.setBounds(335 - smallSize[0]/2,215 + space,bigSize[0],bigSize[1]);
        bReg.addActionListener(Register);

        JButton bExit = new JButton(smallB);
        bExit.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'> Exit </p></html>");
        bExit.setHorizontalTextPosition(SwingConstants.CENTER);
        bExit.setBounds(335,215 + space*2,smallSize[0],smallSize[1]);
        bExit.addActionListener(EXIT);

        //Add to Panel
        panel.add(tTitle);
        panel.add(bLogIn);
        panel.add(bReg);
        panel.add(bExit);

        refreshFrame(startbg);
    }

    public void logInScreen(){
        cleanFrame();

        int space = 70;
        //Data

        //Text
        tTitle.setText("LOG IN");
        tTitle.setBounds(325,30, 400, 40);

        //Text Field
        JTextField getNick = new JTextField("Write your username here...");
        getNick.setFont(font); getNick.setFont(getNick.getFont().deriveFont(12f));
        getNick.setBounds(234,120+space*2,300,30);

        // / / / / / / / / / / / / / / / / / / / / / / / Action Listeners
        ActionListener Continue = e -> {
            //gets the data of the user -> nickname, credits
            //if username incorrect ->
            //getNick.setText("Incorrect Username, Try Again...");
            //else
            scrollScreen();
        };

        //Buttons
        JButton bBack = new JButton(bigB);
        bBack.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'> Back </p></html>");
        bBack.setHorizontalTextPosition(SwingConstants.CENTER);
        bBack.setBounds(198,120 + space*3,bigSize[0],bigSize[1]);
        bBack.addActionListener(Start);

        JButton getText = new JButton(bigB);
        getText.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'> Continue </p></html>");
        getText.setHorizontalTextPosition(SwingConstants.CENTER);
        getText.setBounds(335 + 335/3 - smallSize[0]/2,120 + space*3,bigSize[0],bigSize[1]);
        getText.addActionListener(Continue);

        //Panel
        panel.add(getNick);
        panel.add(tTitle);
        panel.add(bBack);
        panel.add(getText);

        refreshFrame(loginbg);
    }

    public void registerScreen(){ //2
        cleanFrame();

        //Data
        credits = 100;

        //Text
        tTitle.setText("REGISTER");
        tTitle.setBounds(315,30, 400, 40);

        tCredits.setText("Credits: " + credits);
        tCredits.setBounds(600, 15, 300, 30);

        int space = 70;

        //Text Area
        JTextArea textArea = new JTextArea("Write your description here...");
        textArea.setFont(font); textArea.setFont(textArea.getFont().deriveFont(16f));
        textArea.setLineWrap(true); textArea.setWrapStyleWord(true);
        textArea.setBounds(234,120,300,200);


        // / / / / / / / / / / / / / / / / / / / / / / / Action Listeners
        ActionListener Continue = e -> {
            paragraph = textArea.getText();

            if (paragraph.length() <= 10){
                textArea.setText("Your description must be at lest 10 characters long...");
                return;
            }

            nickname = "John"; String ticket = "0001A";
            nicknameScreen(credits, ticket);

        };

        //Buttons
        JButton bBack = new JButton(bigB);
        bBack.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'> Back </p></html>");
        bBack.setHorizontalTextPosition(SwingConstants.CENTER);
        bBack.setBounds(198,120 + space*3,bigSize[0],bigSize[1]);
        bBack.addActionListener(Start);

        JButton getText = new JButton(bigB);
        getText.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'> Continue </p></html>");
        getText.setHorizontalTextPosition(SwingConstants.CENTER);
        getText.setBounds(335 + 335/3 - smallSize[0]/2,120 + space*3,bigSize[0],bigSize[1]);
        getText.addActionListener(Continue);

        //Panel
        panel.add(tTitle);
        panel.add(tCredits);
        panel.add(textArea);
        panel.add(getText);
        panel.add(bBack);

        refreshFrame(regbg);
    }

    public void nicknameScreen(int credits, String ticket){ //3
        cleanFrame();

        int space = 70;

        //Text
        tTitle.setText("<html>Welcome to the Octopi<br><center>" + nickname + "!</center></html>");
        tTitle.setBounds(200,30, 600, 60);

        tNick.setText("<html>Nickname: " + nickname + "</html>");
        tNick.setBounds(20, 15, 300, 30);

        tNormal.setText("<html>Next Ticket:" + ticket + "</html>");
        tNormal.setBounds(200, 30+(int)(space*1.5), 300, 30);

        tCredits.setText("Credits: " + credits); //A must if it's loading an existing user

        // / / / / / / / / / / / / / / / / / / / / / / / Action Listeners
        ActionListener Continue = e -> scrollScreen();

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

        refreshFrame(databg);
    }

    public void scrollScreen(){ //4
        cleanFrame();

        //Data
        credits = 100;
        int price = 0;
        long time = 1;
        // numOfPlayers

        int space = 70;

        //Text
        tTitle.setText("Scroll Through Arenas");
        tTitle.setBounds(200,30, 600, 60);

        tCredits.setText("Credits: " + credits); //Must Because it can change

        JLabel tPrice = new JLabel("<html>Price: " + price +"</html>");
        tPrice.setFont(font.deriveFont(16f)); tPrice.setForeground(Color.WHITE);
        tPrice.setBounds(200, 30 + (int) (space*1.5), 300, 30);

        JLabel tCounter = new JLabel("<html>Time Remaining: " + time +"</html>");
        tCounter.setFont(font.deriveFont(16f)); tCounter.setForeground(Color.WHITE);
        tCounter.setBounds(200, 30+space*2, 300, 30);

        char symbol = (done)? '✓': 'X';
        JLabel tCheckIn = new JLabel("<html>Check-in done: " + symbol +"</html>");
        tCheckIn.setFont(font.deriveFont(16f)); tCheckIn.setForeground(Color.WHITE);
        tCheckIn.setBounds(200, 30+(int) (space*2.5), 300, 30);



        // / / / / / / / / / / / / / / / / / / / / / / / Action Listeners
        ActionListener Check_In = e -> checkInScreen();

        //Buttons
        JButton bCheckIn = new JButton(bigB);
        bCheckIn.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'>Check In</p></html>");
        bCheckIn.setHorizontalTextPosition(SwingConstants.CENTER);
        bCheckIn.setBounds(335 - smallSize[0]/2,215 + space*2,bigSize[0],bigSize[1]);
        bCheckIn.addActionListener(Check_In);

        bCheckIn.setEnabled(!done);

        JButton bBack = new JButton(bigB);
        bBack.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'> Back </p></html>");
        bBack.setHorizontalTextPosition(SwingConstants.CENTER);
        bBack.setBounds(335 - smallSize[0]/2,215 + space*3,bigSize[0],bigSize[1]);
        bBack.addActionListener(Start);

        //bBack.setEnabled(!done);

        // / / / / / / / / / / / / / / / / / / / / / / / Mouse Listener -- SCROLL
        MouseWheelListener scroll = e -> System.out.println(e.getWheelRotation()); //-1 UP <> 1 DOWN --> Use to move around the graph


        // / / / / / / / / / / / / / / / / / / / / / / / Start Fight -- Time == 0 -- Time = FIGHTTIME - CURRENTTIME


        //Panel
        panel.add(tTitle);
        panel.add(tNick);
        panel.add(tCredits);
        panel.add(tPrice);
        panel.add(tCounter);
        panel.add(tCheckIn);
        panel.addMouseWheelListener(scroll);
        panel.add(bCheckIn);
        panel.add(bBack);

        refreshFrame(scrollbg);
    }

    public void checkInScreen(){ //5
        cleanFrame(); //Cancel button

        //Data

        int space = 70;

        //Text
        tTitle.setText("Check In");
        tTitle.setBounds(300,30, 600, 60);

        JLabel tEnergy = new JLabel();
        tEnergy.setFont(font.deriveFont(16f)); tEnergy.setForeground(Color.WHITE);
        tEnergy.setText("Select the Energy for your Octopus");
        tEnergy.setBounds(200, 100, 400, 30);

        tNormal.setText("Select an Amount: ");
        tNormal.setBounds(200,210, 300, 30);

        //Slider -- Energy, from 20 to 100
        JSlider energySlider = new JSlider(20, 100, 60);

        energySlider.setMajorTickSpacing(20);
        energySlider.setMinorTickSpacing(5);

        energySlider.setPaintTicks(true);
        energySlider.setPaintLabels(true);
        energySlider.setSnapToTicks(true);

        energySlider.setFont(font); energySlider.setFont(energySlider.getFont().deriveFont(12f));
        energySlider.setForeground(Color.BLACK);

        energySlider.setBounds(234, 140, 300, 45);

        //ComboBox -- Amount
        String[] dollars = {"$10", "$25", "$50", "$100", "$500", "$1000"};

        JComboBox<String> amountBox = new JComboBox<>(dollars);
        amountBox.setBounds(400, 210, 100, 30);


        // / / / / / / / / / / / / / / / / / / / / / / / Action Listeners
        ActionListener Back = e -> scrollScreen();

        ActionListener CheckIn = e -> {
            done = true;
            System.out.println(energySlider.getValue());
            System.out.println(amountBox.getSelectedIndex() + 1); //
            scrollScreen();
        };


        //Buttons
        JButton bCheckIn = new JButton(bigB);
        bCheckIn.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'>Check In</p></html>");
        bCheckIn.setHorizontalTextPosition(SwingConstants.CENTER);
        bCheckIn.setBounds(335 - smallSize[0]/2,100 + space*3,bigSize[0],bigSize[1]);
        bCheckIn.addActionListener(CheckIn);

        JButton bBack = new JButton(bigB);
        bBack.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'> Back </p></html>");
        bBack.setHorizontalTextPosition(SwingConstants.CENTER);
        bBack.setBounds(335 - smallSize[0]/2,100 + space*4,bigSize[0],bigSize[1]);
        bBack.addActionListener(Back);

        //Panel
        panel.add(bCheckIn);
        panel.add(bBack);
        panel.add(tTitle);
        panel.add(tNormal);
        panel.add(tEnergy);
        panel.add(tNick);
        panel.add(tCredits);
        panel.add(amountBox);
        panel.add(energySlider);

        refreshFrame(checkbg);
    }

    public void fightResultsScreen(){ //7
        cleanFrame();


        refreshFrame(endgamebg);
    }

    ActionListener Start = e -> startScreen(); //Goes to the start


    public static void main(String[] args) throws IOException, FontFormatException {
        new UI();
    }

}

/*
Price? In arenas
Number of Players?

done/check in confirmation for each user
Vector of Octopus for each arena

 */