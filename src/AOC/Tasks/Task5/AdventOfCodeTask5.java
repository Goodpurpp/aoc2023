package AOC.Tasks.Task5;

import AOC.AdventOfCodeTask;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;

public class AdventOfCodeTask5 extends AdventOfCodeTask {
    private long[] seedsInt;

    public AdventOfCodeTask5() throws FileNotFoundException {
        super(5, "in.txt");
    }

    @Override
    public void solveFirstPart() {
        List<String> input = getInputStringsTask();
        String[] seeds = input.get(0).split(" ");
        seedsInt = Arrays.stream(seeds).skip(1).mapToLong(Long::parseLong).toArray();
        long[] pos;
        boolean nextStage = false;
        List<List<Long>> positions = new ArrayList<>();
        for (int i = 0; i < seedsInt.length; i++) {
            positions.add(new ArrayList<>());
            positions.get(i).add(seedsInt[i]);
        }
        for (var str : input) {
            if (str.contains(":")) {
                continue;
            }
            if (str.isBlank()) {
                for (int i = 0; i < seedsInt.length; i++) {
                    if (!positions.get(i).isEmpty())
                        seedsInt[i] = positions.get(i).get(0);
                    for (var s : positions.get(i)) {
                        seedsInt[i] = Math.min(seedsInt[i], s);
                    }
                }
                nextStage = true;
                continue;
            }
            if (str.contains("-")) {
                continue;
            }
            if (nextStage) {
                positions.forEach(List::clear);
                nextStage = false;
            }
            pos = Arrays.stream(str.split(" ")).mapToLong(Long::parseLong).toArray();
            for (int i = 0; i < seedsInt.length; i++) {
                if ((pos[1] <= seedsInt[i]) && (seedsInt[i] <= (pos[1] + pos[2] - 1))) {
                    positions.get(i).add(seedsInt[i] - pos[1] + pos[0]);
                }
            }
        }
        for (int i = 0; i < seedsInt.length; i++) {
            if (!positions.get(i).isEmpty())
                seedsInt[i] = positions.get(i).get(0);
            for (var s : positions.get(i)) {
                seedsInt[i] = Math.min(seedsInt[i], s);
            }
        }
        System.out.println(Arrays.stream(seedsInt).min().getAsLong());
    }

    @Override
    public void solveSecondPart() {
        long ans = Long.MAX_VALUE;
        List<String> input = getInputStringsTask();
        String[] seeds = input.get(0).split(" ");
        long[] seeed = Arrays.stream(seeds).skip(1).mapToLong(Long::parseLong).toArray();
        for (int i = 0; i < seeed.length; i += 2) {
            long[] q = new long[10000];
            long j = seeed[i];
            while (j < seeed[i + 1]) {
                for (int q1 = 0; q1 < 10000; q1++) {
                    q[q1] = q1 + j;
                }
                ans = Math.min(ans, solveForOneNum(q));
                j += 10000;
            }
        }
        System.out.println(ans);
    }

    public Long solveForOneNum(long[] q) {
        seedsInt = q;
        long[] pos;
        boolean nextStage = false;
        List<List<Long>> positions = new ArrayList<>();
        for (int i = 0; i < seedsInt.length; i++) {
            positions.add(new ArrayList<>());
            positions.get(i).add(seedsInt[i]);
        }
        List<String> input = getInputStringsTask();
        for (var str : input) {
            if (str.contains(":")) {
                continue;
            }
            if (str.isBlank()) {
                for (int i = 0; i < seedsInt.length; i++) {
                    if (!positions.get(i).isEmpty())
                        seedsInt[i] = positions.get(i).get(0);
                    for (var s : positions.get(i)) {
                        seedsInt[i] = Math.min(seedsInt[i], s);
                    }
                }
                nextStage = true;
                continue;
            }
            if (str.contains("-")) {
                continue;
            }
            if (nextStage) {
                positions.forEach(List::clear);
                nextStage = false;
            }
            pos = Arrays.stream(str.split(" ")).mapToLong(Long::parseLong).toArray();
            for (int i = 0; i < seedsInt.length; i++) {
                if ((pos[1] <= seedsInt[i]) && (seedsInt[i] <= (pos[1] + pos[2] - 1))) {
                    positions.get(i).add(seedsInt[i] - pos[1] + pos[0]);
                }
            }
        }
        for (int i = 0; i < seedsInt.length; i++) {
            if (!positions.get(i).isEmpty())
                seedsInt[i] = positions.get(i).get(0);
            for (var s : positions.get(i)) {
                seedsInt[i] = Math.min(seedsInt[i], s);
            }
        }
        return Arrays.stream(seedsInt).min().getAsLong();
    }
}
