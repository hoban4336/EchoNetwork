package UDPTest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class UDPServerTest {
	public static final int PORT = 5555;
	public static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		DatagramSocket dsocket = null;

		try {
			dsocket = new DatagramSocket(PORT);
			DatagramPacket receivePacket = new DatagramPacket(
					new byte[BUFFER_SIZE], BUFFER_SIZE);
			consolelog("대기중");

			dsocket.receive(receivePacket); // blocking
			consolelog("수신");

			String message = new String(receivePacket.getData(), 0,
					receivePacket.getLength(), "UTF-8");
			consolelog("received " + message);

			byte[] sendData = message.getBytes("UTF-8");
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, receivePacket.getAddress(),
					receivePacket.getPort());

			dsocket.send(sendPacket);

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dsocket != null && dsocket.isClosed() == false) {
				dsocket.close();
			}
		}

	}

	private static void consolelog(String message) {
		System.out.println("[server]" + message);
	}
}
