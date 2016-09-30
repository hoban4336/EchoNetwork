package File;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTest {

	public static void main(String[] args) {
		try {
		File f = new File("test.txt");
		FileInputStream fis = new FileInputStream(f);
		
		FileOutputStream fos = new FileOutputStream(f);
			fos.write("test 하고 있냐?".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
