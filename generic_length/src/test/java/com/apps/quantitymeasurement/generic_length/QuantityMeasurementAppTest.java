package com.apps.quantitymeasurement.generic_length;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    /* =============================
       SAME UNIT TESTS
       ============================= */

    @Test
    void testEquality_FeetToFeet_SameValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2),
                "1.0 FEET should equal 1.0 FEET");
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.INCH);

        assertTrue(q1.equals(q2),
                "1.0 INCH should equal 1.0 INCH");
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);

        assertFalse(q1.equals(q2),
                "1.0 FEET should not equal 2.0 FEET");
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.INCH);

        assertFalse(q1.equals(q2),
                "1.0 INCH should not equal 2.0 INCH");
    }

    /* =============================
       CROSS UNIT TESTS
       ============================= */

    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        assertTrue(q1.equals(q2),
                "1.0 FEET should equal 12.0 INCH");
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        QuantityLength q1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2),
                "12.0 INCH should equal 1.0 FEET");
    }

    /* =============================
       EQUALS CONTRACT TESTS
       ============================= */

    @Test
    void testEquality_SameReference() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(q1.equals(q1),
                "Object must equal itself (reflexive property)");
    }

    @Test
    void testEquality_NullComparison() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(q1.equals(null),
                "Object must not equal null");
    }

    @Test
    void testEquality_TypeSafety() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(q1.equals("Invalid Type"),
                "Object must not equal different type");
    }

    @Test
    void testEquality_Symmetry() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1),
                "Equality must be symmetric");
    }

    @Test
    void testEquality_Transitivity() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q3));
        assertTrue(q1.equals(q3),
                "Equality must be transitive");
    }

    /* =============================
       VALIDATION TESTS
       ============================= */

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null),
                "Null unit should throw exception");
    }
}
