package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import net.miginfocom.swing.MigLayout;

public class BlackjackView extends JFrame {

    private static final long serialVersionUID = 1L;
    
    JPanel topPanel;
    JPanel centerPanel;
    JPanel bottomPanel;
    JPanel topRightSubPanel;
    JPanel topLeftSubPanel;
    JPanel seat1Panel;
    JPanel seat2Panel;
    JPanel seat3Panel;
    JPanel seat4Panel;
    JPanel seat5Panel;
    JPanel seat6Panel;
    JLabel seat1Label;
    JLabel seat2Label;
    JLabel seat3Label;
    JLabel seat4Label;
    JLabel seat5Label;
    JLabel seat6Label;
    JSpinner seat1Spinner;
    JSpinner seat2Spinner;
    JSpinner seat3Spinner;
    JSpinner seat4Spinner;
    JSpinner seat5Spinner;
    JSpinner seat6Spinner;
    JButton hitButton;
    JButton doubleButton;
    JButton splitButton;
    JButton surrenderButton;
    JButton stayButton;
    JButton dealHandsButton;
    JButton shuffleButton;
    
    public BlackjackView() {
        topPanel         = new JPanel();
        centerPanel      = new JPanel();
        bottomPanel      = new JPanel();
        topRightSubPanel = new JPanel();
        topLeftSubPanel  = new JPanel();
        seat1Panel       = new JPanel();
        seat2Panel       = new JPanel();
        seat3Panel       = new JPanel();
        seat4Panel       = new JPanel();
        seat5Panel       = new JPanel();
        seat6Panel       = new JPanel();
        seat1Label       = new JLabel("<html>[A,A,10,A,A,A,A,A,A]</html>");
        seat2Label       = new JLabel("<html>[A,A,10,A,A,A,A,A,A]</html>");
        seat3Label       = new JLabel("<html>[A,A,10,A,A,A,A,A,A]</html>");
        seat4Label       = new JLabel("<html>[A,A,10,A,A,A,A,A,A]</html>");
        seat5Label       = new JLabel("<html>[A,A,10,A,A,A,A,A,A]</html>");
        seat6Label       = new JLabel("<html>[A,A,10,A,A,A,A,A,A]</html>");
        seat1Spinner     = new JSpinner();
        seat2Spinner     = new JSpinner();
        seat3Spinner     = new JSpinner();
        seat4Spinner     = new JSpinner();
        seat5Spinner     = new JSpinner();
        seat6Spinner     = new JSpinner();
        hitButton        = new JButton("Hit");
        doubleButton     = new JButton("Double Down");
        splitButton      = new JButton("Split");
        surrenderButton  = new JButton("Surrender");
        stayButton       = new JButton("Stay");
        dealHandsButton  = new JButton("Deal Hands");
        shuffleButton    = new JButton("Shuffle");
        
        topPanel.setLayout(new MigLayout("", "[grow]", "[]"));
        topRightSubPanel.setLayout(new BoxLayout(topRightSubPanel, BoxLayout.Y_AXIS));
        topRightSubPanel.add(dealHandsButton);
        topRightSubPanel.add(shuffleButton);
        topLeftSubPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topPanel.add(topLeftSubPanel, "dock west, width 600!");
        topPanel.add(topRightSubPanel, "dock east");
        topPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        
        seat1Panel.setBorder(BorderFactory.createRaisedBevelBorder());
        seat2Panel.setBorder(BorderFactory.createRaisedBevelBorder());
        seat3Panel.setBorder(BorderFactory.createRaisedBevelBorder());
        seat4Panel.setBorder(BorderFactory.createRaisedBevelBorder());
        seat5Panel.setBorder(BorderFactory.createRaisedBevelBorder());
        seat6Panel.setBorder(BorderFactory.createRaisedBevelBorder());
        seat1Panel.add(seat1Label);
        seat2Panel.add(seat2Label);
        seat3Panel.add(seat3Label);
        seat4Panel.add(seat4Label);
        seat5Panel.add(seat5Label);
        seat6Panel.add(seat6Label);
        
        centerPanel.setLayout(new MigLayout("", "[]20[]", "[]20[]"));
        centerPanel.add(seat1Panel);
        centerPanel.add(seat2Panel, "cell 1 1");
        centerPanel.add(seat3Panel, "cell 2 2");
        centerPanel.add(seat4Panel, "cell 3 3");
        centerPanel.add(seat5Panel, "cell 4 4");
        centerPanel.add(seat6Panel, "cell 5 0");
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        bottomPanel.add(hitButton);
        bottomPanel.add(doubleButton);
        bottomPanel.add(splitButton);
        bottomPanel.add(surrenderButton);
        bottomPanel.add(stayButton);
        bottomPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        this.setLayout(new MigLayout());
        this.add(topPanel, "dock north");
        this.add(centerPanel, "height 500!");
        this.add(bottomPanel, "dock South");
    }

}
