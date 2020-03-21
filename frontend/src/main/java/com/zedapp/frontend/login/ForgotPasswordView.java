package com.zedapp.frontend.login;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("forgot_password")
public class ForgotPasswordView extends VerticalLayout {
    public ForgotPasswordView() {
        setAlignItems(Alignment.CENTER);
        setAlignSelf(Alignment.CENTER);

        Label label = new Label("Website under construction!");
        add(label);
    }
}
