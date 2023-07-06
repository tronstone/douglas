package com.unitedcode.douglas.model.pages;

import com.unitedcode.douglas.core.structure.BasePage;
import com.unitedcode.douglas.model.components.CookiesConsentPageComponent;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BasePage<HomePage> {
    @Getter
    private final CookiesConsentPageComponent cookiesConsentPageComponent = new CookiesConsentPageComponent(this);

    private final ParfumPage parfumPage = new ParfumPage();

    public HomePage() {
        super(System.getProperty("environment.url"), byCssSelector(".navigation-main__container"));
    }

    public ParfumPage parfum() {
        $x("//li[@data-index='2']").click();
        return parfumPage.waitUntilPageWillLoad();
    }
}
