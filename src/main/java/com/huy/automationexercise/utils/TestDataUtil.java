package com.huy.automationexercise.utils;

import net.datafaker.Faker;

import java.util.Arrays;
import java.util.List;

public class TestDataUtil {
    private static final Faker faker = new Faker();

    public static UserData generateUser() {
        // 1. Tạo ngày sinh ngẫu nhiên (Kiểu LocalDate)
        java.time.LocalDate birthDate = faker.timeAndDate().birthday(18, 65);
        List<String> validCountries = Arrays.asList("India", "United States", "Canada", "Australia", "Israel", "New Zealand", "Singapore");
        String randomCountry = validCountries.get(faker.number().numberBetween(0, validCountries.size()));
        return new UserData(
                faker.name().firstName(),
                faker.name().lastName(),
                String.valueOf(birthDate.getDayOfMonth()), // Ngày: "25"
                // Lấy tên tháng bằng tiếng Anh để khớp với Dropdown: "December"
                birthDate.getMonth().getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH),
                String.valueOf(birthDate.getYear()),       // Năm: "1995"
                faker.internet().emailAddress(),
                faker.credentials().password(6, 12),
                faker.company().name(),
                faker.address().streetAddress(),
                faker.address().secondaryAddress(),
                randomCountry,
                faker.address().state(),
                faker.address().city(),
                faker.address().zipCode(),
                faker.phoneNumber().cellPhone()
        );
    }

    public static UserData generateUnexistUser() {
        return new UserData(
                faker.internet().emailAddress(),
                faker.credentials().password(6, 12)
        );
    }

}
