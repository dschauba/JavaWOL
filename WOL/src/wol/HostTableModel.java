package wol;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.DefaultTableModel;

class HostTableModel extends DefaultTableModel implements Observer{
	private static enum COLS{
		NAME,IP,SUBNET,MAC;
	}
	private String[] columnNames = {"Name","IP","Subnet","MAC"};
	private List<Host> hosts = new ArrayList<Host>();
	private HostDataModel dataModel;
	public HostTableModel(HostDataModel dataModel){
		this.dataModel = dataModel;
		this.dataModel.addObserver(this);
	}
	public String getColumnName(int col){
		return columnNames[col];
	}
	public Object getValueAt(int row,int col){
		COLS valCol = COLS.values()[col];
		Host host = this.dataModel.getHosts().get(row);
		Object val = null;
		switch(valCol){
			case NAME:
				val = host.getName();
				break;
			case IP:
				val = host.getIp();
				break;
			case SUBNET:
				val = host.getSubnet();
				break;
			case MAC:
				val = host.getSubnet();
				break;
		}
		return val;
	}
	
	public int getColumnCount(){
		return this.columnNames.length;
	}
	
	public int getRowCount(){
		if(this.dataModel==null)return 0;
		return this.dataModel.getHostCount();
	}	
	
	@Override
	public void update(Observable o, Object arg) {		
		this.hosts = this.dataModel.getHosts();
		this.fireTableDataChanged();
	}	
	
	public Host getSelectedRow(int row){
		return this.hosts.get(row);	
	}
}