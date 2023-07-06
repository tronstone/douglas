package com.unitedcode.douglas;

import com.codeborne.selenide.Configuration;
import com.unitedcode.douglas.model.domain.Highlights;
import com.unitedcode.douglas.model.pages.HomePage;
import com.unitedcode.douglas.model.pages.ParfumPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DouglasTests {
    private ParfumPage parfumPage;

    static {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
    }

    @BeforeMethod
    public void initTest() {
        parfumPage = new HomePage().openPage().getCookiesConsentPageComponent().confirmCookiesConsent().parfum();
    }

    @DataProvider
    private Object[][] dataSets() {
        return new Object[][] {
                {Highlights.SALE, "Acqua Colonia", "Eau de Cologne", null, "Unisex"},
                {Highlights.NEU, null, "Eau de Toilette", null, "Männlich"},
                {Highlights.LIMITIERT, "HERMÈS", "Duftset", "Geburtstag", "Männlich"}
        };
    }

    @Test(dataProvider = "dataSets")
    public void ddtest(Highlights highlight, String marke, String produktart, String geschenk, String furwen) {
        parfumPage.highlights(highlight)
                .marke(marke)
                .produktart(produktart)
                .geschenk(geschenk)
                .furwen(furwen)
                .waitUntilPageWillLoad();

        // Asserting products
    }
}
