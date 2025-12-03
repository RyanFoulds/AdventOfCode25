package xyz.foulds.aoc.year25.day03;

public class Bank {
    private final int[] batteries;
    public Bank(final String line) {
        final var chars = line.toCharArray();
        batteries = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            batteries[i] = chars[i] - '0';
        }
    }

    public long getMaxJoltage(final int cellCount) {
        final int[] maxIndices = new int[cellCount];
        for (int j = 0; j < maxIndices.length; j++) {
            final int startingIndex = j == 0 ? 0 : maxIndices[j-1] + 1;
            int currentMax = 0;
            for (int i = startingIndex; i < batteries.length - (maxIndices.length - j - 1); i++) {
                if (batteries[i] > currentMax) {
                    maxIndices[j] = i;
                    currentMax = batteries[maxIndices[j]];
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < maxIndices.length; i++) {
            sum += ((long) Math.pow(10, maxIndices.length -1 - i)) * batteries[maxIndices[i]];
        }
        return sum;
    }
}
