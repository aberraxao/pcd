import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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

    private void addFrameContent() {
        frame.setLayout(new GridLayout(2, 1));

        JPanel pnLabels = new JPanel(new FlowLayout());
        JPanel pnButtons = new JPanel(new GridLayout(2, 1));

        JLabel lb1 = new JLabel("Label 1");
        JLabel lb2 = new JLabel("Label 2");
        JLabel lb3 = new JLabel("Label 3");

        JButton bt1 = new JButton("Button 1");
        JButton bt2 = new JButton("Button 2");
        JButton bt3 = new JButton("Button 3");

        frame.add(pnLabels);
        frame.add(pnButtons);

        // FlowLayout and GridLayout
        pnLabels.add(lb1);
        pnLabels.add(lb2);
        pnLabels.add(lb3);

        pnButtons.add(bt1);
        pnButtons.add(bt2);
        pnButtons.add(bt3);

        // BorderLayout
        // frame.add(lb1, BorderLayout.NORTH);
        // frame.add(lb2, BorderLayout.CENTER); // se não tiver uma componente a ocupar o espaço, o centro toma conta
        // frame.add(lb3, BorderLayout.SOUTH);

        // Event Handlers:
        // Bt1 - Internal class
        bt1.addActionListener(new ListenerBt1());

        // Bt2 - Anonymous class - prof suggestion
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(frame, "Bt2 pressed!");

            }
        });

        // Bt3 - Lambda expression
        bt3.addActionListener(actionEvent -> JOptionPane.showMessageDialog(frame, "Bt3 pressed!"));
    }

    private class ListenerBt1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JOptionPane.showMessageDialog(frame, "Bt1 pressed!");

        }
    }

    public static void main(String[] args) {
        AppExample app = new AppExample();
    }
}
