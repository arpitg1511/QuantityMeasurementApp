package com.apps.quantitymeasurement.unit_conversion;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println("Are equal? " + q1.equals(q2));
    }
}

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

    private double convertToFeet() {
        return unit.toFeet(value);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        QuantityLength other = (QuantityLength) obj;

        double thisInFeet = this.convertToFeet();
        double otherInFeet = other.convertToFeet();

        return Math.abs(thisInFeet - otherInFeet) < TOLERANCE;
    }

    @Override
    public int hashCode() {
        long normalized = Math.round(convertToFeet() / TOLERANCE);
        return Long.hashCode(normalized);
    }
}
