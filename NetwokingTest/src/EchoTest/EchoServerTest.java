package EchoTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoServerTest {
	private static final String IP  = "192.169.1.9";
	private static final int PORT = 6003;
	
	public static void main(String[] args) {
		
		ServerSocket serversocket = null;
			try {
				//1. 나의 정보 가져오기.
				InetAddress ia = InetAddress.getLocalHost();
				String MyAddress = ia.getHostAddress();
				
				//2. 서버 소켓을 만들기 
				serversocket = new ServerSocket();
				serversocket.bind(new InetSocketAddress(MyAddress,PORT)); //InetSocketAddress!!!
								
				//3. accept 소켓을 만들기
				Socket socket = serversocket.accept();
				
				//3-1 상대방의 정보를 가져오기.
				InetSocketAddress client = (InetSocketAddress)socket.getRemoteSocketAddress();
				System.out.println("[server] connected!" + client.getAddress() +" / "+ client.getPort());
				
				//4. 읽기	객체 만들기 
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);

				Scanner scanner = null;
				while(true){	
					String s = br.readLine();
					//읽고
					System.out.println("[server] receieved : "+ s );
					if("exit".equals(s)){
						break;
					}
					//쓰고
					scanner = new Scanner(System.in);
					String answ = scanner.nextLine();
					System.out.println("[server] send : "+ answ);
					pw.println(answ);
				}
				
				socket.close();
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					if(serversocket !=null && serversocket.isClosed())
					serversocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}
}
