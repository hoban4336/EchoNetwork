package UDPTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPClientTest {
	private static final String SERVER_IP = "192.168.1.9";
	private static final int PORT = 5555;
	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		DatagramSocket dsocket = null;
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			dsocket = new DatagramSocket();
			while (true) {
				System.out.println(">>");
				String message = scanner.nextLine();

				// 보낼 패킷
				byte[] sendData = message.getBytes("UTF-8");
				DatagramPacket sendpacket = new DatagramPacket(sendData,
						sendData.length, new InetSocketAddress(SERVER_IP, PORT));
				// 보내기
				dsocket.send(sendpacket);

				// 5.받을 패킷
				DatagramPacket receivepacket = new DatagramPacket(
						new byte[BUFFER_SIZE], BUFFER_SIZE);

				// 6. 받기
				dsocket.receive(receivepacket);
				message = new String(receivepacket.getData(), 0,
						receivepacket.getLength(), "UTF-8");
				System.out.println("received "+message);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
			if (dsocket != null && dsocket.isClosed()==false) {

				dsocket.close();
			}
		}

	}
}
