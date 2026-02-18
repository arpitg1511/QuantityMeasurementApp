package com.apps.quantitymeasurement.inch_equality;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    /* ========== FEET TESTS ========== */

    @Test
    void testFeetEquality_SameValue() {
        assertTrue(QuantityMeasurementApp.compareFeet(1.0, 1.0),
                "1.0 ft should equal 1.0 ft");
    }

    @Test
    void testFeetEquality_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compareFeet(1.0, 2.0),
                "1.0 ft should not equal 2.0 ft");
    }

    @Test
    void testFeetEquality_SameReference() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertTrue(f.equals(f), "Object must equal itself");
    }

    @Test
    void testFeetEquality_NullComparison() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(f.equals(null), "Feet should not equal null");
    }

    @Test
    void testFeetEquality_NonNumericInput() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(f.equals("Invalid"),
                "Feet should not equal non-Feet object");
    }

    /* ========== INCHES TESTS ========== */

    @Test
    void testInchesEquality_SameValue() {
        assertTrue(QuantityMeasurementApp.compareInches(1.0, 1.0),
                "1.0 inch should equal 1.0 inch");
    }

    @Test
    void testInchesEquality_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compareInches(1.0, 2.0),
                "1.0 inch should not equal 2.0 inch");
    }

    @Test
    void testInchesEquality_SameReference() {
        QuantityMeasurementApp.Inches i =
                new QuantityMeasurementApp.Inches(1.0);

        assertTrue(i.equals(i), "Object must equal itself");
    }

    @Test
    void testInchesEquality_NullComparison() {
        QuantityMeasurementApp.Inches i =
                new QuantityMeasurementApp.Inches(1.0);

        assertFalse(i.equals(null), "Inches should not equal null");
    }

    @Test
    void testInchesEquality_NonNumericInput() {
        QuantityMeasurementApp.Inches i =
                new QuantityMeasurementApp.Inches(1.0);

        assertFalse(i.equals(123),
                "Inches should not equal non-Inches object");
    }
}
