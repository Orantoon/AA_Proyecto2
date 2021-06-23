package UI;

import GameLogic.Map;
import GameLogic.Octopus;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameUI {

    //Frame and panels
    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    private final JPanel textP = new JPanel();

    //Images
    private final JLabel bg = new JLabel(new ImageIcon("images/arena.png"));
    private final JLabel tbg = new JLabel(new ImageIcon("images/textbg.png"));
    private final ImageIcon[] octopusImgs = new ImageIcon[10];

    //Font
    private final Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Font/pixelmix.ttf"));

    //Text
    private final JLabel[] tNormal = new JLabel[10];
    private final JLabel[] tShadow = new JLabel[10];

    private final int ArenaSize = 768;

    public GameUI() throws IOException, FontFormatException {

        int Width = 768 + 14 + 385, Height = 768 + 35;

        frame.setTitle("Octopus Arena");
        frame.setSize(Width, Height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initPanels(Width, Height);
        initImgs();

//      updateFrame(map, id);

        frame.setVisible(true);

    }

    public void initPanels(int width, int height){
        panel.setBounds(0,0,width,height);
        panel.setLayout(null);
        panel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f)); //Transparent

        bg.setBounds(0,0, ArenaSize, ArenaSize);
        bg.setLayout(null);

        textP.setBounds(ArenaSize,0,width - ArenaSize, height);
        textP.setLayout(null);
        textP.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f)); //Transparent

        tbg.setBounds(0,0, ArenaSize, ArenaSize);
    }

    public void initImgs(){
        for (int i = 0; i < 10; i++){
            octopusImgs[i] = new ImageIcon("images/octopus"+(i+1)+".png");
            tNormal[i] = new JLabel();
            tNormal[i].setFont(font.deriveFont(16f));
            tNormal[i].setForeground(Color.WHITE);

            tShadow[i] = new JLabel();
            tShadow[i].setFont(font.deriveFont(16f));
            tShadow[i].setForeground(Color.BLACK);
        }
    }

    public void updateFrame(Map map, int id){
        frame.getContentPane().removeAll();
        panel.removeAll();
        textP.removeAll(); //

        printStats(map);
        frame.add(textP); //
        printOctopus(map, id);
        frame.add(panel);
        frame.add(bg);


        frame.revalidate();
        frame.repaint();
    }

    public void printOctopus(Map map, int id){
        for (Octopus o : map.getOctopuses()){
            if (o.getLife() > 0){
                int c = o.getPosition()[1], r = o.getPosition()[0];
                int oId = o.getId();

                JLabel octIMG = new JLabel();
                octIMG.setBounds(c*96, r*96, 96, 96);
                octIMG.setIcon(octopusImgs[oId-1]);

                panel.add(octIMG);

                if (id == oId){
                    JLabel POV = new JLabel(new ImageIcon("images/pov.png"));
                    POV.setBounds(c*96, r*96, 96, 96);

                    panel.add(POV);
                }
            }
        }
    }

    public void printStats(Map map){

        int i = 0;
        for (Octopus o : map.getOctopuses()){
            int health = Math.max(o.getLife(), 0), r = o.getPosition()[0], c = o.getPosition()[1];
            tNormal[i].setText("<html><b>Octopus " + o.getId() + " Energy: " + health + "<br>Attack: " + o.getAttack() + "<br>Position: [" + r + ", " + c + "] </b></html>");
            tNormal[i].setBounds(65, -60+75*i, 500, 200);
            textP.add(tNormal[i]);

            tShadow[i].setText("<html><b>Octopus " + o.getId() + " Energy: " + health + "<br>Attack: " + o.getAttack() + "<br>Position: [" + r + ", " + c + "] </b></html>");
            tShadow[i].setBounds(63, -59+75*i, 500, 200);
            textP.add(tShadow[i++]);
        }

        textP.add(tbg);
    }
}
