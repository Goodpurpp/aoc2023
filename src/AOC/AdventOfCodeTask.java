package AOC;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public abstract class AdventOfCodeTask {
    private final Integer taskNumber;
    private FileReader taskFileReader;
    private final StringBuilder stringBuilder = new StringBuilder();

    protected AdventOfCodeTask(int taskNumber, String fileNameTask) throws FileNotFoundException {
        this.taskNumber = taskNumber;
        if (fileNameTask != null) {
            Path path = Paths.get(makeAbsolutePath(fileNameTask));
            this.taskFileReader = new FileReader(String.valueOf(path.toAbsolutePath()));
        }
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    protected List<String> getInputStringsTask() {
        return getInputStrings(taskFileReader);
    }

    private List<String> getInputStrings(FileReader taskFileReader){
        List<String> inputLines = new LinkedList<>();
        try (var reader = new BufferedReader(taskFileReader)) {
            String line = reader.readLine();
            while (line != null) {
                inputLines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (inputLines.isEmpty()) {
            throw new IllegalArgumentException("Empty input file");
        }
        return inputLines;
    }

    private String makeAbsolutePath(String fileNameTask) {
        return stringBuilder.append("src/AOC/Tasks/Task")
                .append(taskNumber)
                .append("/")
                .append(fileNameTask).toString();
    }

    public abstract void solveFirstPart();

    public abstract void solveSecondPart();
}
