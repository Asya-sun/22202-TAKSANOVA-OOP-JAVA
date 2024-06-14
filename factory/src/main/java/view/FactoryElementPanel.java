package view;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class FactoryElementPanel extends JPanel {
    JLabel label;
    JSlider slider;
    public FactoryElementPanel(int current, String labelName, ChangeListener changeListener) {

        this.label = new JLabel(labelName);
        label.setSize(50,50);

        slider = new JSlider(5, 50, current / 1000);


        slider.setMajorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(changeListener);
        slider.setPaintTrack(true);
        this.add(label);
        this.add(slider);

    }

}
