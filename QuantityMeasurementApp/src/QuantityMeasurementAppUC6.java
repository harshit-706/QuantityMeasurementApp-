import java.util.Scanner;

public class QuantityMeasurementAppUC6 {

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

        public QuantityLength add(QuantityLength other) {
            if (other == null || unit == null || other.unit == null) {
                throw new IllegalArgumentException();
            }
            if (!Double.isFinite(value) || !Double.isFinite(other.value)) {
                throw new IllegalArgumentException();
            }
            double base1 = unit.toFeet(value);
            double base2 = other.unit.toFeet(other.value);
            double sumBase = base1 + base2;
            double result = unit.fromFeet(sumBase);
            return new QuantityLength(result, unit);
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter first value: ");
            double v1 = scanner.nextDouble();
            System.out.print("Enter first unit (FEET/INCHES/YARDS/CENTIMETERS): ");
            Unit u1 = Unit.valueOf(scanner.next().toUpperCase());

            System.out.print("Enter second value: ");
            double v2 = scanner.nextDouble();
            System.out.print("Enter second unit (FEET/INCHES/YARDS/CENTIMETERS): ");
            Unit u2 = Unit.valueOf(scanner.next().toUpperCase());

            QuantityLength q1 = new QuantityLength(v1, u1);
            QuantityLength q2 = new QuantityLength(v2, u2);

            QuantityLength result = q1.add(q2);

            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Invalid Input");
        } finally {
            scanner.close();
        }
    }
}