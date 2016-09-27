package EchoTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClientTest {
	private static final String IP  = "192.168.1.9";
	private static final int PORT = 6003;
	public static void main(String[] args) {
		
		//1. ���� ���� 
		InetSocketAddress isa = new InetSocketAddress(IP, PORT);
		
		//2. ���� ����
		Socket socket = new Socket();
		try {
			socket.connect(isa);
						
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
			Scanner scanner =null;
			while(true){
				//����
				scanner = new Scanner(System.in);
				String text = scanner.nextLine();
				pw.println(text);
				pw.flush();
				System.out.println("[client] send : " + text);
				if("exit".equals(text)){
					break;
				}
				//�а�
				System.out.println("[server] receieved : "  + br.readLine());
			
			}
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
