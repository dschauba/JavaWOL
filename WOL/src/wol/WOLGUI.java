package wol;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

class WOLGUI extends JFrame implements ActionListener{
	private HostDataModel dataModel = new HostDataModel();
	private HostTableModel hostTableModel;
	private JTable hostTable;
	private JButton addHostButton;
	private JButton removeHostButton;
	private JButton editHostButton;
	private JButton startHostButton;
	private JMenuBar menuBar;
	public WOLGUI(){
		this.setSize(400,400);
		this.guiSetup();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addDummyHost();
	}
	
	
	private void addDummyHost(){		
		String ip = "192.168.178.10";
		String subnet = "255.255.255.0";
		String mac = "00:27:0e:01:a7:87";
		this.dataModel.addHost("fileserver",ip,mac,
								subnet,Utils.calculateBroadcast(ip, subnet));
	}
	
	private void guiSetup(){
		this.hostTableModel = new HostTableModel(this.dataModel);
		this.hostTable = new JTable(this.hostTableModel);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		this.addHostButton = new JButton("Add Host");
		this.removeHostButton = new JButton("Remove Host");
		this.editHostButton = new JButton("Edit Host");
		this.startHostButton = new JButton("Start");
		buttonPanel.add(this.addHostButton);
		buttonPanel.add(this.editHostButton);
		buttonPanel.add(this.removeHostButton);
		buttonPanel.add(this.startHostButton);
		this.addHostButton.addActionListener(this);
		this.startHostButton.addActionListener(this);
		this.add(buttonPanel);
		this.add(new JScrollPane(this.hostTable));
		this.menuBar = new JMenuBar();
		this.menuBar.add(new JMenu("Data"));
		this.setJMenuBar(this.menuBar);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source==this.addHostButton){
			HostDialog d = new HostDialog(this.dataModel);
			d.setVisible(true);
		}else if(source==this.startHostButton){
			this.startSelectedHost();
		}		
	}
	
	private void startSelectedHost(){
		int row = this.hostTable.getSelectedRow();
		if(row<0)return;
		Host selectedHost = this.hostTableModel.getSelectedRow(row);
		System.out.println("selected host: "+selectedHost);
		WOLPacketSender.sendMagicPacket(selectedHost);		
	}	
	
	private static void setSystemPLAF(){
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception{
		setSystemPLAF();
		new WOLGUI();
	}	
}
