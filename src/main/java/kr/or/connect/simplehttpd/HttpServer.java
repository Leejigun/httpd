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

    public HttpServer(int portNumber) {
        this.portNumber = portNumber;
    }

    public void run() {
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
        ) {
                Socket clientSocket = serverSocket.accept();

                Request request = requestHandler.handle(clientSocket);
                log.info("request:::::"+request);
                clientSocket.close();
        } catch (IOException e) {
            log.info("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            log.error(e.getMessage());
        }

    }
}
