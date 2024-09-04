import java.util.ArrayList;
import java.util.List;

public class NumberOperations {

    public static void main(String[] args) {
        List<Number> numbers = new ArrayList<>();
        numbers.add((byte) 1);      // Byte
        numbers.add((byte) 4);

        numbers.add(10);            // Integer
        numbers.add(50);
        numbers.add(70);
        numbers.add(90);

        numbers.add(20.5f);           // Float (явний float)
        numbers.add(40.7f);
        numbers.add(60.3f);
        numbers.add(80.1f);
        numbers.add(100.9f);

        numbers.add((short) 563);     // Short
        numbers.add((short) 1054);

        numbers.add(426092952.0);     // Double (явний double)
        numbers.add(6208674592L);     // Long

        numbers.add(45L);            // Long
        numbers.add(90L);

        System.out.print("Числа: ");
        for (Number number : numbers) {
            System.out.print(number + " ");
        }

        System.out.print("\nЦілі числа: ");
        for (Number number : numbers) {
            System.out.print(number.intValue() + " ");
        }

        System.out.print("\nДробові числа з двома знаками після коми: ");
        for (Number number : numbers) {
            System.out.print(String.format("%.2f", number.doubleValue()) + " ");
        }

        List<Byte> byteNumbers = new ArrayList<>();
        List<Short> shortNumbers = new ArrayList<>();
        List<Integer> integerNumbers = new ArrayList<>();
        List<Long> longNumbers = new ArrayList<>();
        List<Float> floatNumbers = new ArrayList<>();
        List<Double> doubleNumbers = new ArrayList<>();

        for (Number number : numbers) {
            if (number instanceof Byte) {
                byteNumbers.add((Byte) number);
            } else if (number instanceof Short) {
                shortNumbers.add((Short) number);
            } else if (number instanceof Integer) {
                integerNumbers.add((Integer) number);
            } else if (number instanceof Long) {
                longNumbers.add((Long) number);
            } else if (number instanceof Float) {
                floatNumbers.add((Float) number);
            } else if (number instanceof Double) {
                doubleNumbers.add((Double) number);
            }
        }

        System.out.print("\nByte числа: ");
        for (Byte number : byteNumbers) {
            System.out.print(number + " ");
        }

        System.out.print("\nShort числа: ");
        for (Short number : shortNumbers) {
            System.out.print(number + " ");
        }

        System.out.print("\nInteger числа: ");
        for (Integer number : integerNumbers) {
            System.out.print(number + " ");
        }

        System.out.print("\nLong числа: ");
        for (Long number : longNumbers) {
            System.out.print(number + " ");
        }

        System.out.print("\nFloat числа: ");
        for (Float number : floatNumbers) {
            System.out.print(number + " ");
        }

        System.out.print("\nDouble числа: ");
        for (Double number : doubleNumbers) {
            System.out.print(number + " ");
        }

        sumOfNumbersGreaterThan50(numbers);
    }

    public static void sumOfNumbersGreaterThan50(List<Number> numbers) {
        double sum = 0;
        for (Number number : numbers) {
            if (number.doubleValue() > 50) {
                sum += number.doubleValue();
            }
        }
        String formattedSum = String.format("%.2f", sum);
        System.out.println("\nСума чисел більших за 50: " + formattedSum);
    }
}
