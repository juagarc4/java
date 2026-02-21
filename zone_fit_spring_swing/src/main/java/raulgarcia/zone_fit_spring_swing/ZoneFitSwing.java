package raulgarcia.zone_fit_spring_swing;

import com.formdev.flatlaf.FlatLightLaf;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import raulgarcia.zone_fit_spring_swing.gui.ZoneFitForm;

import javax.swing.*;

@SpringBootApplication
public class ZoneFitSwing {
    static void main(String[] args) {
        FlatLightLaf.setup();
        UIManager.put("PasswordField.showRevealButton", true);
        ConfigurableApplicationContext ctxSpring = new SpringApplicationBuilder(ZoneFitSwing.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
        SwingUtilities.invokeLater(() -> {
            ZoneFitForm zoneFitForm = ctxSpring.getBean(ZoneFitForm.class);
            zoneFitForm.setVisible(true);
        });

    }
}
