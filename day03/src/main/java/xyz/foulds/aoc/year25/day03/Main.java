package xyz.foulds.aoc.year25.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    static void main(final String[] args) throws IOException {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }

        final var batteryBanks = Files.readAllLines(Paths.get(args[0]))
                .stream()
                .flatMap(str -> Arrays.stream(str.split(",")))
                .map(String::trim)
                .map(Bank::new)
                .toList();

        final long start = System.nanoTime();
        final long p1 = batteryBanks.stream().mapToLong(bank -> bank.getMaxJoltage(2)).sum();
        final long p2 = batteryBanks.stream().mapToLong(bank -> bank.getMaxJoltage(12)).sum();
        final long end = System.nanoTime();

        final var millis = (end - start) / 1_000_000d;
        IO.println("Part 1: %d\nPart 2: %d\nTook: %.1fms".formatted(p1, p2, millis));
    }
}