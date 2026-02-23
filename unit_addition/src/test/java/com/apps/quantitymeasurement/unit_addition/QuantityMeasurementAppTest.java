package com.apps.quantitymeasurement.unit_addition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;


    @Nested
    @DisplayName("Equality Tests")
    class EqualityTests {

        @Test
        void yardToYard_sameValue() {
            assertEquals(
                    new QuantityLength(1.0, LengthUnit.YARDS),
                    new QuantityLength(1.0, LengthUnit.YARDS)
            );
        }

        @Test
        void yardToYard_differentValue() {
            assertNotEquals(
                    new QuantityLength(1.0, LengthUnit.YARDS),
                    new QuantityLength(2.0, LengthUnit.YARDS)
            );
        }

        @Test
        void yardToFeet_equivalent() {
            assertEquals(
                    new QuantityLength(1.0, LengthUnit.YARDS),
                    new QuantityLength(3.0, LengthUnit.FEET)
            );
        }

        @Test
        void yardToInches_equivalent() {
            assertEquals(
                    new QuantityLength(1.0, LengthUnit.YARDS),
                    new QuantityLength(36.0, LengthUnit.INCHES)
            );
        }

        @Test
        void centimeterToInch_equivalent() {
            assertEquals(
                    new QuantityLength(1.0, LengthUnit.CENTIMETERS),
                    new QuantityLength(0.393701, LengthUnit.INCHES)
            );
        }

        @Test
        void nonEquivalentValues() {
            assertNotEquals(
                    new QuantityLength(1.0, LengthUnit.FEET),
                    new QuantityLength(1.0, LengthUnit.CENTIMETERS)
            );
        }

        @Test
        void transitiveProperty() {
            QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
            QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
            QuantityLength inches = new QuantityLength(36.0, LengthUnit.INCHES);

            assertEquals(yard, feet);
            assertEquals(feet, inches);
            assertEquals(yard, inches);
        }

        @Test
        void reflexiveProperty() {
            QuantityLength yard = new QuantityLength(2.0, LengthUnit.YARDS);
            assertEquals(yard, yard);
        }

        @Test
        void nullComparison() {
            QuantityLength yard = new QuantityLength(2.0, LengthUnit.YARDS);
            assertNotEquals(yard, null);
        }
    }

    

    @Nested
    @DisplayName("UC6 - Addition Tests")
    class AdditionTests {

        @Test
        void sameUnit_feetPlusFeet() {
            QuantityLength result =
                    new QuantityLength(1.0, LengthUnit.FEET)
                            .add(new QuantityLength(2.0, LengthUnit.FEET));

            assertEquals(
                    new QuantityLength(3.0, LengthUnit.FEET),
                    result
            );
        }

        @Test
        void crossUnit_feetPlusInches() {
            QuantityLength result =
                    new QuantityLength(1.0, LengthUnit.FEET)
                            .add(new QuantityLength(12.0, LengthUnit.INCHES));

            assertEquals(
                    new QuantityLength(2.0, LengthUnit.FEET),
                    result
            );
        }

        @Test
        void crossUnit_inchesPlusFeet() {
            QuantityLength result =
                    new QuantityLength(12.0, LengthUnit.INCHES)
                            .add(new QuantityLength(1.0, LengthUnit.FEET));

            assertEquals(
                    new QuantityLength(24.0, LengthUnit.INCHES),
                    result
            );
        }

        @Test
        void crossUnit_yardPlusFeet() {
            QuantityLength result =
                    new QuantityLength(1.0, LengthUnit.YARDS)
                            .add(new QuantityLength(3.0, LengthUnit.FEET));

            assertEquals(
                    new QuantityLength(2.0, LengthUnit.YARDS),
                    result
            );
        }

        @Test
        void crossUnit_centimeterPlusInch() {
            QuantityLength result =
                    new QuantityLength(2.54, LengthUnit.CENTIMETERS)
                            .add(new QuantityLength(1.0, LengthUnit.INCHES));

            assertEquals(
                    new QuantityLength(5.08, LengthUnit.CENTIMETERS),
                    result
            );
        }

        @Test
        void addingZero_shouldReturnSameValue() {
            QuantityLength result =
                    new QuantityLength(5.0, LengthUnit.FEET)
                            .add(new QuantityLength(0.0, LengthUnit.INCHES));

            assertEquals(
                    new QuantityLength(5.0, LengthUnit.FEET),
                    result
            );
        }

        @Test
        void negativeValues_shouldAddCorrectly() {
            QuantityLength result =
                    new QuantityLength(5.0, LengthUnit.FEET)
                            .add(new QuantityLength(-2.0, LengthUnit.FEET));

            assertEquals(
                    new QuantityLength(3.0, LengthUnit.FEET),
                    result
            );
        }

        @Test
        void commutativity_shouldHold() {
            QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
            QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

            assertEquals(a.add(b), b.add(a));
        }

        @Test
        void largeValues() {
            QuantityLength result =
                    new QuantityLength(1e6, LengthUnit.FEET)
                            .add(new QuantityLength(1e6, LengthUnit.FEET));

            assertEquals(
                    new QuantityLength(2e6, LengthUnit.FEET),
                    result
            );
        }

        @Test
        void smallValues() {
            QuantityLength result =
                    new QuantityLength(0.001, LengthUnit.FEET)
                            .add(new QuantityLength(0.002, LengthUnit.FEET));

            assertEquals(
                    new QuantityLength(0.003, LengthUnit.FEET),
                    result
            );
        }

        @Test
        void nullSecondOperand_shouldThrowException() {
            QuantityLength length = new QuantityLength(1.0, LengthUnit.FEET);

            assertThrows(IllegalArgumentException.class,
                    () -> length.add(null));
        }
    }

   

    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {

        @Test
        void nullUnit_shouldThrowException() {
            assertThrows(IllegalArgumentException.class,
                    () -> new QuantityLength(1.0, null));
        }

        @Test
        void nanValue_shouldThrowException() {
            assertThrows(IllegalArgumentException.class,
                    () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
        }

        @Test
        void infiniteValue_shouldThrowException() {
            assertThrows(IllegalArgumentException.class,
                    () -> new QuantityLength(Double.POSITIVE_INFINITY, LengthUnit.FEET));
        }
    }
}