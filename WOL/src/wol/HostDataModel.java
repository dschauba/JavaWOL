package wol;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.UUID;

class HostDataModel extends Observable{
	private List<Host> hosts;
	public HostDataModel(){
		this.hosts = new ArrayList<Host>();
	}
	public void addHost(String name,String ip,String mac,String subnet,String broadcast){
		UUID id = UUID.randomUUID();
		Host host = new Host(id,name,ip,mac,subnet,broadcast);
		hosts.add(host);
		this.setChanged();
		this.notifyObservers();
	}	
	public List<Host> getHosts(){
		return this.hosts;
	}
	public int getHostCount(){
		return this.hosts.size();
	}
}