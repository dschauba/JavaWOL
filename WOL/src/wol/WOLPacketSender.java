package wol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import org.savarese.rocksaw.net.RawSocket;

public class WOLPacketSender {
	private static final byte[] broadcastMAC = {(byte)0xff,(byte)0xff,(byte)0xff,
												(byte)0xff,(byte)0xff,(byte)0xff};
	private static final byte[] sourcePort = {0x2a,0x2a};
	//dest port 40000
	private static final byte[] destinationPort = {(byte)0x9c,0x40};
	public static boolean sendMagicPacket(Host host){
		boolean success = false;
		RawSocket socket = new RawSocket();
		try {
			socket.open(RawSocket.PF_INET,RawSocket.getProtocolByName("udp"));
			socket.setSendTimeout(10000);
			socket.setReceiveTimeout(10000);
			socket.setIPHeaderInclude(false);
			InetAddress addr = InetAddress.getByName(host.getBroadcast());
			byte[] targetMAC = Utils.macHex2Byte(host.getMac());		
			
			ByteArrayOutputStream bois = new ByteArrayOutputStream();	
			bois.write(sourcePort);			
			bois.write(destinationPort);
			//data length
			bois.write(new byte[]{0x00,0x6E});
			//checksum disable
			bois.write(new byte[]{0x00,0x00});
			bois.write(broadcastMAC);	
			//repeat target MAC 16x
			for(int index=0;index<16;index++){
				bois.write(targetMAC);
			}		
			socket.write(addr,bois.toByteArray());				
			success = true;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}	
}
