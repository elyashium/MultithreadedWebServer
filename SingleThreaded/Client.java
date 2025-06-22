import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    
    public void run() throws UnknownHostException, IOException{
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        //InetAddress is used to get the IP address of the server locally
        //getByName("localhost") returns the IP address of the server locally
        //port is the port number of the server
        Socket socket = new Socket(address, port);
        //Socket is used to connect to the server
        //address is the IP address of the server
        PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
        //print writer is used to send data to the server
        //true is used to flush the output stream
        //by flushing the output stream, the data is sent to the server
        //socket.getOutputStream() returns the output stream of the server
        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //buffered reader is used to read data from the server
        //new InputStreamReader(socket.getInputStream()) returns the input stream of the server
        toSocket.println("Hello World from socket "+socket.getLocalSocketAddress());
        //println is used to send data to the server
        //socket.getLocalSocketAddress() returns the local socket address of the client
        String line = fromSocket.readLine();
        //readLine is used to read data from the server
        //fromSocket.readLine() returns the data from the server
        toSocket.close();
        //close is used to close the output stream
        fromSocket.close();
        //close is used to close the input stream
        socket.close();
        //close is used to close the socket
    }
    
    public static void main(String[] args) {
        Client singleThreadedWebServer_Client = new Client();
        try{
            singleThreadedWebServer_Client.run();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
