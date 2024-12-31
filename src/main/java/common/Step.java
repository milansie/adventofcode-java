package common;

import java.util.List;

public class Step<T> {

    private T current;
    private List<T> path;

    int totalCost = 0;

    public Step(T position, List<T> path, int totalCost) {
        this.current = position;
        this.path = path;
        this.totalCost = totalCost;
    }

    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public List<T> getPath() {
        return path;
    }

    public void setPath(List<T> path) {
        this.path = path;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String toString() {
        return "Step " + current.toString() + " totalCost: " + totalCost + " steps: " + path.size();
    }
}
