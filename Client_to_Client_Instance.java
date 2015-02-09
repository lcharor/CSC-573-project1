import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Client_to_Client_Instance implements Runnable {
private ServerSocket client_to_client_socket;

	public Client_to_Client_Instance(ServerSocket clientUploadListner) {
		System.out.println("UPLOAD SERVER STARTED");
		this.client_to_client_socket = clientUploadListner;
}

	@Override
	public void run() {
		try {
			Socket client2client=client_to_client_socket.accept();
			System.out.println("client to client connection done");
			BufferedReader in_from_client = new BufferedReader(new InputStreamReader(client2client.getInputStream()));
		    PrintWriter out_to_client = new PrintWriter(client2client.getOutputStream(), true);
			String RFC_Request=in_from_client.readLine();
			String request[]=RFC_Request.split(" ");
			String RFC_Name="INDIA.txt";
			if(Integer.parseInt(request[1])==1){
				File myRFC=new File(RFC_Name);
				//BufferedReader RFC_Reader=new BufferedReader(new FileReader(RFC_Name));
				byte[] myByteArray=new byte[(int) myRFC.length()];
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(RFC_Name));
			      bis.read(myByteArray, 0, myByteArray.length);
			      OutputStream os = client2client.getOutputStream();
			      os.write(myByteArray, 0, myByteArray.length);
			      os.flush();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
