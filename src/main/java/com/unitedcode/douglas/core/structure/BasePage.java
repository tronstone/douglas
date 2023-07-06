package com.unitedcode.douglas.core.structure;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.AccessLevel;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.Objects;
import java.util.function.BiConsumer;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage<PAGE extends BasePage<PAGE>> {
    @Getter(AccessLevel.PROTECTED)
    private String pageURL;
    @Getter(AccessLevel.PROTECTED)
    private SelenideElement waitLoadPageElement;

    private BiConsumer<SelenideElement, Duration> defaultWaitLoadPageLogic = (waitLoadPageElement, duration) -> {
        waitLoadPageElement.shouldBe(visible, duration);
    };

    protected BasePage(@Nullable By waitLoadPageLocator) {
        if (Objects.nonNull(waitLoadPageLocator)) {
            waitLoadPageElement = $(waitLoadPageLocator);
        }
    }

    protected BasePage(String pageURL, By waitLoadPageLocator) {
        this(waitLoadPageLocator);
        this.pageURL = pageURL;
    }

    protected final void changeWaitOpenPageLogic(BiConsumer<SelenideElement, Duration> waitLoadPageLogic) {
        defaultWaitLoadPageLogic = waitLoadPageLogic;
    }

    public PAGE openPage() {
        return open(() -> Selenide.open(pageURL));
    }

    @SuppressWarnings("unchecked")
    public PAGE waitUntilPageWillLoad(Duration duration) {
        if (Objects.isNull(waitLoadPageElement)) {
            return (PAGE) this;
        }

        defaultWaitLoadPageLogic.accept(waitLoadPageElement, duration);
        return (PAGE) this;
    }

    public PAGE waitUntilPageWillLoad() {
        return waitUntilPageWillLoad(Duration.ofSeconds(30));
    }

    @SuppressWarnings("unchecked")
    private PAGE open(Runnable openable) {
        if (StringUtils.isNotEmpty(pageURL)) {
            openable.run();
        } else {
            throw new UnsupportedOperationException("Page URL not establish");
        }

        return (PAGE) this;
    }
}
