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

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(converted, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {

        unit.validateOperationSupport("addition");

        double baseSum =
                unit.convertToBaseUnit(value)
                        + other.unit.convertToBaseUnit(other.value);

        double result = unit.convertFromBaseUnit(baseSum);

        return new Quantity<>(result, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double converted = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToTwoDecimals(converted), targetUnit);
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

        unit.validateOperationSupport("subtraction");

        double baseResult =
                unit.convertToBaseUnit(value)
                        - other.unit.convertToBaseUnit(other.value);

        double result = unit.convertFromBaseUnit(baseResult);

        return new Quantity<>(result, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double converted = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToTwoDecimals(converted), targetUnit);
    }
    
    public double divide(Quantity<U> other) {

        unit.validateOperationSupport("division");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        if (base2 == 0)
            throw new ArithmeticException("Division by zero");

        return base1 / base2;
    }

	public double getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	public Object getUnit() {
		// TODO Auto-generated method stub
		return unit;
	}
	
	private enum ArithmeticOperation {

	    ADD((a, b) -> a + b),
	    SUBTRACT((a, b) -> a - b),
	    DIVIDE((a, b) -> {
	        if (b == 0) {
	            throw new ArithmeticException("Division by zero");
	        }
	        return a / b;
	    });

	    private final java.util.function.DoubleBinaryOperator operation;

	    ArithmeticOperation(java.util.function.DoubleBinaryOperator operation) {
	        this.operation = operation;
	    }

	    public double compute(double a, double b) {
	        return operation.applyAsDouble(a, b);
	    }
	}
	
	private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {

	    if (other == null) {
	        throw new IllegalArgumentException("Quantity cannot be null");
	    }

	    if (!unit.getClass().equals(other.unit.getClass())) {
	        throw new IllegalArgumentException("Measurement categories must match");
	    }

	    if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
	        throw new IllegalArgumentException("Values must be finite numbers");
	    }

	    if (targetUnitRequired && targetUnit == null) {
	        throw new IllegalArgumentException("Target unit cannot be null");
	    }
	}
	
	private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {

	    double baseValue1 = unit.convertToBaseUnit(this.value);
	    double baseValue2 = other.unit.convertToBaseUnit(other.value);

	    return operation.compute(baseValue1, baseValue2);
	}
	
	private double roundToTwoDecimals(double value) {
	    return Math.round(value * 100.0) / 100.0;
	}
}