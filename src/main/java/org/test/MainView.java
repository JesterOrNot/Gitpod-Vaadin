package org.test;

import java.time.LocalDate;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.Label;
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

    public MainView() {
        TextField nameField = new TextField("Name");
        DatePicker datePicker = new DatePicker();
        datePicker.setClearButtonVisible(true);
        datePicker.setLabel("Birthday");
        Button submitButton = new Button("Submit", new Icon(VaadinIcon.ARROW_RIGHT), event -> {
            int age;
            boolean canShowText = true;
            try {
                age = datePicker.getValue().until(LocalDate.now()).getYears();
            } catch (Exception e) {
                age = 0;
                Notification.show("Error: Please enter a valid date.");
                canShowText = false;
            }
            if (nameField.isEmpty()) {
                canShowText = false;
                Notification.show("Error: Please enter a name.");
            }
            String canVote = age >= 18 ? "Congrats, You can vote " : "Sorry, you can't vote ";
            if (canShowText) {
                new Dialog(new Label(canVote + nameField.getValue())).open();
            }
            nameField.clear();
            datePicker.clear();
        });
        submitButton.setIconAfterText(true);
        add(nameField, datePicker, submitButton);
    }
}
