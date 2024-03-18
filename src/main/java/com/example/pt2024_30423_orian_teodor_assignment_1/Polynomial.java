package com.example.pt2024_30423_orian_teodor_assignment_1;

import java.util.*;

public class Polynomial {
    private Map<Integer, Double> polinom;

    public Polynomial() {
        polinom = new TreeMap<>();
    }

    public Double getCoefficient(int exponent) {
        return polinom.get(exponent);
    }
    public int degree() {
        int maxDegree = -1;
        for (int exponent : polinom.keySet()) {
            if (exponent > maxDegree) {
                maxDegree = exponent;
            }
        }
        return maxDegree;
    }
    public void addTerm(int exponent, double coefficient) {
        boolean found = false;
        for (Map.Entry<Integer, Double> entry : polinom.entrySet()) {
            if (entry.getKey() == exponent) {
                //if we write something like p(x)=x+2x or 2 terms with the same exponent in the same polinom
                double existingCoefficient = entry.getValue();
                polinom.put(exponent, existingCoefficient + coefficient);
                found = true;
                break;
            }
        }
        if (!found) {
            polinom.put(exponent, coefficient);
        }
    }
    public Polynomial add(Polynomial other) {
        Polynomial result = new Polynomial();

        //for each
        for (Map.Entry<Integer, Double> entry : polinom.entrySet()) {
            int exponent = entry.getKey();
            double coefficient = entry.getValue();
            //add the current term to the result
            result.addTerm(exponent, coefficient);
        }
        //for each
        for (Map.Entry<Integer, Double> entry : other.polinom.entrySet()) {
            int exponent = entry.getKey();
            double coefficient = entry.getValue();
            if (result.polinom.containsKey(exponent)) {
                //add coeficients if it has the same exponent
                result.polinom.put(exponent, result.polinom.get(exponent) + coefficient);
            } else {
                //if not add the entire term
                result.addTerm(exponent, coefficient);
            }
        }
        return result;
    }

    public Polynomial subtract(Polynomial other) {
        Polynomial result = new Polynomial();
        //for each in first
        for (Map.Entry<Integer, Double> entry : polinom.entrySet()) {
            int exponent = entry.getKey();
            double coefficient = entry.getValue();
            //add the current term to the result
            result.addTerm(exponent, coefficient);
        }
        for (Map.Entry<Integer, Double> entry : other.polinom.entrySet()) {//for each in other
            int exponent = entry.getKey();
            double coefficient = entry.getValue();
            if (result.polinom.containsKey(exponent)) {
                //if the exponent already exists we substract the  coeficients
                result.polinom.put(exponent, result.polinom.get(exponent) - coefficient);
            } else {
                //if not we substract the entire term
                result.addTerm(exponent, -coefficient);
            }
        }
        return result;
    }
    public Polynomial multiply(Polynomial other){//multiply
        Polynomial result= new Polynomial();
        for (Map.Entry<Integer, Double> entry : polinom.entrySet()){//for each set in the first polynomial we add the exponents and multply the coeficients
            int exponent1=entry.getKey();
            double coeficient1=entry.getValue();
            for (Map.Entry<Integer, Double> entry2 : other.polinom.entrySet()){
                int exponent2=entry2.getKey();
                double coeficient2=entry2.getValue();
                result.addTerm(exponent1+exponent2,coeficient1*coeficient2);

            }
        }

        return result;
    }
    public Polynomial derive(){
        Polynomial result=new Polynomial();
        for(Map.Entry<Integer,Double> entry :polinom.entrySet()){
            int exponent= entry.getKey();
            double coeficient=entry.getValue();
            if(exponent!=0)
            {
                result.addTerm(exponent-1,coeficient*exponent);
            }else{
                result.addTerm(0,0);
            }
        }
        return result;
    }
    public Polynomial integrate(){
        Polynomial result=new Polynomial();
        for(Map.Entry<Integer,Double> entry :polinom.entrySet()){
            int exponent= entry.getKey();
            double coeficient=entry.getValue();
                result.addTerm(exponent+1,coeficient/(exponent+1));
        }
        return result;
    }
    public void copyFrom(Polynomial other) {
        polinom.clear(); //clear the current polynomial
        for (Map.Entry<Integer, Double> entry : other.polinom.entrySet()) {
            polinom.put(entry.getKey(), entry.getValue());
        }
    }

    public Polynomial divide(Polynomial divisor) {
        if (divisor.getCoefficient(divisor.degree()) == 0.0) {
            throw new ArithmeticException("Division by zero is not allowed!!!!");
        }
        Polynomial quotient = new Polynomial(); //quotient polynomial
        Polynomial remainder = new Polynomial(); //remainder polynomial
        remainder.copyFrom(this); //remainder=ala ce l impartim
        while (remainder.degree() >= divisor.degree()) {
            int exponentDifference = remainder.degree() - divisor.degree();
            double coefficient = remainder.getCoefficient(remainder.degree()) / divisor.getCoefficient(divisor.degree());
            //multiply the divisor by the quotient of leading terms
            Polynomial term = new Polynomial();
            for (Map.Entry<Integer, Double> entry : divisor.polinom.entrySet()) {
                int termExponent = entry.getKey() + exponentDifference;
                double termCoefficient = entry.getValue() * coefficient;
                term.addTerm(termExponent, termCoefficient);
            }
            quotient.addTerm(exponentDifference, coefficient);
            remainder = remainder.subtract(term); //remainder-term
            //scadem degree
            while (remainder.degree() >= 0 && remainder.getCoefficient(remainder.degree()) == 0) {
                remainder.polinom.remove(remainder.degree());
            }
        }
        return quotient;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        boolean isFirstTerm = true;
        List<Map.Entry<Integer, Double>> entries = new ArrayList<>(polinom.entrySet());
        Collections.reverse(entries);
        //for each
        for (Map.Entry<Integer, Double> entry : entries) {
            int exponent = entry.getKey();
            double coefficient = entry.getValue();
            //skip the terms with coefficient =0
            if (coefficient == 0.0) {
                continue;
            }
            //add + in between the terms
            if (!isFirstTerm) {
                result.append(" + ");
            }
            // Handle coefficients and signs explicitly
            if (coefficient < 0.0) { // Ensure a negative coefficient is always preceded by a "-"
                result.append("-");
                coefficient = -coefficient;
            }

            if (coefficient != 1.0 || exponent == 0) {
                result.append(coefficient);
            }

            if (exponent > 0) {
                result.append("x");
                if (exponent > 1) {
                    result.append("^").append(exponent);
                }
            }

            isFirstTerm = false;
        }
        //if the string is empty then the polynomial is 0
        if (result.length() == 0) {
            return "0";
        }
        return result.toString();
    }

}
