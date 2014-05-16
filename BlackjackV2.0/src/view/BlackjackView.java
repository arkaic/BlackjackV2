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
    JPanel seat1Area;
    JPanel seat2Area;
    JPanel seat3Area;
    JPanel seat4Area;
    JPanel seat5Area;
    JPanel seat6Area;
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
        seat1Area       = new JPanel();
        seat2Area       = new JPanel();
        seat3Area       = new JPanel();
        seat4Area       = new JPanel();
        seat5Area       = new JPanel();
        seat6Area       = new JPanel();
        seat1Label       = new JLabel("<html>[$55+$55][A,A,10,A,A,A,A,A,A]<br>adsfadsf</html>");
        seat2Label       = new JLabel("<html>[$55+$55][A,A,10,A,A,A,A,A,A]<br>g<br>g<br>g<br></html>");
        seat3Label       = new JLabel("<html>[$55+$55][A,A,10,A,A,A,A,A,A]<br>g<br>g<br>g<br></html>");
        seat4Label       = new JLabel("<html>[$55+$55][A,A,10,A,A,A,A,A,A]<br>g<br>g<br>g<br></html>");
        seat5Label       = new JLabel("<html>[$55+$55][A,A,10,A,A,A,A,A,A]<br>g<br>g<br>g<br></html>");
        seat6Label       = new JLabel("<html>[$55+$55][A,A,10,A,A,A,A,A,A]<br>g<br>g<br>g<br></html>");
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
        
        seat1Area.setBorder(BorderFactory.createRaisedBevelBorder());
        seat1Area.setLayout(new MigLayout());
        seat2Area.setBorder(BorderFactory.createRaisedBevelBorder());
        seat2Area.setLayout(new MigLayout());
        seat3Area.setBorder(BorderFactory.createRaisedBevelBorder());
        seat3Area.setLayout(new MigLayout());
        seat4Area.setBorder(BorderFactory.createRaisedBevelBorder());
        seat4Area.setLayout(new MigLayout());
        seat5Area.setBorder(BorderFactory.createRaisedBevelBorder());
        seat5Area.setLayout(new MigLayout());
        seat6Area.setBorder(BorderFactory.createRaisedBevelBorder());
        seat6Area.setLayout(new MigLayout());
        seat1Area.add(seat1Label);
        seat1Area.add(seat1Spinner, "dock south");
        seat2Area.add(seat2Label);
        seat2Area.add(seat2Spinner, "dock south");
        seat3Area.add(seat3Label);
        seat3Area.add(seat3Spinner, "dock south");
        seat4Area.add(seat4Label);
        seat4Area.add(seat4Spinner, "dock south");
        seat5Area.add(seat5Label);
        seat5Area.add(seat5Spinner, "dock south");
        seat6Area.add(seat6Label);
        seat6Area.add(seat6Spinner, "dock south");
        
        centerPanel.setLayout(new MigLayout("", "[][][]10[]", "[]20[]"));
        centerPanel.add(seat1Area);
        centerPanel.add(seat2Area, "cell 1 1");
        centerPanel.add(seat3Area, "cell 2 2");
        centerPanel.add(seat4Area, "cell 3 2");
        centerPanel.add(seat5Area, "cell 4 1");
        centerPanel.add(seat6Area, "cell 5 0");
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        bottomPanel.add(hitButton);
        bottomPanel.add(doubleButton);
        bottomPanel.add(splitButton);
        bottomPanel.add(surrenderButton);
        bottomPanel.add(stayButton);
        bottomPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        this.setLayout(new MigLayout());
        this.add(topPanel, "dock north");
        this.add(centerPanel);
        this.add(bottomPanel, "dock South");
    }

}
