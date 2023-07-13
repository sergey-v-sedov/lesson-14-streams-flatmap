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
    }
}