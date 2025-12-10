package xyz.foulds.aoc.year25.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    static void main(final String[] args) throws IOException {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }

        final var machines = Files.readAllLines(Paths.get(args[0]))
                .stream()
                .map(String::trim)
                .map(Machine::new)
                .toList();

        IO.println(solveInParallel(machines, m -> m::findFewestPresses));
        IO.println(solveInParallel(machines, m -> m::findFewestJoltagePresses));

    }

    private static long solveInParallel(final List<Machine> machines, final Function<Machine, Supplier<Long>> function) {
        try (ExecutorService e = Executors.newFixedThreadPool(10)) {
            return machines.stream()
                    .map(function)
                    .map(s -> CompletableFuture.supplyAsync(s, e))
                    .reduce((a, b) -> a.thenCombineAsync(b, Long::sum))
                    .get().get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}