package com.apps.quantitymeasurement.target_unit_addition;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Nested
    class ExplicitTargetUnitTests {

        @Test
        void testAddition_ExplicitTargetUnit_Feet() {
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
        void testAddition_ExplicitTargetUnit_Inches() {
            QuantityLength result =
                    new QuantityLength(1.0, LengthUnit.FEET)
                            .add(
                                    new QuantityLength(12.0, LengthUnit.INCHES),
                                    LengthUnit.INCHES
                            );

            assertEquals(
                    new QuantityLength(24.0, LengthUnit.INCHES),
                    result
            );
        }

        @Test
        void testAddition_ExplicitTargetUnit_Yards() {
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
        void testAddition_ExplicitTargetUnit_Commutativity() {

            QuantityLength a =
                    new QuantityLength(1.0, LengthUnit.FEET);

            QuantityLength b =
                    new QuantityLength(12.0, LengthUnit.INCHES);

            assertEquals(
                    a.add(b, LengthUnit.YARDS),
                    b.add(a, LengthUnit.YARDS)
            );
        }

        @Test
        void testAddition_ExplicitTargetUnit_NullTargetUnit() {

            assertThrows(
                    IllegalArgumentException.class,
                    () -> new QuantityLength(1.0, LengthUnit.FEET)
                            .add(
                                    new QuantityLength(12.0, LengthUnit.INCHES),
                                    null
                            )
            );
        }
    }
}