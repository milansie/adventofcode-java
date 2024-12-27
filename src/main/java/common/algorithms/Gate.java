package common.algorithms;

public class Gate {

    private String input1;
    private String input2;

    private String output;

    private InstructionType instruction;

    public int performIntegerAction(Integer input1Value, Integer input2Value) {

        switch (instruction) {
            case AND:
                return input1Value & input2Value;
            case OR:
                return input1Value | input2Value;
            case XOR:
                return input1Value ^ input2Value;
            case NOT:
                return ~input1Value & 0xFFFF;
            case RSHIFT:
                return input1Value >> input2Value;
            case LSHIFT:
                return input1Value << input2Value;
            case SET:
                return input1Value;
            default:
                throw new IllegalStateException("Unknown instruction");
        }

    }

    public Boolean performBinaryAction(Integer input1Value, Integer input2Value) {
        switch (instruction) {
            case AND:
                return input1Value == 1 && input2Value == 1;
            case OR:
                return input1Value == 1 || input2Value == 1;
            case XOR:
                return input1Value == 1 ^ input2Value == 1;
            case NOT:
                return input1Value == 0;
            case SET:
                return input1Value == 1;

            default:
                throw new IllegalStateException("Unknown instruction");
        }
    }

    public enum InstructionType {
        AND,
        OR,
        XOR,
        NOT,
        RSHIFT,
        LSHIFT,
        SET
    }

    public Gate (String output, InstructionType instructionType, String input1) {
        this.output = output;
        this.instruction = instructionType;
        this.input1 = input1;
        this.input2 = null;
    }

    public Gate(String output, InstructionType instructionType, String input1, String input2) {
        this.output = output;
        this.instruction = instructionType;
        this.input1 = input1;
        this.input2 = input2;
    }

    public InstructionType getInstruction() {
        return instruction;
    }

    public void setInstruction(InstructionType instruction) {
        this.instruction = instruction;
    }

    public String getInput1() {
        return input1;
    }

    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public String getInput2() {
        return input2;
    }

    public void setInput2(String input2) {
        this.input2 = input2;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String toString() {

        if (input2 == null) {
            return "Gate [" + instruction + " " + input1 + " -> " + output + "]";
        } else {
            return "Gate [" + input1 + " " + instruction + " " + input2 + " -> " + output + "]";
        }

  }
}
