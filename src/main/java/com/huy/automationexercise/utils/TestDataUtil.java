package com.huy.automationexercise.utils;

import net.datafaker.Faker;

public class TestDataUtil {
    private static final Faker faker = new Faker();

    public static UserData generateUser() {
        return new UserData(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password(6, 12),
                faker.company().name(),
                faker.address().streetAddress(),
                faker.address().secondaryAddress(),
                "Canada", // country có sẵn trong dropdown
                faker.address().state(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.phoneNumber().cellPhone()
        );
    }
}
