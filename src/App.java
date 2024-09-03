public class App {
    public static void main(String[] args) throws Exception {
        ServerWindow server = new ServerWindow();
        new ClientGUI(server);
        new ClientGUI(server);
    }
}
