package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class BlackjackView extends JFrame {

    private static final long serialVersionUID = 1L;

    public BlackjackView() {
        JPanel panelN           = new JPanel();
        JPanel panelNE          = new JPanel();
        JPanel panelNW          = new JPanel();
        JPanel panelS           = new JPanel();
        JPanel panelC           = new JPanel();
        JPanel seat1Panel       = new JPanel();
        JPanel seat2Panel       = new JPanel();
        JPanel seat3Panel       = new JPanel();
        JPanel seat4Panel       = new JPanel();
        JPanel seat5Panel       = new JPanel();
        JPanel seat6Panel       = new JPanel();
        JButton hitButton       = new JButton("Hit");
        JButton doubleButton    = new JButton("Double Down");
        JButton splitButton     = new JButton("Split");
        JButton surrenderButton = new JButton("Surrender");
        JButton stayButton      = new JButton("Stay");
        JButton dealHandsButton = new JButton("Deal Hands");
        JButton shuffleButton   = new JButton("Shuffle");
        
        panelN.setLayout(new MigLayout("", "[grow]", "[]"));
        panelNE.setLayout(new BoxLayout(panelNE, BoxLayout.Y_AXIS));
        panelNE.add(dealHandsButton);
        panelNE.add(shuffleButton);
        panelNW.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelN.add(panelNW, "dock west, width 600!");
        panelN.add(panelNE, "dock east");
        panelN.setBorder(BorderFactory.createRaisedBevelBorder());
        
        seat1Panel.setBorder(BorderFactory.createRaisedBevelBorder());
        seat2Panel.setBorder(BorderFactory.createRaisedBevelBorder());
        seat3Panel.setBorder(BorderFactory.createRaisedBevelBorder());
        seat4Panel.setBorder(BorderFactory.createRaisedBevelBorder());
        seat5Panel.setBorder(BorderFactory.createRaisedBevelBorder());
        seat6Panel.setBorder(BorderFactory.createRaisedBevelBorder());
        panelC.setLayout(new MigLayout());
        panelC.add(seat1Panel, "width 100!");
        panelC.add(seat2Panel, "width 100!");
        panelC.add(seat3Panel, "width 100!");
        panelC.add(seat4Panel, "width 100!");
        panelC.add(seat5Panel, "width 100!");
        panelC.add(seat6Panel, "width 100!");
        panelC.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        panelS.add(hitButton);
        panelS.add(doubleButton);
        panelS.add(splitButton);
        panelS.add(surrenderButton);
        panelS.add(stayButton);
        panelS.setBorder(BorderFactory.createRaisedBevelBorder());

        this.setLayout(new MigLayout());
        this.add(panelN, "dock north");
        this.add(panelC, "height 500!, width 700:800:900");
        this.add(panelS, "dock south");
    }

}
