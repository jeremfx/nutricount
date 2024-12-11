package userinterface.web.technical;

import userinterface.web.WebServer;

public class Main {
    public static void main(String[] args) {
        WebServer ws = new WebServer();
        ws.start(80);
    }
}
