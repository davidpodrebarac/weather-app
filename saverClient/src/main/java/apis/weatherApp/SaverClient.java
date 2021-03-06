package apis.weatherApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SaverClient {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println(
                    "Usage: java SaverClient <host name> <port number> <message>");
            System.exit(1);
        }

        String hostName = args[0].trim();
        int portNumber = Integer.parseInt(args[1]);
        String message = args[2].trim();

        try (
                Socket socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            System.out.println("Sending message '" + message + "' to server " + hostName + ":" + portNumber);
            out.println(message);
            String response = in.readLine();
            System.out.println("Received message from server: '" + response + "'");
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}
