package xyz.foulds.aoc.year25.day11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Network {

    private final Map<String, List<String>> devicesById;

    public Network(final List<Device> devices) {
        this.devicesById = devices.stream()
                .collect(Collectors.toMap(Device::id, Device::outputs));
    }

    public long countPaths() {
        return search("you", "out");
    }

    public long countPathsThroughDacFft() {
        final var fftToOut = search("fft", "out");
        final var dacToFft = search("dac", "fft");
        final var svrToDac = search("svr", "dac");

        final var dacToOut = search("dac", "out");
        final var fftToDac = search("fft", "dac");
        final var svrToFft = search("svr", "fft");

        return svrToDac * dacToFft * fftToOut + svrToFft * fftToDac * dacToOut;
    }

    private long search(final String start, final String end) {
        final var cache = new HashMap<String, Long>();
        return recurse(start, end, cache);
    }

    private long recurse(final String start, final String end, final Map<String, Long> cache) {
        if (cache.containsKey(start)) {
            return cache.get(start);
        }
        if (start.equals(end)) {
            return 1L;
        } else if ("out".equals(start)) {
            return 0;
        }
        final var answer = devicesById.get(start).stream()
                .mapToLong(nextStart -> recurse(nextStart, end, cache))
                .sum();
        cache.put(start, answer);
        return answer;
    }
}
