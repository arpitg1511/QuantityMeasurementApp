package com.apps.quantitymeasurement.weight_measurement;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.weight_measurement.LengthUnit;
import com.apps.quantitymeasurement.weight_measurement.QuantityLength;

import static org.junit.jupiter.api.Assertions.*;

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
}