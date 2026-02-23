package com.apps.quantitymeasurement.target_unit_addition;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityLength result1 =
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(
                                new QuantityLength(12.0, LengthUnit.INCHES),
                                LengthUnit.FEET
                        );

        System.out.println(result1);

        QuantityLength result2 =
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(
                                new QuantityLength(12.0, LengthUnit.INCHES),
                                LengthUnit.YARDS
                        );

        System.out.println(result2);
    }
}

// NOT public
class QuantityLength {

    private final double value;
    private final LengthUnit unit;
    private static final double TOLERANCE = 0.0001;

    public QuantityLength(double value, LengthUnit unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    private double toBaseFeet() {
        return unit.toFeet(value);
    }

    private static double addInBase(
            QuantityLength q1,
            QuantityLength q2
    ) {
        return q1.toBaseFeet() + q2.toBaseFeet();
    }

    public QuantityLength add(QuantityLength other) {

        if (other == null)
            throw new IllegalArgumentException("Other length cannot be null");

        double sumInFeet = addInBase(this, other);
        double resultValue = unit.fromFeet(sumInFeet);

        return new QuantityLength(resultValue, this.unit);
    }

    public QuantityLength add(
            QuantityLength other,
            LengthUnit targetUnit
    ) {

        if (other == null)
            throw new IllegalArgumentException("Other length cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumInFeet = addInBase(this, other);
        double resultValue = targetUnit.fromFeet(sumInFeet);

        return new QuantityLength(resultValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        QuantityLength other = (QuantityLength) obj;

        return Math.abs(
                this.toBaseFeet() - other.toBaseFeet()
        ) < TOLERANCE;
    }

    @Override
    public int hashCode() {
        long normalized = Math.round(toBaseFeet() / TOLERANCE);
        return Long.hashCode(normalized);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}