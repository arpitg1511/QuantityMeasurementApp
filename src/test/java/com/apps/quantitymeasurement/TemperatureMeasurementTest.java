package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class TemperatureMeasurementTest {

    private static final double EPSILON = 0.01;

    // ---------- Equality Tests ----------

    @Test
    void testTemperatureEquality_CelsiusToCelsius_SameValue() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        assertEquals(t1, t2);
    }

    @Test
    void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> t2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertEquals(t1, t2);
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_ZeroEqualsThirtyTwo() {
        Quantity<TemperatureUnit> celsius = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> fahrenheit = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertEquals(celsius, fahrenheit);
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_100Equals212() {
        Quantity<TemperatureUnit> celsius = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> fahrenheit = new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);

        assertEquals(celsius, fahrenheit);
    }

    @Test
    void testTemperatureEquality_Negative40SamePoint() {
        Quantity<TemperatureUnit> celsius = new Quantity<>(-40.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> fahrenheit = new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT);

        assertEquals(celsius, fahrenheit);
    }

    @Test
    void testTemperatureEquality_ReflexiveProperty() {
        Quantity<TemperatureUnit> temp = new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertEquals(temp, temp);
    }

    @Test
    void testTemperatureEquality_SymmetricProperty() {
        Quantity<TemperatureUnit> c = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> f = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(c.equals(f));
        assertTrue(f.equals(c));
    }

    // ---------- Conversion Tests ----------

    @Test
    void testTemperatureConversion_CelsiusToFahrenheit() {
        Quantity<TemperatureUnit> celsius = new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                celsius.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(212.0, result.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_FahrenheitToCelsius() {
        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> result =
                fahrenheit.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_RoundTrip() {
        Quantity<TemperatureUnit> original =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> converted =
                original.convertTo(TemperatureUnit.FAHRENHEIT)
                        .convertTo(TemperatureUnit.CELSIUS);

        assertEquals(original.getValue(), converted.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_SameUnit() {
        Quantity<TemperatureUnit> temp =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                temp.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(50.0, result.getValue(), EPSILON);
    }

    // ---------- Unsupported Arithmetic ----------

    @Test
    void testTemperatureUnsupportedOperation_Add() {
        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.add(t2));
    }

    @Test
    void testTemperatureUnsupportedOperation_Subtract() {
        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.subtract(t2));
    }

    @Test
    void testTemperatureUnsupportedOperation_Divide() {
        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(UnsupportedOperationException.class,
                () -> t1.divide(t2));
    }

    // ---------- Operation Support Checks ----------

    @Test
    void testOperationSupportMethods_TemperatureUnit() {
        assertFalse(TemperatureUnit.CELSIUS.supportsArithmetic());
        assertFalse(TemperatureUnit.FAHRENHEIT.supportsArithmetic());
    }

    @Test
    void testOperationSupportMethods_LengthUnit() {
        assertTrue(LengthUnit.FEET.supportsArithmetic());
    }

    // ---------- Cross Category Safety ----------

    @Test
    void testTemperatureVsLengthIncompatibility() {

        Quantity<TemperatureUnit> temp =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<LengthUnit> length =
                new Quantity<>(100.0, LengthUnit.FEET);

        assertNotEquals(temp, length);
    }

    // ---------- Validation ----------

    @Test
    void testTemperatureNullUnitValidation() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(100.0, null));
    }

    @Test
    void testTemperatureDifferentValuesInequality() {
        Quantity<TemperatureUnit> t1 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        assertNotEquals(t1, t2);
    }
}