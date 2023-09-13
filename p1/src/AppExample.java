import javax.swing.*;
import java.awt.*;

public class AppExample {

    private JFrame frame;

    public AppExample() {
        frame = new JFrame("Example");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        addFrameContent();
        // frame.setSize(300,200);
        frame.pack();
        frame.setVisible(true);
    }

    private void addFrameContent(){
        frame.setLayout(new GridLayout(2,1));

        JPanel pnLabels = new JPanel(new FlowLayout());
        JPanel pnButtons = new JPanel(new GridLayout(2,1));

        JLabel lb1 = new JLabel("Label 1");
        JLabel lb2 = new JLabel("Label 2");
        JLabel lb3 = new JLabel("Label 3");

        JButton bt1 = new JButton("Button 1");
        JButton bt2 = new JButton("Button 2");

        frame.add(pnLabels);
        frame.add(pnButtons);

        // FlowLayout and GridLayout
        pnLabels.add(lb1);
        pnLabels.add(lb2);
        pnLabels.add(lb3);

        pnButtons.add(bt1);
        pnButtons.add(bt2);

        // BorderLayout
        // frame.add(lb1, BorderLayout.NORTH);
        // frame.add(lb2, BorderLayout.CENTER);
        // frame.add(lb3, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        AppExample app = new AppExample();
    }
}
