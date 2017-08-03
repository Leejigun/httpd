package kr.or.connect.simplehttpd;

import java.io.*;
import java.net.Socket;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.MimetypesFileTypeMap;

public class ResponseHandler {
    private static final Logger log = LoggerFactory.getLogger(ResponseHandler.class);

    public Response handle(String path, Socket clientSocket) {
        Response response = null;
        try {
            OutputStream clientOut = clientSocket.getOutputStream();

            File f = new File("webapp/" + path);
            if(!f.exists()){ // 파일이 존재하지 않을 경우
                response = new Response();
                response.setStatusCode("404");
                response.setReasonPhrase("Not found");
                response.addHeader("Content-Type: text/html");
                File file = new File("webapp/404.html");
                response.addHeader("Content-Length: " + file.length());
            }else {
                response = new Response();
                response.setStatusCode("200");
                response.setReasonPhrase("OK");
                Path source = Paths.get(f.getAbsolutePath());
                response.addHeader("Content-Type: " + Files.probeContentType(source));
                response.addHeader("Server: 127.0.0.1");

                response.addHeader("Content-Length: " + f.length());
            }

            StringBuffer sb = new StringBuffer();
            sb.append(response.getStatusLine());
            sb.append("\n");
            sb.append(response.getHeaderString());
            sb.append("\n");
            clientOut.write(sb.toString().getBytes());

            response.setOutputStream(clientOut);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage());
        }
        return response;
    }
}
