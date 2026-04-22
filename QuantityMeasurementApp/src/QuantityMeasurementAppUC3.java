import java.util.Scanner;

public class QuantityMeasurementAppUC3 {

    enum Unit {
        FEET, INCHES
    }

    public static class QuantityLength {
        private final double value;
        private final Unit unit;

        private static final double FEET_TO_FEET = 1.0;
        private static final double INCH_TO_FEET = 1.0 / 12.0;

        public QuantityLength(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            if (unit == Unit.FEET) return value * FEET_TO_FEET;
            if (unit == Unit.INCHES) return value * INCH_TO_FEET;
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter first value: ");
            double v1 = scanner.nextDouble();
            System.out.print("Enter first unit (FEET/INCHES): ");
            Unit u1 = Unit.valueOf(scanner.next().toUpperCase());

            System.out.print("Enter second value: ");
            double v2 = scanner.nextDouble();
            System.out.print("Enter second unit (FEET/INCHES): ");
            Unit u2 = Unit.valueOf(scanner.next().toUpperCase());

            QuantityLength q1 = new QuantityLength(v1, u1);
            QuantityLength q2 = new QuantityLength(v2, u2);

            System.out.println("Equal (" + q1.equals(q2) + ")");
        } catch (Exception e) {
            System.out.println("Equal (false)");
        } finally {
            scanner.close();
        }
    }
}