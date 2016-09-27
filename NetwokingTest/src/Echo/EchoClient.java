package Echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoClient {

	private static final int SERVER_PORT = 5002;
	private static final String SERVER_IP = "192.168.1.9";

	public static void main(String[] args) {
		// 1. data소켓 생성.
		Socket socket = new Socket();

		// 2. 서버연결
		try {
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			System.out.println("[client] ");

			// 3. IOstream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			String data = "Hello World \n";
			os.write(data.getBytes("UTF-8"));

			while (true) {
				byte[] buffer = new byte[256];
				int readbytecount = is.read(buffer);
				if (readbytecount == -1) {
					System.out.println("[client] closed by server");
					return;
				}
				data = new String(buffer, 0, readbytecount, "UTF-8");
				System.out.println("[client] received " + data);
				
				/*os.write(b);*/
			}

			
		} catch (SocketException ex){
			System.out.println("[server] abnormal closed by client");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}
}
