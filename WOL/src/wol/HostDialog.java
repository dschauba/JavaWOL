package wol;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

class HostDialog extends JDialog implements ActionListener{
	private JTextField nameField;
	private JTextField ipField;
	private JTextField subnetField;
	private JTextField broadcastField;
	private JTextField macField;
	private JButton createButton;
	private JButton abortButton;
	private HostDataModel dataModel;
	public HostDialog(HostDataModel dataModel){
		this.dataModel = dataModel;
		this.setSize(400,200);
		this.guiSetup();
	}
	private void guiSetup(){
		this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));		
		JPanel inputFieldGrid = new JPanel();
		inputFieldGrid.setBorder(new TitledBorder("Host"));
		inputFieldGrid.setLayout(new GridLayout(4,2));
		this.nameField = new JTextField(20);
		this.ipField = new JTextField(20);
		this.subnetField = new JTextField(20);
		this.broadcastField = new JTextField(20);
		this.macField = new JTextField(20);
		inputFieldGrid.add(new JLabel("Name:"));
		inputFieldGrid.add(this.nameField);
		inputFieldGrid.add(new JLabel("IP:"));
		inputFieldGrid.add(this.ipField);
		inputFieldGrid.add(new JLabel("Subnet:"));
		inputFieldGrid.add(this.subnetField);
		inputFieldGrid.add(new JLabel("Broadcast: "));
		inputFieldGrid.add(this.broadcastField);
		inputFieldGrid.add(new JLabel("MAC:"));
		inputFieldGrid.add(this.macField);
		this.add(inputFieldGrid);
		
		JPanel buttonPanel = new JPanel();
		this.createButton = new JButton("Create");
		this.abortButton = new JButton("Abort");
		this.createButton.addActionListener(this);
		this.abortButton.addActionListener(this);
		buttonPanel.add(this.createButton);
		buttonPanel.add(this.abortButton);
		this.add(buttonPanel);		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source==this.createButton){
			this.createHost();
			this.setVisible(false);
		}		
	}
	
	private void createHost(){
		this.dataModel.addHost(this.nameField.getText(),
							   this.ipField.getText(),
							   this.subnetField.getText(),
							   this.broadcastField.getText(),
							   this.macField.getText());
	}
}