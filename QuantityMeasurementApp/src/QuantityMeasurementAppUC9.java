import java.util.Scanner;

enum LengthUnitUC9 {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.0328084);

    private final double toFeetFactor;

    LengthUnitUC9(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    public double toBase(double value) {
        return value * toFeetFactor;
    }

    public double fromBase(double base) {
        return base / toFeetFactor;
    }
}

enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toKgFactor;

    WeightUnit(double toKgFactor) {
        this.toKgFactor = toKgFactor;
    }

    public double toBase(double value) {
        return value * toKgFactor;
    }

    public double fromBase(double base) {
        return base / toKgFactor;
    }
}

public class QuantityMeasurementAppUC9 {

    static class QuantityLength {
        private final double value;
        private final LengthUnitUC9 unit;

        public QuantityLength(double value, LengthUnitUC9 unit) {
            this.value = value;
            this.unit = unit;
        }

        public double convertTo(LengthUnitUC9 target) {
            double base = unit.toBase(value);
            return target.fromBase(base);
        }

        public QuantityLength add(QuantityLength other, LengthUnitUC9 target) {
            double sum = unit.toBase(value) + other.unit.toBase(other.value);
            return new QuantityLength(target.fromBase(sum), target);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            return Double.compare(unit.toBase(value), other.unit.toBase(other.value)) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    static class QuantityWeight {
        private final double value;
        private final WeightUnit unit;

        public QuantityWeight(double value, WeightUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        public double convertTo(WeightUnit target) {
            double base = unit.toBase(value);
            return target.fromBase(base);
        }

        public QuantityWeight add(QuantityWeight other, WeightUnit target) {
            double sum = unit.toBase(value) + other.unit.toBase(other.value);
            return new QuantityWeight(target.fromBase(sum), target);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityWeight other = (QuantityWeight) obj;
            return Double.compare(unit.toBase(value), other.unit.toBase(other.value)) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter weight1 value: ");
            double v1 = sc.nextDouble();
            System.out.print("Enter weight1 unit (KILOGRAM/GRAM/POUND): ");
            WeightUnit u1 = WeightUnit.valueOf(sc.next().toUpperCase());

            System.out.print("Enter weight2 value: ");
            double v2 = sc.nextDouble();
            System.out.print("Enter weight2 unit (KILOGRAM/GRAM/POUND): ");
            WeightUnit u2 = WeightUnit.valueOf(sc.next().toUpperCase());

            System.out.print("Enter target unit: ");
            WeightUnit target = WeightUnit.valueOf(sc.next().toUpperCase());

            QuantityWeight w1 = new QuantityWeight(v1, u1);
            QuantityWeight w2 = new QuantityWeight(v2, u2);

            System.out.println("Equal (" + w1.equals(w2) + ")");
            System.out.println("Converted w1: " + w1.convertTo(target));

            QuantityWeight sum = w1.add(w2, target);
            System.out.println("Addition Result: " + sum);

        } catch (Exception e) {
            System.out.println("Invalid Input");
        } finally {
            sc.close();
        }
    }
}