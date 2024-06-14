package logic.observer_pattern;

abstract public class Event {
    public int value;
    public Event(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
