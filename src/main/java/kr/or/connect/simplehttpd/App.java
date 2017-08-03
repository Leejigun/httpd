package kr.or.connect.simplehttpd;


public class App {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer(8080);
        httpServer.run();
    }
}
