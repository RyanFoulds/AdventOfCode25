package xyz.foulds.aoc.year25.day10;

import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;
import com.microsoft.z3.IntNum;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.function.Predicate;

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

        this. buttons = Arrays.stream(sections, 1, sections.length - 1)
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
        final Queue<LightState> queue = new ArrayDeque<>();
        final HashSet<LightState> visited = new HashSet<>();
        queue.offer(new LightState(goalLightState.length));
        visited.add(queue.peek());

        while (!queue.isEmpty()) {
            final var current = queue.poll();
            if (current.matchesLights(goalLightState)) {
                return current.presses();
            }
            buttons.stream()
                    .map(current::next)
                    .filter(Predicate.not(visited::contains))
                    .forEach(queue::offer);
        }
        return 0L;
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
