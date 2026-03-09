package com.apps.quantitymeasurement.weight_measurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println(q1.convertTo(LengthUnit.INCHES));

        System.out.println(
                q1.add(q2, LengthUnit.FEET)
        );

        System.out.println(
                new QuantityLength(36.0, LengthUnit.INCHES)
                        .equals(new QuantityLength(1.0, LengthUnit.YARDS))
        );
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