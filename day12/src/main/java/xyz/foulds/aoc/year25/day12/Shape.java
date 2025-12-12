package xyz.foulds.aoc.year25.day12;

import java.util.List;

public record Shape(int id, long area) {

    public Shape(final List<String> lines) {
        this(lines.getFirst().charAt(0) - '0',
                lines.stream()
                        .flatMapToInt(String::chars)
                        .filter(i -> i == '#')
                        .count());
    }

    public long area() {
        return area;
    }

    public int id() {
        return id;
    }
}
