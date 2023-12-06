package AOC.Tasks.Task6;

import AOC.AdventOfCodeTask;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class AdventOfCodeTask6 extends AdventOfCodeTask {

    public AdventOfCodeTask6() throws FileNotFoundException {
        super(6, "in.txt");
    }

    @Override
    public void solveFirstPart() {
        long ans = 1;
        List<String> input = getInputStringsTask();
        long[] time = Arrays.stream(input.get(0).split(" ")).skip(1)
                .filter(l -> !l.isBlank())
                .mapToLong(Long::parseLong).toArray();
        long[] dist = Arrays.stream(input.get(1).split(" ")).skip(1)
                .filter(l -> !l.isBlank())
                .mapToLong(Long::parseLong).toArray();
        for(int i = 0; i < time.length;i++) {
            final int q = i;
            ans *= LongStream.range(1,time[i]).filter(sp-> dist[q] < (sp * (time[q] - sp))).count();
        }
        System.out.println(ans);
    }

    @Override
    public void solveSecondPart() {
        long ans = 1;
        List<String> input = getInputStringsTask();
        long time = Arrays.stream(input.get(0).split(":")[1].split(" ")).filter(l -> !l.isBlank())
                .mapToLong(Long::parseLong)
                .reduce((x,y) -> ((x * 100) + y)).getAsLong();
        long dist = Long.parseLong(Arrays.stream(input.get(1).split(":")[1].split(" ")).filter(l -> !l.isBlank())
                .reduce((x,y) -> (x + y)).get());
        ans *= LongStream.range(1,time).filter(sp-> dist < (sp * (time - sp))).count();
        System.out.println(ans);
    }
}
