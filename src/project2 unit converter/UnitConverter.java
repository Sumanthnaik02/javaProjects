package project2;

import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class UnitConverter extends JFrame {
    private final JTextField inputfield;
    private final JTextField outputfield;
    private final JComboBox<String> unitFrom;
    private final JComboBox<String> unitTo;

    UnitConverter() {

        setTitle("Unit Converter");
        setSize(400, 250);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);//exit on program close
        setResizable(false);
        setLocationRelativeTo(null);
        ;//use manual layout

        JLabel inputLabel = new JLabel("Enter Value");
        inputLabel.setBounds(50, 30, 100, 30);
        add(inputLabel);

        inputfield = new JTextField();
        inputfield.setBounds(150, 30, 150, 30);
        add(inputfield);

        String[] units = {"Meters", "Kilometers", "Miles", "Feet", "Inches",
                "Grams", "Kilograms", "Pounds",
                "Celsius", "Fahrenheit", "Kelvin"};
        unitFrom = new JComboBox<>(units);
        unitFrom.setBounds(50, 70, 120, 30);
        add(unitFrom);

        unitTo = new JComboBox<>(units);
        unitTo.setBounds(180, 70, 120, 30);
        add(unitTo);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(100, 120, 150, 30);
        add(convertButton);

        JLabel inputLabel1 = new JLabel("Result:");
        inputLabel1.setBounds(50, 160, 100, 30);
        add(inputLabel1);

        outputfield = new JTextField();
        outputfield.setBounds(100, 160, 150, 30);
        outputfield.setEditable(false);
        add(outputfield);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });
        setVisible(true);
    }

    private void convert() {
        try {
            double input = Double.parseDouble(inputfield.getText());
            String fromunit = (String) unitFrom.getSelectedItem();
            String tounit = (String) unitTo.getSelectedItem();

            double result = convertUnits(input, fromunit, tounit);
            outputfield.setText(String.format("%.4f", result));//show digits
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Input. Please enter a number.");
        }catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public double convertUnits(double value, String from, String to) {
        // Define valid categories
        String[] lengthUnits = {"Meters", "Kilometers", "Miles", "Feet", "Inches"};
        String[] weightUnits = {"Grams", "Kilograms", "Pounds"};
        String[] tempUnits = {"Celsius", "Fahrenheit", "Kelvin"};

        // Check category of 'from' and 'to' units
        boolean isLength = Arrays.asList(lengthUnits).contains(from) && Arrays.asList(lengthUnits).contains(to);
        boolean isWeight = Arrays.asList(weightUnits).contains(from) && Arrays.asList(weightUnits).contains(to);
        boolean isTemp = Arrays.asList(tempUnits).contains(from) && Arrays.asList(tempUnits).contains(to);

        // If 'from' and 'to' don't belong to the same category, throw an error
        if (!isLength && !isWeight && !isTemp) {
            throw new IllegalArgumentException("Cannot convert " + from + " to " + to);
        }

        // Length Conversion (Convert to Meters First)
        if (isLength) {
            double meters = switch (from) {
                case "Meters" -> value;
                case "Kilometers" -> value * 1000;
                case "Miles" -> value * 1609.34;
                case "Feet" -> value * 0.3048;
                case "Inches" -> value * 0.0254;
                default -> value;
            };
            return switch (to) {
                case "Meters" -> meters;
                case "Kilometers" -> meters / 1000;
                case "Miles" -> meters / 1609.34;
                case "Feet" -> meters / 0.3048;
                case "Inches" -> meters / 0.0254;
                default -> value;
            };
        }

        // Weight Conversion (Convert to Grams First)
        if (isWeight) {
            double grams = switch (from) {
                case "Grams" -> value;
                case "Kilograms" -> value * 1000;
                case "Pounds" -> value * 453.592;
                default -> value;
            };
            return switch (to) {
                case "Grams" -> grams;
                case "Kilograms" -> grams / 1000;
                case "Pounds" -> grams / 453.592;
                default -> value;
            };
        }

        // Temperature Conversion
        if (isTemp) {
            if (from.equals(to)) return value;

            return switch (from) {
                case "Celsius" -> switch (to) {
                    case "Fahrenheit" -> (value * 9 / 5) + 32;
                    case "Kelvin" -> value + 273.15;
                    default ->  throw new IllegalArgumentException("Invalid temperature unit: " + to);
                };
                case "Fahrenheit" -> switch (to) {
                    case "Celsius" -> (value - 32) * 5 / 9;
                    case "Kelvin" -> (value - 32) * 5 / 9 + 273.15;
                    default ->  throw new IllegalArgumentException("Invalid temperature unit: " + to);
                };
                case "Kelvin" -> switch (to) {
                    case "Celsius" -> value - 273.15;
                    case "Fahrenheit" -> (value - 273.15) * 9 / 5 + 32;
                    default ->  throw new IllegalArgumentException("Invalid temperature unit: " + to);
                };
                default ->  throw new IllegalArgumentException("Invalid temperature unit: " + from );
            };
        }

        // This should never be reached
        throw new IllegalArgumentException("Unknown error while converting.");
    }

    public static void main(String[] args) {
        new UnitConverter();
        //SwingUtilities.invokeLater(UnitConverter::new);
    }
}


//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//@Entity
//@Table(name = "DMS_PROPERTIES_DETAILS")
//public class DMSPropertiesDetails {
//    @Id
//    @Column(name = "PARAM_NAME")
//    private String paramName;
//
//    @Column(name = "PARAM_VALUE")
//    private String paramValue;
//    public String getParamName() {
//        return this.paramName;
//    }