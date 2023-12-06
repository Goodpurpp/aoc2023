package AOC.Tasks.Task4;

import AOC.AdventOfCodeTask;

import java.io.FileNotFoundException;
import java.util.*;

public class AdventOfCodeTask4 extends AdventOfCodeTask {
    public AdventOfCodeTask4() throws FileNotFoundException {
        super(4, "in.txt");
    }

    @Override
    public void solveFirstPart() {
        long answer = 0;
        List<String> inputStrings = getInputStringsTask();
        Set<Integer> firstCard = new HashSet<>();
        List<Integer> secondCard = new ArrayList<>();
        boolean isFirstCard;
        String[] splitedCards;
        for (var str : inputStrings) {
            firstCard.clear();
            secondCard.clear();
            splitedCards = str.split(" ");
            isFirstCard = true;
            for (int i = 2; i < splitedCards.length; i++) {
                if (splitedCards[i].equals("|")) {
                    isFirstCard = false;
                    continue;
                }
                if (splitedCards[i].isBlank() || splitedCards[i].contains(":"))
                    continue;
                int card = Integer.parseInt(splitedCards[i]);
                if (isFirstCard) {
                    firstCard.add(card);
                    continue;
                }
                secondCard.add(card);
            }
            secondCard.retainAll(firstCard);
            long countWinCards = secondCard.size();
            if (countWinCards != 0)
                answer += (long) Math.pow(2, countWinCards - 1);
        }
        System.out.println(answer);
    }

    @Override
    public void solveSecondPart() {
        List<String> inputStrings = getInputStringsTask();
        Set<Integer> firstCard = new HashSet<>();
        Set<Integer> secondCard = new HashSet<>();
        boolean isFirstCard;
        int[] cardCounter = new int[inputStrings.size()];
        Arrays.fill(cardCounter, 1);
        int counterCards = 0;
        String[] splitedCards;
        for (var str : inputStrings) {
            firstCard.clear();
            secondCard.clear();
            splitedCards = str.split(" ");
            isFirstCard = true;
            for (int i = 2; i < splitedCards.length; i++) {
                if (splitedCards[i].equals("|")) {
                    isFirstCard = false;
                    continue;
                }
                if (splitedCards[i].isBlank() || splitedCards[i].contains(":"))
                    continue;
                int card = Integer.parseInt(splitedCards[i]);
                if (isFirstCard) {
                    firstCard.add(card);
                    continue;
                }
                secondCard.add(card);
            }
            secondCard.retainAll(firstCard);
            for (int j = counterCards + 1; j < counterCards + secondCard.size() + 1; j++) {
                cardCounter[j] += cardCounter[counterCards];
            }
            counterCards++;
        }
        System.out.println(Arrays.stream(cardCounter).sum());
    }

}
