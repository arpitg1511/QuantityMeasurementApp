package com.apps.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

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

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        converted = Math.round(converted * 100.0) / 100.0;

        return new Quantity<>(converted, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        double sum = this.toBaseUnit() + other.toBaseUnit();

        double result = unit.convertFromBaseUnit(sum);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sum = this.toBaseUnit() + other.toBaseUnit();

        double result = targetUnit.convertFromBaseUnit(sum);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (this.unit.getClass() != other.unit.getClass())
            return false;

        double thisBase = this.toBaseUnit();
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Double.compare(thisBase, otherBase) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBaseUnit(), unit.getClass());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
    
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cannot subtract different measurement categories");
        }

        double baseValue1 = unit.convertToBaseUnit(this.value);
        double baseValue2 = other.unit.convertToBaseUnit(other.value);

        double baseResult = baseValue1 - baseValue2;

        double convertedResult = targetUnit.convertFromBaseUnit(baseResult);

        convertedResult = Math.round(convertedResult * 100.0) / 100.0;

        return new Quantity<>(convertedResult, targetUnit);
    }
    
    public double divide(Quantity<U> other) {

        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cannot divide different measurement categories");
        }

        if (other.value == 0) {
            throw new ArithmeticException("Division by zero");
        }

        double baseValue1 = unit.convertToBaseUnit(this.value);
        double baseValue2 = other.unit.convertToBaseUnit(other.value);

        return baseValue1 / baseValue2;
    }

	public double getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	public Object getUnit() {
		// TODO Auto-generated method stub
		return unit;
	}
}