package AOC.Tasks.Task2;

import AOC.AdventOfCodeTask;
import AOC.Solving;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AdventOfCodeTask2 extends AdventOfCodeTask implements Solving {
    public static HashMap<String, Integer> m = new HashMap<>();
    public static int[] balls;

    static {
        m.put("red", 0);
        m.put("green", 1);
        m.put("blue", 2);
    }

    public AdventOfCodeTask2() throws FileNotFoundException {
        super(2, "in.txt");
    }

    @Override
    public void solveFirstPart() {
        int answer = 0;
        List<String> strs;
        List<String> strs1;
        boolean isFirstStr = true;
        boolean success = true;
        int game = 0;
        balls = new int[]{12, 13, 14};
        for (var line : getInputStringsTask()) {
            strs = new ArrayList<>(Arrays.asList(line.split("' '|,|;|:")));
            isFirstStr = true;
            success = true;
            for (var s : strs) {
                System.out.println(s);
                strs1 = List.of(s.split(" "));
                if (isFirstStr) {
                    game = Integer.parseInt(strs1.get(1));
                    isFirstStr = false;
                    continue;
                }
                System.out.println(strs1.get(2)+ " " + strs1.get(1));
                if(balls[m.get(strs1.get(2))] < Integer.parseInt(strs1.get(1))){
                    success = false;
                    break;
                }
            }
            if (success) {
                answer += game;
            }
        }
        System.out.println(answer);
    }

    @Override
    public void solveSecondPart() {
        balls = new int[]{0, 0, 0};
        int answer = 0;
        List<String> strs;
        List<String> strs1;
        for (var line : getInputStringsTask()) {
            strs = new ArrayList<>(Arrays.asList(line.split("' '|,|;|:")));
            strs.remove(0);
            for (var s : strs) {
                strs1 = List.of(s.split(" "));
                balls[m.get(strs1.get(2))] = Math.max(Integer.parseInt(strs1.get(1)), balls[m.get(strs1.get(2))]);
            }
            answer += balls[0] * balls[1] * balls[2];
            balls = new int[]{0, 0, 0};
        }
        System.out.println(answer);
    }
}
