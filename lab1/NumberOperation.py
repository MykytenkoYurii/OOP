class NumberOperations:
    def __init__(self):
        self.numbers = [10, 20.5, 30, 40.7, 50, 60.3, 70, 80.1, 90, 100.9]

    def print_numbers(self):
        print("Числа:", ' '.join(map(str, self.numbers)))

    def print_integers(self):
        integers = [int(number) for number in self.numbers]
        print("Цілі числа:", ' '.join(map(str, integers)))

    def print_doubles(self):
        doubles = [f"{number:.2f}" for number in self.numbers]
        print("Дробові числа з двома знаками після коми:", ' '.join(doubles))

    def sum_of_numbers_greater_than_50(self):
        sum_numbers = sum(number for number in self.numbers if number > 50)
        formatted_sum = f"{sum_numbers:.2f}"
        print("Сума чисел більших за 50:", formatted_sum)


if __name__ == "__main__":
    number_operations = NumberOperations()
    number_operations.print_numbers()
    number_operations.print_integers()
    number_operations.print_doubles()
    number_operations.sum_of_numbers_greater_than_50()
