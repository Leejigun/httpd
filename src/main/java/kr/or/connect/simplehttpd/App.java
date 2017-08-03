package kr.or.connect.simplehttpd;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer(8080);
        httpServer.run();
    }
}
