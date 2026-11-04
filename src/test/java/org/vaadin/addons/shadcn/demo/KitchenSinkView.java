package org.vaadin.addons.shadcn.demo;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.card.Card;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.page.ColorScheme;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.math.BigDecimal;
import java.util.List;

@Route("")
@PageTitle("shadcn-vaadin")
public class KitchenSinkView extends Main {

    public KitchenSinkView() {
        addClassName("demo");
        add(header(),
                section("Buttons", buttons()),
                section("Inputs", inputs()),
                section("Selection", selection()),
                section("Tabs", tabs()),
                section("Cards", cards()),
                section("Table", table()),
                section("Overlays", overlays()));
    }

    private Component header() {
        Div text = new Div(new H1("shadcn-vaadin"),
                new Paragraph("The shadcn/ui look, applied to Vaadin's own components through a stylesheet."));
        text.addClassName("demo__intro");

        Button theme = new Button("Toggle theme", VaadinIcon.ADJUST.create(), e -> {
            Page page = UI.getCurrent().getPage();
            boolean dark = page.getColorScheme() == ColorScheme.Value.DARK;
            page.setColorScheme(dark ? ColorScheme.Value.LIGHT : ColorScheme.Value.DARK);
        });

        Div header = new Div(text, theme);
        header.addClassName("demo__header");
        return header;
    }

    private Section section(String title, Component... content) {
        Section section = new Section(new H2(title));
        section.add(content);
        section.addClassName("demo__section");
        return section;
    }

    private Component buttons() {
        Button primary = new Button("Primary");
        primary.addThemeVariants(ButtonVariant.PRIMARY);

        Button outline = new Button("Outline");

        Button secondary = new Button("Secondary");
        secondary.addClassName("shadcn-secondary");

        Button ghost = new Button("Ghost");
        ghost.addThemeVariants(ButtonVariant.TERTIARY);

        Button link = new Button("Link");
        link.addClassName("shadcn-link");

        Button destructive = new Button("Destructive");
        destructive.addThemeVariants(ButtonVariant.PRIMARY, ButtonVariant.ERROR);

        Button destructiveOutline = new Button("Delete");
        destructiveOutline.addThemeVariants(ButtonVariant.ERROR);

        Button withIcon = new Button("Send email", VaadinIcon.ENVELOPE_O.create());

        Button disabled = new Button("Disabled");
        disabled.setEnabled(false);

        Button disabledPrimary = new Button("Disabled");
        disabledPrimary.addThemeVariants(ButtonVariant.PRIMARY);
        disabledPrimary.setEnabled(false);

        Button small = new Button("Small");
        small.addThemeVariants(ButtonVariant.SMALL);

        Button large = new Button("Large");
        large.addThemeVariants(ButtonVariant.LARGE);

        return new Div(
                row(primary, outline, secondary, ghost, link, destructive, destructiveOutline),
                row(withIcon, disabled, disabledPrimary, small, large));
    }

    private Component inputs() {
        TextField name = new TextField("Name");
        name.setPlaceholder("Jane Doe");
        name.setHelperText("This is your public display name.");

        EmailField email = new EmailField("Email");
        email.setPlaceholder("jane@example.com");
        email.setRequiredIndicatorVisible(true);

        PasswordField password = new PasswordField("Password");
        password.setValue("hunter2");

        TextField invalid = new TextField("Username");
        invalid.setValue("jd");
        invalid.setInvalid(true);
        invalid.setErrorMessage("Username must be at least 3 characters.");

        TextField disabled = new TextField("Disabled");
        disabled.setValue("Read me, can't touch me");
        disabled.setEnabled(false);

        NumberField amount = new NumberField("Amount");
        amount.setValue(250.0);
        amount.setPrefixComponent(new Span("$"));

        ComboBox<String> framework = new ComboBox<>("Framework");
        framework.setItems("Vaadin Flow", "Hilla", "Next.js", "SvelteKit", "Nuxt");
        framework.setPlaceholder("Select a framework");

        Select<String> role = new Select<>();
        role.setLabel("Role");
        role.setItems("Owner", "Admin", "Member", "Viewer");
        role.setValue("Member");

        DatePicker date = new DatePicker("Date of birth");
        date.setPlaceholder("Pick a date");

        TextArea bio = new TextArea("Bio");
        bio.setPlaceholder("Tell us a little bit about yourself");
        bio.setHelperText("You can @mention other users and organizations.");

        Div grid = new Div(name, email, password, invalid, disabled, amount, framework, role, date, bio);
        grid.addClassName("demo__grid");
        return grid;
    }

    private Component selection() {
        Checkbox terms = new Checkbox("Accept terms and conditions");
        terms.setValue(true);

        Checkbox marketing = new Checkbox("Email me about product updates");

        Checkbox disabledChecked = new Checkbox("Disabled, checked");
        disabledChecked.setValue(true);
        disabledChecked.setEnabled(false);

        CheckboxGroup<String> sidebar = new CheckboxGroup<>("Sidebar");
        sidebar.setItems("Recents", "Home", "Applications", "Desktop");
        sidebar.setValue(java.util.Set.of("Recents", "Home"));
        sidebar.setHelperText("Select the items you want to display in the sidebar.");

        RadioButtonGroup<String> notify = new RadioButtonGroup<>("Notify me about");
        notify.setItems("All new messages", "Direct messages and mentions", "Nothing");
        notify.setValue("Direct messages and mentions");

        ProgressBar progress = new ProgressBar(0, 1, 0.66);
        Div progressWrapper = new Div(new Span("Upload progress"), progress);
        progressWrapper.addClassName("demo__stack");

        Div grid = new Div(new Div(terms, marketing, disabledChecked), sidebar, notify, progressWrapper);
        grid.addClassName("demo__grid");
        grid.getComponentAt(0).addClassName("demo__stack");
        return grid;
    }

    private Component tabs() {
        TabSheet sheet = new TabSheet();
        sheet.add(new Tab("Account"), tabContent("Account",
                "Make changes to your account here. Click save when you're done."));
        sheet.add(new Tab("Password"), tabContent("Password",
                "Change your password here. After saving, you'll be logged out."));
        sheet.add(new Tab("Notifications"), tabContent("Notifications",
                "Choose what you want to be notified about."));
        return sheet;
    }

    private Component tabContent(String title, String description) {
        Card card = new Card();
        card.setTitle(title);
        card.setSubtitle(description);
        TextField field = new TextField("Name");
        field.setValue("Jane Doe");
        card.add(field);
        Button save = new Button("Save changes");
        save.addThemeVariants(ButtonVariant.PRIMARY);
        card.addToFooter(save);
        return card;
    }

    private Component cards() {
        Card notifications = new Card();
        notifications.setTitle("Notifications");
        notifications.setSubtitle("You have 3 unread messages.");
        notifications.add(new Checkbox("Push notifications to this device"));
        notifications.add(new Paragraph("Your call has been confirmed. Sent 1 hour ago."));
        notifications.add(new Paragraph("You have a new message! Sent 2 hours ago."));
        Button markRead = new Button("Mark all as read", VaadinIcon.CHECK.create());
        markRead.addThemeVariants(ButtonVariant.PRIMARY);
        notifications.addToFooter(markRead);

        Card project = new Card();
        project.setTitle("Create project");
        project.setSubtitle("Deploy your new project in one click.");
        TextField projectName = new TextField("Name");
        projectName.setPlaceholder("Name of your project");
        Select<String> framework = new Select<>();
        framework.setLabel("Framework");
        framework.setItems("Vaadin Flow", "Hilla");
        project.add(projectName, framework);
        Button cancel = new Button("Cancel");
        Button deploy = new Button("Deploy");
        deploy.addThemeVariants(ButtonVariant.PRIMARY);
        project.addToFooter(cancel, deploy);

        Div grid = new Div(notifications, project);
        grid.addClassName("demo__grid");
        return grid;
    }

    private Component table() {
        Grid<Invoice> grid = new Grid<>(Invoice.class, false);
        grid.addColumn(Invoice::number).setHeader("Invoice").setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(Invoice::status).setHeader("Status");
        grid.addColumn(Invoice::method).setHeader("Method");
        grid.addColumn(i -> "$" + i.amount().toPlainString()).setHeader("Amount").setTextAlign(
                com.vaadin.flow.component.grid.ColumnTextAlign.END);
        grid.setItems(Invoice.samples());
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setAllRowsVisible(true);
        return grid;
    }

    private Component overlays() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Edit profile");
        dialog.add(new Paragraph("Make changes to your profile here. Click save when you're done."));
        TextField name = new TextField("Name");
        name.setValue("Jane Doe");
        TextField username = new TextField("Username");
        username.setValue("@janedoe");
        dialog.add(name, username);
        Button cancel = new Button("Cancel", e -> dialog.close());
        Button save = new Button("Save changes", e -> dialog.close());
        save.addThemeVariants(ButtonVariant.PRIMARY);
        dialog.getFooter().add(cancel, save);

        Button openDialog = new Button("Open dialog", e -> dialog.open());
        Button toast = new Button("Show toast", e -> {
            Notification n = Notification.show("Event has been created. Sunday, December 03, 2026 at 9:00 AM");
            n.setPosition(Notification.Position.BOTTOM_END);
        });
        Button tooltip = new Button("Hover for tooltip");
        tooltip.setTooltipText("Add to library");

        return row(openDialog, toast, tooltip);
    }

    private static Div row(Component... components) {
        Div row = new Div(components);
        row.addClassName("demo__row");
        return row;
    }

    record Invoice(String number, String status, String method, BigDecimal amount) {
        static List<Invoice> samples() {
            return List.of(
                    new Invoice("INV-0001", "Paid", "Credit card", new BigDecimal("250.00")),
                    new Invoice("INV-0002", "Pending", "PayPal", new BigDecimal("150.00")),
                    new Invoice("INV-0003", "Unpaid", "Bank transfer", new BigDecimal("350.00")),
                    new Invoice("INV-0004", "Paid", "Credit card", new BigDecimal("450.00")),
                    new Invoice("INV-0005", "Paid", "PayPal", new BigDecimal("550.00")),
                    new Invoice("INV-0006", "Pending", "Bank transfer", new BigDecimal("200.00")));
        }
    }
}
