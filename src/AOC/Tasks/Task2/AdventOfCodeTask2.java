package AOC.Tasks.Task2;

import AOC.AdventOfCodeTask;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AdventOfCodeTask2 extends AdventOfCodeTask {
    public static HashMap<String, Integer> ballsColor = new HashMap<>();
    public static int[] balls;

    static {
        ballsColor.put("red", 0);
        ballsColor.put("green", 1);
        ballsColor.put("blue", 2);
    }

    public AdventOfCodeTask2() throws FileNotFoundException {
        super(2, "in.txt");
    }

    @Override
    public void solveFirstPart() {
        int answer = 0;
        List<String> strs;
        List<String> strs1;
        boolean isFirstStr;
        boolean isSuccessGame;
        int game = 0;
        balls = new int[]{12, 13, 14};
        for (var line : getInputStringsTask()) {
            strs = new ArrayList<>(Arrays.asList(line.split("' '|,|;|:")));
            isFirstStr = true;
            isSuccessGame = true;
            int ballColorIndex;
            for (var s : strs) {
                strs1 = List.of(s.split(" "));
                if (isFirstStr) {
                    game = Integer.parseInt(strs1.get(1));
                    isFirstStr = false;
                    continue;
                }
                ballColorIndex = ballsColor.get(strs1.get(2));
                if (balls[ballColorIndex] < Integer.parseInt(strs1.get(1))) {
                    isSuccessGame = false;
                    break;
                }
            }
            if (isSuccessGame) {
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
        int ballColorIndex;
        for (var line : getInputStringsTask()) {
            strs = new ArrayList<>(Arrays.asList(line.split("' '|,|;|:")));
            strs.remove(0);
            for (var s : strs) {
                strs1 = List.of(s.split(" "));
                ballColorIndex = ballsColor.get(strs1.get(2));
                balls[ballColorIndex] = Math.max(Integer.parseInt(strs1.get(1)), balls[ballColorIndex]);
            }
            answer += balls[0] * balls[1] * balls[2];
            balls = new int[]{0, 0, 0};
        }
        System.out.println(answer);
    }
}
