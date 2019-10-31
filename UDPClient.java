import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;

public class UDPClient {
	
	private DatagramSocket socket;
    private InetAddress address;
    private int port;
    private byte[] message;
 
    // Constructor
    public UDPClient() throws IOException {
    	// Create socket object to carry data
        socket = new DatagramSocket();
        
        // Set destination address and port number
        address = InetAddress.getByName("localhost");
        port = 1234;
    }
    
    // Constructor
    public UDPClient(String destAddress, int portNumber) throws IOException {
    	// Create socket object to carry data
    	socket = new DatagramSocket();
    	
    	// Set destination address and port number
    	address = InetAddress.getByName(destAddress);
    	port = portNumber;
    }
 
    // Send a string message to the server and receive a response
    public String sendMessage(String msg) throws IOException {
    	// Generate DatagramPacket object to send the message
        message = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(message, message.length, address, port);
        socket.send(packet);
        
        // Generate DatagramPacket object to receive the response
        packet = new DatagramPacket(message, message.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());
        
        return received;
    }
 
    public void close() {
        socket.close();
    }
}
