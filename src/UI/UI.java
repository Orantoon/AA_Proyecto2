package UI;

import GameLogic.Map;
import GameLogic.Octopus;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class UI {

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    //Images
    private final JLabel bg = new JLabel(new ImageIcon("images/arena.png"));
    private final ImageIcon[] octopusImgs = new ImageIcon[10];

    public UI(Map map, int id){

        int Width = 768 + 14, Height = 768 + 35;

        frame.setTitle("Octopus Arena");
        frame.setSize(Width, Height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initPanels(Width, Height);
        initImgs();

//        updateFrame(map, id);

        frame.setVisible(true);

    }

    public void initPanels(int width, int height){
        panel.setBounds(0,0,width,height);
        panel.setLayout(null);
        panel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f)); //Transparent
    }

    public void initImgs(){
        for (int i = 0; i < 10; i++)
            octopusImgs[i] = new ImageIcon("images/octopus"+(i+1)+".png");
    }

    public void updateFrame(Map map, int id){
        frame.getContentPane().removeAll();
        panel.removeAll();

        printOctopus(map, id);
        frame.add(panel);
        frame.add(bg);

        //text

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


    public static void main(String[] args) {
        Vector<Octopus> os = new Vector<>();
        os.add(new Octopus(20, 1));
        os.add(new Octopus(100, 2));
        Map m = new Map(os);
        new UI(m, 2);
    }
}
