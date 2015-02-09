import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Server_Instance implements Runnable {
private Socket server;
	public Server_Instance(Socket server) throws IOException {
		// TODO Auto-generated method stub
		this.server=server;
	  
	}
	public void run() {
		try{
		System.out.println("connection done");
   	    BufferedReader in_from_client = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintWriter out_to_client = new PrintWriter(server.getOutputStream(), true);
        String host_information=in_from_client.readLine();
        String hostname=host_information.substring(0,host_information.indexOf(' '));
        int upload_port_number=Integer.parseInt(host_information.substring(host_information.indexOf(' ')+1));
        Peer_Block peer_new=new Peer_Block(hostname, upload_port_number);    
        System.out.println(peer_new.HostName+"  "+peer_new.upload_port_number);
        P2P_Server.peer_Block_list.add(peer_new);
        out_to_client.println(peer_new.HostName+"-you are added in the list");
      
        while(true){
        String Client_Request=in_from_client.readLine();
        String[] client_request_parced=Client_Request.split(" ");
        
        if(client_request_parced[0].equalsIgnoreCase("ADD")){
       	 
       	 RFC_Block rfc_block=new RFC_Block(Integer.parseInt(client_request_parced[1]), client_request_parced[2],hostname);
       	 P2P_Server.rfc_block_list.add(rfc_block);
       	 out_to_client.println("P2P-CI/1.0 200 OK");
        }
        else if(client_request_parced[0].equalsIgnoreCase("LOOKUP")){
       	 int flag=0;
       	 String Lookup_List="";
       	int port=0;
       	 for(RFC_Block current_block:P2P_Server.rfc_block_list){
       		 if(current_block.RFC_number==Integer.parseInt(client_request_parced[1]) || current_block.RFC_Title.equalsIgnoreCase(client_request_parced[2])){
       			for(Peer_Block cuBlock:P2P_Server.peer_Block_list){
       				if(cuBlock.HostName.equalsIgnoreCase(current_block.HostName))
       				port=cuBlock.upload_port_number;
       			}
       			 Lookup_List=Lookup_List+"->"+current_block.HostName+"->"+Integer.toString(port);
       			 
       			 flag=1;
       		 }
       	 }
       	 if(flag!=1)
       		 out_to_client.println("404 Not Found");
       	 else
       		out_to_client.println(Lookup_List);
       		 
        }
        else if(client_request_parced[0].equalsIgnoreCase("LISTALL")){
       	 String S="";
       	 int count=1;
       	 for(RFC_Block current_block:P2P_Server.rfc_block_list){
       		S=S+" "+Integer.toString(count)+"."+current_block.HostName+"-->"+Integer.toString(current_block.RFC_number)+"-->"+current_block.RFC_Title; 
       		count++;
       	 }
       	 out_to_client.println(S);
        }
        else if(client_request_parced[0].equalsIgnoreCase("quit"))
        { 
        server.close();
        break;
        }
        else{
        	out_to_client.println("400 Bad Request");
        }
        }
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	
	}
}
