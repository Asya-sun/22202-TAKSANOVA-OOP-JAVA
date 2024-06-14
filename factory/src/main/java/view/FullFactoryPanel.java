package view;

import logic.Factory;
import view.factory_observables.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.io.IOException;

public class FullFactoryPanel extends JPanel implements ActionListener {
    private final Factory factory;

    public FullFactoryPanel(Factory factory) {
        this.factory = factory;
        this.setBackground(Color.RED);

        DealerPeriodObservable dealerPeriodObserver = new DealerPeriodObservable();
        AccessoryPeriodObservable accessoryObserver = new AccessoryPeriodObservable();
        EnginePeriodObservable engineObserver = new EnginePeriodObservable();
        BodyworkPeriodObservable bodyworkObserver = new BodyworkPeriodObservable();

        dealerPeriodObserver.addObserver(factory.parameters);
        accessoryObserver.addObserver(factory.parameters);
        engineObserver.addObserver(factory.parameters);
        bodyworkObserver.addObserver(factory.parameters);

        FactoryElementPanel dealerPanel = new FactoryElementPanel(factory.parameters.dealerPeriod,
                "dealer period", dealerPeriodObserver);
        FactoryElementPanel accessoryPanel = new FactoryElementPanel(factory.parameters.accessoryPeriod,
                "accessory period", accessoryObserver);
        FactoryElementPanel bodyworkPanel = new FactoryElementPanel(factory.parameters.bodyworkPeriod,
                "bodywork period", bodyworkObserver);
        FactoryElementPanel enginePanel = new FactoryElementPanel(factory.parameters.enginePeriod,
                "engine period", engineObserver);

        dealerPanel.setLocation(0,100);
        accessoryPanel.setLocation(100, 100);
        bodyworkPanel.setLocation(200, 100);
        enginePanel.setLocation(300, 100);

        this.add(dealerPanel);
        this.add(accessoryPanel);
        this.add(bodyworkPanel);
        this.add(enginePanel);

        Timer timer = new Timer(1000, this);
        timer.start();
    }





    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Image image = null;
        try {
            image = ImageIO.read(FullFactoryPanel.class.getResource("/factory.png"));
            Toolkit toolkit = Toolkit.getDefaultToolkit();

            Dimension dimension = toolkit.getScreenSize();
            int width = dimension.width;
            int height = dimension.height;
            g2.drawImage(image, dimension.width/2 - width/4, dimension.height/2 - height/4,null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        String data = getData();
        FontRenderContext frc = g2.getFontRenderContext();
        Font font = new Font("Times New Roman", Font.BOLD, 15);
        TextLayout layout = new TextLayout(data, font, frc);

        g2.setColor(Color.WHITE);
        g2.setFont(font);
        String[] outputs = data.split("\n");
        for (int i = 0; i < outputs.length; i++) {
            g2.drawString(outputs[i], 10, (int) (200 + i * layout.getBounds().getHeight() + 1));
        }
    }

    private String getData() {
        String info = "";
        info += "Accessories delivered: " + factory.getAccessoryWarehouse().getTotalNumber() + '\n';
        info += "Bodyworks delivered: " + factory.getBodyworkWarehouse().getTotalNumber() + '\n';
        info += "Engines delivered: " + factory.getEngineWarehouse().getTotalNumber() + '\n';
        info += "Autos produced: " + factory.getAutoWarehouse().getTotalNumber() + "\n\n";

        info += "Accessories in warehouse: " + factory.getAccessoryWarehouse().getCurrentSize() + '\n';
        info += "Bodyworks in warehouse: " + factory.getBodyworkWarehouse().getCurrentSize() + '\n';
        info += "Engines in warehouse: " + factory.getEngineWarehouse().getCurrentSize() + '\n';
        info += "Autos in warehouse: " + factory.getAutoWarehouse().getCurrentSize() + "\n";

        return info;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
