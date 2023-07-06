package com.unitedcode.douglas.model.pages;

import com.unitedcode.douglas.core.structure.BasePage;
import com.unitedcode.douglas.model.domain.Highlights;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import static com.codeborne.selenide.ClickOptions.usingDefaultMethod;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ParfumPage extends BasePage<ParfumPage> {
    public ParfumPage() {
        super(byCssSelector(".product-overview-page__layout"));
    }

    public ParfumPage highlights(Highlights highlight) {
        chooseFilterOption("Highlights", highlight.name());
        return this;
    }

    public ParfumPage marke(String marke) {
        if (StringUtils.isEmpty(marke)) {
            return this;
        }

        chooseFilterOption("Marke", marke);
        return this;
    }

    public ParfumPage produktart(String produktart) {
        chooseFilterOption("Produktart", produktart);
        return this;
    }

    public ParfumPage geschenk(String geschenk) {
        if (StringUtils.isEmpty(geschenk)) {
            return this;
        }

        chooseFilterOption("Geschenk für", geschenk);
        return this;
    }

    public ParfumPage furwen(String furwen) {
        chooseFilterOption("Für Wen", furwen);
        return this;
    }

    private void chooseFilterOption(String filterName, @NonNull String option) {
        /*
         * Not a universal way, because when you change the language the tests will fall,
         * need to use a language map of names because the location of the filters change.
        */
        $$(".facet__title").findBy(text(filterName)).click(usingDefaultMethod());

        $(".rc-scrollbars-view").shouldBe(visible)
                .$$(".facet-option__checkbox--rating-stars")
                .findBy(text(option))
                .parent()
                .$(".facet-option__checkbox")
                .click(usingDefaultMethod())
                .shouldHave(cssClass("facet-option__checkbox--selected"));

        $(".facet__close-button").click(usingDefaultMethod()).shouldNotBe(visible);
    }
}
