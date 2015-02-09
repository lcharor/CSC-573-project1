
public class Peer_Block {
public Peer_Block(String hostName, int uploadPortNumber) {
		super();
		HostName = hostName;
		upload_port_number = uploadPortNumber;
	}
String HostName;
int upload_port_number;
public String getHostName() {
	return HostName;
}
public void setHostName(String hostName) {
	HostName = hostName;
}
public int getUpload_port_number() {
	return upload_port_number;
}
public void setUpload_port_number(int uploadPortNumber) {
	upload_port_number = uploadPortNumber;
}
}
