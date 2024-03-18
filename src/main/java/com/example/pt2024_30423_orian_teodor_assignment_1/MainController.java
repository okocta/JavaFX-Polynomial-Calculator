package com.example.pt2024_30423_orian_teodor_assignment_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TextField Ptextfield;
    @FXML
    private TextField Qtextfield;
    @FXML
    private TextArea Rtextfield;
    private Polynomial P;
    private Polynomial Q;

    @FXML
    private ImageView brandingImageView;
    @FXML
    private Button exitButton;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // function to set images because idk why it doesn't work directly from fxml
        File brandingFile = new File("src/images/gears-of-math-background-suitable-as-a-backdrop-for-projects-on-digital-and-computational-processes-math-and-modern-technologies-WWKXH7.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);
        Rtextfield.setVisible(false);

    }

    @FXML
    private void handleAdditionButtonClick() {//add button
        parsePolynomials();
        Polynomial result = P.add(Q);
        updateResultTextField(result);
        Rtextfield.setVisible(true);
    }

    @FXML
    private void handleSubtractionButtonClick() {//substract button
        parsePolynomials();
        Polynomial result = P.subtract(Q);
        updateResultTextField(result);
        Rtextfield.setVisible(true);
    }
    @FXML
    private void handlemultiplyButtonClick() {//multiply button
        parsePolynomials();
        Polynomial result = P.multiply(Q);
        updateResultTextField(result);
        Rtextfield.setVisible(true);
    }
    @FXML
    private void handlederiveButtonClick() {//derivebutton
        parsePolynomials();
        Polynomial result = P.derive();
        updateResultTextField(result);
        Rtextfield.setVisible(true);
    }
    @FXML
    private void handleintegrateButtonClick() {//integratebutton
        parsePolynomials();
        Polynomial result = P.integrate();
        updateResultTextField(result);
        Rtextfield.setVisible(true);
    }
    @FXML
    public void exitButtonOnAction(ActionEvent event){
        Stage stage= (Stage)  exitButton.getScene().getWindow();
        stage.close();//cancel button is pressed
    }
    @FXML
    public void divideButtonOnAction(ActionEvent event){
        parsePolynomials();
        Polynomial result = P.divide(Q);
        updateResultTextField(result);
        Rtextfield.setVisible(true);
    }

    private void parsePolynomials() {//
        P = parsePolynomial(Ptextfield.getText());//we parse the polynomials
        Q = parsePolynomial(Qtextfield.getText());
    }

    private static Polynomial parsePolynomial(String input) {
        Polynomial polynomial = new Polynomial();
        //split input  using + or -
        String[] terms = input.split("\\s*(?=[+-])");
        for (String term : terms) {
            if (!term.isEmpty()) {
                double coefficient = 1.0;
                int exponent = 0;
                //for the coeficient and exponent
                String[] parts = term.split("x\\^");

                if (parts.length > 0 && !parts[0].isEmpty()) {
                    coefficient = parseCoefficient(parts[0]);//we take the coeficient
                }
                if (parts.length > 1) {
                    exponent = Integer.parseInt(parts[1]);//we take the exponent
                } else if (term.contains("x")) {//this is for not writing x^1
                    if (term.indexOf("x") > 0) {
                        String remainingPart = term.substring(0, term.indexOf("x"));//for the coeficient of x we take what it is in front of x
                        if (!remainingPart.isEmpty()) {
                            coefficient = parseCoefficient(remainingPart);
                        }
                    }else coefficient=parseCoefficient("");//if we only have x with no coeficient
                    exponent = 1;//exponent 1 for x (x^1)
                }
                    //we add the term to the polynomial
                polynomial.addTerm(exponent, coefficient);
            }
        }
        return polynomial;
    }

    private static double parseCoefficient(String coefficient) {//function to take the coeficient
        if (coefficient.equals("")) {//if we write nothing in front of the x or x^n the coeficient is 1
            return 1.0;
        } else if (coefficient.equals("-")) {
            return -1.0;//if we only have - in front of x or x^n then the coeficient is -1
        }
        else {
            try {
                return Double.parseDouble(coefficient); //parse the coefficient to double
            } catch (NumberFormatException e) {//if the coeficient is not a number
                return 0.0;
            }
        }
    }
    private void updateResultTextField(Polynomial result) {//update the result text field with the result converted in a string
        Rtextfield.setText(result.toString());
    }
}
