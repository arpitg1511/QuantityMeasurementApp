package com.apps.quantitymeasurement.feet_measurement_equality;

import java.util.Objects;

public class QuantityMeasurementApp {

    /**
     * Inner class representing a Feet measurement.
     * Immutable and encapsulated.
     */
    public static final class Feet {

        private final double value;   // Immutable field

        /**
         * Constructor to initialize feet value.
         * @param value measurement in feet
         */
        public Feet(double value) {
            this.value = value;
        }

        /**
         * Getter method (read-only access).
         */
        public double getValue() {
            return value;
        }

        /**
         * Overriding equals() for value-based equality.
         */
        @Override
        public boolean equals(Object obj) {

            // Reflexive check
            if (this == obj) {
                return true;
            }

            // Null check
            if (obj == null) {
                return false;
            }

            // Type check (ensures type safety)
            if (getClass() != obj.getClass()) {
                return false;
            }

            // Safe casting
            Feet other = (Feet) obj;

            // Floating-point comparison using Double.compare
            return Double.compare(this.value, other.value) == 0;
        }

        /**
         * Whenever equals() is overridden, hashCode() must also be overridden.
         */
        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return value + " ft";
        }
    }

    /**
     * Main method to test functionality.
     */
    public static void main(String[] args) {

        Feet first = new Feet(1.0);
        Feet second = new Feet(1.0);

        boolean result = first.equals(second);

        System.out.println("Comparing: " + first + " and " + second);
        System.out.println("Equal? " + result);
    }
}
