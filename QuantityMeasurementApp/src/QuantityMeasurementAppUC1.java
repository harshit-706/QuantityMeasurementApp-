import java.util.Scanner;

public class QuantityMeasurementAppUC1 {

    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter first value in feet: ");
            double first = scanner.nextDouble();

            System.out.print("Enter second value in feet: ");
            double second = scanner.nextDouble();

            Feet f1 = new Feet(first);
            Feet f2 = new Feet(second);

            System.out.println("Input: " + first + " ft and " + second + " ft");
            System.out.println("Output: Equal (" + f1.equals(f2) + ")");
        } catch (Exception e) {
            System.out.println("Output: Equal (false)");
        } finally {
            scanner.close();
        }
    }
}