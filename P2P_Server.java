import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class P2P_Server{
public static ArrayList<Peer_Block> peer_Block_list=new ArrayList<Peer_Block>();
public static ArrayList<RFC_Block> rfc_block_list=new ArrayList<RFC_Block>();
	public static void main(String[] args) throws Exception {
        System.out.println("The server is running.");
        ServerSocket listener = new ServerSocket(7734);
        try {
            while (true) {
            	Socket server = listener.accept();
            	Runnable server_instance=new Server_Instance(server);
            	Thread server_thread=new Thread(server_instance);
            	server_thread.start();
            }
        } finally {
            listener.close();
        }
    }
	
	
	
}
