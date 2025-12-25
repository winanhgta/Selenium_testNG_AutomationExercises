package com.huy.automationexercise.utils;

import net.datafaker.providers.base.Gender;

import java.util.Date;

public class UserData {
    private String firstName;
    private String lastName;
    private String day;
    private String month;
    private String year;
    private String email;
    private String password;
    private String company;
    private String address1;
    private String address2;
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String mobileNumber;

    // --- Constructor ---
    public UserData(String firstName, String lastName, String day, String month, String year, String email, String password,
                    String company, String address1, String address2, String country,
                    String state, String city, String zipCode, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.day = day;
        this.month = month;
        this.year = year;
        this.email = email;
        this.password = password;
        this.company = company;
        this.address1 = address1;
        this.address2 = address2;
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.mobileNumber = mobileNumber;
    }

    public UserData(String email, String password) {
        this.email = email;
        this.password = password;
    }


    // --- Getters ---
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getCompany() { return company; }
    public String getAddress1() { return address1; }
    public String getAddress2() { return address2; }
    public String getCountry() { return country; }
    public String getState() { return state; }
    public String getCity() { return city; }
    public String getZipCode() { return zipCode; }
    public String getDay() {return day;}
    public String getMonth() {return month;}
    public String getYear() {return year;}
    public String getMobileNumber() { return mobileNumber; }
}
