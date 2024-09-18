import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.*;

// Exercício 2
public class ImagePreview {
    private final JFrame frame;
    private File[] files;
    private final String path;
    private int currentPosition = 0;

    public ImagePreview(String title, String path) {
        this.path = path;
        files = getFiles();

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

    private File[] getFiles() {
        return new File(path).listFiles(new FileFilter() {
            public boolean accept(File f) {
                return f.getName().endsWith(".jpg");
            }
        });
    }

    private String getFileNameAtPosition(int position) {
        if (files.length == 0) return "Não há imagens";
        else return files[position].getName();
    }

    private ImageIcon getImageIconAtPosition(int position) {
        return new ImageIcon(files[position].getPath());
    }

    private void changeFrameContent(JLabel title, JLabel img) {
        if (currentPosition < 0 || currentPosition >= files.length) {
            title.setText(null);
            img.setIcon(null);
            img.setText("Fim das imagens :(");
        } else {
            title.setText(getFileNameAtPosition(currentPosition));
            img.setIcon(getImageIconAtPosition(currentPosition));
            img.setText(null);
        }
    }

    private void addFrameContent() {
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel(getFileNameAtPosition(currentPosition));
        frame.add(title, BorderLayout.NORTH);

        JLabel img = new JLabel(getImageIconAtPosition(currentPosition));
        frame.add(img, BorderLayout.CENTER);

        JButton arrowLeft = new JButton("<");
        frame.add(arrowLeft, BorderLayout.WEST);

        JButton arrowRight = new JButton(">");
        frame.add(arrowRight, BorderLayout.EAST);

        JButton button = new JButton("Update");
        frame.add(button, BorderLayout.SOUTH);

        arrowLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPosition--;
                changeFrameContent(title, img);
            }
        });

        arrowRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPosition++;
                changeFrameContent(title, img);
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                files = getFiles();
                currentPosition = 0;
                changeFrameContent(title, img);
            }
        });
    }

    public static void main(String[] args) {
        String path = "src/images";
        ImagePreview window = new ImagePreview("images", path);
        window.open();
    }
}
