package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @Test
    void testLengthEquality() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(a, b);
    }

    @Test
    void testWeightEquality() {

        Quantity<WeightUnit> a =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> b =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(a, b);
    }

    @Test
    void testLengthConversion() {

        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .convertTo(LengthUnit.INCHES);

        assertEquals(
                new Quantity<>(12.0, LengthUnit.INCHES),
                result
        );
    }

    @Test
    void testWeightConversion() {

        Quantity<WeightUnit> result =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(
                new Quantity<>(1000.0, WeightUnit.GRAM),
                result
        );
    }

    @Test
    void testLengthAddition() {

        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .add(new Quantity<>(12.0, LengthUnit.INCHES), LengthUnit.FEET);

        assertEquals(
                new Quantity<>(2.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testWeightAddition() {

        Quantity<WeightUnit> result =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .add(new Quantity<>(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);

        assertEquals(
                new Quantity<>(2.0, WeightUnit.KILOGRAM),
                result
        );
    }

    @Test
    void testCrossCategoryPrevention() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(length, weight);
    }

    @Test
    void testNullUnitValidation() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null)
        );
    }

    @Test
    void testInvalidValueValidation() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET)
        );
    }
}