package AOC.Tasks.Task3;

import AOC.AdventOfCodeTask;

import java.io.FileNotFoundException;
import java.util.*;

public class AdventOfCodeTask3 extends AdventOfCodeTask {
    private static final HashMap<Character, Integer> digits = new HashMap<>();

    static {
        for (int i = 0; i < 10; i++) {
            digits.put((char) (i + '0'), i);
        }
    }

    public AdventOfCodeTask3() throws FileNotFoundException {
        super(3, "in.txt");
    }

    @Override
    public void solveFirstPart() {
        Long answer = 0L;
        List<String> inputStrings = getInputStringsTask();
        boolean isGoodNum;
        int num;
        for (int i = 0; i < inputStrings.size(); i++) {
            isGoodNum = false;
            num = 0;
            char[] chars = inputStrings.get(i).toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (!digits.containsKey(chars[j])) {
                    if (isGoodNum) {
                        answer += num;
                        isGoodNum = false;
                    }
                    num = 0;
                    continue;
                }
                num = num * 10 + digits.get(chars[j]);
                if (isGoodNum)
                    continue;
                f:
                for (int m = -1; m < 2; m++) {
                    for (int n = -1; n < 2; n++) {
                        if (n == 0 && m == 0)
                            continue;
                        if (i + m < 0 || j + n < 0 || i + m >= inputStrings.size() || j + n >= chars.length)
                            continue;
                        char c1 = inputStrings.get(i + m).toCharArray()[j + n];
                        if (c1 != '.' && !digits.containsKey(c1)) {
                            isGoodNum = true;
                            break f;
                        }
                    }
                }
            }
            if (isGoodNum) {
                answer += num;
            }
        }
        System.out.println(answer);
    }


    @Override
    public void solveSecondPart() {
        Map<Point, List<Long>> goodNumsCords = new HashMap<>();
        Long answer = 0L;
        List<String> inputStrings = getInputStringsTask();
        boolean isGoodNum;
        long num;
        Point gearPoint = null;
        for (int i = 0; i < inputStrings.size(); i++) {
            isGoodNum = false;
            num = 0L;
            char[] chars = inputStrings.get(i).toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (!digits.containsKey(chars[j])) {
                    if (isGoodNum) {
                        isGoodNum = false;
                        List<Long> numbers = goodNumsCords
                                .computeIfAbsent(gearPoint, k -> new ArrayList<>());
                        numbers.add(num);
                    }
                    num = 0L;
                    continue;
                }
                num = num * 10 + digits.get(chars[j]);
                if (isGoodNum)
                    continue;
                f:
                for (int m = -1; m < 2; m++) {
                    for (int n = -1; n < 2; n++) {
                        if (n == 0 && m == 0)
                            continue;
                        if (i + m < 0 || j + n < 0 || i + m >= inputStrings.size() || j + n >= chars.length)
                            continue;
                        char c1 = inputStrings.get(i + m).toCharArray()[j + n];
                        if (c1 == '*') {
                            isGoodNum = true;
                            gearPoint = new Point(i + m, j + n);
                            break f;
                        }
                    }
                }
            }
            if (isGoodNum) {
                List<Long> numbers = goodNumsCords
                        .computeIfAbsent(gearPoint, k -> new ArrayList<>());
                numbers.add(num);
            }
        }
        for (var numbersNearOfGear : goodNumsCords.entrySet()) {
            List<Long> numbers = numbersNearOfGear.getValue();
            if (numbers.size() == 2) {
                answer += numbers.get(0) * numbers.get(1);
            }
        }
        System.out.println(answer);
    }
}
