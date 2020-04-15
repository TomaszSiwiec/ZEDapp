package com.zedapp.frontend.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.zedapp.frontend.domain.Order;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.Inet4Address;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Route("main")
@CssImport("./styles/MainViewStyles.css")
@CssImport(value = "./styles/WrapTextButton.css", themeFor ="vaadin-button")
//@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    private Grid<Order> grid;
    private ListDataProvider<Order> dataProvider;

    private VerticalLayout allLayout;
    private HorizontalLayout mainMenuLayout;
    private VerticalLayout menuLayout;
    private VerticalLayout mainLayout;

    private Button buttonAdd;
    private Button buttonEdit;

    private int i;

    public MainView() throws URISyntaxException, JsonProcessingException, UnknownHostException {
        allLayout = getAllLayout();
        mainMenuLayout = getMainMenuLayout();
        menuLayout = getMenuLayout();
        mainLayout = getMainLayout();

        mainMenuLayout.add(menuLayout, mainLayout);
        allLayout.add(getHeaderLayout(), mainMenuLayout);
        allLayout.setSizeFull();
        setSizeFull();
        setPadding(false);
        add(allLayout);
    }

    private VerticalLayout getAllLayout() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addClassName("allLayout");
        verticalLayout.setSpacing(false);
        verticalLayout.setPadding(false);
        verticalLayout.setSizeFull();
        return verticalLayout;
    }

    private HorizontalLayout getHeaderLayout() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addClassName("headerLayout");

        VerticalLayout verticalLayout = new VerticalLayout();
        horizontalLayout.addClassName("insideHeaderLayout");

        Image image = new Image();
        image.addClassName("profileImageInHeader");

        Label label = new Label("Tomasz Siwiec");
        label.addClassName("headerNameLabel");
        Icon icon = new Icon(VaadinIcon.COG_O);
        HorizontalLayout buttonContent = new HorizontalLayout();
        buttonContent.addClassName("buttonContent");
        buttonContent.add(image, label, icon);
        Button button = new Button(buttonContent);
        button.addClassName("headerIconButton");

        verticalLayout.add(button);
        horizontalLayout.add(verticalLayout);
        horizontalLayout.setWidth("100%");
        return horizontalLayout;
    }

    private HorizontalLayout getMainMenuLayout() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addClassName("mainMenuLayout");
        horizontalLayout.setSizeFull();
        horizontalLayout.setSpacing(false);
        return horizontalLayout;
    }

    private VerticalLayout getMenuLayout() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addClassName("menuLayout");
        verticalLayout.setSizeFull();
        verticalLayout.setAlignItems(Alignment.CENTER);
        verticalLayout.setSpacing(false);
        verticalLayout.setPadding(false);

        Button button = new Button("Zamówienia wewnętrzne");
        button.addClickListener( event -> {
            VerticalLayout vertical = getMenuLayout();
            mainMenuLayout.replace(mainMenuLayout.getComponentAt(1), vertical);
            mainLayout = vertical;
        } );

        Button button2 = new Button("Zamówienia wychodzące");
        button2.setWidthFull();
        button2.addClickListener( event -> {
            VerticalLayout vertical = getOutsideOrdersLayout();
            mainMenuLayout.replace(mainMenuLayout.getComponentAt(1), vertical);
            mainLayout = vertical;
        } );

        Button button3 = new Button("Spis");
        button3.setWidthFull();
        verticalLayout.setWidth("10");

        verticalLayout.add(button, button2, button3);
        return verticalLayout;
    }

    private VerticalLayout getOutsideOrdersLayout() {
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.add(new Button("Test 1"));
        return verticalLayout;
    }

    private VerticalLayout getMainLayout() throws URISyntaxException, JsonProcessingException, UnknownHostException {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addClassName("mainLayout");
        grid = getInsideOrdersGrid();
        grid.setSizeFull();
        verticalLayout.setSizeFull();
        verticalLayout.add(grid);
        return verticalLayout;
    }

    private Grid<Order> getInsideOrdersGrid() throws URISyntaxException, JsonProcessingException, UnknownHostException {
        List<Order> orders = getOrders();
        dataProvider = new ListDataProvider<>(orders);

        grid = new Grid<>(Order.class);
        grid.setDataProvider(dataProvider);
        grid.setColumnReorderingAllowed(true);

        grid.setColumns("id", "name", "comments", "dateOfCreation");

        grid.getColumnByKey("id")
                .setHeader("ID")
                .setFlexGrow(0)
                .setWidth("72px");

        grid.getColumnByKey("name")
                .setHeader("Nazwa")
                .setFlexGrow(0)
                .setWidth("300px");

        grid.getColumnByKey("comments")
                .setHeader("Komentarz");

        grid.removeColumnByKey("dateOfCreation");

        grid.addColumn(order -> order.getDateOfCreation().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)))
                .setKey("dateOfCreation")
                .setHeader("Data Utworzenia")
                .setFlexGrow(0)
                .setWidth("170px")
                .setSortable(true);

        grid.addComponentColumn(this::createButtonEdit)
                .setKey("editButtonColumn")
                .setFlexGrow(0)
                .setWidth("110px");

        grid.addComponentColumn(this::createDeleteButton)
                .setKey("deleteButtonColumn")
                .setFlexGrow(0)
                .setWidth("90px");

        HeaderRow filterRow = grid.appendHeaderRow();

        //Filter for ID
        TextField idField = new TextField();
        idField.addValueChangeListener(event -> dataProvider
                .addFilter(order -> String.valueOf(order.getId()).contains(idField.getValue())));
        idField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(grid.getColumnByKey("id")).setComponent(idField);
        idField.setSizeFull();
        idField.setPlaceholder("ID");

        //Filter for Name
        TextField nameField = new TextField();
        nameField.addValueChangeListener(event -> dataProvider
                .addFilter(order -> StringUtils.containsIgnoreCase(order.getName(), nameField.getValue())));
        nameField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(grid.getColumnByKey("name")).setComponent(nameField);
        nameField.setSizeFull();
        nameField.setPlaceholder("Name");

        //Filter for comments
        TextField commentsField = new TextField();
        commentsField.addValueChangeListener(event -> dataProvider
                .addFilter(order -> StringUtils.containsIgnoreCase(order.getComments(), commentsField.getValue())));
        commentsField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(grid.getColumnByKey("comments")).setComponent(commentsField);
        commentsField.setSizeFull();
        commentsField.setPlaceholder("Comment");

        //Filter for creationDate
        DatePicker creationdateField = new DatePicker();
        creationdateField.addValueChangeListener(event -> dataProvider
                .addFilter(order -> compareDates(convertLocalDateTimeToLocalDate(order.getDateOfCreation()), creationdateField)));

        filterRow.getCell(grid.getColumnByKey("dateOfCreation")).setComponent(creationdateField);
        creationdateField.setSizeFull();
        creationdateField.setPlaceholder("Date");

        //Button for new order

        Button newOrderButton = new Button("Nowe");
        newOrderButton.addClassName("getButtonsAdd");
        newOrderButton.setSizeFull();
        grid.getColumnByKey("deleteButtonColumn")
                .setHeader(newOrderButton)
                .setFlexGrow(0)
                .setWidth("120px");

//        grid.prependHeaderRow().join(
//                grid.getColumnByKey("deleteButtonColumn"),
//                grid.getColumnByKey("addButtonColumn"))
//                .setComponent(newOrderButton);

        return grid;
    }

    private void createOrder() {
        //TODO: ADD NEW WINDOW FOR CREATING ORDERS
    }

    private Button createButtonEdit(Order order) {
        Button button = new Button("Edytuj");
        button.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        button.setWidthFull();
        button.addClickListener(event -> editOrder(order));
        return button;
    }

    private void editOrder(Order order) {
        //TODO: ADD NEW WINDOW FOR EDITING ORDERS
    }

    private Button createDeleteButton(Order order) {
        Button button = new Button("Usuń");
        button.addThemeVariants(ButtonVariant.LUMO_ERROR);
        button.setWidthFull();
        button.addClickListener(e -> {
            try {
                deleteOrder(order.getId());
                refreshInsideOrderLayout();
            } catch (UnknownHostException ex) {
                ex.printStackTrace();
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
        return button;
    }

    private void refreshInsideOrderLayout() throws JsonProcessingException, UnknownHostException, URISyntaxException {
        mainMenuLayout.remove(mainLayout);
        mainLayout = getMainLayout();
        mainLayout.setSizeFull();
        mainMenuLayout.add(mainLayout);
    }

    private void deleteOrder(long id) throws UnknownHostException {
//        RestTemplate restTemplate = new RestTemplate();
//        final String baseURL = "http://" + Inet4Address.getLocalHost().getHostAddress() + ":8080/zedapp/order/delete?_id=" + _id;
//        URI url = UriComponentsBuilder.fromHttpUrl(baseURL)
//                .build().encode().toUri();
//
//        restTemplate.delete(url);
    }

    private boolean compareDates(LocalDate date1, DatePicker datePicker) {
        LocalDate localDate = datePicker.getValue();

        if (date1 == null || localDate == null || datePicker.isEmpty()) {
            return true;
        }

        if (StringUtils.containsIgnoreCase(date1.toString(), localDate.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public List<Order> getOrders() throws URISyntaxException, JsonProcessingException, UnknownHostException {
//        RestTemplate restTemplate = new RestTemplate();
//        final String baseURL = "http://" + Inet4Address.getLocalHost().getHostAddress() + ":8080/zedapp/order/getAll";
//        URI url = UriComponentsBuilder.fromHttpUrl(baseURL)
//                .build().encode().toUri();
//
//        Order[] response = restTemplate.getForObject(url, Order[].class);
//
//        return Arrays.asList(ofNullable(response).orElse(new Order[0]));

        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1L, "Zamowienie 1", "zam nr 1", LocalDateTime.now().minusDays(231)));
        orders.add(new Order(2L, "Zamowienie 2", "zam nr 2", LocalDateTime.now().minusDays(2)));
        orders.add(new Order(3L, "Zamowienie 3", "zam nr 3", LocalDateTime.now().minusDays(12)));
        return orders;
    }

    private LocalDate convertLocalDateTimeToLocalDate(LocalDateTime localDateTime) {
        return LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth());
    }
}
