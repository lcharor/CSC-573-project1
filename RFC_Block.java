
public class RFC_Block {
public RFC_Block(int rFCNumber, String rFCTitle, String hostName) {
		super();
		RFC_number = rFCNumber;
		RFC_Title = rFCTitle;
		HostName = hostName;
	}
int RFC_number;
String RFC_Title;
String HostName;
public int getRFC_number() {
	return RFC_number;
}
public void setRFC_number(int rFCNumber) {
	RFC_number = rFCNumber;
}
public String getRFC_Title() {
	return RFC_Title;
}
public void setRFC_Title(String rFCTitle) {
	RFC_Title = rFCTitle;
}
public String getHostName() {
	return HostName;
}
public void setHostName(String hostName) {
	HostName = hostName;
}
}
