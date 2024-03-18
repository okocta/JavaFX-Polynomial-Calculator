package com.example.pt2024_30423_orian_teodor_assignment_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    @Test
    void testAddTerm() {
        Polynomial polynomial = new Polynomial();
        polynomial.addTerm(2, 3.0); // Add term 3x^2
        polynomial.addTerm(3, 5.0); // Add term 5x^3
        polynomial.addTerm(2, 2.0); // Add term 2x^2 (should merge with existing term)
        assertEquals("5.0x^3 + 5.0x^2", polynomial.toString());
    }

    @Test
    void testAdd() {
        Polynomial p1 = new Polynomial();
        p1.addTerm(2, 3.0); // 3x^2
        p1.addTerm(1, 2.0); // 2x

        Polynomial p2 = new Polynomial();
        p2.addTerm(3, 1.0); // x^3
        p2.addTerm(1, 3.0); // 3x

        Polynomial sum = p1.add(p2); // (3x^2 + 2x) + (x^3 + 3x)
        assertEquals("x^3 + 3.0x^2 + 5.0x", sum.toString());
    }

    @Test
    void testSubtract() {
        Polynomial p1 = new Polynomial();
        p1.addTerm(2, 3.0); // 3x^2
        p1.addTerm(1, 2.0); // 2x

        Polynomial p2 = new Polynomial();
        p2.addTerm(3, 1.0); // x^3
        p2.addTerm(1, 3.0); // 3x

        Polynomial diff = p1.subtract(p2); // (3x^2 + 2x) - (x^3 + 3x)
        assertEquals("-x^3 + 3.0x^2 + -x", diff.toString());
    }

    @Test
    void testMultiply() {
        Polynomial p1 = new Polynomial();
        p1.addTerm(2, 3.0); // 3x^2
        p1.addTerm(1, 2.0); // 2x

        Polynomial p2 = new Polynomial();
        p2.addTerm(3, 1.0); // x^3
        p2.addTerm(1, 3.0); // 3x

        Polynomial product = p1.multiply(p2); // (3x^2 + 2x) * (x^3 + 3x)
        assertEquals("3.0x^5 + 2.0x^4 + 9.0x^3 + 6.0x^2", product.toString());
    }

    @Test
    void testDerive() {
        Polynomial polynomial = new Polynomial();
        polynomial.addTerm(2, 3.0); // 3x^2
        polynomial.addTerm(1, 2.0); // 2x

        Polynomial derivative = polynomial.derive(); // Derivative of 3x^2 + 2x
        assertEquals("6.0x + 2.0", derivative.toString());
    }

    @Test
    void testIntegrate() {
        Polynomial polynomial = new Polynomial();
        polynomial.addTerm(1, 2.0); // 2x

        Polynomial integral = polynomial.integrate(); // Integral of 2x
        assertEquals("x^2", integral.toString());
    }

    @Test
    void testDivide() {
        Polynomial dividend = new Polynomial();
        dividend.addTerm(2, 6.0); // 6x^2
        dividend.addTerm(1, 5.0); // 5x
        dividend.addTerm(0, 2.0); // 2

        Polynomial divisor = new Polynomial();
        divisor.addTerm(1, 2.0); // 2x
        divisor.addTerm(0, 1.0); // 1

        Polynomial quotient = dividend.divide(divisor); // (6x^2 + 5x + 2) / (2x + 1)
        assertEquals("3.0x + 1.0", quotient.toString());
    }
}
