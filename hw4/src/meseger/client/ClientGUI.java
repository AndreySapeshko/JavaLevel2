package meseger.client;

import meseger.server.gui.ServerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {

    private static final int WIDHT = 400;
    private static final int HEIGTH = 300;

    private final JTextArea log = new JTextArea();
    private final JPanel topPanel = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIpAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final JTextField tfLogin = new JTextField("Ivan");
    private final JPasswordField pfPassword = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel bottomPanel = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField tfMesseg = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private final JList<String> userList = new JList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
        throw new RuntimeException("Hello from main");
    }

    private ClientGUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(new Dimension(WIDHT, HEIGTH));
        setResizable(false);
        setTitle("Chat client");
        String[] users = {"user1", "user2", "user3", "user4", "user5",
                "user_with_an_exceptionally_long_name_in_this_chat"};
        userList.setListData(users);
        log.setEditable(false);
        JScrollPane scrolLog = new JScrollPane(log);
        JScrollPane scrolUsers = new JScrollPane(userList);
        scrolUsers.setPreferredSize(new Dimension(100, 0));
        cbAlwaysOnTop.addActionListener(this);
        tfMesseg.addActionListener(this);
        btnSend.addActionListener(this);

        topPanel.add(tfIpAddress);
        topPanel.add(tfPort);
        topPanel.add(cbAlwaysOnTop);
        topPanel.add(tfLogin);
        topPanel.add(pfPassword);
        topPanel.add(btnLogin);
        bottomPanel.add(btnDisconnect, BorderLayout.WEST);
        bottomPanel.add(tfMesseg, BorderLayout.CENTER);
        bottomPanel.add(btnSend, BorderLayout.EAST);


        add(scrolLog, BorderLayout.CENTER);
        add(scrolUsers, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object srs = e.getSource();
        if (srs == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if ( srs == tfMesseg) {
            String msg = tfMesseg.getText() + "\n";
            try {
                FileOutputStream fos = new FileOutputStream("C:\\GB\\java\\java2\\hw4\\src\\meseger\\client\\chatlog.txt", true);
                fos.write(msg.getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            log.append(msg);
            tfMesseg.setText("");
        } else if (srs == btnSend) {
            String msg = tfMesseg.getText() + "\n";
            try {
                FileOutputStream fos = new FileOutputStream("C:\\GB\\java\\java2\\hw4\\src\\meseger\\client\\chatlog.txt", true);
                fos.write(msg.getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            log.append(msg);
            tfMesseg.setText("");
        } else {
            throw new RuntimeException("Unknown source: " + srs);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String msg;
        e.printStackTrace();
        StackTraceElement[] ste = e.getStackTrace();
        msg = "Exception in the thread " + t.getName() + " " + e.getClass().getCanonicalName() + ": " +
                e.getMessage() + "\n\t" + ste[0];
        JOptionPane.showMessageDialog(null, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}
