package com.helphub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "acceptor_requests")
public class AcceptorRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email; // ✅ Add this field
    private String location;
    private String categories;
    private boolean fulfilled = false;

    // Constructors
    public AcceptorRequest() {}

    public AcceptorRequest(String name, String phone, String email, String location, String categories) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.location = location;
        this.categories = categories;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getCategories() { return categories; }
    public void setCategories(String categories) { this.categories = categories; }

    public boolean isFulfilled() { return fulfilled; }
    public void setFulfilled(boolean fulfilled) { this.fulfilled = fulfilled; }
}
