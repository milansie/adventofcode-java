package common;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AdventDay {

    public abstract String first(List<String> input);

    public abstract String second(List<String> input);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    protected boolean run1 = true;
    protected boolean run2 = true;

    protected boolean isTest = false;
    private final StringBuilder logBuffer = new StringBuilder();

    protected void debug(String message) {
        if (isTest) {
            logResult(message);
        }
    }

    private void logResult(String log) {
        if (!logBuffer.isEmpty()) {
            logBuffer.append("\n");
        }
        logBuffer.append(log);
    }

    public void run() {

        logResult("Running " + this.getClass().getPackageName() + "." + this.getClass().getSimpleName());


        isTest = true;
        long start = System.currentTimeMillis();
        try {
            String firstTestResult = first(getFirstTestInput());
            assertEquals(getFirstPartTestResult(), firstTestResult);
            logResult("First test passed in " + (System.currentTimeMillis() - start) + " ms");
        } catch (AssertionError assertionError) {
            logResult("ERROR: First test failed in " + (System.currentTimeMillis() - start) + " ms");
            logResult("ERROR: " + assertionError.getMessage());
        }

        if (run1) {
            isTest = false;
            start = System.currentTimeMillis();
            String firstResult = first(getInput());
            logResult("First part: " + firstResult + " in " + (System.currentTimeMillis() - start) + " ms");
        }

        isTest = true;
        start = System.currentTimeMillis();
        try {
            String secondTestResult = second(getSecondTestInput());
            assertEquals(getSecondPartTestResult(), secondTestResult);
            logResult("Second test passed in " + (System.currentTimeMillis() - start) + " ms");
        } catch (AssertionError assertionError) {
            logResult("ERROR: Second test failed in " + (System.currentTimeMillis() - start) + " ms");
            logResult("ERROR: " + assertionError.getMessage());
        }

        if (run2) {
            isTest = false;
            start = System.currentTimeMillis();
            String secondResult = second(getInput());
            logResult("Second part: " + secondResult + " in " + (System.currentTimeMillis() - start) + " ms");
        }

        writeLogResult();
    }

    private void writeLogResult() {
        System.out.println("==================================================================");
        System.out.println(logBuffer.toString());
        System.out.println("==================================================================");
    }


    public List<String> getFirstTestInput() {
        return readInput("1_input");
    }

    public List<String> getSecondTestInput() {
        return readInput("2_input");
    }

    public String getFirstPartTestResult() {
        List<String> result = readInput("1_result");
        return String.join("\n", result);
    }

    public String getSecondPartTestResult() {
        List<String> result = readInput("2_result");
        return result.isEmpty() ? "" : result.getFirst();
    }

    public List<String> getInput() {
        return readInput("input");
    }

    public List<String> readInput(String fileName) {
        try {
            Path path = Path.of(this.getClass().getClassLoader().getResource(
                    this.getClass().getPackageName().replaceAll("\\.", "/") + "/" + fileName +  ".txt").toURI());

            return Files.readAllLines(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
