package client;


import common.console.ConsoleOutputer;

public class MainClient {

    public static void main(String[] args) {
        ClientApp client = new ClientApp();

        ASCIIArt.greetings(new ConsoleOutputer());
        client.runClient();
    }

}

