package com.apps.quantitymeasurement.unit_conversion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    // Yard to Yard - Same value
    @Test
    void testEquality_YardToYard_SameValue() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(1.0, LengthUnit.YARDS)
        );
    }

    // Yard to Yard - Different value
    @Test
    void testEquality_YardToYard_DifferentValue() {
        assertNotEquals(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(2.0, LengthUnit.YARDS)
        );
    }

    // Yard to Feet
    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET)
        );
    }

    // Feet to Yard
    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        assertEquals(
                new QuantityLength(3.0, LengthUnit.FEET),
                new QuantityLength(1.0, LengthUnit.YARDS)
        );
    }

    // Yard to Inches
    @Test
    void testEquality_YardToInches_EquivalentValue() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(36.0, LengthUnit.INCHES)
        );
    }

    // Centimeters to Inches
    @Test
    void testEquality_CentimetersToInches_EquivalentValue() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.CENTIMETERS),
                new QuantityLength(0.393701, LengthUnit.INCHES)
        );
    }

    // Centimeters to Feet - Not Equal
    @Test
    void testEquality_CentimetersToFeet_NonEquivalentValue() {
        assertNotEquals(
                new QuantityLength(1.0, LengthUnit.CENTIMETERS),
                new QuantityLength(1.0, LengthUnit.FEET)
        );
    }

    // Transitive Property
    @Test
    void testEquality_MultiUnit_TransitiveProperty() {

        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(36.0, LengthUnit.INCHES);

        assertEquals(yard, feet);
        assertEquals(feet, inches);
        assertEquals(yard, inches);
    }

    // Reflexive
    @Test
    void testEquality_SameReference() {
        QuantityLength yard = new QuantityLength(2.0, LengthUnit.YARDS);
        assertEquals(yard, yard);
    }

    // Null comparison
    @Test
    void testEquality_NullComparison() {
        QuantityLength yard = new QuantityLength(2.0, LengthUnit.YARDS);
        assertNotEquals(yard, null);
    }

    // Complex scenario - multiple conversions
    @Test
    void testEquality_AllUnits_ComplexScenario() {

        QuantityLength yard = new QuantityLength(2.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(6.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(72.0, LengthUnit.INCHES);

        assertEquals(yard, feet);
        assertEquals(feet, inches);
        assertEquals(yard, inches);
    }

    // Null unit should throw exception
    @Test
    void testNullUnit_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }

    // Invalid value (NaN)
    @Test
    void testInvalidValue_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
    }

    // Infinity value
    @Test
    void testInfinityValue_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }
}
