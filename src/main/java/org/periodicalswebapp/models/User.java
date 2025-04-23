package org.periodicalswebapp.models;

public class User {
    private int id;
    private String name;
    private String lastname;
    private String address;
    private String index;
    private String email;
    private String password;

    public User(){
    }

    public User(String name, String lastname, String address, String index, String email, String password) {
        //this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.index = index;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
