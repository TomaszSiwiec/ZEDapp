package com.zedapp.frontend.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route("")
@PWA(name = "Project Base for Vaadin", shortName = "Project Base")
public class LoginView extends VerticalLayout {

    public LoginView() {
        VerticalLayout layout = new VerticalLayout();
        LoginForm component = new LoginForm();

        layout.setAlignSelf(Alignment.CENTER);
        layout.setAlignItems(Alignment.CENTER);

        component.addLoginListener(e -> {
            boolean isAuthenticated = authenticate(e);
            if (isAuthenticated) {
                System.out.println("\n\n\n\n\nZalogowano!!");
                UI.getCurrent().navigate("main");
            } else {
                component.setError(true);
            }
        });

        component.addForgotPasswordListener(e ->{
            UI.getCurrent().navigate("forgot_password");
        });
        layout.add(component);
        add(layout);
    }

    private boolean authenticate(AbstractLogin.LoginEvent e) {
        if (e.getUsername().equals("ts@onet.pl") && e.getPassword().equals("test"))
            return true;
        else
            return false;
    }
}
