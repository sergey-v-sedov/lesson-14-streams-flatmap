import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
    }
}