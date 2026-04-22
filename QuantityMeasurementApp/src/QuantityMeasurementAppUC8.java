import java.util.Scanner;

enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.0328084);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * toFeetFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }
}

public class QuantityMeasurementAppUC8 {

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        public double convertTo(LengthUnit targetUnit) {
            if (!Double.isFinite(value) || unit == null || targetUnit == null) {
                throw new IllegalArgumentException();
            }
            double base = unit.convertToBaseUnit(value);
            return targetUnit.convertFromBaseUnit(base);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null || targetUnit == null) {
                throw new IllegalArgumentException();
            }
            if (!Double.isFinite(value) || !Double.isFinite(other.value)) {
                throw new IllegalArgumentException();
            }
            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);
            double sumBase = base1 + base2;
            double result = targetUnit.convertFromBaseUnit(sumBase);
            return new QuantityLength(result, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);
            return Double.compare(base1, base2) == 0;
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
            LengthUnit u1 = LengthUnit.valueOf(scanner.next().toUpperCase());

            System.out.print("Enter second value: ");
            double v2 = scanner.nextDouble();
            System.out.print("Enter second unit (FEET/INCHES/YARDS/CENTIMETERS): ");
            LengthUnit u2 = LengthUnit.valueOf(scanner.next().toUpperCase());

            System.out.print("Enter target unit: ");
            LengthUnit target = LengthUnit.valueOf(scanner.next().toUpperCase());

            QuantityLength q1 = new QuantityLength(v1, u1);
            QuantityLength q2 = new QuantityLength(v2, u2);

            System.out.println("Equal (" + q1.equals(q2) + ")");
            System.out.println("Converted First Value: " + q1.convertTo(target));

            QuantityLength result = q1.add(q2, target);
            System.out.println("Addition Result: " + result);

        } catch (Exception e) {
            System.out.println("Invalid Input");
        } finally {
            scanner.close();
        }
    }
}