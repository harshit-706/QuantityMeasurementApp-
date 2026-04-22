import java.util.Scanner;

public class QuantityMeasurementAppUC4 {

    enum Unit {
        FEET, INCHES, YARDS, CMS
    }

    public static class QuantityLength {
        private final double value;
        private final Unit unit;

        private static final double FEET_TO_INCH = 12.0;
        private static final double INCH_TO_INCH = 1.0;
        private static final double YARD_TO_INCH = 36.0;
        private static final double CM_TO_INCH = 0.393701;

        public QuantityLength(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        private double toInches() {
            switch (unit) {
                case FEET: return value * FEET_TO_INCH;
                case INCHES: return value * INCH_TO_INCH;
                case YARDS: return value * YARD_TO_INCH;
                case CMS: return value * CM_TO_INCH;
                default: return 0;
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toInches(), other.toInches()) == 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter first value: ");
            double v1 = scanner.nextDouble();
            System.out.print("Enter first unit (FEET/INCHES/YARDS/CMS): ");
            Unit u1 = Unit.valueOf(scanner.next().toUpperCase());

            System.out.print("Enter second value: ");
            double v2 = scanner.nextDouble();
            System.out.print("Enter second unit (FEET/INCHES/YARDS/CMS): ");
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