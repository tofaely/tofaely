import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Calculator extends JFrame implements ActionListener {
    private JTextField display; // Text field to display input and results
    private String currentInput = ""; // Stores the current input as a string
    private double result = 0; // Stores the result of calculations
    private String lastOperator = ""; // Stores the last operator pressed

    // Constructor to set up the calculator GUI
    public Calculator() {
        // Set up the JFrame (main window)
        setTitle("Calculator"); // Set the title of the window
        setSize(350, 450); // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when the window is closed
        setLayout(new BorderLayout()); // Use BorderLayout for the main layout
        getContentPane().setBackground(new Color(240, 240, 240)); // Set background color

        // Display (JTextField)
        display = new JTextField();
        display.setEditable(false); // Make the display non-editable
        display.setFont(new Font("Arial", Font.BOLD, 32)); // Set the font for the display
        display.setHorizontalAlignment(JTextField.RIGHT); // Align text to the right
        display.setBackground(new Color(255, 255, 255)); // Set background color
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        add(display, BorderLayout.NORTH); // Add the display to the top of the window

        // Buttons (JPanel with GridLayout)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10)); // 4x4 grid with 10px gaps
        buttonPanel.setBackground(new Color(240, 240, 240)); // Set background color
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Button labels
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        // Create buttons and add them to the panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font for the button
            button.setBackground(new Color(220, 220, 220)); // Set button background color
            button.setForeground(new Color(50, 50, 50)); // Set button text color
            button.setFocusPainted(false); // Remove focus border
            button.setBorder(BorderFactory.createRaisedSoftBevelBorder()); // Add a raised border
            button.addActionListener(this); // Add an action listener to handle button clicks

            // Customize the "=" button
            if (text.equals("=")) {
                button.setBackground(new Color(100, 150, 255)); // Set a different color for "="
                button.setForeground(Color.WHITE); // Set text color to white
            }

            // Customize operator buttons
            if (text.equals("/") || text.equals("*") || text.equals("-") || text.equals("+")) {
                button.setBackground(new Color(180, 180, 180)); // Set a different color for operators
            }

            buttonPanel.add(button); // Add the button to the panel
        }

        add(buttonPanel, BorderLayout.CENTER); // Add the button panel to the center of the window
    }

    // Handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Get the label of the button clicked

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            // If the button is a number (0-9) or a decimal point
            currentInput += command; // Append the input to the current input string
            display.setText(currentInput); // Update the display with the current input
        } else if (command.equals("=")) {
            // If the button is "=", calculate the result
            calculateResult(); // Perform the calculation
            display.setText(String.valueOf(result)); // Display the result
            currentInput = ""; // Reset the current input
            lastOperator = ""; // Reset the last operator
        } else {
            // If the button is an operator (+, -, *, /)
            if (!currentInput.isEmpty()) {
                calculateResult(); // Perform the calculation with the current input
                lastOperator = command; // Store the operator for the next calculation
                currentInput = ""; // Reset the current input
            }
        }
    }

    // Perform calculations based on the last operator and current input
    private void calculateResult() {
        if (!currentInput.isEmpty()) {
            double inputNumber = Double.parseDouble(currentInput); // Convert the current input to a number

            switch (lastOperator) {
                case "+":
                    result += inputNumber; // Addition
                    break;
                case "-":
                    result -= inputNumber; // Subtraction
                    break;
                case "*":
                    result *= inputNumber; // Multiplication
                    break;
                case "/":
                    if (inputNumber != 0) {
                        result /= inputNumber; // Division (check for division by zero)
                    } else {
                        display.setText("Error"); // Display error if dividing by zero
                        return;
                    }
                    break;
                default:
                    result = inputNumber; // If no operator, set the result to the current input
                    break;
            }
        }
    }

    // Main method to start the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator(); // Create an instance of the calculator
            calculator.setVisible(true); // Make the calculator window visible
        });
    }
}