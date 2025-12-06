package xyz.foulds.aoc.year25.day06;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Worksheet {
    private final Collection<Problem> problems;

    public Worksheet(final List<String> lines) {
        problems = new ArrayList<>();
        final var operators = lines.getLast().split("\\s+");
        final var maxLen = lines.stream().mapToInt(String::length).max().getAsInt();

        var lastBreak = 0;
        for (int j = 1; j < maxLen; j++) {
            boolean allBreaks = true;
            for (int i = 0; i < lines.size() - 1; i++) {
                allBreaks &= Character.isWhitespace(getCharSafe(lines.get(i), j));
            }
            if (allBreaks) {
                final var nums = getNums(lines, lastBreak, j);
                problems.add(new Problem(operators[problems.size()].charAt(0), nums));
                lastBreak = j + 1;
            }
        }
        problems.add(new Problem(operators[operators.length -1 ].charAt(0), getNums(lines, lastBreak, maxLen)));
    }

    private static List<String> getNums(final List<String> lines, final int startCol, final int endCol) {
        final var nums = new ArrayList<String>();
        for (int i = 0; i < lines.size() - 1; i++) {
            nums.add(lines.get(i).substring(startCol, endCol));
        }
        return nums;
    }
    private static char getCharSafe(final String string, final int index) {
        return index >= string.length() ? ' ' : string.charAt(index);
    }

    public long getSumOfPartOneAnswers() {
        return problems.stream().mapToLong(Problem::getPartOneAnswer).sum();
    }
    public long getSumOfPartTwoAnswers() {
        return problems.stream().mapToLong(Problem::getPartTwoAnswer).sum();
    }
}
