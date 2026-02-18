package com.apps.quantitymeasurement.inch_equality;

import java.util.Objects;

public class QuantityMeasurementApp {

    /* ===========================
       Static Equality Check Methods
       =========================== */

    public static boolean compareFeet(double first, double second) {
        Feet f1 = new Feet(first);
        Feet f2 = new Feet(second);
        return f1.equals(f2);
    }

    public static boolean compareInches(double first, double second) {
        Inches i1 = new Inches(first);
        Inches i2 = new Inches(second);
        return i1.equals(i2);
    }

    /* ===========================
       Feet Class
       =========================== */
    public static final class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null) return false;

            if (getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    /* ===========================
       Inches Class
       =========================== */
    public static final class Inches {

        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null) return false;

            if (getClass() != obj.getClass()) return false;

            Inches other = (Inches) obj;

            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    /* ===========================
       Main Method
       =========================== */

    public static void main(String[] args) {

        System.out.println("1.0 ft & 1.0 ft -> " + compareFeet(1.0, 1.0));
        System.out.println("1.0 inch & 1.0 inch -> " + compareInches(1.0, 1.0));
    }
}
