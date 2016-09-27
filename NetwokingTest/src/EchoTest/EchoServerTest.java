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
				//1. ���� ���� ��������.
				InetAddress ia = InetAddress.getLocalHost();
				String MyAddress = ia.getHostAddress();
				
				//2. ���� ������ ����� 
				serversocket = new ServerSocket();
				serversocket.bind(new InetSocketAddress(MyAddress,PORT)); //InetSocketAddress!!!
								
				//3. accept ������ �����
				Socket socket = serversocket.accept();
				
				//3-1 ������ ������ ��������.
				InetSocketAddress client = (InetSocketAddress)socket.getRemoteSocketAddress();
				System.out.println("[server] connected!" + client.getAddress() +" / "+ client.getPort());
				
				//4. �б�	��ü ����� 
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);

				Scanner scanner = null;
				while(true){	
					String s = br.readLine();
					//�а�
					System.out.println("[server] receieved : "+ s );
					if("exit".equals(s)){
						break;
					}
					//����
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
