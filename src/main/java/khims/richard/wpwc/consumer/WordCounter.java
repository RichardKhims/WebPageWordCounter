package khims.richard.wpwc.consumer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class WordCounter implements Consumer<String> {
    private Map<String, Long> mapWords = new HashMap<>();

    @Override
    public void accept(String s) {
        if (s.trim().isEmpty()) return;

        Map<String, Long> map = getMapWordsFor(s);
        mergeMapWords(map);
    }

    public Map<String, Long> getMapWords() {
        return mapWords;
    }

    private Map<String, Long> getMapWordsFor(String s) {
        String[] strings = s.trim().split("\\W");
        return Arrays.stream(strings)
                .filter(word -> !word.isEmpty())
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    }

    private void mergeMapWords(Map<String, Long> map) {
        map.forEach((key, value) -> {
            Long currentWordCount = mapWords.getOrDefault(key, 0L);
            mapWords.put(key, currentWordCount + value);
        });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        mapWords.forEach((key, value) -> {
            sb.append(key).append(" - ").append(value).append('\n');
        });
        return sb.toString();
    }
}
