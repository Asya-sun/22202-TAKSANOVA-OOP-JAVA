package logic.details;

abstract public class Detail {
    protected int id;

    public Detail(int identificator) {
        this.id = identificator;
    }

    public int getId() {
        return id;
    }


}
