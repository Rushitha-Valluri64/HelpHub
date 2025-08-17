package com.helphub.dto;

public class AcceptorRequestDto {
    private String name;
    private String phone;
    private String location;
    private String categories;

    public AcceptorRequestDto() {}

    public AcceptorRequestDto(String name, String phone, String location, String categories) {
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
