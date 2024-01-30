
package com.ontariotechu.sofe3980U;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.joda.time.LocalTime;

/* the main application */
public class App {
    public static void main(String[] args) {
        // Create the frame on the event dispatching thread
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Binary Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create UI components
        JTextField firstBinaryInput = new JTextField(10);
        JTextField secondBinaryInput = new JTextField(10);
        JButton addButton = new JButton("Add");
        JButton orButton = new JButton("OR");
        JButton andButton = new JButton("AND");
        JButton multiplyButton = new JButton("Multiply");
        JLabel resultLabel = new JLabel("Result: ");

        // Layout
        frame.setLayout(new FlowLayout());
        frame.add(new JLabel("First Binary: "));
        frame.add(firstBinaryInput);
        frame.add(new JLabel("Second Binary: "));
        frame.add(secondBinaryInput);
        frame.add(addButton);
        frame.add(orButton);
        frame.add(andButton);
        frame.add(multiplyButton);
        frame.add(resultLabel);

        // Action Listeners
        addButton.addActionListener(e -> {
            Binary binary1 = new Binary(firstBinaryInput.getText());
            Binary binary2 = new Binary(secondBinaryInput.getText());
            Binary result = Binary.add(binary1, binary2);
            resultLabel.setText("Result: " + result.getValue());
        });

        orButton.addActionListener(e -> {
            Binary binary1 = new Binary(firstBinaryInput.getText());
            Binary binary2 = new Binary(secondBinaryInput.getText());
            Binary result = Binary.OR(binary1, binary2);
            resultLabel.setText("Result: " + result.getValue());
        });

        andButton.addActionListener(e -> {
            Binary binary1 = new Binary(firstBinaryInput.getText());
            Binary binary2 = new Binary(secondBinaryInput.getText());
            Binary result = Binary.AND(binary1, binary2);
            resultLabel.setText("Result: " + result.getValue());
        });

        multiplyButton.addActionListener(e -> {
            Binary binary1 = new Binary(firstBinaryInput.getText());
            Binary binary2 = new Binary(secondBinaryInput.getText());
            Binary result = Binary.Multiply(binary1, binary2);
            resultLabel.setText("Result: " + result.getValue());
        });

        // Display the window
        frame.setVisible(true);
    }
}
