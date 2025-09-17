package com.huy.automationexercise.utils;

public class UserData {
    private String firstName;
    private String lastName;
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
    public UserData(String firstName, String lastName, String email, String password,
                    String company, String address1, String address2, String country,
                    String state, String city, String zipCode, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
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
    public String getMobileNumber() { return mobileNumber; }
}
