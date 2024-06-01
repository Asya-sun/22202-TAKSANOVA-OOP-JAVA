package logic.id_work;

public class CreatorID {
    static private int id = 0;

    static public int getID() {
        return ++id;
    }
}
