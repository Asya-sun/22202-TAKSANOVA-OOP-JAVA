package logic;

import logic.config_parser.ConfigParser;
import logic.dealer.Dealer;
import logic.details.*;
import logic.factory_controllers.AutoWarehouseController;
import logic.factory_controllers.SuppliersController;
import logic.observer_pattern.Event;
import logic.config_parser.ConfigParameters;
import logic.warehouses.*;
import logic.suppliers.*;
import logic.observer_pattern.Observer;
import view.FactoryFrame;
import view.FullFactoryPanel;
import view.factory_events.AccessoryPeriodEvent;
import view.factory_events.BodyworkPeriodEvent;
import view.factory_events.DealerPeriodEvent;
import view.factory_events.EnginePeriodEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Factory extends JFrame  implements Runnable, Observer {
    //warehouses
    private final AccessoryWarehouse accessoryWarehouse;
    private final EngineWarehouse engineWarehouse;
    private final BodyworkWarehouse bodyworkWarehouse;
    private final AutoWarehouse<Auto> autoWarehouse;
    //suppliers
    private final BodyworkSupplier bodyworkSupplier;
    private final EngineSupplier engineSupplier;
    private final ArrayList<AccessorySupplier> accessorySuppliers;
    //dealers
    private final ArrayList<Dealer> dealers;
    //autro warehouse controller
    private final AutoWarehouseController autoWarehouseController;

    public Parameters parameters;

    public Factory() {
        ConfigParser configParser = new ConfigParser();
        parameters = new Parameters();
        //warehouses initializing
        accessoryWarehouse = new AccessoryWarehouse<>(configParser.getValue(ConfigParameters.StorageAccessorySize));
        engineWarehouse = new EngineWarehouse<>(configParser.getValue(ConfigParameters.StorageEngineSize));
        bodyworkWarehouse = new BodyworkWarehouse<>(configParser.getValue(ConfigParameters.StorageBodyworkSize));
        autoWarehouse = new AutoWarehouse<>(configParser.getValue(ConfigParameters.StorageAutoSize));
        //suppliers initializing
        bodyworkSupplier = new BodyworkSupplier(parameters.bodyworkPeriod, bodyworkWarehouse);
        engineSupplier = new EngineSupplier(parameters.enginePeriod, engineWarehouse);
        accessorySuppliers = new ArrayList<>();
        for (int i = 0; i < configParser.getValue(ConfigParameters.AccessorySupplierNumber); ++i) {
            accessorySuppliers.add(new AccessorySupplier(parameters.accessoryPeriod, accessoryWarehouse));
        }
        //dealers initializing
        dealers = new ArrayList<>();
        boolean useLog = configParser.getValue(ConfigParameters.LogUse) == 1 ? true : false;
        for (int i = 0; i < configParser.getValue(ConfigParameters.DealerNumber); ++i) {
            dealers.add(new Dealer(parameters.dealerPeriod, autoWarehouse, useLog, i));
        }

        //controllers initializing
        SuppliersController suppliersController = new SuppliersController(configParser.getValue(ConfigParameters.WorkerNumber),
                accessoryWarehouse, engineWarehouse, bodyworkWarehouse, autoWarehouse);
        autoWarehouseController = new AutoWarehouseController(autoWarehouse, suppliersController);

        parameters.addObserver(this);
        //frame = new FactoryFrame(this);
        FullFactoryPanel panel = new FullFactoryPanel(this);
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Dimension dimension = toolkit.getScreenSize();
        int width = dimension.width;
        int height = dimension.height;
        this.setBounds(dimension.width/2 - width/2, dimension.height/2 - height/2, width, height);

        //this.setSize(new Dimension(1080, 720));
        this.setTitle("factory");
        this.getContentPane().add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        parameters.addObserver(this);
    }


    @Override
    public void run() {
        for (AccessorySupplier accessorySupplier : accessorySuppliers) {
            accessorySupplier.start();
        }

        engineSupplier.start();
        bodyworkSupplier.start();
        for (Dealer dealer : dealers) {
            dealer.start();
        }
        autoWarehouseController.start();
    }

    @Override
    public void update(Event event) {
        if (event instanceof AccessoryPeriodEvent) {
            parameters.accessoryPeriod = event.getValue() * 10000;
            for (AccessorySupplier supplier : accessorySuppliers) {
                supplier.setPeriod(parameters.accessoryPeriod);
            }
        } else if (event instanceof BodyworkPeriodEvent) {
            parameters.bodyworkPeriod = event.getValue() * 10000;
            bodyworkSupplier.setPeriod(parameters.bodyworkPeriod);
        } else if (event instanceof DealerPeriodEvent) {
            parameters.dealerPeriod = event.getValue() * 10000;
            for (Dealer dealer : dealers) {
                dealer.setPeriod(parameters.dealerPeriod);
            }
        } else if (event instanceof EnginePeriodEvent) {
            parameters.enginePeriod = event.getValue() * 10000;
            engineSupplier.setPeriod(parameters.enginePeriod);
        }
    }

    public AccessoryWarehouse getAccessoryWarehouse() {
        return accessoryWarehouse;
    }

    public AutoWarehouse<Auto> getAutoWarehouse() {
        return autoWarehouse;
    }

    public BodyworkWarehouse getBodyworkWarehouse() {
        return bodyworkWarehouse;
    }

    public EngineWarehouse getEngineWarehouse() {
        return engineWarehouse;
    }

}
