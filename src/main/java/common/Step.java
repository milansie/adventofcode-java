package common;

import java.util.List;

public class Step {

    private Position position;
    private List<Position> path;

    int totalCost = 0;

    public Step(Position position, List<Position> path, int totalCost) {
        this.position = position;
        this.path = path;
        this.totalCost = totalCost;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Position> getPath() {
        return path;
    }

    public void setPath(List<Position> path) {
        this.path = path;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String toString() {
        return "Step " + position.toString() + " totalCost: " + totalCost + " steps: " + path.size();
    }
}
