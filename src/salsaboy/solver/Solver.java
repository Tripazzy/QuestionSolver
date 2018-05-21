package salsaboy.solver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Solver {
    private static JFrame frame = new JFrame();
    private static JTextArea input = new JTextArea("Input");
    private static JScrollPane scrollPane = new JScrollPane(input);
    private static JLabel output = new JLabel("Output");
    private static ArrayList<String> undo = new ArrayList<>();
    public static void main(String[] args) {
        frame.setLayout(new GridLayout(0, 1));
        
        frame.add(scrollPane);
        frame.add(output);
        
        input.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //Check for undo
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    undo.add(input.getText());
                }
                
                //Update
                if (new Interpretation(input.getText()).isValid) {
                    output.setText(new Interpretation(input.getText()).toString());
                }
            }
    
            @Override
            public void keyPressed(KeyEvent e) {
        
            }
    
            @Override
            public void keyReleased(KeyEvent e) {
        
            }
        });
        
        output.setHorizontalAlignment(SwingConstants.CENTER);
        
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
