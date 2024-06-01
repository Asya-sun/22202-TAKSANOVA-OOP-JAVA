package logic.details;

import logic.id_work.CreatorID;

public class Auto extends Detail{
    private Engine engine;
    private Accessory accessory;
    private Bodywork bodywork;
    public Auto(Engine engine1, Bodywork bodywork1, Accessory accessory1) {
        super(CreatorID.getID());
        engine = engine1;
        bodywork = bodywork1;
        accessory = accessory1;
    }

    public Engine getEngine() {
        return engine;
    }

    public Bodywork getBodywork() {
        return bodywork;
    }

    public Accessory getAccessory() {
        return accessory;
    }
}
