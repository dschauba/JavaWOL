package wol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;

import org.savarese.rocksaw.net.RawSocket;

public class WOLPacketSender {
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
			byte[] broadcast = Utils.macHex2Byte("FF:FF:FF:FF:FF:FF");		
			System.out.println(Arrays.toString(targetMAC));		
			System.out.println(Integer.parseInt("0e",16));
			System.out.println(Integer.toHexString(-40));

			ByteArrayOutputStream bois = new ByteArrayOutputStream();
			//src port 55437
			bois.write(new byte[]{-40,-115});
			//dest port
			bois.write(new byte[]{0x2f,-1});
			//data length
			bois.write(new byte[]{0x00,0x6E});
			//checksum disable
			bois.write(new byte[]{0x00,0x00});
			bois.write(broadcast);	
			for(int index=0;index<16;index++){
				bois.write(targetMAC);
			}				

			socket.write(addr,bois.toByteArray());	
			socket.close();
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
