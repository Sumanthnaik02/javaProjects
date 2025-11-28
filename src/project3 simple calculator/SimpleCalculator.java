package project3;

import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class SimpleCalculator extends JFrame {
    private final JTextField inputfield1;
    private final JTextField inputfield2;
    private final JTextField outputfield;
    private final JComboBox<String> symbols;
    SimpleCalculator() {

        setTitle("Simple calculator");
        setSize(400, 250);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);//exit on program close
        setResizable(false);
        setLocationRelativeTo(null);
        ;//use manual layout

        //add a input label
        JLabel inputLabel1 = new JLabel("input A:");
        inputLabel1.setBounds(25, 30, 100, 30);
        add(inputLabel1);

        //adds a input field
        inputfield1 = new JTextField();
        inputfield1.setBounds(70, 30, 75, 30);
        add(inputfield1);

        //adds a downscroll for symol
        String[] units = {"+","-","*","/","%"};
        symbols = new JComboBox<>(units);
        symbols.setBounds(150, 30, 60, 30);
        add(symbols);

        //adds b input label
        JLabel inputLabel2 = new JLabel("input B:");
        inputLabel2.setBounds(215, 30, 100, 30);
        add(inputLabel2);

        //adds b input field
         inputfield2 = new JTextField();
         inputfield2.setBounds(263, 30, 75, 30);
         add(inputfield2);

         //adds a convert button
        JButton convertButton = new JButton("Calculate");
        convertButton.setBounds(125, 100, 150, 30);
        add(convertButton);

        //ouput label for result
        JLabel outputLabel1 = new JLabel("Result:");
        outputLabel1.setBounds(50, 150, 100, 30);
        add(outputLabel1);

        //output result field
        outputfield = new JTextField();
        outputfield.setBounds(125, 150, 150, 30);
        outputfield.setEditable(false);
        add(outputfield);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
        setVisible(true);
    }

    private void calculate() {
        try {
            double inputA = Double.parseDouble(inputfield1.getText());
            double inputB = Double.parseDouble(inputfield2.getText());
            String symbol= (String) symbols.getSelectedItem();

            if (symbol.equals("/") && inputB == 0) {
                throw new ArithmeticException("Cannot divide by zero!");
            }
            double result = DoOperation(inputA, inputB, symbol);
            outputfield.setText(String.format("%.4f", result));//show digits
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error:"+"`Enter a valid input","Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,"Error:" + e.getMessage(),"Invalid Operation", JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Math Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double DoOperation(double valueA,double valueB, String symbol) {
        double x;
         switch (symbol){
             case("+")  ->x=valueA+valueB;
             case("-") ->x=valueA-valueB;
             case("*") ->x=valueA*valueB;
             case("/") ->x=valueA/valueB;
             case("%") -> x=valueA%valueB;
             default -> throw new IllegalArgumentException("Invalid operation selected.");
         };
         return x;
        }

    public static void main(String[] args) {
        new SimpleCalculator();
        //SwingUtilities.invokeLater(UnitConverter::new);
    }
}
