package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookUP {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String host = "www.naver.com";
		try {
			InetAddress[] ia = InetAddress.getAllByName(host);
			for(InetAddress i : ia){
				System.out.println(i.getCanonicalHostName());
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
