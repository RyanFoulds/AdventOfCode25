package xyz.foulds.aoc.year25.day02;


import java.util.stream.LongStream;

public class Range {
    private final long start;
    private final long end;

    public Range(final String inputLine) {
        final var input = inputLine.split("-");
        this.start = Long.parseLong(input[0]);
        this.end = Long.parseLong(input[1]);
    }

    public long getSumOfAllCandidates() {
        return LongStream.rangeClosed(2L, String.valueOf(end).length())
                .flatMap(this::getCandidates)
                .distinct()
                .sum();
    }

    public LongStream getCandidates(final long repeats) {
        final var first = getFirstCandidateComponent(repeats);
        final var last = getLastCandidateComponent(repeats);
        if (first > last) return LongStream.of(0L);

        return LongStream.rangeClosed(first, last)
                .map(l -> repeat(l, repeats));
    }

    private long getFirstCandidateComponent(final long repeats) {
        final String startStr = String.valueOf(start);
        final var candidateDigitCount = startStr.length() / repeats;

        if (startStr.length() % repeats == 0) {
            final var candidate = Long.parseLong(startStr.substring(0, (int) candidateDigitCount));
            return repeat(candidate, repeats) >= start ? candidate : candidate + 1;
        } else {
            return Long.parseLong("1" + "0".repeat((int) candidateDigitCount));
        }
    }

    private long getLastCandidateComponent(final long repeats) {
        final String endStr = String.valueOf(end);
        final var candidateDigitCount = endStr.length() / repeats;
        if (candidateDigitCount == 0) {
            return 0;
        }

        if (endStr.length() % repeats == 0) {
            final var den = LongStream.range(1, repeats).map(l -> (long) Math.pow(10L, l * candidateDigitCount)).sum() + 1;
            return end / den;
        } else {
            return Long.parseLong("9".repeat((int) candidateDigitCount));
        }
    }

    public static long repeat(final long input, final long times) {
        final var len = (long) Math.log10(input) + 1;
        final var multiplier = LongStream.range(1, times).map(l -> (long) Math.pow(10L, l * len)).sum() + 1;
        return multiplier * input;
    }
}
