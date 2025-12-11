package xyz.foulds.aoc.year25.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    static void main(final String[] args) throws IOException {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }

        final var devices = Files.readAllLines(Paths.get(args[0]))
                .stream()
                .map(String::trim)
                .map(Device::new)
                .toList();

        final var network = new Network(devices);

        IO.println(network.countPaths());
        IO.println(network.countPathsThroughDacFft());
    }
}