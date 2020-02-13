package org.test;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.router.Route;

@Route
@Theme(value = Lumo.class, variant = Lumo.DARK)
@PWA(name = "Voter Registration", shortName = "CanVote")
public class MainView extends VerticalLayout {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MainView() {
        TextField nameField = new TextField("Name");
        TextField ageField = new TextField("Age");
        Paragraph message = new Paragraph("");
        Button submitButton = new Button("Submit", new Icon(VaadinIcon.ARROW_RIGHT), event -> {
            int num;
            boolean canSetText = true;
            try {
                num = Integer.parseInt(String.valueOf(ageField.getValue()));
            } catch (Exception e) {
                num = 0;
                Notification.show("Please enter a valid number");
                canSetText = false;
                message.setText("");
            }
            String canVote = num >= 18 ? ", you can vote!" : ", you can't vote!";
            if (canSetText) {
                message.setText(nameField.getValue() + canVote);
            }
            nameField.clear();
            ageField.clear();
        });
        submitButton.setIconAfterText(true);
        add(nameField, ageField, message, submitButton);
    }
}
