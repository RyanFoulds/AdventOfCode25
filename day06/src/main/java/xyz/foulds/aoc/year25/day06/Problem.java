package xyz.foulds.aoc.year25.day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongBinaryOperator;

public class Problem {
    private final List<String> values = new ArrayList<>();
    private final int maxLength;
    private final LongBinaryOperator operator;

    public Problem(final char operator, final List<String> value) {
        this.operator = operator == '*' ? (a, b) -> a * b : Long::sum;
        values.addAll(value);
        maxLength = values.stream().mapToInt(String::length).max().getAsInt();
    }

    public long getPartOneAnswer() {
        return values.stream()
                .map(String::trim)
                .mapToLong(Long::parseLong)
                .reduce(this.operator)
                .getAsLong();
    }

    public long getPartTwoAnswer() {
        final var partTwoValues = new String[maxLength];
        for (int i = 0; i < maxLength; i++) {
            var builder = new StringBuilder();
            for (final String value : values) {
                builder.append(value.charAt(i));
            }
            partTwoValues[i] = builder.toString().trim();
        }

        return Arrays.stream(partTwoValues).mapToLong(Long::parseLong).reduce(this.operator).getAsLong();
    }
}
