package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable> void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        System.out.println(q1 + " == " + q2 + " : " + q1.equals(q2));
    }

    public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> q, U target) {
        System.out.println(q + " -> " + q.convertTo(target));
    }

    public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U target) {
        System.out.println(q1 + " + " + q2 + " = " + q1.add(q2, target));
    }

    public static void main(String[] args) {

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
        demonstrateAddition(v1, v3, VolumeUnit.MILLILITRE);
    }
}