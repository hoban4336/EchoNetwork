package TCP;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		try {
			//³ª ÀÚ½Å : host  <-> remote
			InetAddress inetAddress = InetAddress.getLocalHost();
			
			String hostAddress = inetAddress.getHostAddress();
			String hostname = inetAddress.getHostName();
			System.out.println(hostAddress +" : "+ hostname);
			//192.168.1.9  
			//byte[] -> int[] 
			byte[] address = inetAddress.getAddress();
			for(int b : address){
				System.out.print(b & 0x000000ff);
				System.out.print(".");
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			
		}
		
	}
}
