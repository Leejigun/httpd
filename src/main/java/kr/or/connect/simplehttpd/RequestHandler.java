package kr.or.connect.simplehttpd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestHandler {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    public Request handle(Socket clientSocket) {
        Request request = null;
        try {
            InputStream socketIn = clientSocket.getInputStream();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socketIn));
            request = new Request();
            String inputLine = in.readLine();
            request.parseRequestLine(inputLine);
            while (!"".equals(inputLine)) {
                inputLine = in.readLine();
                request.addHeader(inputLine);
            }
            request.setBodyInput(socketIn); // socketInputStream은 body부터 읽어들일 수 있다.
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return request;
    }
}
