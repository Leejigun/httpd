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
            while(true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    HttpProcessThread httpProcessThread = new HttpProcessThread(clientSocket, requestHandler, responseHandler);
                    httpProcessThread.start();
                }catch(Exception ex){
                    log.error("http process error : " + ex.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
            log.error(e.getMessage());
        }

    }
}

class HttpProcessThread extends Thread{
    Socket clientSocket;
    RequestHandler requestHandler = null;
    ResponseHandler responseHandler = null;


    public HttpProcessThread(Socket clientSocket, RequestHandler requestHandler, ResponseHandler responseHandler){
        this.clientSocket = clientSocket;
        this.requestHandler = requestHandler;
        this.responseHandler = responseHandler;
    }
    public void run(){
        try {

            Request request = requestHandler.handle(clientSocket);
            Response response = responseHandler.handle(request.getRequestTarget(), clientSocket);

            DefaultServlet defaultServlet = new DefaultServlet();
            defaultServlet.service(request, response);
            clientSocket.close();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
