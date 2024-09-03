import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class ServerWindow extends JFrame {

    private static final int WIDTH = 555;
    private static final int HEIGHT = 507;
    private static boolean isRun = false;

    ArrayList<ArrayList> listMessages = new ArrayList<>();

    ServerWindow () {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat server");
        setResizable(false);
        setVisible(true);

        JButton btnStart = new JButton("Start");
        JButton btnStop = new JButton("Stop");

        JPanel panBottom = new JPanel(new GridLayout(1, 2));
        panBottom.add(btnStart);
        panBottom.add(btnStop);
        add(panBottom, BorderLayout.SOUTH);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRun = true;
                System.out.println("Is run: true");
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRun = false;
                System.out.println("Is run: false");
            }
        });
    }

    public void send(String login, String message) {
        if (isRun) {
            ArrayList<String> block = new ArrayList<>();
            block.add(login);
            block.add(message);
            listMessages.add(block);
            writeLog(login, message);
        }
    }

    public String readMessages(String login) {
        String nickName, name, message, text = "<html>";

        if (isRun) {
            for (ArrayList<String> block: listMessages) {
                nickName = block.get(0);
                message = block.get(1);
                name = nickName.equals(login) ? "I : " : login+" > ";
                text += name + message +"<br>";
            }
        }
        text += "</html>";
        return text;
    }

    public void writeLog(String login, String message) {
        try(FileWriter f = new FileWriter("log.txt", true))
        {
            String line = login + " : " + message + "\n";
            f.write(line);
            f.flush();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
