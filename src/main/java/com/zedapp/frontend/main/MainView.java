package com.zedapp.frontend.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.zedapp.domain.dto.OrderDto;
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
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Route("main")
@CssImport("./styles/MainViewStyles.css")
@CssImport(value = "./styles/WrapTextButton.css", themeFor ="vaadin-button")
//@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    private Grid<OrderDto> grid;
    private ListDataProvider<OrderDto> dataProvider;

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

    private VerticalLayout getButtonsAddAndEdit() {
        //TODO: ADD BOTTOM DIV
//        Icon iconAdd = new Icon(VaadinIcon.PLUS_CIRCLE);
//        buttonAdd = new Button(iconAdd);
//        buttonAdd.addClassName("getButtonsAdd");
        return new VerticalLayout();
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

//        Image image = new Image("http://zed-lift.pl/wp-content/themes/zed/demos/barber/images/zed-logo3.png", "DummyImage");
        Image image = new Image("zedapp-logo.png", "DummyImage");
        image.setHeight("90px");
        image.addClassName("headerLogo");
        horizontalLayout.add(image);

        VerticalLayout verticalLayout = new VerticalLayout();
        horizontalLayout.addClassName("insideHeaderLayout");

        Label label = new Label("Tomasz Siwiec");
        label.addClassName("headerNameLabel");
        Icon icon = new Icon(VaadinIcon.COG_O);
        HorizontalLayout buttonContent = new HorizontalLayout();
        buttonContent.addClassName("buttonContent");
        buttonContent.add(label, icon);
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
            VerticalLayout vertical = null;
            try {
                vertical = getMainLayout();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            mainMenuLayout.replace(mainLayout, vertical);
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

    private Grid<OrderDto> getInsideOrdersGrid() throws URISyntaxException, JsonProcessingException, UnknownHostException {
        List<OrderDto> orders = getOrders();
        dataProvider = new ListDataProvider<>(orders);

        grid = new Grid<>(OrderDto.class);
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

        grid.addColumn(orderDto -> orderDto.getDateOfCreation().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)))
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
                .addFilter(orderDto -> String.valueOf(orderDto.getId()).contains(idField.getValue())));
        idField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(grid.getColumnByKey("id")).setComponent(idField);
        idField.setSizeFull();
        idField.setPlaceholder("ID");

        //Filter for Name
        TextField nameField = new TextField();
        nameField.addValueChangeListener(event -> dataProvider
                .addFilter(orderDto -> StringUtils.containsIgnoreCase(orderDto.getName(), nameField.getValue())));
        nameField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(grid.getColumnByKey("name")).setComponent(nameField);
        nameField.setSizeFull();
        nameField.setPlaceholder("Name");

        //Filter for comments
        TextField commentsField = new TextField();
        commentsField.addValueChangeListener(event -> dataProvider
                .addFilter(orderDto -> StringUtils.containsIgnoreCase(orderDto.getComments(), commentsField.getValue())));
        commentsField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(grid.getColumnByKey("comments")).setComponent(commentsField);
        commentsField.setSizeFull();
        commentsField.setPlaceholder("Comment");

        //Filter for creationDate
        DatePicker creationdateField = new DatePicker();
        creationdateField.addValueChangeListener(event -> dataProvider
                .addFilter(orderDto -> compareDates(convertLocalDateTimeToLocalDate(orderDto.getDateOfCreation()), creationdateField)));

        filterRow.getCell(grid.getColumnByKey("dateOfCreation")).setComponent(creationdateField);
        creationdateField.setSizeFull();
        creationdateField.setPlaceholder("Date");

        //Button for new order

        Button newOrderButton = new Button("Nowe", event -> allLayout.add(getLayoutWithNewOrderDialog()));
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

    private VerticalLayout getLayoutWithNewOrderDialog() {
        VerticalLayout verticalLayout = new VerticalLayout();
        Dialog dialog = new Dialog();

        VerticalLayout column1 = new VerticalLayout();
        TextField name = new TextField("Nazwa");
        TextField comments = new TextField("Komentarz");
        column1.add(name, comments);

        VerticalLayout column2 = new VerticalLayout();
        DatePicker enterDate = new DatePicker("Data wprowadzenia");
        DatePicker deadlineDate = new DatePicker("Termin");

        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAutoUpload(false);

        upload.addSucceededListener(event -> {
            System.out.println("DODANO PLIKI!");
        });

        column2.add(enterDate, deadlineDate, upload);

        HorizontalLayout columns = new HorizontalLayout(column1, column2);
        dialog.add(columns);
        verticalLayout.add(dialog);
        return verticalLayout;
    }

    private void createOrder() {
        //TODO: ADD NEW WINDOW FOR CREATING ORDERS
    }

    private Button createButtonEdit(OrderDto orderDto) {
        Button button = new Button("Edytuj");
        button.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        button.setWidthFull();
        button.addClickListener(event -> editOrder(orderDto));
        return button;
    }

    private void editOrder(OrderDto orderDto) {
        //TODO: ADD NEW WINDOW FOR EDITING ORDERS
    }

    private Button createDeleteButton(OrderDto orderDto) {
        Button button = new Button("Usuń");
        button.addThemeVariants(ButtonVariant.LUMO_ERROR);
        button.setWidthFull();
        button.addClickListener(e -> {
            try {
                deleteOrder(orderDto.getId());
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
        RestTemplate restTemplate = new RestTemplate();
        final String baseURL = "http://" + Inet4Address.getLocalHost().getHostAddress() + ":8080/zedapp/order/delete?id=" + id;
        URI url = UriComponentsBuilder.fromHttpUrl(baseURL)
                .build().encode().toUri();

        restTemplate.delete(url);
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

    public List<OrderDto> getOrders() throws URISyntaxException, JsonProcessingException, UnknownHostException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseURL = "http://" + Inet4Address.getLocalHost().getHostAddress() + ":8080/zedapp/order/getAll";
        URI url = UriComponentsBuilder.fromHttpUrl(baseURL)
                .build().encode().toUri();

        OrderDto[] response = restTemplate.getForObject(url, OrderDto[].class);

        return Arrays.asList(ofNullable(response).orElse(new OrderDto[0]));
    }

    private LocalDate convertLocalDateTimeToLocalDate(LocalDateTime localDateTime) {
        return LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth());
    }
}
