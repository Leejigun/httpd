package kr.or.connect.simplehttpd;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {
    public static final Logger log = LoggerFactory.getLogger(HttpServer.class);

    int portNumber;

    RequestHandler requestHandler = new RequestHandler();
    ResponseHandler responseHandler = new ResponseHandler();


    public HttpServer(int portNumber) {
        this.portNumber = portNumber;
    }

    public void run() {
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
        ) {
                Socket clientSocket = serverSocket.accept();

                Request request = requestHandler.handle(clientSocket);
                Response response = responseHandler.handle(request.getRequestTarget(), clientSocket);

                clientSocket.close();
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
            log.error(e.getMessage());
        }

    }
}
