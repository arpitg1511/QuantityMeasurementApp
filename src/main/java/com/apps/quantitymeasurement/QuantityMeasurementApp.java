package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable> void demonstrateEquality(
            Quantity<U> q1,
            Quantity<U> q2) {

        System.out.println(q1 + " == " + q2 + " ? " + q1.equals(q2));
    }

    public static <U extends IMeasurable> void demonstrateConversion(
            Quantity<U> quantity,
            U targetUnit) {

        System.out.println(quantity + " -> " + quantity.convertTo(targetUnit));
    }

    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        System.out.println(q1 + " + " + q2 + " = " + q1.add(q2, targetUnit));
    }
    
    public static void demonstrateSubtraction() {

        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        System.out.println("Subtraction Result: " + result);
    }
    
    public static void demonstrateDivision() {

        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        double result = q1.divide(q2);

        System.out.println("Division Result: " + result);
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> length1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        demonstrateEquality(length1, length2);

        demonstrateConversion(length1, LengthUnit.INCHES);

        demonstrateAddition(length1, length2, LengthUnit.FEET);

        Quantity<WeightUnit> weight1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        demonstrateEquality(weight1, weight2);

        demonstrateConversion(weight1, WeightUnit.GRAM);

        demonstrateAddition(weight1, weight2, WeightUnit.KILOGRAM);
        
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("Volume Equality:");
        demonstrateEquality(v1, v2);

        System.out.println("\nVolume Conversion:");
        demonstrateConversion(v1, VolumeUnit.MILLILITRE);
        demonstrateConversion(v3, VolumeUnit.LITRE);

        System.out.println("\nVolume Addition:");
        demonstrateAddition(v1, v2, VolumeUnit.LITRE);
        
        System.out.println("\nVolume Addition:");
        demonstrateAddition(v1, v3, VolumeUnit.MILLILITRE);
        
        demonstrateSubtraction();

        demonstrateDivision();
        
        Quantity<TemperatureUnit> celsius =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        System.out.println("0°C equals 32°F : " + celsius.equals(fahrenheit));

        Quantity<TemperatureUnit> converted =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT);

        System.out.println("100°C in Fahrenheit: " + converted);

        try {
            celsius.add(fahrenheit);
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
    }
}

class QuantityLength {

    private final double value;
    private final LengthUnit unit;
    private static final double EPSILON = 0.0001;

    public QuantityLength(double value, LengthUnit unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new QuantityLength(converted, targetUnit);
    }

    public QuantityLength add(QuantityLength other) {

        if (other == null)
            throw new IllegalArgumentException("Other length cannot be null");

        double sum = this.toBaseUnit() + other.toBaseUnit();
        double result = unit.convertFromBaseUnit(sum);

        return new QuantityLength(result, unit);
    }

    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Other length cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sum = this.toBaseUnit() + other.toBaseUnit();
        double result = targetUnit.convertFromBaseUnit(sum);

        return new QuantityLength(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof QuantityLength other))
            return false;

        return Math.abs(
                this.toBaseUnit() - other.toBaseUnit()
        ) < EPSILON;
    }

    @Override
    public int hashCode() {
        long normalized = Math.round(toBaseUnit() / EPSILON);
        return Long.hashCode(normalized);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";    
    }
}