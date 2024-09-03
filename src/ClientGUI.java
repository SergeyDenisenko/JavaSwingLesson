import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ClientGUI extends JFrame{

    private static final int WIDTH = 555;
    private static final int HEIGHT = 500;
    private String login;
    ServerWindow srv;

    ClientGUI(ServerWindow server) {
        srv = server;
        setSize(WIDTH, HEIGHT);
        setResizable(true);

        setTitle("Chat client");
        setVisible(true);

        JTextField ipField = new JTextField("127.0.0.1");
        JTextField portField = new JTextField("8189");
        JTextField nameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton btnLogin = new JButton("login");
        JPanel splitPane = new JPanel();

        JPanel panelTop = new JPanel(new GridLayout(2, 3));
        panelTop.add(ipField);
        panelTop.add(portField);
        panelTop.add(splitPane);
        panelTop.add(nameField);
        panelTop.add(passwordField);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        JPanel panelCenter = new JPanel(new GridLayout(1, 1));
        JLabel messages = new JLabel("");
        panelCenter.add(messages);
        add(panelCenter, BorderLayout.CENTER);

        JPanel panelBottom = new JPanel(new FlowLayout());
        JTextArea messageField = new JTextArea(1, 40);
        JButton btnSend = new JButton("Send");

        panelBottom.add(messageField);
        panelBottom.add(btnSend);
        add(panelBottom, BorderLayout.SOUTH);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login = nameField.getText();
                System.out.println("Login set");
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                messageField.setText("");
                server.send(login, message);

                String text = srv.readMessages(login);
                messages.setText(text);
                System.out.println(text);
            }
        });
    }
}
