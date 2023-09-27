import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ImagePreview {
    private JFrame frame;

    public ImagePreview(String title, String path) {
        frame = new JFrame(title);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addFrameContent();

        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.pack();
    }

    public void open() {
        frame.setVisible(true);
    }

    private void addFrameContent() {

        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("picture.png");
        frame.add(title, BorderLayout.NORTH);

        JButton arrowLeft = new JButton("<");
        frame.add(arrowLeft, BorderLayout.WEST);

        JLabel img = new JLabel("picture.png");
        ImageIcon icon = new ImageIcon("timor1.png");
        img.setIcon(icon);
        frame.add(img, BorderLayout.CENTER);

        JButton arrowRight = new JButton(">");
        frame.add(arrowRight, BorderLayout.EAST);

        JButton button = new JButton("Update");
        frame.add(button, BorderLayout.SOUTH);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("press");
            }
        });
    }

    public static void main(String[] args) {
        ImagePreview window = new ImagePreview("images", "lol");
        window.open();
    }
}
