package Echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String[] args) {
		String SERVER = "192.168.1.9";
		int PORT = 5002;
		ServerSocket serverSocket = null;
		try {
			InetAddress ia = InetAddress.getLocalHost();
			SERVER = ia.getHostAddress();

			// 1. 서버 소켓 생성.
			serverSocket = new ServerSocket();
			// 2. 서버 소켓에 소켓주소 (IP+PORT) 을 바인드.
			serverSocket.bind(new InetSocketAddress(SERVER, PORT));
			System.out.println("[server] binding "+SERVER +" : "+PORT);
			// 3. accpet (client로 부터 Connect 연결요청)
			Socket datasock = serverSocket.accept(); // block
			System.out.println("[server] connected by client");

			InetSocketAddress isa = (InetSocketAddress) datasock
					.getRemoteSocketAddress();
			String remoteHostName = isa.getHostName();
			int remoteHostPORT = isa.getPort();

			System.out.println("[server] connected by client[" + remoteHostName
					+ "," + remoteHostPORT + "," + isa.getAddress() + "]");
				try {
					// 4. data 읽기
					InputStream is = datasock.getInputStream();
					OutputStream os = datasock.getOutputStream();
					byte[] buffer = new byte[256];
					while (true) {
					int bytecount = is.read(buffer);
					if (bytecount == -1) {
						// 정상종료.(remote socket(serversocket) close() 불러서 정상적으로
						// 소켓을 닫았다는 것을 의미.
						System.out.println("[server] closed by client");
						break;
					}
					String data = new String(buffer, 0, bytecount, "UTF-8");
					System.out.println("[server] received :" + data);

					// 6. 쓰기
					os.write(data.getBytes("UTF-8"));
					}// while
				} catch (IOException e) {
				System.out.println("message");
						
				} finally {
					try {
						// 5. 자원정리
						datasock.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
