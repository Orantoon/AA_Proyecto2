package UI;

import GameLogic.Game;
import GameLogic.Octopus;
import Graphs.Graph;
import Graphs.ScrollList;
import Players.Player;
import Players.Ticket;
import Utils.Clock;
import Files.PlayersFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Vector;

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
    private Player player = null;
    private Graph graph;
    private ScrollList scrollList = null;
    private int ticketDate = 1;

    private boolean gameDone;
    private Game g;
    private final PlayersFile playersFile = new PlayersFile();
    private boolean fromLogIn = false;

    private final int WindowSize = 768;

    public UI() throws IOException, FontFormatException {
        graph = new Graph();    // Graph is created and connected

        int Width = WindowSize + 14, Height = WindowSize + 35;

        frame.setTitle("Octopus Arena");
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

        gameDone = false;

        //Text
        tTitle.setText("OCTOPUS ARENA");
        tTitle.setBounds(270, 30, 400, 40);

        // / / / / / / / / / / / / / / / / / / / / / / / Action Listeners
        ActionListener EXIT = e -> System.exit(0);
        ActionListener Register = e -> {
            paragraphScreen();
        };
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
        ActionListener Start = e -> startScreen();

        ActionListener Continue = e -> {
            String nickname = getNick.getText();
            int line = 0;

            try { line = playersFile.existingPlayer(nickname); }
            catch (FileNotFoundException fileNotFoundException) { fileNotFoundException.printStackTrace(); }

            if (line == -1){
                getNick.setText("Incorrect Username, Try Again...");
                return;
            }


            try { player = playersFile.getPlayer(nickname, playersFile.existingPlayer(nickname)); }
            catch (FileNotFoundException fileNotFoundException) { fileNotFoundException.printStackTrace(); }

            //player = findplayer();
            //gets the data of the player -> nickname, credits
            //if username incorrect ->
            //
            //else
            fromLogIn = true;
            paragraphScreen();
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

    public void paragraphScreen() { //2
        cleanFrame();

        int space = 70;

        //Data
        if (player == null || !fromLogIn){
            player = new Player();
            fromLogIn = false;
            // new random nickname
        }

        player.checkInReady = false;

        //Text
        tTitle.setText("PARAGRAPH");
        tTitle.setBounds(315,30, 400, 40);

        //Paragraph specifications
        tNormal.setText("<html><center>*Al describir la cantidad de jugadores contra los cuales le<br>gustaria jugar, " +
                "por favor indiquelo despues de ingresar el<br>numero, igualmente con la cantidad de creditos/dolares" +
                "<br>que le gustaria apostar." +
                "<br><br>Por ejemplo:<br>Me gustaria jugar contra 7 pulpos y apostar 20 dolares." +
                "</center></html>");
        tNormal.setBounds(50, 20+space*3, 900, 500);

        tCredits.setText("Credits: " + player.credits);
        tCredits.setBounds(560, 15, 300, 30);

        //Text Area
        JTextArea textArea = new JTextArea("Write your description here...");
        textArea.setFont(font); textArea.setFont(textArea.getFont().deriveFont(16f));
        textArea.setLineWrap(true); textArea.setWrapStyleWord(true);
        textArea.setBounds(234,120,300,200);


        // / / / / / / / / / / / / / / / / / / / / / / / Action Listeners
        ActionListener Start = e -> startScreen();

        ActionListener Continue = e -> {
            player.inscription = textArea.getText();

            if (player.inscription.length() <= 10){
                textArea.setText("Your description must be at lest 10 characters long...");
                return;
            }

            // Player Inscription (Fight Size & Bet Price) ==========
            player.readIns();
            if (player.fightSize == -1 || player.betPrice == -1){
                textArea.setText("Please write clearly what you want...");
                return;
            }

            // New Ticket ==========
            int count = 0;
            while (!graph.nodeList.isDate(ticketDate)){
                if (count > 7)  // No Arenas Available at all
                    return;

                if (ticketDate == 7)
                    ticketDate = 1;
                else
                    ticketDate += 1;
                count += 1;
            }
            player.ticket = new Ticket(ticketDate);
            if (ticketDate == 7)
                ticketDate = 1;
            else
                ticketDate += 1;

            //player.ticket.print();

            //player.ticket.date = new int[]{28,6,2021}; /////////////////////////////////////////////////////////////////
            //player.ticket.time = new int[]{4,23,0};

            // New Nickname ==========
            if (player.nickname == null) {
                try {
                    player.randNickname();

                    // Validating repeated nicknames
                    while (playersFile.existingPlayer(player.nickname) != -1){
                        player.randNickname();
                    }

                    playersFile.addNewPlayer(player);
                    player.credits = (float) 1000.00;

                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            } //else {
              //  player.credits = (float) 1000.00;
            //}


            // New Arena ==========
            try {
                graph.playerToNode(player);     // Player in Arena
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }

            if (player.arena == null){
                System.out.println("No Arena for that date");
                return;
            }

            scrollList = new ScrollList(player.arena, graph.nodeList);  // New Scroll List

            nicknameScreen();

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
        panel.add(tNormal);
        panel.add(textArea);
        panel.add(getText);
        panel.add(bBack);


        refreshFrame(regbg);
    }

    public void nicknameScreen(){ //3
        cleanFrame();

        int space = 70;

        //Text
        tTitle.setText("<html>Welcome to the Octopi<br><center>" + player.nickname + "!</center></html>");
        tTitle.setBounds(200,60, 600, 60);

        tNick.setText("<html>Nickname: " + player.nickname + "</html>");
        tNick.setBounds(20, 15, 600, 30);

        tNormal.setText("<html>Fight Date: " + player.ticket.getDate() + "</html>");
        tNormal.setBounds(200, 30+(int)(space*2.5), 310, 30);

        tCredits.setText("Credits: " + player.credits); //A must if it's loading an existing user

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
        int space = 70;
        Clock clock = new Clock();

        //Text
        tTitle.setText("Scroll Through Arenas");
        tTitle.setBounds(200,30, 600, 60);

        tCredits.setText("Credits: " + player.credits); //Must Because it can change

        JLabel tPrice = new JLabel("<html>Price: " + scrollList.currentNode.betPrice +"</html>");
        tPrice.setFont(font.deriveFont(16f)); tPrice.setForeground(Color.WHITE);
        tPrice.setBounds(200, 30 + (int) (space*1.5), 300, 30);

        JLabel tCounter = new JLabel("<html>Time Remaining: " + clock.timeLeft(player.ticket.date, player.ticket.time) +"</html>");
        tCounter.setFont(font.deriveFont(16f)); tCounter.setForeground(Color.WHITE);
        tCounter.setBounds(200, 30+space*2, 600, 30);

        JLabel tTotal = new JLabel("<html>Total Players: " + scrollList.currentNode.total +"</html>");
        tTotal.setFont(font.deriveFont(16f)); tTotal.setForeground(Color.WHITE);
        tTotal.setBounds(200, 30+(int) (space*2.5), 300, 30);

        JLabel tReady = new JLabel("<html>Players Ready: " + scrollList.currentNode.readyPlayers +"</html>");
        tReady.setFont(font.deriveFont(16f)); tReady.setForeground(Color.WHITE);
        tReady.setBounds(200, 30+space*3, 300, 30);

        char symbol = (player.checkInReady)? '✓': 'X';
        JLabel tCheckIn = new JLabel("<html>Check-in done: " + symbol +"</html>");
        tCheckIn.setFont(font.deriveFont(16f)); tCheckIn.setForeground(Color.WHITE);
        tCheckIn.setBounds(200, 30+(int) (space*3.5), 300, 30);

        Thread newTh = new Thread(() ->{
            try {
                g = new Game(scrollList.currentNode.Octopi, scrollList.currentNode.Octopi.lastElement().getId());
                Thread.sleep(4000);
                g.getGameUi().getFrame().setVisible(false);
                fightResultsScreen();
            } catch (InterruptedException | IOException | FontFormatException e) {
                e.printStackTrace();
            }
        });

        // / / / / / / / / / / / / / / / / / / / / / / / Action Listeners
        ActionListener Check_In = e -> checkInScreen();
        ActionListener UpdateTime = e -> {
            if (scrollList.currentNode.playerList.contains(player)){
                tCounter.setText("<html>Time Remaining: " + clock.timeLeft(player.ticket.date,player.ticket.time) +"</html>");

                if (gameDone) tCounter.setText("<html>FIGHT IN PROGRESS</html>");

                if (clock.ready(player.ticket.date, player.ticket.time) && !scrollList.currentNode.Octopi.isEmpty() && !gameDone){
                    newTh.start();
                    gameDone = true;
                    return;
                }

            } else
                tCounter.setText("Time Remaining: NOT YOUR ARENA");

            frame.revalidate();
            frame.repaint();

        };

        // / / / / / / / / / / / / / / / / / / / / / / / Timer
        Timer t = new Timer(1000, UpdateTime);
        t.start();


        //Buttons
        JButton bCheckIn = new JButton(bigB);
        bCheckIn.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'>Check In</p></html>");
        bCheckIn.setHorizontalTextPosition(SwingConstants.CENTER);
        bCheckIn.setBounds(335 - smallSize[0]/2,215 + space*2,bigSize[0],bigSize[1]);
        bCheckIn.addActionListener(Check_In);

        bCheckIn.setEnabled(!player.checkInReady && scrollList.currentNode.playerList.contains(player));

        JButton bBack = new JButton(bigB);
        bBack.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'> Back </p></html>");
        bBack.setHorizontalTextPosition(SwingConstants.CENTER);
        bBack.setBounds(335 - smallSize[0]/2,215 + space*3,bigSize[0],bigSize[1]);
        bBack.addActionListener(Start);

        // / / / / / / / / / / / / / / / / / / / / / / / Mouse Listener -- SCROLL
        MouseWheelListener scroll = e -> {
            //System.out.println(e.getWheelRotation()); //-1 UP <> 1 DOWN --> Use to move around the graph
            if (e.getWheelRotation() == 1)
                scrollList.next();
            else
                scrollList.back();

            //System.out.println(scrollList.currentNode.id);
            refreshScrolls(tPrice, tTotal, tReady, symbol, tCheckIn, bCheckIn);

        };


        //Panel
        panel.add(tTitle);
        panel.add(tNick);
        panel.add(tCredits);
        panel.add(tPrice);
        panel.add(tCounter);
        panel.add(tTotal);
        panel.add(tReady);
        panel.add(tCheckIn);
        panel.addMouseWheelListener(scroll);
        panel.add(bCheckIn);
        panel.add(bBack);

        refreshFrame(scrollbg);
    }

    public void refreshScrolls(JLabel  tPrice, JLabel tTotal, JLabel tReady, char symbol, JLabel tCheckIn, JButton bCheckIn){
        tPrice.setText("<html>Price: " + scrollList.currentNode.betPrice +"</html>");
        tTotal.setText("<html>Total Players: " + scrollList.currentNode.total +"</html>");
        tReady.setText("<html>Players Ready: " + scrollList.currentNode.readyPlayers +"</html>");
        symbol = (player.checkInReady)? '✓': 'X';
        tCheckIn.setText("<html>Check-in done: " + symbol +"</html>");

        bCheckIn.setEnabled(!player.checkInReady && scrollList.currentNode.playerList.contains(player));

        frame.revalidate();
        frame.repaint();
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
            int bet = switch (amountBox.getSelectedIndex() + 1) {
                case 1 -> 10;
                case 2 -> 25;
                case 3 -> 50;
                case 4 -> 100;
                case 5 -> 500;
                case 6 -> 1000;
                default -> 0;
            };

            if (bet < scrollList.currentNode.betPrice || bet > player.credits)
                return;

            //System.out.println(energySlider.getValue());
            //System.out.println(amountBox.getSelectedIndex() + 1);

            //Add octopus
            scrollList.currentNode.Octopi.add(new Octopus(energySlider.getValue(), scrollList.currentNode.readyPlayers+1, player.nickname, bet));

            player.checkInReady = true;
            player.credits -= bet;

            try { playersFile.savePlayer(player.nickname, player.timeZone, player.credits, player.matchesPlayed); } //Overwrite data
            catch (IOException ioException) { ioException.printStackTrace(); }

            scrollList.currentNode.readyPlayers++;
            scrollList.currentNode.totalPrice += bet;
            scrollList.currentNode.usersCharge.put(player.nickname, bet);

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


    public void fightResultsScreen() throws InterruptedException, FontFormatException, IOException { //7
        cleanFrame();

        // player + winnings
        int space = 50;

        tTitle.setText("Rankings");

        Vector<Octopus> octopuses = g.getOctopuses();

        //Distribute winning
        int total = scrollList.currentNode.totalPrice, credit, numP = octopuses.size();
        String nickname;

        JLabel[] players = new JLabel[10];
        for (int i = 0; i < octopuses.size(); i++){
            Octopus o = octopuses.get(i);
            nickname = o.getUser();

            players[i] = new JLabel();
            players[i].setFont(font.deriveFont(16f)); players[i].setForeground(Color.WHITE);
            players[i].setText("#"+(i+1)+". "+nickname+" Time: "+o.getTime());
            players[i].setBounds(20, 120+space*i, 600, 30);

            credit = total / (i+1);
            Player p = playersFile.getPlayer(nickname, playersFile.existingPlayer(nickname));
            playersFile.savePlayer(nickname, p.timeZone, p.credits + credit, p.matchesPlayed + 1);

            panel.add(players[i]);
        }

        //Buttons
        JButton bBack = new JButton(bigB);
        bBack.setText("<html><p color='white' style='font-size:16' face='pixelmix Regular'> Back </p></html>");
        bBack.setHorizontalTextPosition(SwingConstants.CENTER);
        bBack.setBounds(502 - smallSize[0]/2,100 + space*4,bigSize[0],bigSize[1]);
        bBack.addActionListener(Start);


        panel.add(tTitle);
        panel.add(bBack);

        refreshFrame(endgamebg);


        graph.nodeList.cleanNodes();    // Resets arenas and scroll list
        //scrollList = null; //ERROR
        player.clean();
    }

    ActionListener Start = e -> startScreen(); //registerScreen(); //Goes to the start (Register Screen)


    public static void main(String[] args) throws IOException, FontFormatException {
        new UI();
    }

}

