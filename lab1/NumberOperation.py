class NumberOperations:
    def __init__(self):
        self.numbers = [
            1,                  # Byte equivalent
            4,                  # Byte equivalent
            10,                 # Integer
            50,                 # Integer
            70,                 # Integer
            90,                 # Integer
            20.5,               # Float
            40.7,               # Float
            60.3,               # Float
            80.1,               # Float
            100.9,              # Float
            563,                # Short equivalent
            1054,               # Short equivalent
            426092952.0,        # Double equivalent
            6208674592,         # Long equivalent
            45,                 # Long
            90,                 # Long
        ]

    def print_numbers(self):
        print("Числа:", ' '.join(map(str, self.numbers)))

    def print_integers(self):
        print("Цілі числа:", ' '.join(map(lambda x: str(int(x)), self.numbers)))

    def print_floats(self):
        print("Дробові числа з двома знаками після коми:", ' '.join(map(lambda x: f"{float(x):.2f}", self.numbers)))

    def separate_numbers(self):
        byte_numbers = []
        short_numbers = []
        integer_numbers = []
        long_numbers = []
        float_numbers = []
        double_numbers = []

        for number in self.numbers:
            if isinstance(number, int):
                if -128 <= number <= 127:
                    byte_numbers.append(number)
                elif -32768 <= number <= 32767:
                    short_numbers.append(number)
                elif -2147483648 <= number <= 2147483647:
                    integer_numbers.append(number)
                else:
                    long_numbers.append(number)
            elif isinstance(number, float):
                if number == int(number):
                    integer_numbers.append(int(number))
                else:
                    float_numbers.append(number)
                double_numbers.append(number)

        print("\nByte числа:", ' '.join(map(str, byte_numbers)))
        print("Short числа:", ' '.join(map(str, short_numbers)))
        print("Integer числа:", ' '.join(map(str, integer_numbers)))
        print("Long числа:", ' '.join(map(str, long_numbers)))
        print("Float числа:", ' '.join(map(str, float_numbers)))
        print("Double числа:", ' '.join(map(str, double_numbers)))

    def sum_of_numbers_greater_than_50(self):
        sum_greater_than_50 = sum(number for number in self.numbers if number > 50)
        print(f"\nСума чисел більших за 50: {sum_greater_than_50:.2f}")

if __name__ == "__main__":
    operations = NumberOperations()
    operations.print_numbers()
    operations.print_integers()
    operations.print_floats()
    operations.separate_numbers()
    operations.sum_of_numbers_greater_than_50()
