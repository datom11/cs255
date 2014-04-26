

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ButtonModel extends JFrame implements ActionListener{

	private JPanel panel;
    private JButton okbtn;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JTextField str1;
    private JTextField str2;
    
    //private JCheckBox cb;

    public ButtonModel() {

        createButtonsAndTextFields();
        initUI();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		if (action.equals("ok")) {
			System.out.println("Button pressed!");
			//panel.add(l3);
			JLabel l4 = new JLabel("Edit Distance: 8");
	        l4.setSize(l4.getPreferredSize());
	        l4.setLocation(40, 180);
	        this.getFrames()[0].add(l4);
	        System.out.println("str1 = " + str1.getText());
		
	        computeED ed = new computeED();
	        int val = ed.editDistance(str1.getText(), str2.getText());
	        System.out.println("edit dist = " + val);
		}
	}

    private void createButtonsAndTextFields() {

        okbtn = new JButton("ok");

        //cb = new JCheckBox("Enabled", false);

        okbtn.setBounds(40, 30, 80, 25);
        okbtn.addActionListener(this);

		// define a custom short action command for the button
		okbtn.setActionCommand("ok");

	    l1 = new JLabel("Enter String 1: ");
        l1.setSize(l1.getPreferredSize());
        l1.setLocation(40, 90);
    
        str1 = new JTextField();
        str1.setSize(l1.getPreferredSize());
        str1.setLocation(160, 90);
        
        l2 = new JLabel("Enter String 2: ");
        l2.setSize(l2.getPreferredSize());
        l2.setLocation(40, 120);
    
        str2 = new JTextField();
        str2.setSize(l2.getPreferredSize());
        str2.setLocation(160, 120);
        
        
        l3 = new JLabel("Edit Distance: 8");
        l3.setSize(l3.getPreferredSize());
        l3.setLocation(40, 150);
        
		//l3.setVisible(false);
    }

    private void initUI() {

        panel = new JPanel();
        panel.setLayout(null);

        panel.add(okbtn);
        //panel.add(cb);
        panel.add(l1);
        panel.add(str1);
        
        panel.add(l2);
        panel.add(str2);
        
        panel.add(l3);
        
        add(panel);

        setTitle("ButtonModel");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        ButtonModel ex = new ButtonModel();
    	ex.setVisible(true);
    }
}