import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NumberOperations {

    public static void main(String[] args) {
        List<Number> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20.5);
        numbers.add(30);
        numbers.add(40.7);
        numbers.add(50);
        numbers.add(60.3);
        numbers.add(70);
        numbers.add(80.1);
        numbers.add(90);
        numbers.add(100.9);

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

        List<Integer> integerNumbers = new ArrayList<>();
        List<Double> doubleNumbers = new ArrayList<>();

        for (Number number : numbers) {
            if (number instanceof Integer) {
                integerNumbers.add((Integer) number);
            } else if (number instanceof Double) {
                doubleNumbers.add((Double) number);
            }
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
