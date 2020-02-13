package org.test;

import java.time.LocalDate;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route
@Theme(value = Lumo.class, variant = Lumo.DARK)
@PageTitle("Voter Registration")
@PWA(name = "Voter Registration", shortName = "CanVote")
public class MainView extends VerticalLayout {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MainView() {
        TextField nameField = new TextField("Name");
        DatePicker datePicker = new DatePicker();
        datePicker.setClearButtonVisible(true);
        datePicker.setLabel("Birthday");
        Paragraph message = new Paragraph("");
        Button submitButton = new Button("Submit", new Icon(VaadinIcon.ARROW_RIGHT), event -> {
            int age;
            boolean canSetText = true;
            try {
                age = datePicker.getValue().until(LocalDate.now()).getYears();
            } catch (Exception e) {
                age = 0;
                Notification.show("Error: Please enter a valid date.");
                canSetText = false;
                message.setText("");
            }
            if(nameField.isEmpty()) {
                canSetText = false;
                Notification.show("Error: Please enter a name.");
            }
            String canVote = age >= 18 ? ", you can vote!" : ", you can't vote!";
            if (canSetText) {
                message.setText(nameField.getValue() + canVote);
            }
            nameField.clear();
            datePicker.clear();
        });
        submitButton.setIconAfterText(true);
        add(nameField, datePicker, message, submitButton);
    }
}
