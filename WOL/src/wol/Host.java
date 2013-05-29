package wol;

import java.util.UUID;

class Host{
	private UUID id;
	private String name;
	private String ip;
	private String broadcast;
	private String mac;
	private String subnet;
	public Host(UUID id,String name,String ip,String mac,String subnet,String broadcast){
		this.id = id;
		this.name = name;
		this.ip = ip;
		this.mac = mac;
		this.subnet = subnet;
		this.broadcast = broadcast;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getSubnet() {
		return subnet;
	}
	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}
	public String getBroadcast() {
		return broadcast;
	}
	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}
	public String toString(){
		return String.format("id: %s name: %s ip: %s mac: %s subnet:%s broadcast: %s",id,name,ip,mac,subnet,broadcast);
	}
}