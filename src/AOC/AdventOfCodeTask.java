package AOC;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class AdventOfCodeTask {
    private final Integer taskNumber;
    private FileReader taskFirstFileReader;

    protected AdventOfCodeTask(int taskNumber, String fileNameTask) throws FileNotFoundException {
        this.taskNumber = taskNumber;
        if (fileNameTask != null) {
            fileNameTask = "src/AOC/Tasks/Task" + Integer.toString(taskNumber) + "/" + fileNameTask;
            Path path = Paths.get(fileNameTask);
            System.out.println(path.toAbsolutePath());
            this.taskFirstFileReader = new FileReader(String.valueOf(path.toAbsolutePath()));
        }
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    protected List<String> getInputStringsTask() {
        return getInputStrings(taskFirstFileReader);
    }

    private List<String> getInputStrings(FileReader taskFileReader) {
        List<String> inputLines = new ArrayList<>();
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

    public abstract void solveFirstPart();

    public abstract void solveSecondPart();
}
