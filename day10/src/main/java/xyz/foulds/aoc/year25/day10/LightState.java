package xyz.foulds.aoc.year25.day10;

import java.util.Arrays;
import java.util.List;

public class LightState {
    private final boolean[] lights;
    private final long presses;

    public LightState(final int numOfLights) {
        presses = 0;
        lights = new boolean[numOfLights];
    }

    private LightState(final boolean[] newLights, final long presses) {
        this.lights = newLights;
        this.presses = presses;
    }

    public LightState next(final List<Integer> toggles) {
        final boolean[] newLights = Arrays.copyOf(lights, lights.length);
        for (int i : toggles) {
            newLights[i] = !lights[i];
        }
        return new LightState(newLights, presses + 1);
    }

    public boolean matchesLights(final boolean[] goal) {
        return Arrays.equals(lights, goal);
    }

    public long presses() {
        return presses;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof LightState other)) {
            return false;
        }
        return Arrays.equals(this.lights, other.lights);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(lights);
    }
}
