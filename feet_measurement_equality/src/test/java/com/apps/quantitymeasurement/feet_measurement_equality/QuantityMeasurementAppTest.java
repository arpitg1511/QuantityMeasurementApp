package com.apps.quantitymeasurement.feet_measurement_equality;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testEquality_SameValue() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(first.equals(second), "1.0 ft should equal 1.0 ft");
    }

    @Test
    void testEquality_DifferentValue() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(2.0);

        assertFalse(first.equals(second), "1.0 ft should not equal 2.0 ft");
    }

    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(first.equals(null), "Value should not equal null");
    }

    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(first.equals(first), "Object should equal itself");
    }

    @Test
    void testEquality_NonNumericInput() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(first.equals("Not a Feet Object"),
                "Feet should not equal non-Feet object");
    }
}
