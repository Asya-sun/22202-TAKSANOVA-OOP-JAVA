package view;

import logic.Factory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.net.MalformedURLException;
import java.net.URL;

public class FactoryFrame extends JFrame  {
    private Factory factory;
    private FullFactoryPanel factoryPanel;
    public FactoryFrame(Factory factory) {
        this.factory = factory;
        this.setVisible(true);
        this.setTitle("factory");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = dimension.width/2;
        int height = dimension.height/2;
        this.setBounds(dimension.width/2 - width/4*3, dimension.height/2 - height/2, width, height);


        factoryPanel = new FullFactoryPanel(factory);

        this.getContentPane().add(factoryPanel);
        //this.setResizable(false);

    }




}
