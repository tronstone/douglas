package com.unitedcode.douglas.model.components;

import com.codeborne.selenide.Selenide;
import com.unitedcode.douglas.model.pages.HomePage;
import lombok.AllArgsConstructor;

import java.util.Objects;

import static com.codeborne.selenide.ClickOptions.usingDefaultMethod;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@AllArgsConstructor
public class CookiesConsentPageComponent {
    private final HomePage homePage;

    public HomePage confirmCookiesConsent() {
        if (Objects.isNull(Selenide.localStorage().getItem("uc_user_interaction"))) {
            $(".uc-list-button__accept-all").shouldBe(visible).click(usingDefaultMethod()).shouldNotBe(visible);
        }

        return homePage;
    }
}
