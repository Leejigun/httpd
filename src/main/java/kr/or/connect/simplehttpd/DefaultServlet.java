package kr.or.connect.simplehttpd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DefaultServlet {
    public void service(Request request, Response response){
        File f = null;

        if("404".equals(response.getStatusCode())) {
            f = new File("webapp/404.html");
        }else{
            f = new File("webapp/" + request.getRequestTarget());
        }
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int readCount = 0;
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(f);
            while((readCount = fis.read(buffer)) != -1){
                out.write(buffer,0,readCount);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try{
                request.getInputStream().close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
            try {
                out.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }


    }
}
