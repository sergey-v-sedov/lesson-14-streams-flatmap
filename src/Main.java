import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // Дан список строк, представляющих предложения. Используя Stream API
        // отсортируйте предложения по количеству слов в каждом предложении в порядке убывания.

        List<String> text = Arrays.asList(
                "sdfsdf dfgsdfg dfgsdfg dfgdfsgdsf dfgsdfgsdf dsfgdfsgsdf",
                "sdfsdf dfgsdfg dfgsdfg dfgdfsgdsf dfgsdfgsdf",
                "sdfsdf dfgsdfg dfgsdfg dfgdfsgdsffgdfgdfgdfgdf dfgsdfgsdf dsfgdfsgsdf dfsdfdf",
                "sdfsdf dfgsdfg dfgsdfg dfgdfsgdsf"
        );

        List<String> sortedText = text.stream().sorted(Comparator.comparingInt(sentence -> sentence.split(" ").length)).sorted(Comparator.reverseOrder()).toList();
        System.out.println(sortedText);

        String longestWord = text.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .max(Comparator.comparingInt(String::length)).orElse("");

        System.out.println(longestWord);

        //======================================

        List<List<String>> countries = Arrays.asList(
                Arrays.asList("Moscow", "St.Pet"),
                Arrays.asList("Berlin", "Frankfurt"),
                Arrays.asList("New York", "Moscow")
        );

        long count = countries.stream().flatMap((country)-> country.stream()).count();
        System.out.println(count);

        Set<String> cities = countries.stream().flatMap((country)-> country.stream()).collect(Collectors.toSet());
        System.out.println(cities);

        //======================================

        List<String> words = Arrays.asList("dsdfdsd", "dfsdfdsfds", "dfsd", "fgd", "d");
        Map<String, Integer> wordsLength = words.stream().collect(Collectors.toMap(word->word, word->word.length()));
        System.out.println(wordsLength);

        //======================================

        String allWords = words.stream().filter(word->word.length()>3).collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().collect(Collectors.joining("*"))));
        System.out.println(allWords);

        String value = words.toString();
        System.out.println(value);

        //======================================

        String wordsWithSeparator = words.stream().collect(Collectors.joining(";"));
        System.out.println(wordsWithSeparator);

        //======================================

        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange", "Avocado");
        Map<Character, Long> countByLength = fruits.stream().collect(Collectors.groupingBy((fruit)->fruit.charAt(0), Collectors.counting()));
        System.out.println(countByLength);

        //=======================================

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Иванов Иван", "Программист", 2000, "Разработки"));
        employees.add(new Employee("Пётр Петров", "Программист", 1500, "Разработки"));
        employees.add(new Employee("Сидр Сидоров", "Менеджер", 999, "Разработки"));
        employees.add(new Employee("Сергей Сергеев", "Тестировщик", 1800, "Тестирования"));
        employees.add(new Employee("Константин Константинов", "Тестировщик", 1500, "Тестирования"));

        // Группировка сотрудников по должности
        Map<String, List<Employee>> employeesByPosition = employees.stream()
                .collect(Collectors.groupingBy(Employee::getPosition));
        System.out.println("По должности:" + employeesByPosition);

        // Вычисление средней зарплаты всех сотрудников
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);
        System.out.println("Средняя зарплата: " + averageSalary);

        // Cписок сотрудников с зарплатой > 1000
        List<Employee> highSalaryEmployees = employees.stream()
                .filter(employee -> employee.getSalary() > 1000)
                .toList();
        System.out.println("Сотрудники с зарплатой > 1000:" + highSalaryEmployees);

        // Увеличение зарплаты сотрудников из отдела "Тестирования" на 15%
        List<Employee> itEmployees = employees.stream()
                .filter(employee -> employee.getDepartment().equals("Тестирования"))
                .peek(employee -> employee.setSalary(employee.getSalary() * 1.15))
                .toList();
        System.out.println("Сотрудники с увеличенной зарплатой:" + itEmployees);

        // Получение сотрудника с самой низкой зарплатой
        Employee lowestSalaryEmployee = employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println("С самой низкой зарплатой: " + lowestSalaryEmployee);

        // Получение сотрудников из всех отделов с максимальной зарплатой
        Map<String,Optional<Employee>> employeesByMaxSalary = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparingDouble(emp -> emp.getSalary()))));
        System.out.println("С самой высокой зарплатой по отделам: " +employeesByMaxSalary);
    }
}