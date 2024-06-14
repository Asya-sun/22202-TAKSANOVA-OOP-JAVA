package logic;

import logic.config_parser.ConfigParser;
import logic.dealer.Dealer;
import logic.details.*;
import logic.factory_controller.FactoryController;
import logic.observer_pattern.Event;
import logic.config_parser.ConfigParameters;
import logic.warehouses.*;
import logic.suppliers.*;
import logic.observer_pattern.Observer;
import view.FullFactoryPanel;
import view.factory_events.AccessoryPeriodEvent;
import view.factory_events.BodyworkPeriodEvent;
import view.factory_events.DealerPeriodEvent;
import view.factory_events.EnginePeriodEvent;

import javax.swing.*;
import java.awt.*;
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
    private final FactoryController factoryController;

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


        //controllers initializing
        factoryController = new FactoryController( accessoryWarehouse ,autoWarehouse, bodyworkWarehouse, engineWarehouse, configParser.getValue(ConfigParameters.WorkerNumber));

        autoWarehouse.setFactoryController(factoryController);

        boolean useLog = configParser.getValue(ConfigParameters.LogUse) == 1 ? true : false;
        for (int i = 0; i < configParser.getValue(ConfigParameters.DealerNumber); ++i) {
            dealers.add(new Dealer(parameters.dealerPeriod, autoWarehouse, factoryController, useLog, i));
        }

        parameters.addObserver(this);
        FullFactoryPanel panel = new FullFactoryPanel(this);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = dimension.width;
        int height = dimension.height;
        this.setBounds(0, 0, width, height);
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

    }

    @Override
    public void notify(Event event) {
        if (event instanceof AccessoryPeriodEvent) {
            parameters.accessoryPeriod = event.value * 1000;
            for (AccessorySupplier supplier : accessorySuppliers) {
                supplier.setPeriod(parameters.accessoryPeriod);
            }
        } else if (event instanceof BodyworkPeriodEvent) {
            parameters.bodyworkPeriod = event.value * 1000;
            bodyworkSupplier.setPeriod(parameters.bodyworkPeriod);
        } else if (event instanceof DealerPeriodEvent) {
            parameters.dealerPeriod = event.value * 1000;
            for (Dealer dealer : dealers) {
                dealer.setPeriod(parameters.dealerPeriod);
            }
        } else if (event instanceof EnginePeriodEvent) {
            parameters.enginePeriod = event.value * 1000;
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
