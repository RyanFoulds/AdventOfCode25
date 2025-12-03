package xyz.foulds.aoc.year25.day01;

public class Dial {
    private final long rollover;
    private long timesAtZero = 0;
    private long timesPassingZero = 0;
    private long position;

    public Dial(final long rollover, final long startingPosition) {
        this.rollover = rollover;
        this.position = startingPosition;
    }

    public void turn(final Direction direction, final long distance) {
        final var delta = direction == Direction.R ? distance : -1 * distance;
        final var newPosition = this.position + delta;
        final var simplePosition = newPosition % rollover;
        final var finalPosition = simplePosition < 0 ? simplePosition + rollover : simplePosition;

        if (direction == Direction.L && distance >= position) {
            // +1 and any full turns
            timesPassingZero += 1 + (distance-position) / rollover;
            if (position == 0) {
                timesPassingZero -= 1;
            }
        } else if (direction == Direction.R && distance + position >= rollover) {
            // +1 and any full turns
            timesPassingZero += 1 + (distance + position - rollover) / rollover;
        }

        if (finalPosition == 0) {
            timesAtZero += 1;
        }

        this.position = finalPosition;
    }

    public long getTimesAtZero() {
        return timesAtZero;
    }

    public long getTimesPassingZero() {
        return timesPassingZero;
    }
}
