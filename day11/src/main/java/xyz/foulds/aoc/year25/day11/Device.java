package xyz.foulds.aoc.year25.day11;

import java.util.Arrays;
import java.util.List;

public record Device(String id, List<String> outputs) {
    public Device(final String line) {
        final var splitLine = line.split(": ");
        this(splitLine[0], Arrays.stream(splitLine[1].split(" ")).toList());
    }
}
