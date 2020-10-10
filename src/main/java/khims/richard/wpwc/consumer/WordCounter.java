package khims.richard.wpwc.consumer;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class WordCounter implements Consumer<String> {
    @Override
    public void accept(String s) {
        if (s.trim().isEmpty()) return;
        String[] strings = s.trim().split("\\W");
        System.out.println(Arrays.stream(strings).filter(item -> !item.isEmpty()).collect(Collectors.toList()));
    }
}
