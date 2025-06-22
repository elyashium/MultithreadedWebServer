import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    
    //This model is simple to understand but impractical for real-world use.
    //If one client connects and the server is doing some time-consuming work, all other clients have to wait.
    //This is because the server is single-threaded and can only handle one client at a time.
    //This is not efficient and is not scalable.
    //This is why we need to use a multi-threaded server.
    //A multi-threaded server can handle multiple clients at the same time.
    //This is more efficient and is scalable.
    //This is why we need to use a multi-threaded server.
    
    public void run() throws IOException, UnknownHostException{
        //we are throwing an exception because we are not handling it
        //we are not handling the exception because we are not using a try-with-resources block

        //IOE exception is thrown when there is an error in the input/output stream
        //UnknownHostException is thrown when the host is not found

        //listening on port 8010
        int port = 8010;

        //creating a server socket
        ServerSocket socket = new ServerSocket(port);
        
        //setting a timeout of 20 seconds
        socket.setSoTimeout(20000);

        //infinite loop to accept connections
        while(true){
            //printing the port number
            System.out.println("Server is listening on port: "+port);

            //accepting a connection from the client
            Socket acceptedConnection = socket.accept();

            //printing the remote socket address
            System.out.println("Connected to "+acceptedConnection.getRemoteSocketAddress());
            //getRemoteSocketAddress() returns the remote socket address of the client
            //getInetAddress() returns the local socket address of the server
            //getPort() returns the port number of the client
            //getAddress() returns the IP address of the client
            //getPort() returns the port number of the server
            //getAddress() returns the IP address of the server
            //getInetAddress() returns the local socket address of the server

            //creating a print writer to send data to the client
            PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
            //print writer is used to send data to the client
            //true is used to flush the output stream
            //acceptedConnection.getOutputStream() returns the output stream of the client
            //acceptedConnection.getInputStream() returns the input stream of the client
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
            //buffered reader is used to read data from the client
            //new InputStreamReader(acceptedConnection.getInputStream()) returns the input stream of the client
            toClient.println("Hello World from the server");
            //print writer is used to send data to the client
        }
    }

    public static void main(String[] args){
        Server server = new Server();
        try{
            server.run();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
