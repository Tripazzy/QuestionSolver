package salsaboy.solver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Solver {
    private static JFrame frame = new JFrame();
    private static JTextArea input = new JTextArea("Input");
    private static JScrollPane scrollPane = new JScrollPane(input);
    private static JLabel output = new JLabel("Output");
    private static ArrayList<String> undo = new ArrayList<>();
    private static String oldText = "Input";
    private static ActionListener check = e -> {
        if (!(oldText.equals(input.getText()))) {
            oldText = input.getText();
            Interpretation interpretation = new Interpretation(oldText.split("\n"));
            
            if (interpretation.isValid) {
                output.setText(interpretation.toString());
            }
        }
    };
    public static void main(String[] args) {
        frame.setLayout(new GridLayout(0, 1));
        
        frame.add(scrollPane);
        frame.add(output);
        
        Timer timer = new Timer(300, check);
        timer.start();
        
        output.setHorizontalAlignment(SwingConstants.CENTER);
        
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
