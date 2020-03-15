package meseger.server.gui;

import meseger.server.core.ChatServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {
    private static final int POS_X = 1000;
    private static final int POS_Y = 550;
    private static final int HEIGHT = 100;
    private static final int WIDTH = 200;

    private final ChatServer chatServer = new ChatServer();
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerGUI();
            }
        });
        throw new RuntimeException("Hello from main");
    }

    private ServerGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        setLayout(new GridLayout(1, 2));
        btnStart.addActionListener(this);
        btnStop.addActionListener(this);

        add(btnStart);
        add(btnStop);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object srs = e.getSource();
        if (srs == btnStart) {
            chatServer.start(8189);
        } else if (srs == btnStop) {
//            chatServer.stop();
            throw new RuntimeException("Hello from EDT");
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
