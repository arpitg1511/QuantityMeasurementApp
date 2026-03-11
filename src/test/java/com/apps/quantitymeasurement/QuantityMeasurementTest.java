package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class QuantityMeasurementTest {

    private static final double EPSILON = 0.0001;

    @Nested
    class LengthUnitTests {

        @Test
        void testConvertToBaseUnit_InchesToFeet() {
            assertEquals(
                    1.0,
                    LengthUnit.INCHES.convertToBaseUnit(12.0),
                    EPSILON
            );
        }

        @Test
        void testConvertToBaseUnit_YardsToFeet() {
            assertEquals(
                    3.0,
                    LengthUnit.YARDS.convertToBaseUnit(1.0),
                    EPSILON
            );
        }

        @Test
        void testConvertFromBaseUnit_FeetToInches() {
            assertEquals(
                    12.0,
                    LengthUnit.INCHES.convertFromBaseUnit(1.0),
                    EPSILON
            );
        }

        @Test
        void testConvertFromBaseUnit_FeetToCentimeters() {
            assertEquals(
                    30.48,
                    LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),
                    EPSILON
            );
        }
    }

    @Nested
    class QuantityConversionTests {

        @Test
        void testConvertFeetToInches() {

            QuantityLength q =
                    new QuantityLength(1.0, LengthUnit.FEET);

            QuantityLength result =
                    q.convertTo(LengthUnit.INCHES);

            assertEquals(
                    new QuantityLength(12.0, LengthUnit.INCHES),
                    result
            );
        }

        @Test
        void testConvertCentimetersToInches() {

            QuantityLength q =
                    new QuantityLength(2.54, LengthUnit.CENTIMETERS);

            QuantityLength result =
                    q.convertTo(LengthUnit.INCHES);

            assertEquals(
                    new QuantityLength(1.0, LengthUnit.INCHES),
                    result
            );
        }
    }

    @Nested
    class QuantityEqualityTests {

        @Test
        void testEqualityAcrossUnits() {

            assertEquals(
                    new QuantityLength(1.0, LengthUnit.FEET),
                    new QuantityLength(12.0, LengthUnit.INCHES)
            );
        }

        @Test
        void testInchesEqualsYards() {

            assertEquals(
                    new QuantityLength(36.0, LengthUnit.INCHES),
                    new QuantityLength(1.0, LengthUnit.YARDS)
            );
        }
    }

    @Nested
    class QuantityAdditionTests {

        @Test
        void testAddFeetAndInches_ResultFeet() {

            QuantityLength result =
                    new QuantityLength(1.0, LengthUnit.FEET)
                            .add(
                                    new QuantityLength(12.0, LengthUnit.INCHES),
                                    LengthUnit.FEET
                            );

            assertEquals(
                    new QuantityLength(2.0, LengthUnit.FEET),
                    result
            );
        }

        @Test
        void testAddFeetAndInches_ResultYards() {

            QuantityLength result =
                    new QuantityLength(1.0, LengthUnit.FEET)
                            .add(
                                    new QuantityLength(12.0, LengthUnit.INCHES),
                                    LengthUnit.YARDS
                            );

            assertEquals(
                    new QuantityLength(0.6667, LengthUnit.YARDS),
                    result
            );
        }

        @Test
        void testAdditionCommutative() {

            QuantityLength a =
                    new QuantityLength(1.0, LengthUnit.FEET);

            QuantityLength b =
                    new QuantityLength(12.0, LengthUnit.INCHES);

            assertEquals(
                    a.add(b, LengthUnit.YARDS),
                    b.add(a, LengthUnit.YARDS)
            );
        }
    }

    @Nested
    class ValidationTests {

        @Test
        void testNullUnit() {

            assertThrows(
                    IllegalArgumentException.class,
                    () -> new QuantityLength(1.0, null)
            );
        }

        @Test
        void testInvalidValue() {

            assertThrows(
                    IllegalArgumentException.class,
                    () -> new QuantityLength(Double.NaN, LengthUnit.FEET)
            );
        }
    }
    
 // Same unit subtraction
    @Test
    void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(5.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // Cross unit subtraction
    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(9.5, result.getValue(), EPSILON);
    }

    // Explicit target unit
    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2, LengthUnit.INCHES);

        assertEquals(114.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // Negative result
    @Test
    void testSubtraction_ResultNegative() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(-5.0, result.getValue(), EPSILON);
    }

    // Zero result
    @Test
    void testSubtraction_ResultZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(120.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    // Division same unit
    @Test
    void testDivision_SameUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(5.0, result, EPSILON);
    }

    // Division cross unit
    @Test
    void testDivision_CrossUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(1.0, result, EPSILON);
    }

    // Ratio less than one
    @Test
    void testDivision_RatioLessThanOne() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(0.5, result, EPSILON);
    }

    // Division by zero
    @Test
    void testDivision_ByZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    // Null operand subtraction
    @Test
    void testSubtraction_NullOperand() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.subtract(null));
    }

    // Null operand division
    @Test
    void testDivision_NullOperand() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.divide(null));
    }

    // Cross category prevention
    @Test
    void testSubtraction_CrossCategory() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> q2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> q1.subtract((Quantity) q2));
    }

    // Immutability test
    @Test
    void testImmutability_AfterSubtract() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        q1.subtract(q2);

        assertEquals(10.0, q1.getValue());
        assertEquals(5.0, q2.getValue());
    }

    // Addition still works after refactor
    @Test
    void testAddition_BehaviorPreserved() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

}