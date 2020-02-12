package org.test;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "Voter Registration", shortName = "CanVote")
public class MainView extends VerticalLayout {

    public MainView() {
        TextField nameField = new TextField("Name");
        TextField ageField = new TextField("Age");
        Paragraph greeting = new Paragraph("");
        Button myButton = new Button("Button!", event -> {
            int num;
            boolean canSetText = true;
            try {
                num = Integer.parseInt(String.valueOf(ageField.getValue()));
            } catch (Exception e) {
                num = 0;
                Notification.show("Please enter a valid number");
                canSetText = false;
            }
            String canVote = num >= 18 ? " Can Vote" : " Can't Vote";
            if (canSetText) {
                greeting.setText("Hello, " + nameField.getValue() + canVote);
            }
            nameField.clear();
            ageField.clear();
        });
        add(nameField, ageField, greeting, myButton);
    }
}
