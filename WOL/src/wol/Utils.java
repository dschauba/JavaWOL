package wol;

public class Utils {
	public static String calculateBroadcast(String ip,String netmask){
		String[] rawIP = ip.split("\\.");
		String[] rawNetmask = netmask.split("\\.");
		String broadcastIP = "";
		for(int index=0;index<4;index++){
			int field = Integer.parseInt(rawIP[index]);
			int netmaskField = Integer.parseInt(rawNetmask[index]);
			netmaskField=~netmaskField;
			int broadcastField = field|netmaskField;
			broadcastIP+=(index>0 ? "." : "")+(broadcastField&0xff);
		}
		return broadcastIP;
	}	
	
	public static byte[] macHex2Byte(String mac){
		String[] macHex = mac.split(":");
		byte[] macBytes = new byte[6];
		for(int lv = 0;lv<macBytes.length;lv++){
			macBytes[lv] = (byte)(int)Integer.valueOf(macHex[lv],16);
		}
		return macBytes;
	}
}
