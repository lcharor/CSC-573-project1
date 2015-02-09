import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class P2P_Client {
static String hostname;
static int upload_port_number;
  public static void main(String args[]) throws IOException{
	  Scanner in = new Scanner(System.in);
	  System.out.println("Enter Server Address:");
	  String Server_Address=in.nextLine();
	  Socket client=new Socket(Server_Address,7734);
	  BufferedReader In_from_server = new BufferedReader(new InputStreamReader(client.getInputStream()));
      PrintWriter out_to_server = new PrintWriter(client.getOutputStream(), true);
      System.out.println("Enter Hostname");
      hostname=in.nextLine();
      System.out.println("Enter Upload  Port:");
	  upload_port_number=Integer.parseInt(in.nextLine());
	  //run client upload server
	  ServerSocket client_upload_listner=new ServerSocket(upload_port_number);
	  Runnable client2clientInstance=new Client_to_Client_Instance(client_upload_listner);
	  Thread client2clientThread=new Thread(client2clientInstance);
	  client2clientThread.start();
	  
	  //sending request to server
      out_to_server.println(hostname+" "+upload_port_number);
	  System.out.println(In_from_server.readLine());
	  System.out.println("Enter Your Request");
	  String Request=in.nextLine();
	  while(!Request.equalsIgnoreCase("quit")){
		  	if(!Request.contains("GET")){
		  				out_to_server.println(Request);
		  				System.out.println(In_from_server.readLine());
		  }
		  else{
		 	  String[] request_to_client=Request.split(" ");
			  int rfc_requested_number=Integer.parseInt(request_to_client[1]);
			  int port_client=Integer.parseInt(request_to_client[2]);
			  Socket client_request=new Socket(hostname,port_client);
			  BufferedReader in_from_clientRequest=new BufferedReader(new InputStreamReader(client_request.getInputStream()));
			  PrintWriter out_to_clientRequest = new PrintWriter(client_request.getOutputStream(), true);
			  out_to_clientRequest.println(Request);
			
			    byte[] mybytearray = new byte[1024];
			    InputStream is = client_request.getInputStream();
			    FileOutputStream fos = new FileOutputStream("s.txt");
			    BufferedOutputStream bos = new BufferedOutputStream(fos);
			    int bytesRead = is.read(mybytearray, 0, mybytearray.length);
			    bos.write(mybytearray, 0, bytesRead);
			    bos.close();
			  
		  }
	  System.out.println("Enter Your Request");
	  Request=in.nextLine();
	  }
	  client.close();
  }
}
