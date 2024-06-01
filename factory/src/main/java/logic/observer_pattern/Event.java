package logic.observer_pattern;

abstract public class Event {
    private int value;
    public Event(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
