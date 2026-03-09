package com.apps.quantitymeasurement.generic_measurement;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.generic_measurement.QuantityWeight;
import com.apps.quantitymeasurement.generic_measurement.WeightUnit;

import static org.junit.jupiter.api.Assertions.*;

class QuantityWeightTest {

    private static final double EPSILON = 0.00001;

    @Nested
    class EqualityTests {

        @Test
        void testEquality_KilogramToKilogram_SameValue() {

            assertEquals(
                    new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                    new QuantityWeight(1.0, WeightUnit.KILOGRAM)
            );
        }

        @Test
        void testEquality_KilogramToGram() {

            assertEquals(
                    new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                    new QuantityWeight(1000.0, WeightUnit.GRAM)
            );
        }

        @Test
        void testEquality_KilogramToPound() {

            assertEquals(
                    new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                    new QuantityWeight(2.20462, WeightUnit.POUND)
            );
        }

        @Test
        void testEquality_NullComparison() {

            QuantityWeight w =
                    new QuantityWeight(1.0, WeightUnit.KILOGRAM);

            assertNotEquals(w, null);
        }

        @Test
        void testEquality_SameReference() {

            QuantityWeight w =
                    new QuantityWeight(2.0, WeightUnit.KILOGRAM);

            assertEquals(w, w);
        }
    }

    @Nested
    class ConversionTests {

        @Test
        void testConversion_KilogramToGram() {

            QuantityWeight result =
                    new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                            .convertTo(WeightUnit.GRAM);

            assertEquals(
                    new QuantityWeight(1000.0, WeightUnit.GRAM),
                    result
            );
        }

        @Test
        void testConversion_PoundToKilogram() {

            QuantityWeight result =
                    new QuantityWeight(2.20462, WeightUnit.POUND)
                            .convertTo(WeightUnit.KILOGRAM);

            assertEquals(
                    new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                    result
            );
        }

        @Test
        void testConversion_RoundTrip() {

            QuantityWeight result =
                    new QuantityWeight(1.5, WeightUnit.KILOGRAM)
                            .convertTo(WeightUnit.GRAM)
                            .convertTo(WeightUnit.KILOGRAM);

            assertEquals(
                    new QuantityWeight(1.5, WeightUnit.KILOGRAM),
                    result
            );
        }
    }

    @Nested
    class AdditionTests {

        @Test
        void testAddition_SameUnit() {

            QuantityWeight result =
                    new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                            .add(new QuantityWeight(2.0, WeightUnit.KILOGRAM));

            assertEquals(
                    new QuantityWeight(3.0, WeightUnit.KILOGRAM),
                    result
            );
        }

        @Test
        void testAddition_CrossUnit() {

            QuantityWeight result =
                    new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                            .add(new QuantityWeight(1000.0, WeightUnit.GRAM));

            assertEquals(
                    new QuantityWeight(2.0, WeightUnit.KILOGRAM),
                    result
            );
        }

        @Test
        void testAddition_ExplicitTargetUnit() {

            QuantityWeight result =
                    new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                            .add(
                                    new QuantityWeight(1000.0, WeightUnit.GRAM),
                                    WeightUnit.GRAM
                            );

            assertEquals(
                    new QuantityWeight(2000.0, WeightUnit.GRAM),
                    result
            );
        }

        @Test
        void testAddition_Commutativity() {

            QuantityWeight a =
                    new QuantityWeight(1.0, WeightUnit.KILOGRAM);

            QuantityWeight b =
                    new QuantityWeight(1000.0, WeightUnit.GRAM);

            assertEquals(
                    a.add(b),
                    b.add(a)
            );
        }
    }

    @Nested
    class ValidationTests {

        @Test
        void testNullUnit() {

            assertThrows(
                    IllegalArgumentException.class,
                    () -> new QuantityWeight(1.0, null)
            );
        }

        @Test
        void testInvalidValue() {

            assertThrows(
                    IllegalArgumentException.class,
                    () -> new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM)
            );
        }
    }
}