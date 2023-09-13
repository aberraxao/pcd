import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyFrame {
    private JFrame frame;

    public MyFrame(String title, int width, int height) {
        frame = new JFrame(title);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addFrameContent();

        frame.setSize(width, height);

        frame.pack();
    }

    public void open() {
        frame.setVisible(true);
    }

    private void addFrameContent() {

        frame.setLayout(new GridLayout(4, 1));

        JTextField title = addPanel("title", "Hello");
        JTextField width = addPanel("width", "300");
        JTextField height = addPanel("height", "150");

        addBottomPanel(title, width, height);
    }

    private JTextField addPanel(String lb, String txt) {
        JPanel pnTitle = new JPanel(new GridLayout(1, 2));
        frame.add(pnTitle);
        JLabel label = new JLabel(lb);
        pnTitle.add(label);
        JTextField text = new JTextField(txt);
        pnTitle.add(text);
        return text;
    }

    private void addBottomPanel( JTextField title, JTextField width, JTextField height) {
        JPanel pnBottom = new JPanel(new GridLayout(1, 2));
        frame.add(pnBottom);

        JButton button = new JButton("update");
        JCheckBox check = new JCheckBox("center");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setTitle(title.getText());
                frame.setSize(Integer.parseInt(width.getText()), Integer.parseInt(height.getText()));
                if (check.isSelected())
                    frame.setLocationRelativeTo(null);
            }
        });

        pnBottom.add(button);
        pnBottom.add(check);
    }


    public static void main(String[] args) {
        MyFrame window = new MyFrame("Hello", 300, 150);
        window.open();
    }
}
