package xyz.foulds.aoc.year25.day10;

import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;
import com.microsoft.z3.IntNum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Machine {

    private final List<List<Integer>> buttons;
    private final boolean[] goalLightState;
    private final long[] joltageRequirements;

    public Machine(final String line) {
        var sections = line.split(" ");
        final String lights = sections[0].substring(1, sections[0].length() - 1);
        this.goalLightState = new boolean[lights.length()];
        for (int i = 0; i < this.goalLightState.length; i++) {
            this.goalLightState[i] = lights.charAt(i) == '#';
        }

        this.buttons = Arrays.stream(sections, 1, sections.length - 1)
                .map(s -> s.substring(1, s.length() - 1))
                .map(indices -> Arrays.stream(indices.split(","))
                        .map(Integer::parseInt)
                        .toList())
                .toList();

        final String joltages = sections[sections.length - 1].substring(1, sections[sections.length - 1].length() - 1);
        this.joltageRequirements = Arrays.stream(joltages.split(","))
                .mapToLong(Long::parseLong)
                .toArray();
    }

    public long findFewestPresses() {
        var minPresses = Long.MAX_VALUE;
        // Just try all possible combinations of button presses, only 0 or 1 each.
        final int combinations = Math.powExact(2, buttons.size());
        for (int i = 0; i < combinations; i++) {
            final var bitCount = Integer.bitCount(i);
            if (bitCount > minPresses) continue;

            var result = new LightState(goalLightState.length);
            for (int j = 0; j < buttons.size(); j++) {
                if ((i / Math.powExact(2, j)) % 2 == 1) {
                    result = result.next(buttons.get(j));
                }
            }

            if (result.matchesLights(goalLightState)) {
                minPresses = bitCount;
            }
        }
        return minPresses;
    }

    public long findFewestJoltagePresses() {
        try (final Context ctx = new Context()) {
            final var problem = ctx.mkOptimize();
            final var zero = ctx.mkInt(0);
            final var buttonPresses = new IntExpr[buttons.size()];
            for (int i = 0; i < buttons.size(); i++) {
                buttonPresses[i] = ctx.mkIntConst("button " + i);
                final var nonNegativeConstraint = ctx.mkGe(buttonPresses[i], zero);
                problem.Add(nonNegativeConstraint);
            }

            for (int i = 0; i < joltageRequirements.length; i++) {
                final List<IntExpr> buttonVars = new ArrayList<>();
                for (int j = 0; j < buttons.size(); j++) {
                    if (buttons.get(j).contains(i)) {
                        buttonVars.add(buttonPresses[j]);
                    }
                }

                final var target = ctx.mkInt(joltageRequirements[i]);
                final var sumOfPresses = ctx.mkAdd(buttonVars.toArray(new IntExpr[0]));
                final var constraint = ctx.mkEq(target, sumOfPresses);
                problem.Add(constraint);
            }

            final var sum = ctx.mkAdd(buttonPresses);

            problem.MkMinimize(sum);
            problem.Check();

            return ((IntNum) problem.getModel().evaluate(sum, false)).getInt();
        }
    }
}
