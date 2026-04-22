import java.util.Scanner;

public class QuantityMeasurementAppUC5 {

    enum Unit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);

        private final double toFeetFactor;

        Unit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    public static class QuantityLength {
        private final double value;
        private final Unit unit;

        public QuantityLength(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        public double to(Unit targetUnit) {
            if (!Double.isFinite(value) || unit == null || targetUnit == null) {
                throw new IllegalArgumentException();
            }
            double baseValue = unit.toFeet(value);
            return targetUnit.fromFeet(baseValue);
        }

        public static double convert(double value, Unit source, Unit target) {
            if (!Double.isFinite(value) || source == null || target == null) {
                throw new IllegalArgumentException();
            }
            double baseValue = source.toFeet(value);
            return target.fromFeet(baseValue);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter value: ");
            double value = scanner.nextDouble();

            System.out.print("Enter source unit (FEET/INCHES/YARDS/CENTIMETERS): ");
            Unit source = Unit.valueOf(scanner.next().toUpperCase());

            System.out.print("Enter target unit (FEET/INCHES/YARDS/CENTIMETERS): ");
            Unit target = Unit.valueOf(scanner.next().toUpperCase());

            double result = QuantityLength.convert(value, source, target);

            System.out.println("Converted Value: " + result);
        } catch (Exception e) {
            System.out.println("Invalid Input");
        } finally {
            scanner.close();
        }
    }
}